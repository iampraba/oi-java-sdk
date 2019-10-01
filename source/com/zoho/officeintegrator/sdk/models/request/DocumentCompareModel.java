//$Id$
package com.zoho.officeintegrator.sdk.models.request;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.zoho.common.util.JsonUtils;
import com.zoho.common.util.restapi.MultiPartFile;
import com.zoho.officeintegrator.sdk.constants.Constants.PARAMS;
import com.zoho.officeintegrator.sdk.models.base.SourceDocument;

public class DocumentCompareModel {

	private SourceDocument document1 = null;
	
	private SourceDocument document2 = null;
	
	public SourceDocument getSourceDocument1() {
		return document1;
	}

	public DocumentCompareModel setSourceDocument1(SourceDocument sourceDocument1) {
		this.document1 = sourceDocument1;
		return this;
	}

	public SourceDocument getSourceDocument2() {
		return document2;
	}

	public DocumentCompareModel setSourceDocument2(SourceDocument sourceDocument2) {
		this.document2 = sourceDocument2;
		return this;
	}
	
	public JSONObject toJson() {
		JSONObject jsonObject = getSourceDocument1().toJson();
		
		JsonUtils.mergeJSONObjects(jsonObject, getSourceDocument2().toJson());
		
		return jsonObject;
	}

	public Map<String, MultiPartFile> getFileMap() {
		Map<String, MultiPartFile> fileMap = new HashMap<String, MultiPartFile>();

		fileMap.putAll(this.getSourceDocument1().getFileMap(PARAMS.DOCUMENT1));
		fileMap.putAll(this.getSourceDocument2().getFileMap(PARAMS.DOCUMENT2));

		return fileMap;
	}

}
