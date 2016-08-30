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

import com.yoshio3.restclient.jaxb.entities.excel.TablesValue;
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
@Named(value = "tableselection")
public class ExcelSheetTablesSelectionModel extends ListDataModel<TablesValue> implements SelectableDataModel<TablesValue>, Serializable {
    public ExcelSheetTablesSelectionModel(){
        super();
    }

    private List<TablesValue> allTables;
    private TablesValue selectedTable;

    /**
     * @return the allTables
     */
    public List<TablesValue> getAllTables() {
        return allTables;
    }

    /**
     * @param allTables the allTables to set
     */
    public void setAllTables(List<TablesValue> allTables) {
        this.allTables = allTables;
    }

    /**
     * @return the selectedTable
     */
    public TablesValue getSelectedTable() {
        return selectedTable;
    }

    /**
     * @param selectedTable the selectedTable to set
     */
    public void setSelectedTable(TablesValue selectedTable) {
        this.selectedTable = selectedTable;
    }

    @Override
    public Object getRowKey(TablesValue table) {
        return table.getId();
    }

    @Override
    public TablesValue getRowData(String rowKey) {
        return getSelectedTable();
    }
}
