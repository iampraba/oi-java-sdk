//$Id$
package com.zoho.officeintegrator.sdk.models.request;

import java.util.Map;

import org.json.JSONObject;

import com.zoho.common.util.restapi.MultiPartFile;
import com.zoho.officeintegrator.sdk.constants.Constants.PARAMS;
import com.zoho.officeintegrator.sdk.constants.Constants.SERVICE_TYPE;
import com.zoho.officeintegrator.sdk.models.base.SourceDocument;

public class DocumentEditModel extends DocumentCreateModel {

    private SourceDocument document = new SourceDocument();

    public SourceDocument getSourceDocument() {
        return this.document;
    }

    public DocumentEditModel setSourceDocument(SourceDocument sourceDocument) {
        this.document = sourceDocument;
        return this;
    }

    public JSONObject toJson(SERVICE_TYPE service) {
        JSONObject jsonObject = super.toJson(service);

        jsonObject.put(PARAMS.URL, this.getSourceDocument().getDocumentUrl());

        return jsonObject;
    }

    public Map<String, MultiPartFile> getFileMap() {
        return this.getSourceDocument().getFileMap();
    }

}
