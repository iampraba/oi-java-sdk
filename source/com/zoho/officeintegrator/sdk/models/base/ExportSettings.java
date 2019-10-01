/** $Id$ 
 * Settings model to configure editor related settings 
 */
package com.zoho.officeintegrator.sdk.models.base;

import org.json.JSONObject;

import com.zoho.officeintegrator.sdk.constants.Constants.CONST;
import com.zoho.officeintegrator.sdk.constants.Constants.PARAMS;
import com.zoho.officeintegrator.sdk.constants.Constants.SERVICE_TYPE;
import com.zoho.officeintegrator.sdk.constants.FileConstants.FILE_EXTENSION;

public class ExportSettings {
	
	private String save_url = null; //No I18N
	
	private String save_format = null; //No I18N
	
	private String context_info = null; //No I18N

	public String getSaveUrl() {
		return save_url;
	}

	public void setSaveUrl(String save_url) {
		this.save_url = save_url;
	}

	public String getSaveFormat() {
		return save_format;
	}

	public String getSaveFormat(SERVICE_TYPE service) {
		if ( this.getSaveFormat() == null ) {
			switch (service) {
				case WRITER:
					return FILE_EXTENSION.DOCX;
				case SHEET:
					return FILE_EXTENSION.XLSX;
				case SHOW:
					return FILE_EXTENSION.PPTX;
			}
		}
		return save_format;
	}

	public void setSaveFormat(String save_format) {
		this.save_format = save_format;
	}

	public String getContextInfo() {
		return context_info;
	}

	public void setContextInfo(String context_info) {
		this.context_info = context_info;
	}

	public JSONObject toJson(SERVICE_TYPE service) {
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put(PARAMS.SAVE_FORMAT, this.getSaveFormat(service));

		if ( this.getSaveUrl() != null ) {
			jsonObject.put(PARAMS.SAVE_URL, this.getSaveUrl());
		}

		switch (service) {
			case SHOW:
			case WRITER:
				if ( this.getContextInfo() != null ) {
					jsonObject.put(PARAMS.CONTEXT_INFO, this.getContextInfo());
				}
				break;
			default:
				break;
		}
		
		return jsonObject;
	}

}
