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
package com.yoshio3.restclient.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.yoshio3.restclient.jaxb.entities.auth.AccessToken;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.jackson.JacksonFeature;
import com.yoshio3.cdiutil.LoggerQualifier;
import com.yoshio3.restclient.exceptions.RestClientIllegalStateException;
import com.yoshio3.restclient.jaxb.entities.error.DetailErrorCode;
import com.yoshio3.restclient.jaxb.entities.error.RestClientErrorHandler;
import java.io.Serializable;

/**
 *
 * @author Yoshio Terada
 */
public class RestClientService implements Serializable{

    public static final String ACCESS_TOKEN_SESSION_NAME = "access_token";

    protected String tenant;
    protected String authString;
    protected Client jaxrsClient;
    protected final static String GRAPH_SEVER_HOST = "graph.microsoft.com";
    protected final static String GRAPH_SERVER_V1_URL = "https://graph.microsoft.com/v1.0";

    //CDI で LOGGER を実装
    @Inject
    @LoggerQualifier
    transient Logger logger;

    @PostConstruct
    protected void init() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        init(request);
    }

    protected void init(HttpServletRequest request) {
        AccessToken accessToken = (AccessToken) request.getSession().getAttribute(ACCESS_TOKEN_SESSION_NAME);
        authString = "Bearer " + accessToken.getAccess_token();
        tenant = request.getServletContext().getInitParameter("tenant");

        jaxrsClient = ClientBuilder.newClient()
                .register((new JacksonJaxbJsonProvider(new ObjectMapper(), JacksonJaxbJsonProvider.DEFAULT_ANNOTATIONS)))
                .register(JacksonFeature.class);
        System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
    }
    /*
    HTTP GET リクエストの送信
     */
    protected Response execHTTPGetRequest(String graphURL) {
        Response response = jaxrsClient.target(graphURL)
                .request()
                .header("Host", GRAPH_SEVER_HOST)
                .header("Accept", "application/json")
                .header("api-version", "1.0")
                .header("Authorization", authString)
                .get();
        return response;
    }
    /*
    HTTP POST リクエストの送信
     */
    protected Response execHTTPPostRequest(Entity entity, String graphURL) {
        Response response = jaxrsClient.target(graphURL)
                .request()
                .header("Host", GRAPH_SEVER_HOST)
                .header("Accept", "application/json")
                .header("api-version", "1.0")
                .header("Authorization", authString)
                .post(entity);
        return response;
    }
    /*
    送信したリクエストが成功か否かをチェック
     */
    protected boolean checkRequestSuccess(Response response) {        
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
}
