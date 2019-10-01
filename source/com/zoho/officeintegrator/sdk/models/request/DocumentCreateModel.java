/** $Id$ 
 * 
 */
package com.zoho.officeintegrator.sdk.models.request;

import org.json.JSONObject;

import com.zoho.officeintegrator.sdk.constants.Constants.PARAMS;
import com.zoho.officeintegrator.sdk.constants.Constants.SERVICE_TYPE;
import com.zoho.officeintegrator.sdk.models.base.DocumentDefaults;
import com.zoho.officeintegrator.sdk.models.base.DocumentInfo;
import com.zoho.officeintegrator.sdk.models.base.EditorSettings;
import com.zoho.officeintegrator.sdk.models.base.ExportSettings;
import com.zoho.officeintegrator.sdk.models.base.Permissions;
import com.zoho.officeintegrator.sdk.models.base.UserInfo;

public class DocumentCreateModel {
	
	private DocumentInfo document_info = new DocumentInfo();

	private DocumentDefaults document_defaults = new DocumentDefaults();
	
	private ExportSettings export_settings = new ExportSettings();
	
	private EditorSettings editor_settings = new EditorSettings();

	private Permissions permissions = new Permissions();

	private UserInfo user_info = new UserInfo();

	public DocumentInfo getDocumentInfo() {
		return document_info;
	}

	public DocumentCreateModel setDocumentInfo(DocumentInfo document_info) {
		this.document_info = document_info;
		return this;
	}

	public DocumentDefaults getDocumentDefaults() {
		return document_defaults;
	}

	public void setDocumentDefault(DocumentDefaults document_defaults) {
		this.document_defaults = document_defaults;
	}

	public ExportSettings getExportSettings() {
		return export_settings;
	}

	public void setExportSettings(ExportSettings export_settings) {
		this.export_settings = export_settings;
	}

	public Permissions getPermissions() {
		return permissions;
	}

	public void setPermissions(Permissions permissions) {
		this.permissions = permissions;
	}

	public UserInfo getUserInfo() {
		return user_info;
	}

	public void setUserInfo(UserInfo user_info) {
		this.user_info = user_info;
	}

	public EditorSettings getEditorSettings() {
		return editor_settings;
	}

	public void setEditorSettings(EditorSettings editor_settings) {
		this.editor_settings = editor_settings;
	}

	public JSONObject toJson(SERVICE_TYPE service) {
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put(PARAMS.DOCUMENT_INFO, this.getDocumentInfo().toJson())
			.put(PARAMS.USER_INFO, this.getUserInfo().toJson(service))
			.put(PARAMS.PERMISSIONS, this.getPermissions().toJson(service))
			.put(PARAMS.EDITOR_SETTINGS, this.getEditorSettings().toJson(service))
			.put(PARAMS.CALLBACK_SETTINGS, this.getExportSettings().toJson(service));

		switch (service) {
			case WRITER:
				jsonObject.put(PARAMS.DOCUMENT_DEFAULTS, this.getDocumentDefaults().toJson());
				break;
			default:
				break;
		}
		
		return jsonObject;
	}

}
