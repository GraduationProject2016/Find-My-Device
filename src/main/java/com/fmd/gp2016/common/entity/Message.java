
package com.fmd.gp2016.common.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

@MappedSuperclass
public abstract class Message implements Serializable {

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

	@Transient
	private Boolean type;

	public Message() {
	}

	public abstract Object getSender();

	public abstract Object getSenderId();

	public abstract Object getReceiever();

	public abstract Object getReceieverId();

	public Long getId() {
		return id;
	}

	public Boolean getType() {
		return type;
	}

	public String getContent() {
		return content;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setType(Boolean type) {
		this.type = type;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
