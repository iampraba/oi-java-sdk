//$Id$
package com.zoho.officeintegrator.sdk.controllers;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONObject;

import com.zoho.common.util.CommonUtils;
import com.zoho.common.util.restapi.HttpResponse;
import com.zoho.common.util.restapi.RestApiUtil;
import com.zoho.common.util.restapi.RestApiUtil.HttpRequestMethod;
import com.zoho.officeintegrator.sdk.configurations.AppConfiguration;
import com.zoho.officeintegrator.sdk.constants.Constants.SERVICE_TYPE;
import com.zoho.officeintegrator.sdk.models.request.DocumentConvertModel;
import com.zoho.officeintegrator.sdk.models.request.DocumentCreateModel;
import com.zoho.officeintegrator.sdk.models.request.DocumentEditModel;
import com.zoho.officeintegrator.sdk.models.request.DocumentPreviewModel;

public class ShowController extends BaseController {
	
	private static final String CREATE_PRESENTATION_END_POINT = AppConfiguration.getShowServerUrl() + "/show/officeapi/v1/presentation";
	
	private static final String PREVIEW_PRESENTATION_END_POINT = AppConfiguration.getShowServerUrl() + "/show/officeapi/v1/presentation/preview";

	private static final String CONVERT_PRESENTATION_END_POINT = AppConfiguration.getShowServerUrl() + "/show/officeapi/v1/presentation/convert";

	private static final String DELETE_PRESENTATION_END_POINT = AppConfiguration.getShowServerUrl() + "/show/officeapi/v1/presentation/<presentationid>";

	private static final String DELETE_SESSION_END_POINT = AppConfiguration.getShowServerUrl() + "/show/officeapi/v1/session/<sessionid>";
	
	public static JSONObject createDocument(DocumentCreateModel createModel) {
		
		try {
			HttpResponse response = RestApiUtil.makeApiCall(CREATE_PRESENTATION_END_POINT, HttpRequestMethod.POST, new HashMap<String, String>(), getApikeyParams(), CommonUtils.convertJsonToMapObject(createModel.toJson(SERVICE_TYPE.SHOW)), false);
			
			return response.getResponseAsJSON();
		} catch (IOException e) {}
		return null;
	}

	public static JSONObject editDocument(DocumentEditModel documentEditModel) throws Exception {
		
		try {
			if ( !documentEditModel.getSourceDocument().hasDocumentToEdit() ) {
				throw new Exception("No document or document url persent to edit the document.");
			}

			HttpResponse response = RestApiUtil.makeApiCall(CREATE_PRESENTATION_END_POINT, new HashMap<String, String>(), getApikeyParams(), CommonUtils.convertJsonToMapObject(documentEditModel.toJson(SERVICE_TYPE.SHOW)), documentEditModel.getFileMap(), false);

			return response.getResponseAsJSON();
		} catch (IOException e) {}
		return null;
	}

	public static JSONObject coEditDocument(DocumentEditModel documentEditModel) throws Exception {
		
		try {
			if ( documentEditModel.getDocumentInfo().getDocumentId() == null ) {
				throw new Exception("Mandarory parameter Document id not persent to open document in collaboration mode.");
			}
			
			HttpResponse response = RestApiUtil.makeApiCall(CREATE_PRESENTATION_END_POINT, new HashMap<String, String>(), getApikeyParams(), CommonUtils.convertJsonToMapObject(documentEditModel.toJson(SERVICE_TYPE.SHOW)), documentEditModel.getFileMap(), false);
			
			return response.getResponseAsJSON();
		} catch (IOException e) {}
		return null;
	}
	

	public static JSONObject previewDocument(DocumentPreviewModel documentPreviewModel) throws Exception {

		try {
			if ( !documentPreviewModel.getSourceDocument().hasDocumentToEdit() ) {
				throw new Exception("No document or document url persent to preview.");
			}
			HttpResponse response = RestApiUtil.makeApiCall(PREVIEW_PRESENTATION_END_POINT, new HashMap<String, String>(), getApikeyParams(), CommonUtils.convertJsonToMapObject(documentPreviewModel.toJson(SERVICE_TYPE.SHOW)), documentPreviewModel.getFileMap(), false);
			
			return response.getResponseAsJSON();
		} catch (IOException e) {}

		return null;
	}
	
	public static byte[] convertDocument(DocumentConvertModel documentConvertModel) throws Exception {
		
		try {

			if ( !documentConvertModel.getSourceDocument().hasDocumentToEdit() ) {
				throw new Exception("No document or document url persent to convert.");
			}

			HttpResponse response = RestApiUtil.makeApiCall(CONVERT_PRESENTATION_END_POINT, new HashMap<String, String>(), getApikeyParams(), CommonUtils.convertJsonToMapObject(documentConvertModel.toJson(SERVICE_TYPE.SHOW)), documentConvertModel.getSourceDocument().getFileMap(), false);

			return response.getResponseBytes();
		} catch (IOException e) {}
		
		return null;
	}

	public static JSONObject deleteDocument(String presentationId) throws Exception {
		
		try {
			HttpResponse response = RestApiUtil.makeApiCall(DELETE_PRESENTATION_END_POINT.replace("<presentationid>", presentationId), HttpRequestMethod.DELETE, null, getApikeyParams(), false);

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
	}
	

}
