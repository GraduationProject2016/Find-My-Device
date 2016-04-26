/**
 * @author mohamed265
 * Created On : Apr 24, 2016 7:46:10 AM
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
import javax.validation.constraints.Size;

/**
 * @author mohamed265
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "FileSystemStructure.findFSSByDeviceIdAndPath", query = "SELECT fss FROM FileSystemStructure fss WHERE fss.device.id = :DEVICEID and fss.path = :PATH"),
		@NamedQuery(name = "FileSystemStructure.findAllFSSByDeviceId", query = "SELECT fss FROM FileSystemStructure fss WHERE fss.device.id = :DEVICEID") })
public class FileSystemStructure implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;

	@JoinColumn(name = "user_id", referencedColumnName = "id")
	@ManyToOne
	private User user;

	@JoinColumn(name = "device_id", referencedColumnName = "id")
	@ManyToOne
	private Device device;

	@Basic(optional = false)
	@Column(name = "path")
	@Size(max = 500)
	private String path;

	@Basic(optional = false)
	@Column(name = "structure")
	@Size(max = 20000)
	private String structure;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "takein")
	private Date takeIn;

	public FileSystemStructure() {
	}

	public static FileSystemStructure getInstance(Device device, String path, String structure) {
		FileSystemStructure fss = new FileSystemStructure();
		fss.setDevice(device);
		fss.setId(null);
		fss.setUser(device.getUser());
		fss.setPath(path);
		fss.setStructure(structure);
		return fss;
	}

	public Integer getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public Device getDevice() {
		return device;
	}

	public String getPath() {
		return path;
	}

	public String getStructure() {
		return structure;
	}

	public Date getTakeIn() {
		return takeIn;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setStructure(String structure) {
		this.structure = structure;
	}

	public void setTakeIn(Date takeIn) {
		this.takeIn = takeIn;
	}

}
