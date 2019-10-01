/** $Id$ 
 * Document default options model
 */
package com.zoho.officeintegrator.sdk.models.base;

import org.json.JSONObject;

import com.zoho.officeintegrator.sdk.constants.Constants.PARAMS;

public class DocumentDefaults {
	
	private int font_size = 12; //No I18N
	
	private boolean track_changes = false;
	
	private String font_name = "Arial"; //No I18N
	
	private String paper_size = "Letter"; //No I18N

	private String orientation = "portrait"; //No I18N
	
	private JSONObject margin = new JSONObject(); //No I18N
	
	public int getFontSize() {
		return this.font_size;
	}

	public DocumentDefaults setFontSize(int font_size) {
		this.font_size = font_size;
		return this;
	}

	public String getFontName() {
		return this.font_name;
	}
	
	public DocumentDefaults setFontName(String font_name) {
		this.font_name = font_name;
		return this;
	}
	
	public String getOrientation() {
		return this.orientation;
	}
	
	public DocumentDefaults setOrientation(String orientation) {
		this.orientation = orientation;
		return this;
	}
	
	public String getPaperSize() {
		return this.paper_size;
	}
	
	public DocumentDefaults setPaperSize(String paper_size) {
		this.paper_size = paper_size;
		return this;
	}

	public JSONObject getMargin() {
		return this.margin;
	}

	public DocumentDefaults setMargin(JSONObject margin) {
		this.margin = margin;
		return this;
	}

	public boolean isTrackChangesEnabled() {
		return this.track_changes;
	}
	
	public DocumentDefaults isTrackChanges(boolean isEnabledTrackChange) {
		this.track_changes = isEnabledTrackChange;
		return this;
	}

	public JSONObject toJson() {
		JSONObject jsonObject = new JSONObject();
		
		return jsonObject.put(PARAMS.FONT_SIZE, this.getFontSize())
				.put(PARAMS.MARGIN, this.getMargin())
				.put(PARAMS.FONT_NAME, this.getFontName())
				.put(PARAMS.PAPER_SIZE, this.getPaperSize())
				.put(PARAMS.ORIENTATION, this.getOrientation())
				.put(PARAMS.TRACK_CHANGES, "disabled");//this.isTrackChangesEnabled());
	}

}