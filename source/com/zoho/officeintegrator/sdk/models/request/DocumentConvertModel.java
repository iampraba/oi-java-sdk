//$Id$
package com.zoho.officeintegrator.sdk.models.request;

import org.json.JSONObject;

import com.zoho.officeintegrator.sdk.constants.Constants.PARAMS;
import com.zoho.officeintegrator.sdk.constants.Constants.SERVICE_TYPE;
import com.zoho.officeintegrator.sdk.constants.FileConstants.FILE_EXTENSION;
import com.zoho.officeintegrator.sdk.models.base.ExportSettings;
import com.zoho.officeintegrator.sdk.models.base.SourceDocument;

public class DocumentConvertModel {
	
	private String format = null;
	
	private SourceDocument source_document = new SourceDocument();
	
	private ExportSettings export_settings = new ExportSettings();
	
	public DocumentConvertModel setSourceDocument(SourceDocument source_document) {
		this.source_document = source_document;
		return this;
	}
	
	public SourceDocument getSourceDocument() {
		return this.source_document;
	}

	
	public ExportSettings getExportSettings() {
		return export_settings;
	}

	public DocumentConvertModel setExportSettings(ExportSettings export_settings) {
		this.export_settings = export_settings;
		return this;
	}

	public String getFormat() {
		return format;
	}

	public String getFormat(SERVICE_TYPE service) {
		if ( getFormat() == null ) {
			switch (service) {
				case WRITER:
					format = FILE_EXTENSION.PDF;
					break;
				case SHEET:
					format = FILE_EXTENSION.XLSX;
					break;
				case SHOW:
					format = FILE_EXTENSION.PPTX;
					break;
			}
		}
		return format;
	}

	public DocumentConvertModel setFormat(String format) {
		this.format = format;
		return this;
	}
	
	public JSONObject toJson(SERVICE_TYPE service) {
		JSONObject jsonObject = this.getSourceDocument().toJson();
		
		jsonObject.put(PARAMS.FORMAT, this.getFormat(service));
		//jsonObject.put(PARAMS.CALLBACK_SETTINGS, this.getExportSettings().toJson(service));
		
		return jsonObject;
	}
	
}
