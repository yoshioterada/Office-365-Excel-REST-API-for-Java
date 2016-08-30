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
public class OneDriveParentReference {
    private String driveId;
    private String id;
    private String path;    

    /**
     * @return the driveId
     */
    public String getDriveId() {
        return driveId;
    }

    /**
     * @param driveId the driveId to set
     */
    public void setDriveId(String driveId) {
        this.driveId = driveId;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "OneDriveParentReference{" + "driveId=" + driveId + ", id=" + id + ", path=" + path + '}';
    }
    
}
