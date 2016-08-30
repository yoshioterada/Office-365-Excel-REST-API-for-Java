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
package com.yoshio3.backingBean.primefacesselectionmodel;

import com.yoshio3.restclient.jaxb.entities.excel.ExcelWorkSheetValue;
import java.io.Serializable;
import java.util.List;
import javax.faces.model.ListDataModel;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author Yoshio Terada
 */
@ViewScoped
@Named(value = "worksheetselection")
public class WorkSheetSelectionModel extends ListDataModel<ExcelWorkSheetValue> implements SelectableDataModel<ExcelWorkSheetValue>, Serializable {

    private List<ExcelWorkSheetValue> allWorkSheets;
    private ExcelWorkSheetValue selectedWorkSheets;

    public WorkSheetSelectionModel(){
        super();
    }
    
    @Override
    public Object getRowKey(ExcelWorkSheetValue workSheet) {
        return workSheet.getId();
    }

    @Override
    public ExcelWorkSheetValue getRowData(String sheetID) {
        return getSelectedWorkSheets();
    }

    /**
     * @return the allWorkSheets
     */
    public List<ExcelWorkSheetValue> getAllWorkSheets() {
        return allWorkSheets;
    }

    /**
     * @param allWorkSheets the allWorkSheets to set
     */
    public void setAllWorkSheets(List<ExcelWorkSheetValue> allWorkSheets) {
        this.allWorkSheets = allWorkSheets;
    }

    /**
     * @return the selectedWorkSheets
     */
    public ExcelWorkSheetValue getSelectedWorkSheets() {
        return selectedWorkSheets;
    }

    /**
     * @param selectedWorkSheets the selectedWorkSheets to set
     */
    public void setSelectedWorkSheets(ExcelWorkSheetValue selectedWorkSheets) {
        this.selectedWorkSheets = selectedWorkSheets;
    }
}
