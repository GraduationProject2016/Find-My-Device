/**
 * @author Ibrahim Ali
 * Created On : Dec 7, 2015 9:24:06 AM
 */
package com.fmd.gp2016.common.util.language;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fmd.gp2016.common.util.Constants;
import com.fmd.gp2016.common.util.jsf.annotation.SpringSessionScoped;

/**
 * @author Ibrahim Ali
 *
 */

@Named("language")
@SpringSessionScoped
public class LanguageBean implements Language {

	private Language lang;
	private String selectedLanguage;

	// public LanguageBean() {
	// selectedLanguage = getLanguageCookie();
	// setupLanguage();
	// }

	// public LanguageBean(int x) {
	// selectedLanguage = getLanguageCookie();
	// setupLanguage();
	// }

	@PostConstruct
	public void init() {
		selectedLanguage = getLanguageCookie();
		setupLanguage();
	}

	private void setupLanguage() {
		if (selectedLanguage.equals(Constants.ENGLISH_LANGUAGE)) {
			lang = LanguageFactory.getEnglishLanguage();
		} else if (selectedLanguage.equals(Constants.ARABIC_LANGUAGE)) {
			lang = LanguageFactory.getArabicLanguage();
		}
	} 

	public String changeLanguage(String selectedLanguage) {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		Cookie cookie = new Cookie(Constants.LANGUAGE, selectedLanguage);

		HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
		response.addCookie(cookie);

		this.selectedLanguage = selectedLanguage;
		setupLanguage();
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().put("language", lang);
		return "";
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

	public String getOUTPUTTEXT_NAME() {
		return lang.getOUTPUTTEXT_NAME();
	}

	public String getPLACEHOLDER_NAME() {
		return lang.getPLACEHOLDER_NAME();
	}

	public String getSIGNUP_PAGE_NAME() {
		return lang.getSIGNUP_PAGE_NAME();
	}

	public String getOUTPUTTEXT_MOBILE() {
		return lang.getOUTPUTTEXT_MOBILE();
	}

	public String getPLACEHOLDER_MOBILE() {
		return lang.getPLACEHOLDER_MOBILE();
	}

	public String getOUTPUTTEXT_EMAIL() {
		return lang.getOUTPUTTEXT_EMAIL();
	}

	public String getPLACEHOLDER_EMAIL() {
		return lang.getPLACEHOLDER_EMAIL();
	}

	public String getOUTPUTTEXT_USERNAME() {
		return lang.getOUTPUTTEXT_USERNAME();
	}

	public String getPLACEHOLDER_USERNAME() {
		return lang.getPLACEHOLDER_USERNAME();
	}

	public String getOUTPUTTEXT_PASSWORD() {
		return lang.getOUTPUTTEXT_PASSWORD();
	}

	public String getPLACEHOLDER_PASSWORD() {
		return lang.getPLACEHOLDER_PASSWORD();
	}

	public String getOUTPUTTEXT_CONFIRMPASSWORD() {
		return lang.getOUTPUTTEXT_CONFIRMPASSWORD();
	}

	public String getPLACEHOLDER_CONFIRMPASSWORD() {
		return lang.getPLACEHOLDER_CONFIRMPASSWORD();
	}

	public String getERROR_PASSWORD_MATCHING() {
		return lang.getERROR_PASSWORD_MATCHING();
	}

	public String getDir() {
		return lang.getDir();
	}

	@Override
	public String getArabicLanguageName() {
		return lang.getArabicLanguageName();
	}

	@Override
	public String getEnglishLanguageName() {
		return lang.getEnglishLanguageName();
	}

	@Override
	public String getSignupName() {
		return lang.getSignupName();
	}

	@Override
	public String getLoginLink() {
		return lang.getLoginLink();
	}

	@Override
	public String getSignLink() {
		return lang.getSignLink();
	}

	@Override
	public String getLoginName() {
		return lang.getLoginName();
	}

}
