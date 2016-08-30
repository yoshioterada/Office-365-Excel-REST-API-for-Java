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
package com.yoshio3.restclient.jaxb.entities.excel;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Yoshio Terada
 */
public class ExcelWorkSheetValue {
    @JsonProperty("@odata.id")
    private String metadata;

    private String id;
    private String name;
    private Integer position;
    private String visibility;

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
     * @return the id
     */
    public String getId() {
        return removeCurlyBrackets(id);
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
     * @return the position
     */
    public Integer getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(Integer position) {
        this.position = position;
    }

    /**
     * @return the visibility
     */
    public String getVisibility() {
        return visibility;
    }

    /**
     * @param visibility the visibility to set
     */
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    @Override
    public String toString() {
        return "ExcelWorkSheetValue{" + "metadata=" + metadata + ", id=" + id + ", name=" + name + ", position=" + position + ", visibility=" + visibility + '}';
    }
    private String removeCurlyBrackets(String origin) {
        /* The Actual Retun Value of JSON for WorkSheet as follows.
        In order to use the "id" value in the program,
        the "{}" character is not futility.
        {"@odata.context":
        "https://graph.microsoft.com/v1.0/$metadata#users('1b1fa500-f6cf-4995-bec4-09b0f800bc58')/drive/items('01O57PPYFBESMGG7JGIZFI3T3TQZ72C37D')/workbook/worksheets","value":[{"@odata.id":"/users('1b1fa500-f6cf-4995-bec4-09b0f800bc58')/drive/items('01O57PPYFBESMGG7JGIZFI3T3TQZ72C37D')/workbook/worksheets(%27%7B00000000-0001-0000-0000-000000000000%7D%27)",
        "id":"{00000000-0001-0000-0000-000000000000}",
        "name":"Sheet1",
        "position":0,
        "visibility":"Visible"
        }]}]]
         */
        if (origin.contains("{") && origin.contains("}")) {
            int length = origin.length();
            return origin.substring(1, length - 1);
        }
        return origin;
    }    
}
