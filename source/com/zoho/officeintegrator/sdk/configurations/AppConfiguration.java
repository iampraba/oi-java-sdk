/** $Id$ 
 * 
 */
package com.zoho.officeintegrator.sdk.configurations;

public class AppConfiguration {

	private static String APIKEY = "2ae438cf864488657cc9754a27daa480"; //TODO: Need to replace with user apikey
	
	private static String WRITER_SERVER_URL = "https://writer.zoho.com"; //TODO: Need to replace with user apikey
	
	private static String SHOW_SERVER_URL = "https://show.zoho.com"; //TODO: Need to replace with user apikey

	private static String SHEET_SERVER_URL = "https://sheet.zoho.com"; //TODO: Need to replace with user apikey

	public static String getApikey() {
		return APIKEY;
	}

	public static String getWriterServerUrl() {
		return WRITER_SERVER_URL;
	}

	public static void setWriterServerUrl(String writerServerUrl) {
		WRITER_SERVER_URL = writerServerUrl;
	}

	public static String getShowServerUrl() {
		return SHOW_SERVER_URL;
	}

	public static void setShowServerUrl(String showServerUrl) {
		SHOW_SERVER_URL = showServerUrl;
	}
	
	public static String getSheetServerUrl() {
		return SHEET_SERVER_URL;
	}
	
	public static void setSheetServerUrl(String sheetServerUrl) {
		SHEET_SERVER_URL = sheetServerUrl;
	}
	
}
