//$Id$
package com.zoho.common.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONObject;

public class CommonUtils {
	
    public static Map<String, String> convertJsonToMapObject(JSONObject inputJson ) {
        if ( inputJson == null ) {
            return null;
        }
        String key;
        Iterator<String> iterator = inputJson.keys();
        Map<String,String> resultMap = new HashMap<String,String>();

        while (iterator.hasNext()) {
             key = iterator.next();
            resultMap.put(key, inputJson.optString(key));
        }
        return resultMap;
    }

}
