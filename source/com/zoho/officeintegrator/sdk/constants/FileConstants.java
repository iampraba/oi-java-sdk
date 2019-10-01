//$Id$
package com.zoho.officeintegrator.sdk.constants;

import java.util.HashMap;
import java.util.Map;

public class FileConstants {

    public static class FILE_EXTENSION {
        public final static String HTM ="htm";// No i18n
        public final static String HTML = "html";// No i18n
        public final static String ZIP = "zip";// No i18n
        public final static String TXT = "txt";// No i18n
        public final static String DOC = "doc";// No i18n
        public final static String DOCX = "docx";// No i18n
        public final static String PPT = "ppt";//No i18n
        public final static String PPTX = "pptx";//No i18n
        public final static String XLSX = "xlsx";//No i18n
        public final static String ZDOC = "zdoc";// No i18n
        public final static String GIF = "gif";// No i18n
        public final static String JPEG = "jpeg";// No i18n
        public final static String JPG = "jpg";// No i18n
        public final static String ODT = "odt";// No i18n
        public final static String PDF = "pdf";// No i18n
        public final static String PNG = "png";// No i18n
        public final static String RTF = "rtf";// No i18n
        public final static String SXW = "sxw";// No i18n
        public final static String EPUB = "epub";// No i18n
        public final static String DOTX = "dotx";// No i18n
        public final static String DOTM = "dotm";// No i18n
        public final static String DOT = "dot";// No i18n
        public final static String DOCM = "docm";// No i18n
        public final static String TEX = "tex";//No i18n
        public final static String JSON = "json";//No i18n
        public final static String LATEX = "latex"; //No i18n
        public final static String CSV = "csv"; //No I18N
    }

    public static class CONTENT_TYPE {
        public static final String JSON = "application/json"; //No i18n
        public static final String HTML = "text/html"; //No i18n
        public static final String TEXT_PLAIN = "text/plain"; //No i18n
        public static final String TEXT = "text/plain;charset=UTF-8"; //No i18n
        public static final String DOC = "application/msword"; //No i18n
        public static final String DOCX = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";//No i18n
        public static final String PPT = "application/vnd.ms-powerpoint";//NO I18N
        public static final String PPTX = "application/vnd.openxmlformats-officedocument.presentationml.presentation";//NO I18N
        public static final String XLSX = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";//NO I18N
        public static final String RTF = "application/rtf";//No i18n
        public static final String PDF = "application/pdf";//No i18n
        public static final String ZIP = "application/zip";//No i18n
        public static final String ZDOC = "application/zip";//No i18n
        public static final String ODT = "application/odt";//No i18n
        public static final String HTM = "text/html";//No i18n
        public static final String SXW = "application/vnd.sun.xml.writer";//No i18n
        public static final String EPUB = "application/epub+zip";//No i18n
        public static final String DOTX = "application/vnd.openxmlformats-officedocument.wordprocessingml.template";//No i18n
        public static final String DOTM = "application/vnd.ms-word.template.macroEnabled.12";//No i18n
        public static final String DOT = "application/msword";//No i18n
        public static final String DOCM = "application/vnd.ms-word.document.macroEnabled.12";//No i18n
        public static final String TEX = "application/x-tex";//No i18n
        public static final String LATEX = "application/x-latex";//No i18n

        public static final String PNG = "image/png"; //no i18n
        public static final String JPG = "image/jpg"; //No i18n
        public static final String JPEG = "image/jpeg"; //No i18n
    }

    public static final Map<String, String> GET_CONTENTTYPE_FOR_EXTENSION = new HashMap<String, String>() {
        {
            put(FILE_EXTENSION.HTML, CONTENT_TYPE.HTML);
            put(FILE_EXTENSION.TXT, CONTENT_TYPE.TEXT);
            put(FILE_EXTENSION.DOCX, CONTENT_TYPE.DOCX);
            put(FILE_EXTENSION.PPT, CONTENT_TYPE.PPT);
            put(FILE_EXTENSION.PPTX, CONTENT_TYPE.PPTX);
            put(FILE_EXTENSION.XLSX, CONTENT_TYPE.XLSX);
            put(FILE_EXTENSION.ZDOC, CONTENT_TYPE.ZIP);
            put(FILE_EXTENSION.DOC, CONTENT_TYPE.DOC);
            put(FILE_EXTENSION.RTF, CONTENT_TYPE.RTF);
            put(FILE_EXTENSION.ODT, CONTENT_TYPE.ODT);
            put(FILE_EXTENSION.PDF, CONTENT_TYPE.PDF);
            put(FILE_EXTENSION.ZIP, CONTENT_TYPE.ZIP);
            put(FILE_EXTENSION.HTM, CONTENT_TYPE.HTM);
            put(FILE_EXTENSION.SXW, CONTENT_TYPE.SXW);
            put(FILE_EXTENSION.EPUB, CONTENT_TYPE.EPUB);
            put(FILE_EXTENSION.DOTX, CONTENT_TYPE.DOTX);
            put(FILE_EXTENSION.DOTM, CONTENT_TYPE.DOTM);
            put(FILE_EXTENSION.DOT, CONTENT_TYPE.DOT);
            put(FILE_EXTENSION.DOCM, CONTENT_TYPE.DOCM);
            put(FILE_EXTENSION.TEX, CONTENT_TYPE.TEX);
            put( FILE_EXTENSION.LATEX,CONTENT_TYPE.LATEX );
            put( FILE_EXTENSION.PNG, CONTENT_TYPE.PNG );
            put( FILE_EXTENSION.JPEG, CONTENT_TYPE.JPEG );
            put( FILE_EXTENSION.JPG, CONTENT_TYPE.JPG );
        }
    };

}
