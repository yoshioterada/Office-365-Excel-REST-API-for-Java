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
package com.yoshio3.restclient.services.adauth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.yoshio3.cdiutil.LoggerQualifier;
import com.yoshio3.restclient.exceptions.RestClientIllegalStateException;
import com.yoshio3.restclient.jaxb.entities.auth.AccessToken;
import com.yoshio3.restclient.jaxb.entities.error.DetailErrorCode;
import com.yoshio3.restclient.jaxb.entities.error.RestClientErrorHandler;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.jackson.JacksonFeature;

/**
 *
 * @author Yoshio Terada
 */
public class OAuth2Service {

    private final String auth_server;
    private final String tenantId;
    private final String clientId;
    private final String secretKey;
    private final String graphServer;
    private final String redirectURL;

    private final static String GRANT_TYPE = "grant_type";
    private final static String CLIENT_ID = "client_id";
    private final static String CODE = "code";
    private final static String REDIRECT_URI = "redirect_uri";
    private final static String CLIENT_SECRET = "client_secret";
    private final static String AUTH_CODE = "authorization_code";
    private final static String REFRESH_TOKEN = "refresh_token";
    private final static String HOST_HEADER = "Host";

    @Inject
    @LoggerQualifier
    transient Logger logger;

    public OAuth2Service(String auth_server, String graphServer, String tenantId, String clientId, String secretKey, String redirectURL) {
        this.auth_server = auth_server;
        this.graphServer = graphServer;
        this.tenantId = tenantId;
        this.clientId = clientId;
        this.secretKey = secretKey;
        this.redirectURL = redirectURL;
    }

    /* 認証されていな最初のリクエストの場合の Azure AD リダイレクト先 URL の取得*/
    public String getRedirectOpenIDServerURL(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws UnsupportedEncodingException, IOException {
        String currentUri = getCurrentUri(httpRequest);
        StringBuilder redirectUrl = new StringBuilder();

        redirectUrl.append(auth_server)
                .append(tenantId)
                .append("/oauth2/authorize?response_type=code%20id_token&scope=openid&response_mode=form_post&redirect_uri=")
                .append(URLEncoder.encode(redirectURL, "UTF-8"))
                .append("&client_id=")
                .append(clientId)
                .append("&resource=")
                .append(URLEncoder.encode(graphServer, "UTF-8"))
                .append("&nonce=")
                .append(UUID.randomUUID())
                .append("&site_id=")
                .append("12345");
        return redirectUrl.toString();
    }

    public Optional<AccessToken> getAccessToken(HttpServletRequest httpRequest) throws RestClientIllegalStateException {
        return getAccessToken(httpRequest, "");
    }

    /* Expire 後、リフレッシュ・トークンから新しいアクセス・トークンを取得 */
    public Optional<AccessToken> getRefreshedAccessToken(HttpServletRequest httpRequest, String refreshToken) throws RestClientIllegalStateException {
        return getAccessToken(httpRequest, refreshToken);
    }

    /* アクセス・トークンの取得 */
    private Optional<AccessToken> getAccessToken(HttpServletRequest httpRequest, String refreshToken) throws RestClientIllegalStateException {
        String currentUri = getCurrentUri(httpRequest);
        String code = httpRequest.getParameter(CODE);
        String state = httpRequest.getParameter("session_state");
        if (currentUri == null || code == null || state == null || currentUri.isEmpty() || code.isEmpty() || state.isEmpty()) {
            return Optional.empty();
        }

        Client jaxrsClient = ClientBuilder.newClient()
                .register((new JacksonJaxbJsonProvider(new ObjectMapper(), JacksonJaxbJsonProvider.DEFAULT_ANNOTATIONS)))
                .register(JacksonFeature.class);
        System.setProperty("sun.net.http.allowRestrictedHeaders", "true");

        Form form = new Form();

        form.param(CLIENT_ID, clientId);
        form.param(CODE, code);
        form.param(REDIRECT_URI, currentUri);
        form.param(CLIENT_SECRET, secretKey);

        if (refreshToken.equals("")) {
            form.param(GRANT_TYPE, AUTH_CODE);
        } else {
            form.param(GRANT_TYPE, REFRESH_TOKEN);
            form.param(REFRESH_TOKEN, refreshToken);
        }

        Response response = jaxrsClient.target("https://login.microsoftonline.com/common/oauth2/token")
                .request()
                .header(HOST_HEADER, "login.microsoftonline.com")
                .post(Entity.form(form));
        if (checkRequestSuccess(response)) {
            AccessToken data = response.readEntity(AccessToken.class);
            return Optional.of(data);
        } else {
            handleIllegalState(response);
            return Optional.empty();
        }
    }

    private boolean checkRequestSuccess(Response response) {
        Response.StatusType statusInfo = response.getStatusInfo();
        Response.Status.Family family = statusInfo.getFamily();
        return family != null && family == Response.Status.Family.SUCCESSFUL;
    }

    /*
    エラー発生時に出力するメッセージ
     */
    protected void handleIllegalState(Response response) throws RestClientIllegalStateException {
        RestClientErrorHandler error = response.readEntity(RestClientErrorHandler.class);
        DetailErrorCode detailError = error.getError();
        logger.log(Level.SEVERE, "{0}:{1}", new Object[]{detailError.getCode(), detailError.getMessage()});
        throw new RestClientIllegalStateException(detailError.getMessage());
    }

    /* リクエストの URI を取得 */
    private String getCurrentUri(HttpServletRequest request) {
        String scheme = request.getScheme();
        int serverPort = request.getServerPort();
        String portNumberString = "";
        if (!((scheme.equals("http") && serverPort == 80) || (scheme.equals("https") && serverPort == 443))) {
            portNumberString = ":" + String.valueOf(serverPort);
        }
        String uri = scheme + "://"
                + request.getServerName()
                + portNumberString
                + request.getRequestURI();
        return uri;
    }
}
