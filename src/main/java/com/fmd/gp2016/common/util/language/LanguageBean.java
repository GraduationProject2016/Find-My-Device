
package com.fmd.gp2016.common.util.language;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fmd.gp2016.common.util.Constants;
import com.fmd.gp2016.common.util.jsf.annotation.SpringSessionScoped;

@Named("language")
@SpringSessionScoped
public class LanguageBean implements Language {

	public Language lang;
	private String selectedLanguage;

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
		context.getExternalContext().getSessionMap().put(Constants.SESSION_LANGUAGE, lang);
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

	@Override
	public String getERROR_UNIQUE_USERNAME() {
		return lang.getERROR_UNIQUE_USERNAME();
	}

	@Override
	public String getERROR_UNIQUE_EMAIL() {
		return lang.getERROR_UNIQUE_EMAIL();
	}

	@Override
	public String getERROR_SIGNUP() {
		return lang.getERROR_SIGNUP();
	}

	@Override
	public String getERROR_LOGIN() {
		return lang.getERROR_LOGIN();
	}

	@Override
	public String getSUCCESSFUL_MESSAGE() {
		return lang.getSUCCESSFUL_MESSAGE();
	}

	@Override
	public String getSIGNIN_PAGE_NAME() {
		return lang.getSIGNIN_PAGE_NAME();
	}

	@Override
	public String getUSER_DEVICES_PAGE_NAME() {
		return lang.getUSER_DEVICES_PAGE_NAME();
	}

	@Override
	public String getERROR_MESSAGE() {
		return lang.getERROR_MESSAGE();
	}

	@Override
	public String getEMPTY_DEVICES() {
		return lang.getEMPTY_DEVICES();
	}

	@Override
	public String getALL_DEVICES() {
		return lang.getALL_DEVICES();
	}

	@Override
	public String getDEVICE_CONTROL() {
		return lang.getDEVICE_CONTROL();
	}

	@Override
	public String getERROR_UNIQUE_PHONE() {
		return lang.getERROR_UNIQUE_PHONE();
	}

	@Override
	public String getDEVICE_DELETE() {
		return lang.getDEVICE_DELETE();
	}

	@Override
	public String getUPDATE_PROFILE_VALUE() {
		return lang.getUPDATE_PROFILE_VALUE();
	}

	@Override
	public String getDEVICE_PASSWORD() {
		return lang.getDEVICE_PASSWORD();
	}

	@Override
	public String getMODEL_HEADER() {
		return lang.getMODEL_HEADER();
	}

	@Override
	public String getUPDATE_PROFILE_PAGE_NAME() {
		return lang.getUPDATE_PROFILE_PAGE_NAME();
	}

	@Override
	public String getDEVICE_NAME() {
		return lang.getDEVICE_NAME();
	}

	@Override
	public String getDEVICE_LASTACTIVEDATE() {
		return lang.getDEVICE_LASTACTIVEDATE();
	}

	@Override
	public String getDEVICE_TYPE() {
		return lang.getDEVICE_TYPE();
	}

	@Override
	public String getDEVICE_OPERATINS() {
		return lang.getDEVICE_OPERATINS();
	}

	@Override
	public String getDOWNLOAD_LINK() {
		return lang.getDOWNLOAD_LINK();
	}

	@Override
	public String getDOWNLOAD_PAGE_NAME() {
		return lang.getDOWNLOAD_PAGE_NAME();
	}

	@Override
	public String getBACKGROUND() {
		return lang.getBACKGROUND();
	}

	@Override
	public String getPRO_DEFINITION() {
		return lang.getPRO_DEFINITION();
	}

	@Override
	public String getSUGGEST_SOLUTION() {
		return lang.getSUGGEST_SOLUTION();

	}

	@Override
	public String getADDR_BACKGROUND() {
		return lang.getADDR_BACKGROUND();
	}

	@Override
	public String getADDR_PRO_DEFINITION() {
		return lang.getADDR_PRO_DEFINITION();
	}

	@Override
	public String getADDR_SUGGEST_SOLUTION() {
		return lang.getADDR_SUGGEST_SOLUTION();

	}

	@Override
	public String getABOUT_PAGE_NAME() {
		return lang.getABOUT_PAGE_NAME();
	}

	@Override
	public String getUSER_MANUAL() {
		return lang.getUSER_MANUAL();
	}

	@Override
	public String getSIGNUP_PICTURE() {
		return lang.getSIGNUP_PICTURE();
	}

	@Override
	public String getLOGIN_PICTURE() {
		return lang.getLOGIN_PICTURE();
	}

	@Override
	public String getUPDATE_PICTURE() {
		return lang.getUPDATE_PICTURE();
	}

	@Override
	public String getHOW_LOGIN() {
		return lang.getHOW_LOGIN();
	}

	@Override
	public String getHOW_SIGNUP() {
		return lang.getHOW_SIGNUP();
	}

	@Override
	public String getHOW_UPDATE() {
		return lang.getHOW_UPDATE();
	}

	@Override
	public String getUSERDEVICE_PICTURE() {
		return lang.getUSERDEVICE_PICTURE();
	}

	@Override
	public String getHOW_CONTROL_Delete() {
		return lang.getHOW_CONTROL_Delete();
	}

	@Override
	public String getDOWNLOAD_PICTURE() {
		return lang.getDOWNLOAD_PICTURE();
	}

	@Override
	public String getHOW_DOWNLOAD() {
		return lang.getHOW_DOWNLOAD();
	}

	@Override
	public String getDeviceSettingPage() {

		return lang.getDeviceSettingPage();
	}

	@Override
	public String getDeviceDetails() {
		return lang.getDeviceDetails();
	}

	@Override
	public String getChangeDevicePassword() {
		return lang.getChangeDevicePassword();
	}

	@Override
	public String getChangeDeviceName() {
		return lang.getChangeDeviceName();
	}

	@Override
	public String getHomePageName() {
		return lang.getHomePageName();
	}

	@Override
	public String getprofileName() {
		return lang.getprofileName();
	}

	@Override
	public String getDownloadName() {
		return lang.getDownloadName();
	}

	@Override
	public String getUserManualName() {
		return lang.getUserManualName();
	}

	@Override
	public String getAboutUsName() {
		return lang.getAboutUsName();
	}

	@Override
	public String getlogOutName() {
		return lang.getlogOutName();
	}

	@Override
	public String getThereIsNoFile() {
		return lang.getThereIsNoFile();
	}

	@Override
	public String getDeleteConfirmation() {
		return lang.getDeleteConfirmation();
	}

	@Override
	public String getAreYouSure() {
		return lang.getAreYouSure();
	}

	@Override
	public String getYes() {
		return lang.getYes();
	}

	@Override
	public String getNO() {
		return lang.getNO();
	}

	@Override
	public String getDeviceName() {
		return lang.getDeviceName();
	}

	@Override
	public String getMacAdName() {
		return lang.getMacAdName();
	}

	@Override
	public String getLastActiveName() {
		return lang.getLastActiveName();
	}

	@Override
	public String getOldPassword() {
		return lang.getOldPassword();
	}

	@Override
	public String getNewPassword() {
		return lang.getNewPassword();
	}

	@Override
	public String getReNewPassword() {

		return lang.getReNewPassword();
	}

	@Override
	public String getChangeName() {
		return lang.getChangeName();
	}

	@Override
	public String getFiles() {
		return lang.getFiles();
	}

	@Override
	public String getDeviceLocation() {
		return lang.getDeviceLocation();
	}

	@Override
	public String getNOW() {
		return lang.getNOW();
	}

	@Override
	public String getThereIsAnErrorInRecording() {
		return lang.getThereIsAnErrorInRecording();
	}

	@Override
	public String getRecordingNow() {
		return lang.getRecordingNow();
	}

	public String getRecordVoice() {
		return lang.getRecordVoice();
	}

	public String getRecordVedieo() {
		return lang.getRecordVedieo();
	}

	@Override
	public String getMessageSent() {
		return lang.getMessageSent();
	}

	@Override
	public String getOfflineMode() {
		return lang.getOfflineMode();
	}

	@Override
	public String getConfigration() {
		return lang.getConfigration();
	}

	@Override
	public String getResponceTime() {
		return lang.getResponceTime();
	}

	@Override
	public String getVideoRecordTime() {
		return lang.getVideoRecordTime();
	}

	@Override
	public String getAudioRecordTime() {
		return lang.getAudioRecordTime();
	}

	@Override
	public String getCommands() {
		return lang.getCommands();
	}

	@Override
	public String getLISTOFDEVICES() {
		return lang.getLISTOFDEVICES();
	}

	@Override
	public String getHOW_CONFIGURATION() {
		return lang.getHOW_CONFIGURATION();
	}

	@Override
	public String getHOW_FINDLOCATION() {
		return lang.getHOW_FINDLOCATION();
	}

	@Override
	public String getHOW_DETAILS() {
		return lang.getHOW_DETAILS();
	}

	@Override
	public String getHOW_CONTROL() {
		return lang.getHOW_CONTROL();
	}

	@Override
	public String getHOW_COMMAND() {
		return lang.getHOW_COMMAND();
	}

	@Override
	public String getHOW_RECORDVIDEO() {
		return lang.getHOW_RECORDVIDEO();
	}

	@Override
	public String getHOW_RECORDVOICE() {
		return lang.getHOW_RECORDVOICE();
	}

	@Override
	public String getHOW_RECONFIRMPASSWORD() {
		return lang.getHOW_RECONFIRMPASSWORD();
	}

}
