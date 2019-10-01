/* $Id$ */
/**
 * @author  Prabakaran R
 * @emailId prabakaran.r@zohocorp.com
 * @since 28-09-2019
 */
package com.zoho.common.util.restapi;

import org.json.JSONObject;

public class HttpResponse {

    private int statusCode;

    private byte[] responseBytes;

    /**
     * @return Returns response status code of the actual HTTP response. if some internal exception happened, Default 500 would be returned.
     */
    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * @return Returns response bytes. if some internal exception happened, Default null would be returned.
     */
    public byte[] getResponseBytes() {
        return responseBytes;
    }

    /**
     * @return Returns response bytes. if some internal exception happened, Default null would be returned.
     */
    public JSONObject getResponseAsJSON() {
        try {
            if (this.getResponseBytesLength() == 0) {
                return null;
            }
            return new JSONObject(new String(this.responseBytes, "UTF-8")); //No I18N
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

    public void setResponseBytes(byte[] responseBytes) {
        this.responseBytes = responseBytes;
    }

    /**
     * @return Returns response bytes length. If response not available(i.e response bytes null or empty), then 0 will be returned as default value.
     */
    public int getResponseBytesLength() {
        return this.responseBytes != null ? this.responseBytes.length: 0;
    }

    public HttpResponse() {
        statusCode = 500;
        responseBytes = null;
    }

    /**
     * @return Returns true if response status code between 200 to 299. Or else returns false.
     */
    public boolean isRequestSuccess() {
        return statusCode >= 200 && statusCode <= 299;
    }

}
