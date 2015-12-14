/**
 * @author mohamed265
 * Created On : Dec 14, 2015 10:07:21 AM
 */
package com.fmd.gp2016.common.dto;

import java.io.Serializable;

/**
 * @author mohamed265
 *
 */
public class MessageDto implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final boolean CLIENT_TO_SERVER = true;

	public static final boolean SERVER_TO_CLIENT = true;

	private int deviceId;

	private int userId;

	private String content;

	private boolean messageType;

	public MessageDto(boolean type) {
		messageType = type;
	}

	public MessageDto(Integer deviceId, Integer userId, String content, boolean type) {
		this.deviceId = deviceId;
		this.userId = userId;
		this.content = content;
		this.messageType = type;
	} 

	@Override
	public String toString() {
		return "MessageDto [deviceId=" + deviceId + ", userId=" + userId + ", content=" + content + ", messageType="
				+ messageType + "]";
	}

	public int getDeviceId() {
		return deviceId;
	}

	public int getUserId() {
		return userId;
	}

	public String getContent() {
		return content;
	}

	public boolean isMessageType() {
		return messageType;
	}

	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
