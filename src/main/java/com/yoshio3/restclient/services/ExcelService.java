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
package com.yoshio3.restclient.services;

import com.yoshio3.cdiutil.LoggerQualifier;
import com.yoshio3.restclient.exceptions.RestClientIllegalStateException;
import com.yoshio3.restclient.jaxb.entities.excel.ExcelWorkSheet;
import com.yoshio3.restclient.jaxb.entities.excel.ExcelWorkSheetValue;
import com.yoshio3.restclient.jaxb.entities.excel.RequestAddRowDataToTable;
import com.yoshio3.restclient.jaxb.entities.excel.RequestCreateTableOnWorkSheet;
import com.yoshio3.restclient.jaxb.entities.excel.TablesInWorkSheet;
import com.yoshio3.restclient.jaxb.entities.excel.TablesValue;
import com.yoshio3.restclient.jaxb.entities.excel.UsedRangeOfWorkSheet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Yoshio Terada
 */
@Dependent
public class ExcelService extends RestClientService {

    @Inject
    @LoggerQualifier
    transient Logger logger;

    /* 指定したファイル ID に存在する Excel のワークシートを一覧を取得*/
    public Optional<List<ExcelWorkSheetValue>> getExcelWorkSheetInFile(String fileID) throws RestClientIllegalStateException {
        String graphURL = GRAPH_SERVER_V1_URL + "/me/drive/items/" + fileID + "/workbook/worksheets";
        Response response = execHTTPGetRequest(graphURL);

        if (checkRequestSuccess(response)) {
            ExcelWorkSheet worksheet = response.readEntity(ExcelWorkSheet.class);
            List<ExcelWorkSheetValue> value = worksheet.getValue();
            return Optional.of(value);
        } else {
            handleIllegalState(response);
            return Optional.empty();
        }
    }

    /* テーブル一覧の取得 */
    public Optional<List<TablesValue>> getAllTableInWorkSheet(String fileID, String workSheetID) throws RestClientIllegalStateException {
        //Exam : /me/drive/items('01O57PPYB2APDISPWM5RALJVQAF2D2CYXP')/workbook/worksheets(%27%7B00000000-0001-0000-0000-000000000000%7D%27)/tables
        //https://graph.microsoft.com/v1.0

        String graphURL = GRAPH_SERVER_V1_URL + "/me/drive/items('" + fileID + "')/workbook/worksheets(%27%7B" + workSheetID + "%7D%27)/tables";
        Response response = execHTTPGetRequest(graphURL);

        if (checkRequestSuccess(response)) {
            TablesInWorkSheet tableData = response.readEntity(TablesInWorkSheet.class);
            return Optional.of(tableData.getValue());
        } else {
            handleIllegalState(response);
            return Optional.empty();
        }
    }

    /* ワークシートのレンジ(範囲)・データを取得*/
    public Optional<UsedRangeOfWorkSheet> getRangeOfWorkSheet(String fileID, String workSheetID) throws RestClientIllegalStateException {
//        String graphURL = GRAPH_SERVER_V1_URL + excelWorkSheet.getMetadata() + "/UsedRange";

        String graphURL = GRAPH_SERVER_V1_URL + "/me/drive/items('" + fileID + "')/workbook/worksheets(%27%7B" + workSheetID + "%7D%27)/UsedRange";
        Response response = execHTTPGetRequest(graphURL);

        if (checkRequestSuccess(response)) {
            UsedRangeOfWorkSheet rangeData = response.readEntity(UsedRangeOfWorkSheet.class);
            return Optional.of(rangeData);
        } else {
            handleIllegalState(response);
            return Optional.empty();
        }
    }

    /* テーブルに対して１行を追加 */
    public void addRowDataToTable(String fileID, String workSheetID, String tableID, List<Object> addData) throws RestClientIllegalStateException {
        // /me/drive/items('01O57PPYFBESMGG7JGIZFI3T3TQZ72C37D')/workbook/worksheets(%27%7B00000000-0001-0000-0000-000000000000%7D%27)/tables(%272%27)/rows

        String graphURL = GRAPH_SERVER_V1_URL + "/me/drive/items('" + fileID + "')/workbook/worksheets(%27%7B" + workSheetID + "%7D%27)/tables(%27" + tableID + "%27)/rows";

        List<List<Object>> listData = new ArrayList();
        listData.add(addData);
        RequestAddRowDataToTable rowData = new RequestAddRowDataToTable();
        rowData.setValues(listData);
        Entity<RequestAddRowDataToTable> entity = Entity.entity(rowData, MediaType.APPLICATION_JSON_TYPE);

        Response response = execHTTPPostRequest(entity, graphURL);

        if (checkRequestSuccess(response)) {
            String tableData = response.readEntity(String.class);
        } else {
            handleIllegalState(response);
        }
    }

    /* ワークシートにテーブル（表）の作成 */
    public void createTableInWorkSheet(ExcelWorkSheetValue excelWorkSheet, String startPositionX, String endPositionY, boolean showHeader, boolean showTotals) throws RestClientIllegalStateException {
        String graphURL = GRAPH_SERVER_V1_URL + excelWorkSheet.getMetadata() + "/tables/add";
        String sheetRange = excelWorkSheet.getName() + "!" + startPositionX + ":" + endPositionY;
        RequestCreateTableOnWorkSheet sendData = new RequestCreateTableOnWorkSheet();
        sendData.setAddress(sheetRange);
        sendData.setHasHeaders(showHeader);
        sendData.setHasTotals(showTotals);
        Entity<RequestCreateTableOnWorkSheet> entity = Entity.entity(sendData, MediaType.APPLICATION_JSON_TYPE);
        Response response = execHTTPPostRequest(entity, graphURL);

        if (checkRequestSuccess(response)) {
            String tableData = response.readEntity(String.class);
        } else {
            handleIllegalState(response);
        }
    }

    /* 行や列に対してパッチを適用 */
    public void updateRangedColumnRow() {
        //PATCH /{version}/me/drive/items/01CYZLFJDYBLIGAE7G5FE3I4VO2XP7BLU4/workbook/worksheets('test')/range(address='test!A1:B2')
        /*
            { "values": [ [ "Test", "Value" ], [ "For", "Update" ] ] }
         */
    }


}
