/* $Id$ */
/**
 * @author  Prabakaran R
 * @emailId prabakaran.r@zohocorp.com
 * @since 07-08-2017
 */
package com.zoho.common.util.restapi;

import java.io.File;

import com.zoho.common.util.FileUtils;

/**
 * Rudimentary helper class used to construct a multi-part file body object for multi-part request.
 */
public class MultiPartFile {

    private String fileName;

    private String mimeType;

    private byte[] fileContent;

    /**
     * Constructor to create file object for multipart file object.
     *
     * @param fileName - {String} Name of the file content
     * @param mimeType - {String} Mime type of the file
     * @param fileContent - {bytes[]} Bytes array of file content
     */
    public MultiPartFile(String fileName, String mimeType, byte[] fileContent) {
        this.fileName = fileName;
        this.mimeType = mimeType;
        this.fileContent = fileContent;
    }

    /**
     * Constructor to create file object for multipart file object.
     *
     * @param fileName - {String} Name of the file content
     * @param mimeType - {String} Mime type of the file
     * @param fileContent - {bytes[]} Bytes array of file content
     */
    public MultiPartFile(File file) {
        this.fileName = file.getName();
        this.mimeType = FileUtils.getFileMimeType(file);
        this.fileContent = FileUtils.readFileToByteArray(file);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public byte[] getFileContent() {
        return this.fileContent == null ? "".getBytes(): this.fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }

    public int getFileContentLength() {
        return ( this.fileContent == null ? 0: this.fileContent.length );
    }
}
