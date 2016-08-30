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
package com.yoshio3.restclient.jaxb.entities.onedrive;


/**
 *
 * @author Yoshio Terada
 */
public class OneDriveUser {
    private OneDriveUserValue user;
    private Object application;

    /**
     * @return the user
     */
    public OneDriveUserValue getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(OneDriveUserValue user) {
        this.user = user;
    }
    /**
     * @return the application
     */
    public Object getApplication() {
        return application;
    }

    /**
     * @param application the application to set
     */
    public void setApplication(Object application) {
        this.application = application;
    }

    @Override
    public String toString() {
        return "OneDriveUser{" + "user=" + user + ", application=" + application + '}';
    }
    
}
