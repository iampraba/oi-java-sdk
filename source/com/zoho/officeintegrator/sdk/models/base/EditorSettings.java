/** $Id$ 
 * Settings model to configure editor related settings 
 */
package com.zoho.officeintegrator.sdk.models.base;

import org.json.JSONObject;

import com.zoho.officeintegrator.sdk.constants.Constants.COUNTRY;
import com.zoho.officeintegrator.sdk.constants.Constants.EDITOR_VIEW;
import com.zoho.officeintegrator.sdk.constants.Constants.LANGUAGE;
import com.zoho.officeintegrator.sdk.constants.Constants.PARAMS;
import com.zoho.officeintegrator.sdk.constants.Constants.SERVICE_TYPE;
import com.zoho.officeintegrator.sdk.constants.Constants.UNIT;

public class EditorSettings {
	
	private UNIT unit = UNIT.INCH;
	
	private COUNTRY country = COUNTRY.INDIA;
	
	private LANGUAGE language = LANGUAGE.ENGLISH;
	
	private EDITOR_VIEW view = EDITOR_VIEW.PAGEVIEW;
	
	public UNIT getUnit() {
		return this.unit;
	}

	public EditorSettings setUnit(UNIT unit) {
		this.unit = unit;
		return this;
	}

	public LANGUAGE getLanguage() {
		return this.language;
	}
	
	public EditorSettings setLanguage(LANGUAGE language) {
		this.language = language;
		return this;
	}
	
	public EDITOR_VIEW getView() {
		return this.view;
	}
	
	public EditorSettings setView(EDITOR_VIEW view) {
		this.view = view;
		return this;
	}
	
	public COUNTRY getCountry() {
		return country;
	}

	public EditorSettings setCountry(COUNTRY country) {
		this.country = country;
		return this;
	}

	public JSONObject toJson(SERVICE_TYPE service) {
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put(PARAMS.LANGUAGE, this.getLanguage().getLanguageCode());

		switch (service) {
			case WRITER:
				jsonObject.put(PARAMS.UNIT, this.getUnit().getUnitCode());
				jsonObject.put(PARAMS.VIEW, this.getView().toString().toLowerCase());
				break;
			case SHEET:
				jsonObject.put(PARAMS.COUNTRY, this.getCountry().getCountryCode());
				break;
			default:
				break;
		}
		
		return jsonObject;
	}

}
