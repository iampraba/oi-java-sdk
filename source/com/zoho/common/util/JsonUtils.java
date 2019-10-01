//$Id$
package com.zoho.common.util;

import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {

    /**
     * Method merge mergeFrom(JSONObject) into  mergeTo(JSONObject). mergeFrom -> mergeTo
     * "mergeFrom" object keys values are inserted into "mergeTo" object. If keys already exist, then it will be overwritten.
     * Result object won't be returned, since reference object values are updated.
     * @param mergeTo - Target JSONObject
     * @param mergeFrom - Source JSONObject
     * @throws JSONException
     */
    public static void mergeJSONObjects(JSONObject mergeTo, JSONObject mergeFrom) throws JSONException {
        if ( mergeTo == null || mergeFrom == null ) {
            return;
        }
        Iterator<String> iterator = mergeFrom.keys();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            mergeTo.put(key, mergeFrom.get(key));
        }
    }
}
