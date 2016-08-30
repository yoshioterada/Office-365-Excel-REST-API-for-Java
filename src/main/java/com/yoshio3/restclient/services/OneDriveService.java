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

import com.yoshio3.restclient.jaxb.entities.onedrive.OneDriveChildren;
import com.yoshio3.restclient.jaxb.entities.onedrive.OneDriveChildrenValue;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import com.yoshio3.cdiutil.LoggerQualifier;
import com.yoshio3.restclient.exceptions.RestClientIllegalStateException;
import java.util.stream.Collectors;


/**
 *
 * @author Yoshio Terada
 */
@Dependent
public class OneDriveService extends RestClientService {

    @Inject @LoggerQualifier
    private transient Logger logger;
    
    /* OneDrive に存在するディレクトリ・ファイル一覧を取得*/
    public Optional<List<OneDriveChildrenValue>> getFilesOnOneDrive() throws RestClientIllegalStateException {
        String graphURL = GRAPH_SERVER_V1_URL + "/me/drive/root/children";
        Response response = execHTTPGetRequest(graphURL);

        if (checkRequestSuccess(response)) {
            OneDriveChildren children = response.readEntity(OneDriveChildren.class);
            List<OneDriveChildrenValue> childrenValue = children.getValue();
            //TODO : 再帰呼び出しで子ディレクトリ配下のファイル検索はしていない
            List<OneDriveChildrenValue> collect = childrenValue.stream()
                    .filter(child -> child.getFile() != null)
                    .collect(Collectors.toList());
            return Optional.of(collect);
        } else {    
            handleIllegalState(response);
            return Optional.empty();
        }
    }
}
