/* $Id$ */
/**
 * @author  Prabakaran R
 * @emailId prabakaran.r@zohocorp.com
 * @since 28-09-2019
 */
package com.zoho.common.util.restapi;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Rudimentary helper class used to construct a multi-part body for use in an
 * HTTP POST request containing a file as the payload. Note that the output
 * stream of the HTTP connection passed into the constructor is not closed by
 * this class. The caller must close it when done.
 *
 * <p>
 * Other than populating the request body, callers also need to set the
 * multi-part boundary as an HTTP header. However this can only be done before
 * retrieving the connection's output stream. So the workflow should be:
 * <ol>
 * <li>Create an instance of this class.</li>
 * <li>Retrieve the boundary generated, using {@link #getBoundary()}, for use in
 * the HTTP header.</li>
 * <li>Open the output stream on the connection and associate it with this class
 * instance using {@link #setOutputStream(OutputStream)}.</li>
 * <li>Call the various add methods as required.</li>
 * <li>End with a call to {@link #endBody()}.</li>
 * </ol>
 * </p>
 */
public class MultiPartRequestBody {

    private static final Logger LOGGER = Logger.getLogger(MultiPartRequestBody.class.getName());

    // The multi-part boundary value to use.
    private final String boundary = "MultiPartFileBoundary" + Long.toHexString(System.currentTimeMillis()); //No I18N

    // The writer that writes to the output stream.
    private MultiPartWriter writer;

    /**
     * Returns the boundary generated for use by the current instance of this
     * class.
     */
    public String getBoundary() {
        return boundary;
    }

    /**
     * Sets the HTTP output stream for use by this class.
     *
     * @param outStream
     *            Output stream of the HTTP connection. Callers must close the
     *            output stream themselves when done.
     * @throws IOException
     */
    public void setOutputStream(OutputStream outStream) throws IOException {
        writer = new MultiPartWriter(outStream);
    }

    /**
     * Adds a simple part to this multi-part message. In our usage this is
     * typically meta-data associated with the file we want to send. The name
     * and value should only contain characters in the ASCII character set.
     *
     * @param name
     *            The name to be given to the part.
     * @param value
     *            The value of the part.
     * @throws IOException
     */
    public void addMetaPart(String name, String value) throws IOException {
        writeBoundary(false);
        writer.println("Content-Disposition: form-data; name=\"" + name + "\""); //No I18N
        writer.println();
        writer.print(value); // don't add a line separator here since that would be considered a part of the value.
    }

    /**
     * Adds a file to this multi-part message.
     *
     * @param name
     *            The name to be given to the part.
     * @param fileObject
     *            - MultiPartFile containing file content and meta data about
     *            the file object
     * @throws IOException
     */
    public void addFilePart(String name, MultiPartFile fileObject)
            throws IOException {
        try {
            writeBoundary(false);
            // Add the headers of the file part.
            writer.println("Content-Disposition: form-data; name=\"" + name + "\"; filename=\"" + fileObject.getFileName() + "\""); // No I18N
            writer.println("Content-Type: " + fileObject.getMimeType()); //No I18N
            writer.println();
            writer.write(fileObject.getFileContent(), 0, fileObject.getFileContentLength()); // Append the file bytes to request.
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Exception in adding file part in request. ", e);
        }
    }

    /**
     * Ends the request body by writing the final part boundary.
     *
     * @throws IOException
     */
    public void endBody() throws IOException {
        writeBoundary(true);
    }

    /**
     * Writes the boundary to the output stream writer.
     *
     * @param end
     *            True if this is the final boundary of the request body.
     * @throws IOException
     */
    private void writeBoundary(boolean end) throws IOException {
        // A boundary should always start and end with a line separator.
        writer.println();
        writer.println("--" + boundary + (end ? "--" : ""));
    }

}
