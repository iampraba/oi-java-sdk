/* $Id$ */
/**
 * @author  Prabakaran R
 * @emailId prabakaran.r@zohocorp.com
 * @since 28-09-2019
 * Utility methods to make request using Java HTTPUrlconnection
 */
package com.zoho.common.util.restapi;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

/**
 * Utility methods to make the actual REST call. The main methods of interest for callers are the makeApiCall overloads. Most others are
 * helpers to perform the low-level operations involved in making the call, such as establishing network connections, etc.
 */
public class RestApiUtil {

    private static final Logger LOGGER = Logger.getLogger(RestApiUtil.class.getName());

    private static final int DEFAULT_CONNECTION_TIMEOUT = 120000;

    private static final int DEFAULT_CONNECTION_READ_TIMEOUT = 120000;

    /**
     * Representation of the HTTP Request methods used by this class.
     */
    public static enum HttpRequestMethod {
        GET,
        POST,
        PUT,
        DELETE;
    }

    /**
     * Representation of common HTTP header fields relevant for the REST API.
     */
    public static enum HttpHeaderField {
        CONTENT_TYPE("Content-Type"), //No I18N
        ACCESS_TOKEN("Access-Token"), //No I18N
        FILE_NAME("File-Name"), //No I18N
        MIME_TYPE("Mime-Type"), //No I18N
        USER_EMAIL("X-User-Email"), //No I18N
        USER_AGENT("User-Agent"), //No I18N
        ACCEPT("ACCEPT"); //No I18N

        private final String fieldName;

        HttpHeaderField(String fieldName) {
            this.fieldName = fieldName;
        }

        @Override
        public String toString() {
            return fieldName;
        }
    }


    /**
     * Representation of common mime types used by this class.
     */
    public static enum MimeType {
        JSON("application/json"), //No I18N
        PDF("application/pdf"), //No I18N
        PLAIN_TEXT("text/plain"), //No I18N
        FORM("application/x-www-form-urlencoded"); //No I18N

        private final String typeString;

        MimeType(String typeString) {
            this.typeString = typeString;
        }

        @Override
        public String toString() {
            return typeString;
        }
    }

    /**
     * List of specific error cases and messages.
     */
    public enum RestError {

        FILE_NOT_FOUND("Cannot find the given file at the specified location."), //No I18N
        INPUT_OUTPUT_EXCEPTION("Unable to perform input output operation on the specified resource."), //No I18N
        GENERAL_PARSING_ERROR("Error while parsing the specified object."), //No I18N
        MALFORMED_URL("Unable to process request as URL is found malformed."), //No I18N
        UNAUTHORIZED("You are not authorized to make this call. Get your access token first."), //No I18N
        GENERAL_ERROR("Error occured while performing specified operation"), //No I18N
        SEND_REMINDER_ERROR("Failure in sending reminders to the next participant of the specified agreement."), //No I18N
        FILE_NOT_SAVED("Error while saving document to the location specified"), //No I18N
        UNSUPPORTED_ENCODING("Unable to process request as encoding type is not supported."); //No I18N

        public final String errMessage;

        RestError(String message) {
            this.errMessage = message;
        }
    }

    /**
     * Invokes the REST API at the specified end point using the specified method and headers.
     *
     * @param apiUrl URL for the API request.
     * @param method HTTP request method for the API request.
     * @param headers HTTP header fields and values required for the API request.
     * @param queryParams Query parameters as key values for the request URL
     * @param isEnableProxy - True, if proxy need to be set for outgoing request, else false(For internal server requests proxy not need).
     * @return {@HttpResponse} http response object with STATUS_CODE & RESPONSE
     *  {
     *      STATUS_CODE: Response status code returned by the API call.
     *      RESPONSE : The body of the HTTP response returned by the API call. (json/byte array depending on the request made Null in case of an error.)
     *  }
     * @throws IOException
     */
    public static HttpResponse makeApiCall(String apiUrl, HttpRequestMethod method, Map<String, String> headers, Map<String, String> queryParams, boolean isEnableProxy) throws IOException {
        String body = null;
        return makeApiCall(apiUrl, method, headers, queryParams, body, isEnableProxy);
    }

    /**
     * Invokes the REST API at the specified end point using the specified method and headers.
     *
     * @param apiUrl URL for the API request.
     * @param method HTTP request method for the API request.
     * @param headers HTTP header fields and values required for the API request.
     * @param queryParams Query parameters as key values for the request URL
     * @param bodyParams Request body parameters as key values
     * @param isEnableProxy - True, if proxy need to be set for outgoing request, else false(For internal server requests proxy not need).
     * @return {@HttpResponse} http response object with STATUS_CODE & RESPONSE
     *  {
     *      STATUS_CODE: Response status code returned by the API call.
     *      RESPONSE : The body of the HTTP response returned by the API call. (json/byte array depending on the request made Null in case of an error.)
     *  }
     * @throws IOException
     */
    public static HttpResponse makeApiCall(String apiUrl, HttpRequestMethod method, Map<String, String> headers, Map<String, String> queryParams, Map<String, String> bodyParams, boolean isEnableProxy) throws IOException {
        return makeApiCall(apiUrl, method, headers, queryParams, construstRequestParams(bodyParams), isEnableProxy);
    }

    /**
     * Invokes the REST API at the specified end point using the specified method and headers.
     *
     * @param apiUrl URL for the API request.
     * @param method HTTP request method for the API request.
     * @param headers HTTP header fields and values required for the API request.
     * @param body Request body string
     * @param isEnableProxy - True, if proxy need to be set for outgoing request, else false(For internal server requests proxy not need).
     * @return {@HttpResponse} http response object with STATUS_CODE & RESPONSE
     *  {
     *      STATUS_CODE: Response status code returned by the API call.
     *      RESPONSE : The body of the HTTP response returned by the API call. (json/byte array depending on the request made Null in case of an error.)
     *  }
     * @throws IOException
     */
    public static HttpResponse makeApiCall(String apiUrl, HttpRequestMethod method, Map<String, String> headers, Map<String, String> queryParams, String body, boolean isEnableProxy) throws IOException {

        return makeApiCall(modifyUrlUsingQueryParams(queryParams, apiUrl), method, headers, body, isEnableProxy);
    }

    /**
     * Invokes the REST API at the specified end point using the specified method and headers and an optional body.
     *
     * @param apiUrl URL for the API request.
     * @param method HTTP request method for the API request.
     * @param headers HTTP header fields and values required for the API request.
     * @param body Optional HTTP request body for the API request. May be null.
     * @param isEnableProxy - True, if proxy need to be set for outgoing request, else false(For internal server requests proxy not need).
     * @return {@HttpResponse} http response object with STATUS_CODE & RESPONSE
     *  {
     *      STATUS_CODE: Response status code returned by the API call.
     *      RESPONSE : The body of the HTTP response returned by the API call. (json/byte array depending on the request made Null in case of an error.)
     *  }
     * @throws IOException
     */
    public static HttpResponse makeApiCall(String apiUrl, HttpRequestMethod method, Map<String, String> headers, String body, boolean isEnableProxy) throws IOException {

        HttpURLConnection connection = createRequest(apiUrl, method, headers, body, isEnableProxy); // Open an HTTPS connection in preparation for the call.

        return executeRequest( connection );
    }

    /**
     * Invokes any REST API end point that accepts a file as a payload. The file is sent in a multi-part request body using the specified
     * headers.
     *
     * @param apiUrl URL for the API request.
     * @param headers HTTP header fields and values required for the API request.
     * @param fileMetaData Meta-data associated with the file, to be used as parts in the multi-part request body.
     * @param fileToUpload reference to the file being uploaded.
     * @param isEnableProxy - True, if proxy need to be set for outgoing request, else false(For internal server requests proxy not need).
     * @return {@HttpResponse} http response object with STATUS_CODE & RESPONSE
     *  {
     *      STATUS_CODE: Response status code returned by the API call.
     *      RESPONSE : The body of the HTTP response returned by the API call. (json/byte array depending on the request made Null in case of an error.)
     *  }
     * @throws IOException
     */
    public static HttpResponse makeApiCall(String apiUrl, Map<String, String> headers, Map<String, String> queryParams, Map<String, String> formData, Map<String, MultiPartFile> fileObjects, boolean isEnableProxy ) throws IOException {

        return makeApiCall(modifyUrlUsingQueryParams(queryParams, apiUrl), headers, formData, fileObjects, isEnableProxy);
    }

    /**
     * Invokes any REST API end point that accepts a file as a payload. The file is sent in a multi-part request body using the specified
     * headers.
     *
     * @param apiUrl URL for the API request.
     * @param headers HTTP header fields and values required for the API request.
     * @param fileMetaData Meta-data associated with the file, to be used as parts in the multi-part request body.
     * @param fileToUpload reference to the file being uploaded.
     * @param isEnableProxy - True, if proxy need to be set for outgoing request, else false(For internal server requests proxy not need).
     * @return {@HttpResponse} http response object with STATUS_CODE & RESPONSE
     *  {
     *      STATUS_CODE: Response status code returned by the API call.
     *      RESPONSE : The body of the HTTP response returned by the API call. (json/byte array depending on the request made Null in case of an error.)
     *  }
     * @throws IOException
     */
    public static HttpResponse makeApiCall(String apiUrl, Map<String, String> headers, Map<String, String> formData, Map<String, MultiPartFile> fileObjects, boolean isEnableProxy ) throws IOException {

        HttpURLConnection connection = createMultiPartRequest(apiUrl, headers, formData, fileObjects, isEnableProxy); // Open an HTTPS connection in preparation for the call.

        return executeRequest( connection );
    }

    /**
     * Prepares an HTTP request for calling the specified API URL. Does not actually make the request.
     *
     * @param apiUrl URL for the API request.
     * @param method HTTP request method for the API request.
     * @param headers HTTP header fields and values required for the API request.
     * @param body Optional HTTP request body. May be null.
     * @param isEnableProxy - True, if proxy need to be set for outgoing request, else false(For internal server requests proxy not need).
     * @return The URL connection object if successful, not yet connected.
     * @throws IOException
     */
    private static HttpURLConnection createRequest(String apiUrl, HttpRequestMethod method, Map<String, String> headers, String body, boolean isEnableProxy) throws IOException {

        HttpURLConnection conn = null; // Initiate connection to URL
        boolean allGood = false;

        try {
            conn = openConnection(apiUrl, isEnableProxy);
            if (conn != null) {
                conn.setRequestMethod(method.toString()); // Set the HTTP Request method.
                if ( headers != null ) {
                    for (Entry<String, String> map : headers.entrySet()) {
                        conn.setRequestProperty(map.getKey(), map.getValue()); // Add header field/value pairs to the API request.
                    }
                }
                if (body != null) {
                    setRequestBody(conn, body);// Set body for the API call if one is specified
                }

                conn.setConnectTimeout(DEFAULT_CONNECTION_TIMEOUT);
                conn.setReadTimeout(DEFAULT_CONNECTION_READ_TIMEOUT);
                allGood = true; //If we reached here no exceptions were thrown.
                LOGGER.info( "Request object successfully created. " ); //No I18N
            }
        }
        catch (MalformedURLException e) {
            LOGGER.warning("URL Malfunctioned Exception :: " + RestError.MALFORMED_URL.errMessage + " :: Message :: " + e.getMessage()); //No I18N
            throw e;
        }
        catch (IOException e) {
            LOGGER.warning("IO Exception :: " + RestError.INPUT_OUTPUT_EXCEPTION.errMessage + " :: Message :: " + e.getMessage()); //No I18N
            throw e;
        }
        finally {
            // Disconnect in the face of any exception. We could have simply caught Exception and rethrown that, but the
            // method does not declare that it throws general Exceptions (since it in fact does not explicitly do so).
            if (!allGood && conn != null) {
              conn.disconnect();
            }
        }
        return conn;
    }

    /**
     * Prepares a multi-part HTTP request (containing a file to upload) for calling the specified API URL. Does not actually make the request.
     * The request method is always POST.
     *
     * @param apiUrl URL for the API request.
     * @param headers HTTP header fields and values required for the API request.
     * @param fileMetaData Meta-data associated with the file, to be used as parts in the multi-part request body.
     * @param fileToUpload Reference to the file being uploaded.
     * @param isEnableProxy - True, if proxy need to be set for outgoing request, else false(For internal server requests proxy not need).
     * @return The URL connection object if successful, not yet connected.
     * @throws IOException
     */
    private static HttpURLConnection createMultiPartRequest(String apiUrl, Map<String, String> headers, Map<String, String> formData, Map<String, MultiPartFile> fileObjects, boolean isEnableProxy) throws IOException {
        HttpURLConnection connection = createRequest(apiUrl, HttpRequestMethod.POST, headers, null, isEnableProxy);

        OutputStream outputStream = null; // Output stream of the connection.

        try {
            MultiPartRequestBody multiPartBody = new MultiPartRequestBody(); // Create an encapsulation of the request body with multi-part content.
            final String contentType = "multipart/form-data; boundary=" + multiPartBody.getBoundary(); //No I18N

            connection.setRequestProperty(HttpHeaderField.CONTENT_TYPE.toString(), contentType); // Set the Content-Type header.

            outputStream = connection.getOutputStream(); // Give the multi-part body a stream to write to.
            multiPartBody.setOutputStream(outputStream);

            if ( formData != null ) {
                LOGGER.info( "Adding mulitpart from data. Total params :: " + formData.size() ); //No I18N
                for ( Map.Entry< String, String > dataItem : formData.entrySet() )
                {
                    multiPartBody.addMetaPart( dataItem.getKey(), dataItem.getValue()  );
                }
            }
            if ( fileObjects != null ) {
                LOGGER.info( "Adding mulitpartfile. Total files :: " + fileObjects.size() ); //No I18N
                for ( Map.Entry< String, MultiPartFile > fileObject : fileObjects.entrySet() )
                {
                    multiPartBody.addFilePart(fileObject.getKey(), fileObject.getValue());
                }
            }
            multiPartBody.endBody(); // Mark the multi-part request body as completed.
            LOGGER.info( "MultiPartBody successfully constructed. " ); //No I18N
        }
        catch (UnsupportedEncodingException e) {
            LOGGER.warning("Exception :: " + RestError.UNSUPPORTED_ENCODING.errMessage + " :: Message :: " + e.getMessage()); //No I18N
            throw e;
        }
        catch (IOException e) {
            LOGGER.warning("Exception :: " + RestError.INPUT_OUTPUT_EXCEPTION.errMessage + " :: Message :: " + e.getMessage()); //No I18N
            throw e;
        }
        finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
        return connection;
    }

    /**
     * Executes the API call associated with the specified connection object.
     *
     * @param conn HttpsURLConnection object associated with the request.
     * @return JSONObject with status code & response.
     *  {
     *      STATUS_CODE: Response status code returned by the API call.
     *      RESPONSE : The body of the HTTP response returned by the API call. (json/byte array depending on the request made Null in case of an error.)
     *  }
     * @throws IOException
     */
    private static HttpResponse executeRequest(HttpURLConnection conn) throws IOException {
        int statusCode = 500;
        HttpResponse httpResponse = new HttpResponse();

        try {
            conn.connect(); // Open the URL connection, and await a response.
            statusCode = conn.getResponseCode();
            LOGGER.info( "Response statusCode :: " + statusCode  ); //No I18N
            httpResponse.setResponseBytes(getResponseFromRequestConnection(conn, statusCode));
        }
        catch (IOException e) {
            LOGGER.warning("Exception :: " + RestError.INPUT_OUTPUT_EXCEPTION.errMessage + " :: Message :: " + e.getMessage()); //No I18N
        } finally {
            if ( conn != null ) {
                conn.disconnect();
            }
            httpResponse.setStatusCode(statusCode);
        }
        return httpResponse;
    }

    /**
     * Given an established successful connection, returns the body of the response as either a JSON object or a byte array by reading the
     * connection's regular input stream.
     *
     * @param conn HttpsURLConnection object associated with the request. Connection must already have been established.
     * @return Body of the HTTP response, as either a JSON object or byte array.
     * @throws IOException
     */
    private static byte[] getResponseFromRequestConnection(HttpURLConnection conn, int statusCode) throws IOException {
        byte[] responseObj = null;
        InputStream connStream = null;

        try {
             /** Check for successful API invocation.(i.e status code falls between 200 to 299)
              * If Success, Get the response body using the connection's regular input stream.
              * If Error, Get the error response using the connection's error stream.
              */
            connStream = ( statusCode >= 200 && statusCode <= 299 ) ? conn.getInputStream(): conn.getErrorStream();
            responseObj = getResponseBytes(conn, connStream, statusCode);
        }
        catch (UnsupportedEncodingException e) {
            LOGGER.warning("Exception :: " + RestError.UNSUPPORTED_ENCODING.errMessage + " :: Message :: " + e.getMessage()); //No I18N
            throw e;
        }
        catch (IOException e) {
            LOGGER.warning("Exception :: " + RestError.INPUT_OUTPUT_EXCEPTION.errMessage + " :: Message :: " + e.getMessage()); //No I18N
            throw e;
        }
        finally {
            if (connStream != null) {
                connStream.close();
            }
        }
        return responseObj;
    }

    /**
     * Converts the specified string to bytes using UTF-8 encoding and sets the result as the body of the HTTPS request going out.
     *
     * @param conn The HTTP request URL connection object.
     * @param body String to set as the body for the HTTP request.
     * @throws IOException
     */
    private static void setRequestBody(HttpURLConnection conn, String body) throws IOException {
        DataOutputStream outStream = null;
        try {
            outStream = new DataOutputStream(conn.getOutputStream());
            outStream.write(body.getBytes("UTF-8")); //No I18N
            LOGGER.info( "Request body successfully added. Body length :: " + body.length()  ); //No I18N
        }
        catch (IOException e) {
            LOGGER.warning("Exception :: " + RestError.INPUT_OUTPUT_EXCEPTION.errMessage + " :: Message :: " + e.getMessage()); //No I18N
            throw e;
        }
        finally {
            if (outStream != null) {
                outStream.close();
            }
        }
    }

    /**
     * Get the response body from the API call as a byte array.
     *
     * @param conn The HTTP request URL connection object.
     * @param connStream The connection stream to use.
     * @return The response body.
     * @throws IOException
     */
    private static byte[] getResponseBytes(HttpURLConnection conn, InputStream connStream, int statusCode) throws IOException {
        byte[] responseBytes = new byte[4096];
        InputStream inputStream = new BufferedInputStream(connStream);
        int totalBytesRead = 0;

        try {
            int bytesRead = inputStream.read(responseBytes);

            // Read all the bytes of the connection stream. Since it may happen that the content length is unknown we must keep reallocating our
            // buffer as we encounter new data. Note that this may not be the most efficient way to do this.
            while (bytesRead >= 0) {
                totalBytesRead += bytesRead;

                // Allocate a larger byte array for the next read operation.
                responseBytes = Arrays.copyOf(responseBytes, totalBytesRead + 4096);

                // Read into the re-allocated buffer.
                bytesRead = inputStream.read(responseBytes, totalBytesRead, responseBytes.length - totalBytesRead);
            }

            // Final reallocation to return an array that is precisely the length of the data that was read in.
            responseBytes = Arrays.copyOfRange(responseBytes, 0, totalBytesRead);
            LOGGER.info("Total byte read form connection stream :: " + totalBytesRead  ); //No I18N

            if ( statusCode < 200 && statusCode > 299 && totalBytesRead > 0  ) {
                LOGGER.warning( "Error response :: " + new String( responseBytes )  ); //No I18N
            }
        }
        catch (IOException e) {
            LOGGER.warning("Exception :: " + RestError.INPUT_OUTPUT_EXCEPTION.errMessage + " :: Message :: " + e.getMessage()); //No I18N
            throw e;
        }

        String as = new String(responseBytes);
        return responseBytes;
    }

    /**
     * Set up an HTTPS connection to the specified URL.
     *
     * @param apiUrl URL for the API request.
     * @param isEnableProxy - True, if proxy need to be set for outgoing request, else false(For internal server requests proxy not need).
     * @return True on success, false otherwise.
     * @throws MalformedURLException
     * @throws IOException
     */
    private static HttpURLConnection openConnection(String apiUrl, boolean isEnableProxy) throws MalformedURLException, IOException {
      HttpURLConnection conn = null;
        try {
            URL url = new URL(apiUrl);
            conn = (HttpURLConnection) url.openConnection();
            LOGGER.info("HttpURLConnection successfully opened." ); //No I18N
        }
        catch (MalformedURLException e) {
            LOGGER.warning("URL Malfunctioned Exception :: " + RestError.MALFORMED_URL.errMessage + " :: Message :: " + e.getMessage()); //No I18N
            throw e;
        }
        catch (IOException e) {
            LOGGER.warning("Exception :: " + RestError.INPUT_OUTPUT_EXCEPTION.errMessage + " :: Message :: " + e.getMessage()); //No I18N
            throw e;
        }

        if (conn != null) {
            // This connection is used for both input and output.
            conn.setDoInput(true);
            conn.setDoOutput(true);
        }
        return conn;
    }

    /**
     * Adds the query parameters to the URL.
     *
     * @param queryParams
     *          Map containing the query parameters and their corresponding values to be used for modifying the given URL.
     * @param url
     *          URL need to be modified.
     * @return Modified URL containing the query parameters and corresponding value appended
     *
     */
    public static String modifyUrlUsingQueryParams(Map<String, String> queryParams, String url)
    {
        if ( queryParams != null && queryParams.size() > 0 ) {
            String keyValuePairStr = construstRequestParams(queryParams);

            if ( keyValuePairStr != null && keyValuePairStr.length() > 0 ) {
                url = ( url + "?" + keyValuePairStr );
            }
        }
        return url;
    }

    /**
     * Method to get params request string as key value pair.
     * @param params Map containing the query parameters and their corresponding values to be used for modifying the given URL.
     * @return String containing key, value pair to send in post or get request. (Ex. key1=value1&key2=value2&etc..,)
     */
    public static String construstRequestParams(Map<String, String> params) {
        StringBuilder paramsString = new StringBuilder();

        if ( params == null || params.size() == 0 ) {
            return null;
        }
        for (Map.Entry<String, String> queryParam : params.entrySet()) {
              paramsString.append(queryParam.getKey()).append("=")
                  .append(queryParam.getValue()).append("&");
        }

        return paramsString.substring(0, paramsString.length()-1);
    }

    }
