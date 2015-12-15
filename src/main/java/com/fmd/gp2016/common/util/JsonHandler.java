/**
 * @author mohamed265
 * Created On : Nov 17, 2015 8:22:13 PM
 */
package com.fmd.gp2016.common.util;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import com.fmd.gp2016.common.dto.MessageDto;

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

	public static MessageDto getMessageDtoObject(String str) {
		JsonObject jsonObject = getJsonObjec(str);
		MessageDto messageDto = new MessageDto(jsonObject.getBoolean(Constants.MESSAGE_TYPE));
		messageDto.setContent(jsonObject.getString(Constants.MESSAGE_CONTENT));
		messageDto.setDeviceId(jsonObject.getInt(Constants.MESSAGE_DEVICE));
		messageDto.setUserId(jsonObject.getInt(Constants.MESSAGE_USER));
		return messageDto;
	}

	public static String getMessageDtoJson(MessageDto messageDto) {
		JsonObject jsonObject = Json.createObjectBuilder().add(Constants.MESSAGE_TYPE, messageDto.isMessageType())
				.add(Constants.MESSAGE_CONTENT, messageDto.getContent())
				.add(Constants.MESSAGE_DEVICE, messageDto.getDeviceId())
				.add(Constants.MESSAGE_USER, messageDto.getUserId()).build();
		return jsonObject.toString();
	}
}

// Tutorial
// http://www.java2s.com/Tutorials/Java/JSON/0100__JSON_Java.htm