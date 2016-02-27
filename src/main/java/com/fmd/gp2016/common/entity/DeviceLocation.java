/**
 * @author mohamed265
 * Created On : Feb 27, 2016 1:37:19 PM
 */
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author mohamed265
 */
@Entity
@NamedQueries({ @NamedQuery(name = "DeviceLocation.findAllByDeviceId", query = "SELECT l FROM DeviceLocation l WHERE l.device.id =:DEVICEID") })
public class DeviceLocation implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;

	@Basic(optional = false)
	@JoinColumn(name = "device_id", referencedColumnName = "id")
	@ManyToOne
	private Device device;

	@Basic(optional = false)
	@Column(name = "latitude")
	private String latitude;

	@Basic(optional = false)
	@Column(name = "longitude")
	private String longitude;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "takein")
	private Date takeIn;

	public DeviceLocation() {
	}

	public String toDisplayForm() {
		return latitude + "," + longitude;
	}

	public Integer getId() {
		return id;
	}

	public Device getDevice() {
		return device;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public Date getTakeIn() {
		return takeIn;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public void setTakeIn(Date takeIn) {
		this.takeIn = takeIn;
	}

}
