//$Id$
package com.zoho.officeintegrator.sdk.constants;

public class Constants {

	public static class PARAMS {
		public static final String URL = "url";
		public static final String UNIT = "unit";
		public static final String VIEW = "view";
		public static final String FORMAT = "format";
		public static final String APIKEY = "apikey";
		public static final String MARGIN = "margin";
		public static final String COUNTRY = "country";
		public static final String USER_ID = "user_id";
		public static final String LANGUAGE = "language";
		public static final String DOCUMENT = "document";
		public static final String SAVE_URL = "save_url";
		public static final String FONT_SIZE = "font_size";
		public static final String FONT_NAME = "font_name";
		public static final String DOCUMENT1 = "document1";
		public static final String DOCUMENT2 = "document2";
		public static final String USER_INFO = "user_info";
		public static final String SESSION_ID = "session_id";
		public static final String PAPER_SIZE = "paper_size";
		public static final String PERMISSIONS = "permissions";
		public static final String ORIENTATION = "orientation";
		public static final String DOCUMENT_ID = "document_id";
		public static final String SAVE_FORMAT = "save_format";
		public static final String CONTEXT_INFO = "context_info";
		public static final String DISPLAY_NAME = "display_name";
		public static final String DOCUMENT_INFO = "document_info";
		public static final String DOCUMENT_NAME = "document_name";
		public static final String TRACK_CHANGES = "track_changes";
		public static final String EDITOR_SETTINGS = "editor_settings";
		public static final String DOCUMENT_DEFAULTS = "document_defaults";
		public static final String CALLBACK_SETTINGS = "callback_settings";
		public static final String DEFAULT_DOCUMENT_NAME = "UntitledDocument";
		
		public static class PERMISSIONS {
			public static final String COLLAB_DOT_CHAT = "collab.chat";
			public static final String DOCUMENT_DOT_EDIT = "document.edit";
			public static final String REVIEW_DOT_COMMENT = "review.comment";
			public static final String DOCUMENT_DOT_PRINT = "document.print";
			public static final String DOCUMENT_DOT_EXPORT = "document.export";
			public static final String REVIEW_DOT_CHANGES_DOT_RESOLVE = "review.changes.resolve";
		}
	}
	
	public enum UNIT {

		INCH("in"),
		MILLI_METER("mm");
		
		private String unit_code;
		
		public String getUnitCode() {
			return this.unit_code;
		}
		
		UNIT(String unit_code) {
			this.unit_code = unit_code;
		}
	}
	
	
	public enum COUNTRY {
		
		INDIA("IN");
		
		private String country_code;
		
		public String getCountryCode() {
			return this.country_code;
		}
		
		COUNTRY(String country_code) {
			this.country_code = country_code;
		}
	}
	
	public enum LANGUAGE {
		
		ENGLISH("en"),
		DANISH("da"),
		GERMAN("de"),
		SPANISH("es"),
		FRENCH("fr"),
		HUNGARIAN("hu"),
		ITALIAN("it");
		
		private String language_code;
		
		public String getLanguageCode() {
			return this.language_code;
		}
		
		LANGUAGE(String language_code) {
			this.language_code = language_code;
		}
	}
	
	public enum EDITOR_VIEW {
		WEBVIEW,
		PAGEVIEW
	}
	
	public static class CONST { 
		public static final String EMPTY = "";
		public static final String GUEST = "Guest";
	}
	
	public enum SERVICE_TYPE { 
		WRITER,
		SHEET,
		SHOW;
	}
	
}
