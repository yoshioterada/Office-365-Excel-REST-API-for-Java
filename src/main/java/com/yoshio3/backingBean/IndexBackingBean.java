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
package com.yoshio3.backingBean;

import com.yoshio3.backingBean.primefacesselectionmodel.ExcelSheetTablesSelectionModel;
import com.yoshio3.backingBean.primefacesselectionmodel.FileSelectionModel;
import com.yoshio3.backingBean.primefacesselectionmodel.WorkSheetSelectionModel;
import com.yoshio3.cdiutil.LoggerQualifier;
import com.yoshio3.restclient.exceptions.RestClientIllegalStateException;
import com.yoshio3.restclient.jaxb.entities.excel.ExcelWorkSheetValue;
import com.yoshio3.restclient.jaxb.entities.excel.TablesValue;
import com.yoshio3.restclient.jaxb.entities.excel.UsedRangeOfWorkSheet;
import com.yoshio3.restclient.services.OneDriveService;
import com.yoshio3.restclient.jaxb.entities.onedrive.OneDriveChildrenValue;
import com.yoshio3.restclient.services.ExcelService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Yoshio Terada
 */
@ViewScoped
@Named(value = "index")
public class IndexBackingBean implements Serializable {

    //CDI で LOGGER を実装
    @Inject
    @LoggerQualifier
    transient Logger logger;

    @Inject
    private OneDriveService oneDriveService;

    @Inject
    private ExcelService excelService;

    @Inject
    FileSelectionModel fileSelection;

    @Inject
    WorkSheetSelectionModel workSheetSelection;

    @Inject
    ExcelSheetTablesSelectionModel tableSelection;

    private OneDriveChildrenValue selectedFile;
    private ExcelWorkSheetValue selectedWorkSheet;
    private TablesValue selectedTables;
    private UsedRangeOfWorkSheet selectedSheetValue;

    
    //登録するユーザの名前、カナ、メールアドレス、会社名
    private String NameOfPerson;
    private String NameOfPersonByKana;
    private String EMailAddress;
    private String Corporation;
    
    
    public void submitGetAllFileIds() {
        try {
            //OneDrive にあるディレクトリ・ファイル一覧の取得
            Optional<List<OneDriveChildrenValue>> filesOnOneDrive
                    = oneDriveService.getFilesOnOneDrive();
            if (filesOnOneDrive.isPresent()) {
                fileSelection.setListChildrens(filesOnOneDrive.get());
            }
        } catch (RestClientIllegalStateException rcise) {
            logger.log(Level.SEVERE, null, rcise);
            FacesContext.getCurrentInstance().addMessage("client-id", new FacesMessage(rcise.getMessage()));
        }
    }

    public void onFileRowSelect(SelectEvent event) {
        this.selectedFile = ((OneDriveChildrenValue) event.getObject());
        String fileID = selectedFile.getId();
        List<ExcelWorkSheetValue> workSheets;
        try {
            Optional<List<ExcelWorkSheetValue>> excelWorkSheetInFile
                    = excelService.getExcelWorkSheetInFile(fileID);
            if (excelWorkSheetInFile.isPresent()) {
                workSheetSelection.setAllWorkSheets(excelWorkSheetInFile.get());
            }
        } catch (RestClientIllegalStateException ex) {
            logger.log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage("client-id", new FacesMessage(ex.getMessage()));
        }
    }

    public void onWorkSheetRowSelect(SelectEvent event) {
        this.selectedWorkSheet = (ExcelWorkSheetValue) event.getObject();
        String fileID = selectedFile.getId();
        String sheetID = selectedWorkSheet.getId();
        getRangedData(fileID, sheetID);
        getTablesinWorkSheet(fileID, sheetID);
    }

    
    public void getTablesinWorkSheet(String fileID, String workSheetID) {
        try {
            Optional<List<TablesValue>> allTableInWorkSheet
                    = excelService.getAllTableInWorkSheet(fileID, workSheetID);
            if (allTableInWorkSheet.isPresent()) {
                tableSelection.setAllTables(allTableInWorkSheet.get());
            }
        } catch (RestClientIllegalStateException ex) {
            logger.log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage("client-id", new FacesMessage(ex.getMessage()));
        }
    }
    
    public void getRangedData(String fileID, String workSheetID) {
        try {
            Optional<UsedRangeOfWorkSheet> rangeOfWorkSheet
                    = excelService.getRangeOfWorkSheet(fileID, workSheetID);

            if (rangeOfWorkSheet.isPresent()) {
                this.setSelectedSheetValue(rangeOfWorkSheet.get());
            }
        } catch (RestClientIllegalStateException ex) {
            logger.log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage("client-id", new FacesMessage(ex.getMessage()));
        }
    }
    
    public void onTableRowSelect(SelectEvent event) {
        this.selectedTables = (TablesValue) event.getObject();
    }
    
    public void addUser(){
        String fileID = selectedFile.getId();
        String workSheetID = selectedWorkSheet.getId();
        String tableID = selectedTables.getId();
        
        List<Object> user = new ArrayList<>();
        user.add(getNameOfPerson());
        user.add(getNameOfPersonByKana());
        user.add(getEMailAddress());
        user.add(getCorporation());
        
        try {
            excelService.addRowDataToTable(fileID, workSheetID, tableID, user);
        } catch (RestClientIllegalStateException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }
    
    
    //////////////// Setter & Getter ////////////////////

    /**
     * @return the selectedSheetValue
     */
    public UsedRangeOfWorkSheet getSelectedSheetValue() {
        return selectedSheetValue;
    }

    /**
     * @param selectedSheetValue the selectedSheetValue to set
     */
    public void setSelectedSheetValue(UsedRangeOfWorkSheet selectedSheetValue) {
        this.selectedSheetValue = selectedSheetValue;
    }

    /**
     * @return the selectedTables
     */
    public TablesValue getSelectedTables() {
        return selectedTables;
    }

    /**
     * @return the NameOfPerson
     */
    public String getNameOfPerson() {
        return NameOfPerson;
    }

    /**
     * @param NameOfPerson the NameOfPerson to set
     */
    public void setNameOfPerson(String NameOfPerson) {
        this.NameOfPerson = NameOfPerson;
    }

    /**
     * @return the NameOfPersonByKana
     */
    public String getNameOfPersonByKana() {
        return NameOfPersonByKana;
    }

    /**
     * @param NameOfPersonByKana the NameOfPersonByKana to set
     */
    public void setNameOfPersonByKana(String NameOfPersonByKana) {
        this.NameOfPersonByKana = NameOfPersonByKana;
    }

    /**
     * @return the EMailAddress
     */
    public String getEMailAddress() {
        return EMailAddress;
    }

    /**
     * @param EMailAddress the EMailAddress to set
     */
    public void setEMailAddress(String EMailAddress) {
        this.EMailAddress = EMailAddress;
    }

    /**
     * @return the Corporation
     */
    public String getCorporation() {
        return Corporation;
    }

    /**
     * @param Corporation the Corporation to set
     */
    public void setCorporation(String Corporation) {
        this.Corporation = Corporation;
    }
}
