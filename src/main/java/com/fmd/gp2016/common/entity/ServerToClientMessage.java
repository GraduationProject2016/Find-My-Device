/**
 * @author mohamed265
 * Created On : Dec 11, 2015 3:44:32 PM
 */
package com.fmd.gp2016.common.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author mohamed265
 *
 */
@Entity
@Table(name = "server_to_client_message")
public class ServerToClientMessage extends Message {

	@JoinColumn(name = "device_id", referencedColumnName = "id")
	@ManyToOne
	private Device device;

	@JoinColumn(name = "user_id", referencedColumnName = "id")
	@ManyToOne
	private User user;

	public ServerToClientMessage() {

	}

	@Override
	public Object getSender() {
		return device;
	}

	public Object getSenderId() {
		return device.getId();
	}

	@Override
	public Object getReceiever() {
		return user;
	}

	@Override
	public Object getReceieverId() {
		return user.getId();
	}

	public Device getDevice() {
		return device;
	}

	public User getUser() {
		return user;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "ServerToClientMessage [device=" + device + ", user=" + user + ", getId()=" + getId() + ", getType()="
				+ getType() + ", getContent()=" + getContent() + "]";
	}

}
