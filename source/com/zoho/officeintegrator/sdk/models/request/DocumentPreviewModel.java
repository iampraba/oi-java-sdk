//$Id$
package com.zoho.officeintegrator.sdk.models.request;

import java.util.Map;

import org.json.JSONObject;

import com.zoho.common.util.JsonUtils;
import com.zoho.common.util.restapi.MultiPartFile;
import com.zoho.officeintegrator.sdk.constants.Constants.LANGUAGE;
import com.zoho.officeintegrator.sdk.constants.Constants.SERVICE_TYPE;
import com.zoho.officeintegrator.sdk.models.base.EditorSettings;
import com.zoho.officeintegrator.sdk.models.base.SourceDocument;

public class DocumentPreviewModel {

    private EditorSettings editor_settings = new EditorSettings();

    private LANGUAGE language = LANGUAGE.ENGLISH;

    private SourceDocument source_document = new SourceDocument();

    public DocumentPreviewModel setSourceDocument(SourceDocument source_document) {
        this.source_document = source_document;
        return this;
    }

    public SourceDocument getSourceDocument() {
        return this.source_document;
    }

    public EditorSettings getEditorSettings() {
        return editor_settings;
    }

    public DocumentPreviewModel setExportSettings(EditorSettings editor_settings) {
        this.editor_settings = editor_settings;
        return this;
    }

    public LANGUAGE getLanguage() {
        return language;
    }

    public DocumentPreviewModel setLanguage(LANGUAGE language) {
        this.language = language;
        return this;
    }

    public JSONObject toJson(SERVICE_TYPE service) {
        JSONObject jsonObject = new JSONObject();

        JsonUtils.mergeJSONObjects(jsonObject, this.getSourceDocument().toJson());

        switch (service) {
            case WRITER:
                jsonObject.put("lang", this.getLanguage().getLanguageCode());
                //jsonObject.put("editor_settings", this.getEditorSettings().toJson(service));
                break;
            case SHEET:
                jsonObject.put("editor_settings", this.getEditorSettings().toJson(service));
                break;
            case SHOW:
                jsonObject.put("language", this.getLanguage().getLanguageCode());
                break;
        }

        return jsonObject;
    }

    public Map<String, MultiPartFile> getFileMap() {
        return this.getSourceDocument().getFileMap();
    }

}
