//$Id$
package com.zoho.officeintegrator.sdk.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.zoho.common.util.CommonUtils;
import com.zoho.common.util.restapi.HttpResponse;
import com.zoho.common.util.restapi.RestApiUtil;
import com.zoho.common.util.restapi.RestApiUtil.HttpRequestMethod;
import com.zoho.officeintegrator.sdk.configurations.AppConfiguration;
import com.zoho.officeintegrator.sdk.constants.Constants.PARAMS;
import com.zoho.officeintegrator.sdk.constants.Constants.SERVICE_TYPE;
import com.zoho.officeintegrator.sdk.models.request.DocumentCreateModel;
import com.zoho.officeintegrator.sdk.models.request.DocumentEditModel;
import com.zoho.officeintegrator.sdk.models.request.DocumentPreviewModel;

public class SheetController {
	
	private static final String CREATE_SPREADSHEET_END_POINT = AppConfiguration.getSheetServerUrl() + "/sheet/officeapi/v1/spreadsheet";
	
	private static final String PREVIEW_SPREADSHEET_END_POINT = AppConfiguration.getSheetServerUrl() + "/sheet/officeapi/v1/spreadsheet/preview";

	private static final String DELETE_SPREADSHEET_END_POINT = AppConfiguration.getSheetServerUrl() + "/sheet/officeapi/v1/spreadsheet/<documentid>";

	private static final String DELETE_SESSION_END_POINT = AppConfiguration.getSheetServerUrl() + "/sheet/officeapi/v1/session/<sessionid>";
	
	private static Map<String, String> getApikeyParams() {
		Map<String, String> queryParams = new HashMap<String, String>();
		
		queryParams.put(PARAMS.APIKEY, AppConfiguration.getApikey());

		return queryParams;
	}
	
	public static JSONObject createDocument(DocumentCreateModel createModel) {
		
		try {
			HttpResponse response = RestApiUtil.makeApiCall(CREATE_SPREADSHEET_END_POINT, HttpRequestMethod.POST, new HashMap<String, String>(), getApikeyParams(), CommonUtils.convertJsonToMapObject(createModel.toJson(SERVICE_TYPE.SHEET)), false);
			
			return response.getResponseAsJSON();
		} catch (IOException e) {}
		return null;
	}

	public static JSONObject editDocument(DocumentEditModel documentEditModel) throws Exception {
		
		try {
			if ( !documentEditModel.getSourceDocument().hasDocumentToEdit() ) {
				throw new Exception("No document or document url persent to edit the document.");
			}

			HttpResponse response = RestApiUtil.makeApiCall(CREATE_SPREADSHEET_END_POINT, new HashMap<String, String>(), getApikeyParams(), CommonUtils.convertJsonToMapObject(documentEditModel.toJson(SERVICE_TYPE.SHEET)), documentEditModel.getFileMap(), false);

			return response.getResponseAsJSON();
		} catch (IOException e) {}
		return null;
	}

	public static JSONObject coEditDocument(DocumentEditModel documentEditModel) throws Exception {
		
		try {
			if ( documentEditModel.getDocumentInfo().getDocumentId() == null ) {
				throw new Exception("Mandarory parameter Document id not persent to open document in collaboration mode.");
			}
			
			HttpResponse response = RestApiUtil.makeApiCall(CREATE_SPREADSHEET_END_POINT, new HashMap<String, String>(), getApikeyParams(), CommonUtils.convertJsonToMapObject(documentEditModel.toJson(SERVICE_TYPE.SHEET)), documentEditModel.getFileMap(), false);
			
			return response.getResponseAsJSON();
		} catch (IOException e) {}
		return null;
	}
	

	public static JSONObject previewDocument(DocumentPreviewModel documentPreviewModel) throws Exception {

		try {
			if ( !documentPreviewModel.getSourceDocument().hasDocumentToEdit() ) {
				throw new Exception("No document or document url persent to preview.");
			}
			HttpResponse response = RestApiUtil.makeApiCall(PREVIEW_SPREADSHEET_END_POINT, new HashMap<String, String>(), getApikeyParams(), CommonUtils.convertJsonToMapObject(documentPreviewModel.toJson(SERVICE_TYPE.SHEET)), documentPreviewModel.getFileMap(), false);
			
			return response.getResponseAsJSON();
		} catch (IOException e) {}

		return null;
	}
	
	/* public static JSONObject deleteDocument(String documentId) throws Exception {
		
		try {
			HttpResponse response = RestApiUtil.makeApiCall(DELETE_SPREADSHEET_END_POINT.replace("<documentid>", documentId), HttpRequestMethod.DELETE, null, getApikeyParams(), false);

			return response.getResponseAsJSON();
		} catch (IOException e) {}
		
		return null;
	}

	public static JSONObject deleteSession(String sessionId) throws Exception {
		
		try {
			HttpResponse response = RestApiUtil.makeApiCall(DELETE_SESSION_END_POINT.replace("<sessionid>", sessionId), HttpRequestMethod.DELETE, null, getApikeyParams(), false);
			
			return response.getResponseAsJSON();
		} catch (IOException e) {}
		
		return null;
	}*/
	
}
