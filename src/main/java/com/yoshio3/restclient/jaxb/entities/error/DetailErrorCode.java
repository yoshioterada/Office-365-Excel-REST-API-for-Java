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
package com.yoshio3.restclient.jaxb.entities.error;

/**
 *
 * @author Yoshio Terada
 */
public class DetailErrorCode {

    private String code;
    private String message;
    private InnerErrorCode innerError;

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the innerError
     */
    public InnerErrorCode getInnerError() {
        return innerError;
    }

    /**
     * @param innerError the innerError to set
     */
    public void setInnerError(InnerErrorCode innerError) {
        this.innerError = innerError;
    }

    @Override
    public String toString() {
        return "DetailOfError{" + "code=" + code + ", message=" + message + ", innerError=" + innerError + '}';
    }
}
