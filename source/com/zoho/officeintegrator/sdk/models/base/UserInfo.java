/** $Id$ 
 * Settings model to configure editor related settings 
 */
package com.zoho.officeintegrator.sdk.models.base;

import org.json.JSONObject;

import com.zoho.officeintegrator.sdk.constants.Constants.CONST;
import com.zoho.officeintegrator.sdk.constants.Constants.PARAMS;
import com.zoho.officeintegrator.sdk.constants.Constants.SERVICE_TYPE;

public class UserInfo {
	
	private String user_id = CONST.EMPTY;
	
	private String display_name = CONST.GUEST;
	
	public String getUserId() {
		return user_id;
	}

	public UserInfo setUserId(String user_id) {
		this.user_id = user_id;
		return this;
	}

	public String getDisplayName() {
		return this.display_name;
	}

	public UserInfo setDisplayName(String display_name) {
		this.display_name = display_name;
		return this;
	}

	public JSONObject toJson(SERVICE_TYPE service) {
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put(PARAMS.DISPLAY_NAME, this.getDisplayName());
		
		switch (service) {
			case WRITER:
			case SHOW:
				jsonObject.put(PARAMS.USER_ID, this.getUserId());
				break;
			default:
				break;
		}
		
		return jsonObject;
	}
}
