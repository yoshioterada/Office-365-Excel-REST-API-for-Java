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

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Yoshio Terada
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RequestAddRowDataToTable {
    private List<List<Object>> values;

    /**
     * @return the values
     */
    public List<List<Object>> getValues() {
        return values;
    }

    /**
     * @param values the values to set
     */
    public void setValues(List<List<Object>> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "RequestAddRowDataToTable{" + "values=" + values + '}';
    }
    
}
