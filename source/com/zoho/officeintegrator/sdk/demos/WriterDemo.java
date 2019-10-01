//$Id$
package com.zoho.officeintegrator.sdk.demos;

import java.io.File;

import org.json.JSONObject;

import com.zoho.common.util.FileUtils;
import com.zoho.common.util.restapi.MultiPartFile;
import com.zoho.officeintegrator.sdk.constants.Constants.PARAMS;
import com.zoho.officeintegrator.sdk.controllers.WriterController;
import com.zoho.officeintegrator.sdk.models.base.SourceDocument;
import com.zoho.officeintegrator.sdk.models.request.DocumentCompareModel;
import com.zoho.officeintegrator.sdk.models.request.DocumentConvertModel;
import com.zoho.officeintegrator.sdk.models.request.DocumentCreateModel;
import com.zoho.officeintegrator.sdk.models.request.DocumentEditModel;
import com.zoho.officeintegrator.sdk.models.request.DocumentPreviewModel;

public class WriterDemo {

	public static final String DEMO_FILE_PATH_1 =  "/Users/praba-2086/Desktop/test.docx";
	
	public static final String DEMO_FILE_PATH_2 =  "/Users/praba-2086/Desktop/test1.docx";

	public static final String CONVERTED_FILE_PATH =  "/Users/praba-2086/Desktop/test1.pdf";
	
	public static void run() throws Exception {
		
        File demoFile = new File(DEMO_FILE_PATH_1);
        File demoFile1 = new File(DEMO_FILE_PATH_2);

        DocumentCreateModel createModel = new DocumentCreateModel();
        
        JSONObject createResponse = WriterController.createDocument(new DocumentCreateModel());


        DocumentEditModel editModel = new  DocumentEditModel();

        editModel.getDocumentInfo().setDocumentId("1000");
        editModel.getSourceDocument().setDocument(new MultiPartFile(demoFile));

        JSONObject editResponse = WriterController.editDocument(editModel);
        
        
        DocumentEditModel coEditModel = new  DocumentEditModel();
        
        coEditModel.getDocumentInfo().setDocumentId("1000");
        
        JSONObject coEditResponse = WriterController.coEditDocument(coEditModel);
        
        
        DocumentPreviewModel documentPreviewModel = new DocumentPreviewModel();
        
        documentPreviewModel.getSourceDocument().setDocument(new MultiPartFile(demoFile));
        
        JSONObject previewresponse = WriterController.previewDocument(documentPreviewModel);

        
        DocumentConvertModel convertModel = new DocumentConvertModel();
        
        convertModel.getSourceDocument().setDocument(new MultiPartFile(demoFile));
        
        byte[] convertedRepsonse = WriterController.convertDocument(convertModel);
        
        FileUtils.writeFile(CONVERTED_FILE_PATH, convertedRepsonse);


        DocumentCompareModel compareModel = new DocumentCompareModel();
        
        compareModel.setSourceDocument1(new SourceDocument().setDocument(new MultiPartFile(demoFile)));
        compareModel.setSourceDocument2(new SourceDocument().setDocument(new MultiPartFile(demoFile1)));

        JSONObject compareRepsonse = WriterController.compareDocument(compareModel);


        JSONObject sessionDeleteresponse = WriterController.deleteSession(coEditResponse.getString(PARAMS.SESSION_ID));

        
        JSONObject deleteresponse = WriterController.deleteDocument(coEditResponse.getString(PARAMS.DOCUMENT_ID));

		return;
	}
}
