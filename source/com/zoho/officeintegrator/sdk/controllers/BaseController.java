/** $Id$ 
 * 
 */
package com.zoho.officeintegrator.sdk.controllers;

import java.util.HashMap;
import java.util.Map;

import com.zoho.officeintegrator.sdk.configurations.AppConfiguration;
import com.zoho.officeintegrator.sdk.constants.Constants.PARAMS;


abstract class BaseController {
	
	protected static final String API_KEY = AppConfiguration.getApikey();
	
	public static Map<String, String> getApikeyParams() {
		Map<String, String> queryParams = new HashMap<String, String>();
		
		queryParams.put(PARAMS.APIKEY, API_KEY);

		return queryParams;
	}
	
}
