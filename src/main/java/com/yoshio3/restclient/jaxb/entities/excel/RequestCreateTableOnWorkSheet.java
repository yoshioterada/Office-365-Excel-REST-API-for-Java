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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Yoshio Terada
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RequestCreateTableOnWorkSheet {

    private String address;
    private Boolean hasHeaders;
    private Boolean hasTotals;

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the hasHeaders
     */
    public Boolean getHasHeaders() {
        return hasHeaders;
    }

    /**
     * @param hasHeaders the hasHeaders to set
     */
    public void setHasHeaders(Boolean hasHeaders) {
        this.hasHeaders = hasHeaders;
    }

    /**
     * @return the hasTotals
     */
    public Boolean getHasTotals() {
        return hasTotals;
    }

    /**
     * @param hasTotals the hasTotals to set
     */
    public void setHasTotals(Boolean hasTotals) {
        this.hasTotals = hasTotals;
    }

    @Override
    public String toString() {
        return "RequestCreateTableOnWorkSheet{" + "address=" + address + ", hasHeaders=" + hasHeaders + ", hasTotals=" + hasTotals + '}';
    }

}
