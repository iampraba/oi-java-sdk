/** $Id$ 
 * Document default options model
 */
package com.zoho.officeintegrator.sdk.models.base;

import org.json.JSONObject;

import com.zoho.officeintegrator.sdk.constants.Constants.PARAMS.PERMISSIONS;
import com.zoho.officeintegrator.sdk.constants.Constants.SERVICE_TYPE;

public class Permissions {

	private boolean export = true;
	
	private boolean print = true;
	
	private boolean edit = true;
	
	private boolean comment = false;
	
	private boolean resolve = false;
	
	private boolean chat = true;

	public boolean isExport() {
		return export;
	}

	public Permissions setExport(boolean export) {
		this.export = export;
		return this;
	}

	public boolean isPrint() {
		return print;
	}

	public Permissions setPrint(boolean print) {
		this.print = print;
		return this;
	}

	public boolean isEdit() {
		return edit;
	}

	public Permissions setEdit(boolean edit) {
		this.edit = edit;
		return this;
	}

	public boolean isComment() {
		return comment;
	}

	public Permissions setComment(boolean comment) {
		this.comment = comment;
		return this;
	}

	public boolean isResolve() {
		return resolve;
	}

	public Permissions setResolve(boolean resolve) {
		this.resolve = resolve;
		return this;
	}

	public boolean isChat() {
		return chat;
	}

	public Permissions setChat(boolean chat) {
		this.chat = chat;
		return this;
	}

	public JSONObject toJson(SERVICE_TYPE service) {
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put(PERMISSIONS.DOCUMENT_DOT_EDIT, this.isEdit())
			.put(PERMISSIONS.DOCUMENT_DOT_PRINT, this.isPrint())
			.put(PERMISSIONS.DOCUMENT_DOT_EXPORT, this.isExport());

		switch (service) {
			case WRITER:
				jsonObject.put(PERMISSIONS.REVIEW_DOT_COMMENT, this.isComment())
					.put(PERMISSIONS.COLLAB_DOT_CHAT, this.isChat())
					.put(PERMISSIONS.REVIEW_DOT_CHANGES_DOT_RESOLVE, this.isResolve());
				break;
			default:
				break;
		}
		
		return jsonObject;
	}

}