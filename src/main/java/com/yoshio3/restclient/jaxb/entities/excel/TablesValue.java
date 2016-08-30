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
public class TablesValue {

    @JsonProperty("@odata.id")
    private String metadata;

    private String id;
    private String name;
    private Boolean showHeaders;
    private Boolean showTotals;
    private String style;

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
     * @return the showHeaders
     */
    public Boolean getShowHeaders() {
        return showHeaders;
    }

    /**
     * @param showHeaders the showHeaders to set
     */
    public void setShowHeaders(Boolean showHeaders) {
        this.showHeaders = showHeaders;
    }

    /**
     * @return the showTotals
     */
    public Boolean getShowTotals() {
        return showTotals;
    }

    /**
     * @param showTotals the showTotals to set
     */
    public void setShowTotals(Boolean showTotals) {
        this.showTotals = showTotals;
    }

    /**
     * @return the style
     */
    public String getStyle() {
        return style;
    }

    /**
     * @param style the style to set
     */
    public void setStyle(String style) {
        this.style = style;
    }

    @Override
    public String toString() {
        return "TablesValue{" + "metadata=" + metadata + ", id=" + id + ", name=" + name + ", showHeaders=" + showHeaders + ", showTotals=" + showTotals + ", style=" + style + '}';
    }


}
