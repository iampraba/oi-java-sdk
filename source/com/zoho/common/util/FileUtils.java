//$Id$
package com.zoho.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.zoho.officeintegrator.sdk.constants.FileConstants;
import com.zoho.officeintegrator.sdk.constants.FileConstants.CONTENT_TYPE;

public class FileUtils {

	/**
     * This method uses java.io.FileInputStream to read
     * file content into a byte array
     * @param file
     * @return
     */
    public static byte[] readFileToByteArray(File file){
        FileInputStream fis = null;
        // Creating a byte array using the length of the file
        // file.length returns long which is cast to int
        byte[] bArray = new byte[(int) file.length()];
        try{
            fis = new FileInputStream(file);
            fis.read(bArray);
            fis.close();        
            
        }catch(IOException ioExp){
            ioExp.printStackTrace();
        }
        return bArray;
    }
    
    public static String getFileMimeType(File file) {
		String fileName = file.getName();
		int index = fileName.lastIndexOf(".");
		String fileExtension = fileName.substring(index + 1, fileName.length());
		
		return FileConstants.GET_CONTENTTYPE_FOR_EXTENSION.containsKey(fileExtension) ? FileConstants.GET_CONTENTTYPE_FOR_EXTENSION.get(fileExtension): CONTENT_TYPE.TEXT;
    }
    
    public static void writeFile( String filePath, byte[] fileBytes ) {
        FileOutputStream fos = null;

        if ( filePath == null || fileBytes == null ) { return; }
        try {
            File f = new File(filePath);
            if (f.exists()) { f.delete(); }
            fos = new FileOutputStream(f);
            fos.write(fileBytes);
            fos.flush();
        } catch (Exception e){} finally {
            try {
                if (fos != null ) {
                    fos.close();
                }
            } catch (Exception e1) {}
        }
    }

}
