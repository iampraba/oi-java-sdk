//$Id$
package com.zoho.officeintegrator.sdk.demos;

import java.io.File;

import org.json.JSONObject;

import com.zoho.common.util.FileUtils;
import com.zoho.common.util.restapi.MultiPartFile;
import com.zoho.officeintegrator.sdk.constants.Constants.PARAMS;
import com.zoho.officeintegrator.sdk.constants.FileConstants.FILE_EXTENSION;
import com.zoho.officeintegrator.sdk.controllers.ShowController;
import com.zoho.officeintegrator.sdk.models.request.DocumentConvertModel;
import com.zoho.officeintegrator.sdk.models.request.DocumentCreateModel;
import com.zoho.officeintegrator.sdk.models.request.DocumentEditModel;
import com.zoho.officeintegrator.sdk.models.request.DocumentPreviewModel;

public class ShowDemo {

	public static final String DEMO_FILE_PATH =  "/Users/praba-2086/Desktop/ZohoShow.pptx";
	
	public static final String CONVERTED_FILE_PATH =  "/Users/praba-2086/Desktop/ZohoShow.pdf";
	
	public static void run() throws Exception {
		
        File demoFile = new File(DEMO_FILE_PATH);

        DocumentCreateModel createModel = new DocumentCreateModel();
        JSONObject createResponse = ShowController.createDocument(new DocumentCreateModel());
        
        
        DocumentEditModel editModel = new  DocumentEditModel();

        editModel.getDocumentInfo().setDocumentId("1000");
        editModel.getSourceDocument().setDocument(new MultiPartFile(demoFile));

        JSONObject editResponse = ShowController.editDocument(editModel);
        
        
        DocumentEditModel coEditModel = new  DocumentEditModel();
        
        coEditModel.getDocumentInfo().setDocumentId("1000");
        
        JSONObject coEditResponse = ShowController.coEditDocument(coEditModel);
        
        
        DocumentPreviewModel documentPreviewModel = new DocumentPreviewModel();
        
        documentPreviewModel.getSourceDocument().setDocument(new MultiPartFile(demoFile));
        
        JSONObject previewresponse = ShowController.previewDocument(documentPreviewModel);

        
        DocumentConvertModel convertModel = new DocumentConvertModel();
        
        convertModel.setFormat(FILE_EXTENSION.PDF);
        convertModel.getSourceDocument().setDocument(new MultiPartFile(demoFile));
        
        byte[] convertedRepsonse = ShowController.convertDocument(convertModel);
        
        FileUtils.writeFile(CONVERTED_FILE_PATH, convertedRepsonse);

        
        JSONObject sessionDeleteresponse = ShowController.deleteSession(coEditResponse.getString(PARAMS.SESSION_ID));

        
        JSONObject deleteresponse = ShowController.deleteDocument(coEditResponse.getString(PARAMS.DOCUMENT_ID));

		return;
	}
}
