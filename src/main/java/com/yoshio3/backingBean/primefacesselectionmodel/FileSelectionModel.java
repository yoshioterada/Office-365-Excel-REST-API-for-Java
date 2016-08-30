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

import com.yoshio3.restclient.jaxb.entities.onedrive.OneDriveChildrenValue;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.faces.model.ListDataModel;
import javax.inject.Named;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author Yoshio Terada
 */
@ViewScoped
@Named(value = "fileselection")
public class FileSelectionModel extends ListDataModel<OneDriveChildrenValue> implements SelectableDataModel<OneDriveChildrenValue>, Serializable {

    public FileSelectionModel() {
        super();
    }

    private List<OneDriveChildrenValue> listChildrens;

    private OneDriveChildrenValue selectedChildrens;

    public FileSelectionModel(List<OneDriveChildrenValue> data) {
        super(data);
    }

    /**
     * @return the listChildrens
     */
    public List<OneDriveChildrenValue> getListChildrens() {
        return listChildrens;
    }

    /**
     * @param listChildrens the listChildrens to set
     */
    public void setListChildrens(List<OneDriveChildrenValue> listChildrens) {
        this.listChildrens = listChildrens;
    }

    /**
     * @return the selectedChildrens
     */
    public OneDriveChildrenValue getSelectedChildrens() {
        return selectedChildrens;
    }

    /**
     * @param selectedChildrens the selectedChildrens to set
     */
    public void setSelectedChildrens(OneDriveChildrenValue selectedChildrens) {
        this.selectedChildrens = selectedChildrens;
    }

    @Override
    public Object getRowKey(OneDriveChildrenValue files) {
        return files.getId();
    }

    @Override
    public OneDriveChildrenValue getRowData(String fileID) {
        return getSelectedChildrens();
    }
}
