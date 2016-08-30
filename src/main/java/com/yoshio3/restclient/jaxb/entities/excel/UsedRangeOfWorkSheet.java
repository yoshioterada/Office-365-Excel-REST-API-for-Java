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
public class UsedRangeOfWorkSheet {
    @JsonProperty("@odata.context")
    private String metaInfo1;

    @JsonProperty("@odata.type")
    private String metaInfo2;

    @JsonProperty("@odata.id")
    private String metaInfo3;
    
    private String address;
    private String addressLocal;
    private Integer cellCount;
    private Integer columnCount;
    private Boolean columnHidden;
    private Integer columnIndex;
    private List<List<String>> formulas;
    private List<List<String>> formulasLocal;
    private List<List<String>> formulasR1C1; //相対参照
    private Boolean hidden;
    private List<List<String>> numberFormat;
    private Integer rowCount;
    private Boolean rowHidden;
    private Integer rowIndex;
    private List<List<String>> text;
    private List<List<String>> values;
    private List<List<String>> valueTypes;

    /**
     * @return the metaInfo1
     */
    public String getMetaInfo1() {
        return metaInfo1;
    }

    /**
     * @param metaInfo1 the metaInfo1 to set
     */
    public void setMetaInfo1(String metaInfo1) {
        this.metaInfo1 = metaInfo1;
    }

    /**
     * @return the metaInfo2
     */
    public String getMetaInfo2() {
        return metaInfo2;
    }

    /**
     * @param metaInfo2 the metaInfo2 to set
     */
    public void setMetaInfo2(String metaInfo2) {
        this.metaInfo2 = metaInfo2;
    }

    /**
     * @return the metaInfo3
     */
    public String getMetaInfo3() {
        return metaInfo3;
    }

    /**
     * @param metaInfo3 the metaInfo3 to set
     */
    public void setMetaInfo3(String metaInfo3) {
        this.metaInfo3 = metaInfo3;
    }

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
     * @return the addressLocal
     */
    public String getAddressLocal() {
        return addressLocal;
    }

    /**
     * @param addressLocal the addressLocal to set
     */
    public void setAddressLocal(String addressLocal) {
        this.addressLocal = addressLocal;
    }

    /**
     * @return the cellCount
     */
    public Integer getCellCount() {
        return cellCount;
    }

    /**
     * @param cellCount the cellCount to set
     */
    public void setCellCount(Integer cellCount) {
        this.cellCount = cellCount;
    }

    /**
     * @return the columnCount
     */
    public Integer getColumnCount() {
        return columnCount;
    }

    /**
     * @param columnCount the columnCount to set
     */
    public void setColumnCount(Integer columnCount) {
        this.columnCount = columnCount;
    }

    /**
     * @return the columnHidden
     */
    public Boolean getColumnHidden() {
        return columnHidden;
    }

    /**
     * @param columnHidden the columnHidden to set
     */
    public void setColumnHidden(Boolean columnHidden) {
        this.columnHidden = columnHidden;
    }

    /**
     * @return the columnIndex
     */
    public Integer getColumnIndex() {
        return columnIndex;
    }

    /**
     * @param columnIndex the columnIndex to set
     */
    public void setColumnIndex(Integer columnIndex) {
        this.columnIndex = columnIndex;
    }

    /**
     * @return the formulasLocal
     */
    public List<List<String>> getFormulasLocal() {
        return formulasLocal;
    }

    /**
     * @param formulasLocal the formulasLocal to set
     */
    public void setFormulasLocal(List<List<String>> formulasLocal) {
        this.formulasLocal = formulasLocal;
    }

    /**
     * @return the formulasR1C1
     */
    public List<List<String>> getFormulasR1C1() {
        return formulasR1C1;
    }

    /**
     * @param formulasR1C1 the formulasR1C1 to set
     */
    public void setFormulasR1C1(List<List<String>> formulasR1C1) {
        this.formulasR1C1 = formulasR1C1;
    }

    /**
     * @return the hidden
     */
    public Boolean getHidden() {
        return hidden;
    }

    /**
     * @param hidden the hidden to set
     */
    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    /**
     * @return the numberFormat
     */
    public List<List<String>> getNumberFormat() {
        return numberFormat;
    }

    /**
     * @param numberFormat the numberFormat to set
     */
    public void setNumberFormat(List<List<String>> numberFormat) {
        this.numberFormat = numberFormat;
    }

    /**
     * @return the rowCount
     */
    public Integer getRowCount() {
        return rowCount;
    }

    /**
     * @param rowCount the rowCount to set
     */
    public void setRowCount(Integer rowCount) {
        this.rowCount = rowCount;
    }

    /**
     * @return the rowHidden
     */
    public Boolean getRowHidden() {
        return rowHidden;
    }

    /**
     * @param rowHidden the rowHidden to set
     */
    public void setRowHidden(Boolean rowHidden) {
        this.rowHidden = rowHidden;
    }

    /**
     * @return the rowIndex
     */
    public Integer getRowIndex() {
        return rowIndex;
    }

    /**
     * @param rowIndex the rowIndex to set
     */
    public void setRowIndex(Integer rowIndex) {
        this.rowIndex = rowIndex;
    }

    /**
     * @return the text
     */
    public List<List<String>> getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(List<List<String>> text) {
        this.text = text;
    }

    /**
     * @return the values
     */
    public List<List<String>> getValues() {
        return values;
    }

    /**
     * @param values the values to set
     */
    public void setValues(List<List<String>> values) {
        this.values = values;
    }

    /**
     * @return the valueTypes
     */
    public List<List<String>> getValueTypes() {
        return valueTypes;
    }

    /**
     * @param valueTypes the valueTypes to set
     */
    public void setValueTypes(List<List<String>> valueTypes) {
        this.valueTypes = valueTypes;
    }

    /**
     * @return the formulas
     */
    public List<List<String>> getFormulas() {
        return formulas;
    }

    /**
     * @param formulas the formulas to set
     */
    public void setFormulas(List<List<String>> formulas) {
        this.formulas = formulas;
    }

    @Override
    public String toString() {
        return "UsedRangeOfWorkSheet{" + "metaInfo1=" + metaInfo1 + ", metaInfo2=" + metaInfo2 + ", metaInfo3=" + metaInfo3 + ", address=" + address + ", addressLocal=" + addressLocal + ", cellCount=" + cellCount + ", columnCount=" + columnCount + ", columnHidden=" + columnHidden + ", columnIndex=" + columnIndex + ", formulas=" + formulas + ", formulasLocal=" + formulasLocal + ", formulasR1C1=" + formulasR1C1 + ", hidden=" + hidden + ", numberFormat=" + numberFormat + ", rowCount=" + rowCount + ", rowHidden=" + rowHidden + ", rowIndex=" + rowIndex + ", text=" + text + ", values=" + values + ", valueTypes=" + valueTypes + '}';
    }


}
