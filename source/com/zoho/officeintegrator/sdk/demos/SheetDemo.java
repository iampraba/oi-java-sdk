//$Id$
package com.zoho.officeintegrator.sdk.demos;

import java.io.File;

import org.json.JSONObject;

import com.zoho.common.util.restapi.MultiPartFile;
import com.zoho.officeintegrator.sdk.constants.Constants.PARAMS;
import com.zoho.officeintegrator.sdk.controllers.SheetController;
import com.zoho.officeintegrator.sdk.models.request.DocumentCreateModel;
import com.zoho.officeintegrator.sdk.models.request.DocumentEditModel;
import com.zoho.officeintegrator.sdk.models.request.DocumentPreviewModel;

public class SheetDemo {

	public static final String DEMO_FILE_PATH =  "/Users/praba-2086/Desktop/ZohoSheet.xlsx";
	
	public static final String CONVERTED_FILE_PATH =  "/Users/praba-2086/Desktop/ZohoSheet.pdf";
	
	public static void run() throws Exception {
		
        File demoFile = new File(DEMO_FILE_PATH);

        DocumentCreateModel createModel = new DocumentCreateModel();
        JSONObject createResponse = SheetController.createDocument(new DocumentCreateModel());
        
        
        DocumentEditModel editModel = new  DocumentEditModel();

        editModel.getDocumentInfo().setDocumentId("1000");
        editModel.getSourceDocument().setDocument(new MultiPartFile(demoFile));

        JSONObject editResponse = SheetController.editDocument(editModel);

        
        DocumentEditModel coEditModel = new  DocumentEditModel();
        
        coEditModel.getDocumentInfo().setDocumentId("1000");
        
        JSONObject coEditResponse = SheetController.coEditDocument(coEditModel);
        
        
        DocumentPreviewModel documentPreviewModel = new DocumentPreviewModel();
        
        documentPreviewModel.getSourceDocument().setDocument(new MultiPartFile(demoFile));
        
        JSONObject previewresponse = SheetController.previewDocument(documentPreviewModel);

        
        /*JSONObject sessionDeleteresponse = SheetController.deleteSession(editResponse.optString(PARAMS.SESSION_ID));

        
        JSONObject deleteresponse = SheetController.deleteDocument(coEditResponse.optString(PARAMS.DOCUMENT_ID));*/

		return;
	}
}
