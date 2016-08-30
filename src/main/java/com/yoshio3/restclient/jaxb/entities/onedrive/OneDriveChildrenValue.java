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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Yoshio Terada
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class OneDriveChildrenValue {

    private String id;
    private String name;
    private OneDriveUser createdBy;
    private String createdDateTime;
    private String cTag;
    private String eTag;
    private OneDriveFolder folder;
    private OneDriveUser lastModifiedBy;
    private String lastModifiedDateTime;
    private OneDriveParentReference parentReference;
    private Integer size;
    private String webUrl;

    @JsonProperty("@microsoft.graph.downloadUrl")
    private String metadata;    
    private Object file;

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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the createdDateTime
     */
    public String getCreatedDateTime() {
        return createdDateTime;
    }

    /**
     * @param createdDateTime the createdDateTime to set
     */
    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    /**
     * @return the cTag
     */
    public String getcTag() {
        return cTag;
    }

    /**
     * @param cTag the cTag to set
     */
    public void setcTag(String cTag) {
        this.cTag = cTag;
    }

    /**
     * @return the eTag
     */
    public String geteTag() {
        return eTag;
    }

    /**
     * @param eTag the eTag to set
     */
    public void seteTag(String eTag) {
        this.eTag = eTag;
    }


    /**
     * @return the lastModifiedDateTime
     */
    public String getLastModifiedDateTime() {
        return lastModifiedDateTime;
    }

    /**
     * @param lastModifiedDateTime the lastModifiedDateTime to set
     */
    public void setLastModifiedDateTime(String lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
    }


    /**
     * @return the size
     */
    public Integer getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(Integer size) {
        this.size = size;
    }

    /**
     * @return the webUrl
     */
    public String getWebUrl() {
        return webUrl;
    }

    /**
     * @param webUrl the webUrl to set
     */
    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    /**
     * @return the metadata
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * @param metadata the metadata to set
     */
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    /**
     * @return the createdBy
     */
    public OneDriveUser getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(OneDriveUser createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return the folder
     */
    public OneDriveFolder getFolder() {
        return folder;
    }

    /**
     * @param folder the folder to set
     */
    public void setFolder(OneDriveFolder folder) {
        this.folder = folder;
    }

    /**
     * @return the lastModifiedBy
     */
    public OneDriveUser getLastModifiedBy() {
        return lastModifiedBy;
    }

    /**
     * @param lastModifiedBy the lastModifiedBy to set
     */
    public void setLastModifiedBy(OneDriveUser lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    /**
     * @return the parentReference
     */
    public OneDriveParentReference getParentReference() {
        return parentReference;
    }

    /**
     * @param parentReference the parentReference to set
     */
    public void setParentReference(OneDriveParentReference parentReference) {
        this.parentReference = parentReference;
    }

    /**
     * @param file the file to set
     */
    public void setFile(Object file) {
        this.file = file;
    }

    /**
     * @return the file
     */
    public Object getFile() {
        return file;
    }

    @Override
    public String toString() {
        return "OneDriveChildrenValue{" + "id=" + id + ", name=" + name + ", createdBy=" + createdBy + ", createdDateTime=" + createdDateTime + ", cTag=" + cTag + ", eTag=" + eTag + ", folder=" + folder + ", lastModifiedBy=" + lastModifiedBy + ", lastModifiedDateTime=" + lastModifiedDateTime + ", parentReference=" + parentReference + ", size=" + size + ", webUrl=" + webUrl + ", metadata=" + metadata + ", file=" + file + '}';
    }
    
}
