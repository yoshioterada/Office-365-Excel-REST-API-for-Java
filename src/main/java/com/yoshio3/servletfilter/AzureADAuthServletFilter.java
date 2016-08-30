/*
* Copyright 2016 Yoshio Terada
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
 */
package com.yoshio3.servletfilter;

import com.yoshio3.restclient.jaxb.entities.auth.AccessToken;
import com.yoshio3.restclient.services.adauth.OAuth2Service;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Yoshio Terada
 */
public class AzureADAuthServletFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(AzureADAuthServletFilter.class.getName());

    public final static String ERROR = "error";
    public final static String ERROR_DESCRIPTION = "error_description";
    public final static String ERROR_URI = "error_uri";
    public final static String ID_TOKEN = "id_token";
    public final static String CODE = "code";

    public static final String ACCESS_TOKEN_SESSION_NAME = "access_token";

    /* web.xml で記載した設定情報の取得 */
    private static String authority;
    private static String graphServer;
    private static String tenant;
    private static String client_id;
    private static String secret_key;
    private static String redirectURL;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        if (authority == null || graphServer == null || tenant == null || client_id == null || secret_key == null) {
            Enumeration<String> initParameterNames = filterConfig.getInitParameterNames();
            Collections.list(initParameterNames)
                    .forEach((String param) -> {
                        switch (param) {
                            case "authority":
                                AzureADAuthServletFilter.authority = filterConfig.getInitParameter(param);
                                break;
                            case "graphServer":
                                AzureADAuthServletFilter.graphServer = filterConfig.getInitParameter(param);
                                break;
                            case "tenant":
                                AzureADAuthServletFilter.tenant = filterConfig.getInitParameter(param);
                                break;
                            case "client_id":
                                AzureADAuthServletFilter.client_id = filterConfig.getInitParameter(param);
                                break;
                            case "secret_key":
                                AzureADAuthServletFilter.secret_key = filterConfig.getInitParameter(param);
                                break;
                            case "redirectURL":
                                AzureADAuthServletFilter.redirectURL = filterConfig.getInitParameter(param);
                            default:
                                break;
                        }
                        LOGGER.log(Level.FINER, "PARAM_NAME:{0} PARAM_VALUE:{1}", new Object[]{param, filterConfig.getInitParameter(param)});
                    });
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        OAuth2Service oauth2 = new OAuth2Service(authority, graphServer, tenant, client_id, secret_key, redirectURL);
        try {
            Optional<AccessToken> accessTokenFromSession = getAccessTokenFromSession(httpRequest);
            //セッション情報に認証結果が含まれない場合
            if (!accessTokenFromSession.isPresent()) {
                if (!isRedirectedRequestFromAuthServer(httpRequest)) {
                    // 最初のリクエストの場合は Azure AD に Redirect
                    String redURL = oauth2.getRedirectOpenIDServerURL(httpRequest, httpResponse);
                    httpResponse.setStatus(302);
                    httpResponse.sendRedirect(redURL);
                } else {
                    //リダイレクトされてきたリクエストの場合
                    Optional<AccessToken> accessToken = oauth2.getAccessToken(httpRequest);
                    if (accessToken.isPresent()) {
                        AccessToken responseToken = accessToken.get(); 
                        //TODO 1 : リダイレクト時に送信した情報かどうかを検証する必要あり。
                        //TODO 2 : セッション ID を切り替える必要あり
                        setAccessTokenToSession(httpRequest, responseToken);
                        chain.doFilter(request, response);
                    }
                }
            } else {
                //セッション情報に認証情報が含まれる場合(有効期限内か確認)
                AccessToken aToken = accessTokenFromSession.get();
                //1970/1/1 からのエポック・タイム（秒）で Instant 生成
                String expires_on = aToken.getExpires_on();
                Instant expire = Instant.ofEpochSecond(Long.valueOf(expires_on));
                //呼び出し時と Expire の時間を比較
                if(Instant.now().isAfter(expire)){
                    //Expireしている場合、リフレッシュトークンを元に新しいアクセストークン取得
                    String refresh_token = aToken.getRefresh_token();
                    Optional<AccessToken> optRefreshed = oauth2.getRefreshedAccessToken(httpRequest, refresh_token);
                    if (optRefreshed.isPresent()) {
                        aToken = optRefreshed.get();
                        setAccessTokenToSession(httpRequest, aToken);
                        LOGGER.log(Level.INFO, "ACCESS TOKEN HAD EXPIRED AND GOT THE NEW REFRESHED ACCESS TOKEN.......");
                    }
                }else{
                    Duration between = Duration.between(Instant.now(), expire);
                    LOGGER.log(Level.INFO, between.getSeconds() + "秒後にアクセストークンが期限切れ");
                }
                chain.doFilter(request, response);
            }
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        } catch (Throwable ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    /* HTTP セッションに認証情報を設定 */
    private void setAccessTokenToSession(HttpServletRequest httpRequest,
            AccessToken access_token) throws Exception {
        httpRequest.getSession().setAttribute(ACCESS_TOKEN_SESSION_NAME, access_token);
    }

    /* HTTP セッションから認証結果の取得 */
    public Optional<AccessToken> getAccessTokenFromSession(HttpServletRequest request) {
        return Optional.ofNullable((AccessToken) request.getSession().getAttribute(ACCESS_TOKEN_SESSION_NAME));
    }

    /* HTTP セッションから認証済みか否かのチェック */
    public boolean isRedirectedRequestFromAuthServer(HttpServletRequest httpRequest) {
        return httpRequest.getMethod().equalsIgnoreCase("POST")
                && (httpRequest.getParameterMap().containsKey(ERROR)
                || httpRequest.getParameterMap().containsKey(
                        ID_TOKEN) || httpRequest.getParameterMap().containsKey(CODE));
    }

    /* 認証データが含まれるか否かのチェック */
    public boolean containsAuthenticationData(HttpServletRequest httpRequest) {
        Map<String, String[]> map = httpRequest.getParameterMap();

        return httpRequest.getMethod().equalsIgnoreCase("POST")
                && (httpRequest.getParameterMap().containsKey(ERROR)
                || httpRequest.getParameterMap().containsKey(
                        ID_TOKEN) || httpRequest.getParameterMap().containsKey(CODE));
    }

    @Override
    public void destroy() {
        ; // TODO : 実装すべき
    }
}
