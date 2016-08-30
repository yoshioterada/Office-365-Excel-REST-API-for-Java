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
package com.yoshio3.restclient.jaxb.entities.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Yoshio Terada
 */

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccessToken implements Serializable{
    private String token_type;
    private String scope;
    private String expires_in;
    private String ext_expires_in;
    private String expires_on;
    private String not_before;
    private String resource;
    private String access_token;
    private String refresh_token;
    private String id_token;

    
    /**
     * @return the token_type
     */
    public String getToken_type() {
        return token_type;
    }

    /**
     * @param token_type the token_type to set
     */
    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    /**
     * @return the scope
     */
    public String getScope() {
        return scope;
    }

    /**
     * @param scope the scope to set
     */
    public void setScope(String scope) {
        this.scope = scope;
    }

    /**
     * @return the expires_in
     */
    public String getExpires_in() {
        return expires_in;
    }

    /**
     * @param expires_in the expires_in to set
     */
    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    /**
     * @return the ext_expires_in
     */
    public String getExt_expires_in() {
        return ext_expires_in;
    }

    /**
     * @param ext_expires_in the ext_expires_in to set
     */
    public void setExt_expires_in(String ext_expires_in) {
        this.ext_expires_in = ext_expires_in;
    }

    /**
     * @return the expires_on
     */
    public String getExpires_on() {
        return expires_on;
    }

    /**
     * @param expires_on the expires_on to set
     */
    public void setExpires_on(String expires_on) {
        this.expires_on = expires_on;
    }

    /**
     * @return the not_before
     */
    public String getNot_before() {
        return not_before;
    }

    /**
     * @param not_before the not_before to set
     */
    public void setNot_before(String not_before) {
        this.not_before = not_before;
    }

    /**
     * @return the resource
     */
    public String getResource() {
        return resource;
    }

    /**
     * @param resource the resource to set
     */
    public void setResource(String resource) {
        this.resource = resource;
    }

    /**
     * @return the access_token
     */
    public String getAccess_token() {
        return access_token;
    }

    /**
     * @param access_token the access_token to set
     */
    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    /**
     * @return the refresh_token
     */
    public String getRefresh_token() {
        return refresh_token;
    }

    /**
     * @param refresh_token the refresh_token to set
     */
    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    /**
     * @return the id_token
     */
    public String getId_token() {
        return id_token;
    }

    /**
     * @param id_token the id_token to set
     */
    public void setId_token(String id_token) {
        this.id_token = id_token;
    }

    @Override
    public String toString() {
        return "AccessToken{" + "token_type=" + token_type + ", scope=" + scope + ", expires_in=" + expires_in + ", ext_expires_in=" + ext_expires_in + ", expires_on=" + expires_on + ", not_before=" + not_before + ", resource=" + resource + ", access_token=" + access_token + ", refresh_token=" + refresh_token + ", id_token=" + id_token + '}';
    }
    
            
}
