/**
 * @author mohamed265
 * Created On : Nov 17, 2015 8:22:13 PM
 */
package com.fmd.gp2016.common.util;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 * @author mohamed265
 */
public class JsonHandler {

	public static JsonObject getJsonObjec(String str) {
		JsonReader reader = Json.createReader(new StringReader(str));
		JsonObject jsonObject = reader.readObject();
		reader.close();
		return jsonObject;
	}
}

// Tutorial
// http://www.java2s.com/Tutorials/Java/JSON/0100__JSON_Java.htm