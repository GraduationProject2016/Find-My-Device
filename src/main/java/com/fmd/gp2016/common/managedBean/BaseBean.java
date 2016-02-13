package com.fmd.gp2016.common.managedBean;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fmd.gp2016.common.entity.User;
import com.fmd.gp2016.common.util.Constants;
import com.fmd.gp2016.common.util.jsf.annotation.SpringViewScoped;
import com.fmd.gp2016.common.util.language.Language;
import com.fmd.gp2016.common.util.language.LanguageBean;
import com.fmd.gp2016.common.util.language.LanguageFactory;

/**
 * @author mohamed265 && Ibrahim Ali
 */
@SpringViewScoped
public class BaseBean {

	private static final Logger logger = LoggerFactory.getLogger(BaseBean.class);

	protected ExternalContext getExternalContext() {
		return FacesContext.getCurrentInstance().getExternalContext();
	}

	protected HttpSession getSession() {
		return (HttpSession) getExternalContext().getSession(true);
	}

	public void setSessionLanguage(Language lang) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().put(Constants.SESSION_LANGUAGE, lang);
	}

	public String getLanguageCookie() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		Cookie cookie = null;
		Cookie[] userCookies = request.getCookies();
		if (userCookies != null && userCookies.length > 0) {
			for (int i = 0; i < userCookies.length; i++) {
				if (userCookies[i].getName().equals(Constants.LANGUAGE)) {
					cookie = userCookies[i];
					break;
				}
			}
		}
		return (cookie == null ? Constants.ENGLISH_LANGUAGE : cookie.getValue());
	}
	

	public Language getSessionLanguage() {
		FacesContext context = FacesContext.getCurrentInstance();
		Language lang_ = (Language) context.getExternalContext().getSessionMap().get(Constants.SESSION_LANGUAGE);
		if (lang_ == null) {
			String selectedLanguage = getLanguageCookie();
			if (selectedLanguage.equals(Constants.ENGLISH_LANGUAGE)) {
				lang_ = LanguageFactory.getEnglishLanguage();
			} else if (selectedLanguage.equals(Constants.ARABIC_LANGUAGE)) {
				lang_ = LanguageFactory.getArabicLanguage();
			}
		}
		return lang_;
	}

	public void setSessionUser(User user) {
		getExternalContext().getSessionMap().put(Constants.SESSION_USER, user);
	}

	public User getSessionUser() {
		FacesContext context = FacesContext.getCurrentInstance();
		return (User) context.getExternalContext().getSessionMap().get(Constants.SESSION_USER);
	}

	public Integer getSessionUserID() {
		return getSessionUser().getId();
	}

	public void redirect(String pageName) {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(pageName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getContextPath() {
		return getExternalContext().getRequestContextPath();
	}

	public void redirectToHomePage() {
		redirect(getContextPath() + "/userDevices.xhtml");
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
}
