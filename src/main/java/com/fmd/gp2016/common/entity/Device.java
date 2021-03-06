
package com.fmd.gp2016.common.entity;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.ws.rs.DefaultValue;

@Entity
@Table(name = "device")
@NamedQueries({ @NamedQuery(name = "Device.getAll", query = "SELECT e FROM Device e"),
		@NamedQuery(name = "Device.getDeviceById", query = "SELECT e FROM Device e WHERE e.id = :ID"),
		@NamedQuery(name = "Device.deleteDevice", query = "DELETE FROM Device e WHERE e.id = :ID"),
		@NamedQuery(name = "Device.getAllByUser", query = "SELECT e FROM Device e WHERE e.user = :USER"),
		@NamedQuery(name = "Device.getAllByUserID", query = "SELECT e FROM Device e WHERE e.user.id = :ID") })
public class Device implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;

	@JoinColumn(name = "user_id", referencedColumnName = "id")
	@ManyToOne
	private User user;

	@Basic(optional = false)
	@Column(name = "name")
	@Size(max = 30)
	private String name;

	@Basic(optional = false)
	@Column(name = "password")
	@Size(max = 30)
	private String password;

	@Basic(optional = false)
	@Column(name = "mac_address")
	@Size(max = 60)
	private String macAddress;

	@Basic(optional = false)
	@Column(name = "last_active_date")
	private Date lastActiveDate;

	@Basic(optional = false)
	@Column(name = "online")
	private Boolean online;

	@Basic(optional = false)
	@Column(name = "type")
	private Boolean type;

	@Basic(optional = false)
	@Column(name = "responceTime", columnDefinition = "Integer default '6'")
	private Integer responceTime;

	@Basic(optional = false)
	@Column(name = "VideoRecordTime")
	private Integer VideoRecordTime;

	@Basic(optional = false)
	@Column(name = "audioRecordTime")
	@DefaultValue("20")
	private Integer audioRecordTime;

	@Basic(optional = false)
	@Column(name = "active")
	private Boolean active;

	@Column(name = "content")
	@Size(max = 10000)
	private String content;

	@Transient
	private String status;

	public Device() {

	}

	public Device(Integer id) {
		this.id = id;
	}

	public Device(User user, String macAddress) {
		super();
		this.user = user;
		this.macAddress = macAddress;
	}

	public Integer getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public Date getLastActiveDate() {
		return lastActiveDate;
	}

	public Boolean getOnline() {
		return online;
	}

	public Boolean getType() {
		return type;
	}

	public Boolean getActive() {
		return active;
	}

	public String getStatus() {
		return status;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public void setLastActiveDate(Date lastActiveDate) {
		this.lastActiveDate = lastActiveDate;
	}

	public void setOnline(Boolean online) {
		this.online = online;
	}

	public void setType(Boolean type) {
		this.type = type;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getResponceTime() {
		return responceTime;
	}

	public void setResponceTime(Integer responceTime) {
		this.responceTime = responceTime;
	}

	public Integer getVideoRecordTime() {
		return VideoRecordTime;
	}

	public void setVideoRecordTime(Integer videoRecordTime) {
		VideoRecordTime = videoRecordTime;
	}

	public Integer getAudioRecordTime() {
		return audioRecordTime;
	}

	public void setAudioRecordTime(Integer audioRecordTime) {
		this.audioRecordTime = audioRecordTime;
	}

	@Override
	public String toString() {
		return "Device [id=" + id + ", user=" + user + ", name=" + name + ", password=" + password + ", macAddress="
				+ macAddress + ", lastActiveDate=" + lastActiveDate + ", online=" + online + ", type=" + type
				+ ", content=" + content + ", active=" + active + ", status=" + status + "]";
	}

}
