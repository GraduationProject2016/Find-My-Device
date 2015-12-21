/**
 * @author mohamed265
 * Created On : Dec 11, 2015 3:46:26 PM
 */
package com.fmd.gp2016.common.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author mohamed265
 */
@Entity
@Table(name = "client_to_server_message")
public class ClientToServerMessage extends Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JoinColumn(name = "device_id", referencedColumnName = "id")
	@ManyToOne
	private Device device;

	@JoinColumn(name = "user_id", referencedColumnName = "id")
	@ManyToOne
	private User user;

	public ClientToServerMessage() {

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
		return "ClientToServerMessage [device=" + device + ", user=" + user + ", getId()=" + getId() + ", getType()="
				+ getType() + ", getContent()=" + getContent() + "]";
	}
}
