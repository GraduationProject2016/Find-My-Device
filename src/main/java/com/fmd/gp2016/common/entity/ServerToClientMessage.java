
package com.fmd.gp2016.common.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "server_to_client_message")
@NamedQueries({
		@NamedQuery(name = "ServerToClientMessage.getAllServerToClientMessageByDeviceID", query = "SELECT scm FROM ServerToClientMessage scm WHERE scm.device.id = :DEVICEID"),
		@NamedQuery(name = "ServerToClientMessage.deleteServerToClientMessage", query = "DELETE FROM ServerToClientMessage e WHERE e.id = :ID") })
public class ServerToClientMessage implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic(optional = false)
	@Column(name = "id")
	private Long id;

	@Size(min = 1, max = 10000)
	@Basic(optional = false)
	@Column(name = "content")
	private String content;

	@JoinColumn(name = "device_id", referencedColumnName = "id")
	@ManyToOne
	private Device device;

	@JoinColumn(name = "user_id", referencedColumnName = "id")
	@ManyToOne
	private User user;

	public ServerToClientMessage() {

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

	public Long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
