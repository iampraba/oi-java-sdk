//$Id$
package com.zoho.officeintegrator.sdk.models.base;

import org.json.JSONObject;

import com.zoho.officeintegrator.sdk.constants.Constants.PARAMS;

public class DocumentInfo {
	
	private String document_id = null;
	
	private String document_name = PARAMS.DEFAULT_DOCUMENT_NAME;
	
	public String getDocumentId() {
		return document_id;
	}

	public void setDocumentId(String document_id) {
		this.document_id = document_id;
	}


	public String getDocumentName() {
		return document_name;
	}


	public void setDocumentName(String document_name) {
		this.document_name = document_name;
	}

	public JSONObject toJson() {
		JSONObject jsonObject = new JSONObject();
		
		if ( this.getDocumentId() != null ) {
			jsonObject.put(PARAMS.DOCUMENT_ID, this.getDocumentId());
		}
		
		return jsonObject.put(PARAMS.DOCUMENT_NAME, this.getDocumentName());
	}

}
