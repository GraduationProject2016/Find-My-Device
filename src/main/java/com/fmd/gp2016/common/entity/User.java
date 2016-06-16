package com.fmd.gp2016.common.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
@NamedQueries({ @NamedQuery(name = "User.getAll", query = "SELECT e FROM User e"),
		@NamedQuery(name = "User.getUserById", query = "SELECT e FROM User e WHERE e.id = :ID"),
		@NamedQuery(name = "User.deleteUser", query = "DELETE FROM User e WHERE e.id = :ID"),
		@NamedQuery(name = "User.loginUsername", query = "SELECT e FROM User e WHERE e.userName = :USERNAME and e.password = :PASSWORD"),
		@NamedQuery(name = "User.loginEmail", query = "SELECT e FROM User e WHERE e.email = :EMAIL and e.password = :PASSWORD") })
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;

	@Basic(optional = false)
	@Column(name = "name")
	@Size(max = 100)
	private String name;

	@Basic(optional = false)
	@Column(name = "password")
	@Size(max = 30)
	private String password;

	@Basic(optional = false)
	@Column(name = "username")
	@Size(max = 20)
	private String userName;

	@Basic(optional = false)
	@Column(name = "email")
	@Size(max = 60)
	private String email;

	@Basic(optional = false)
	@Column(name = "mobileno")
	@Size(max = 30)
	private String mobileNo;

	@Basic(optional = false)
	@Column(name = "active")
	private Boolean active;

	@Transient
	private String status;

	public User() {

	}

	public User(String password, String username) {
		this.password = password;
		this.userName = username;
	}

	public User(String password, String email, Integer id) {
		this.password = password;
		this.email = email;
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getUserName() {
		return userName;
	}

	public String getEmail() {
		return email;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserName(String username) {
		this.userName = username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String sTATES) {
		status = sTATES;
	}

}
