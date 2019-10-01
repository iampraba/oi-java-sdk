//$Id$
package com.zoho.officeintegrator.sdk.models.base;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.zoho.common.util.restapi.MultiPartFile;
import com.zoho.officeintegrator.sdk.constants.Constants.PARAMS;

public class SourceDocument {
	
	private String document_url = null;

	private MultiPartFile document = null;

	public String getDocumentUrl() {
		return document_url;
	}

	public SourceDocument setDocumentUrl(String document_url) {
		this.document_url = document_url;
		return this;
	}

	public MultiPartFile getDocument() {
		return document;
	}

	public SourceDocument setDocument(MultiPartFile document) {
		this.document = document;
		return this;
	}

	public boolean hasDocumentToEdit() {
		return ( this.document_url != null || this.document != null);
	}
	
	public JSONObject toJson() {
		JSONObject jsonObject = new JSONObject();

		if ( document_url != null ) {
			jsonObject.put(PARAMS.URL, this.getDocumentUrl());
		}

		return jsonObject;
	}
	
	public Map<String, MultiPartFile> getFileMap(String paramName) {
		
		Map<String, MultiPartFile> fileMap = new HashMap<String, MultiPartFile>();
		
		if ( this.getDocument() != null ) {
			fileMap.put(paramName, this.getDocument());
		}

		return fileMap;
	}
	
	public Map<String, MultiPartFile> getFileMap() {
		return getFileMap(PARAMS.DOCUMENT);
	}

}
