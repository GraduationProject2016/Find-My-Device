package com.fmd.gp2016.common.managedBean;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fmd.gp2016.common.entity.User;
import com.fmd.gp2016.common.util.jsf.annotation.SpringViewScoped;
import com.fmd.gp2016.common.util.language.Language;

/**
 * @author mohamed265 && Ibrahim Ali
 */

@SpringViewScoped
public class BaseBean {

	private static final Logger logger = LoggerFactory.getLogger(BaseBean.class);

	public Language getSessionLanguage() {
		FacesContext context = FacesContext.getCurrentInstance();
		return (Language) context.getExternalContext().getSessionMap().get("lang");
	}

	public void addSuccessfulMessage(String message) {
		FacesMessage meg = new FacesMessage(message);
		FacesContext.getCurrentInstance().addMessage(null, meg);
	}

	public void addErrorMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
	}

	public void addFatalMessage(String message) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("", new FacesMessage(FacesMessage.SEVERITY_FATAL, message, ""));
	}

	public void addInfoMessage(String message) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, message, ""));
	}

	public void addWarnMessage(String message) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("", new FacesMessage(FacesMessage.SEVERITY_WARN, message, ""));
	}

	public void setSessionUser(User user) {
		getExternalContext().getSessionMap().put("user", user);
	}

	public User getSessionUser() {
		FacesContext context = FacesContext.getCurrentInstance();
		return (User) context.getExternalContext().getSessionMap().get("user");
	}

	public Integer getSessionUserID() {
		return getSessionUser().getId();
	}

	protected ExternalContext getExternalContext() {
		return FacesContext.getCurrentInstance().getExternalContext();
	}

	protected HttpSession getSession() {
		return (HttpSession) getExternalContext().getSession(true);
	}
}
