/**
 * @author  Neama Fouad
 * Created On : Nov 30, 2015 11:27:58 AM
 */
package com.fmd.gp2016.common.util.language;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Named;

/**
 * @author mohamed265
 * @author Neama Fouad
 * @autor Amany Mohamed
 */
@Named("ar")
@ApplicationScoped
public class ArabicLanguage {


	private String SIGNUP_PAGE_NAME = "Registration Page";

	private String OUTPUTTEXT_NAME = "Name:";
	private String PLACEHOLDER_NAME = "Enter your Name ";
	private String OUTPUTTEXT_MOBILE="Mobile Number:";
	private String PLACEHOLDER_MOBILE = "Enter Your Mobile Number";
	private String OUTPUTTEXT_EMAIL="Email :";
	private String PLACEHOLDER_EMAIL="Enter your Email";
	private String OUTPUTTEXT_USERNAME="Username :";
	private String PLACEHOLDER_USERNAME="Enter your username";
	private String OUTPUTTEXT_PASSWORD="Password :";
	private String PLACEHOLDER_PASSWORD="Enter your password";
	private String OUTPUTTEXT_CONFIRMPASSWORD="Confirm Password :";
	private String PLACEHOLDER_CONFIRMPASSWORD="Confirm password";
	/**
	 * @return the oUTPUTTEXT_NAME
	 */
	public String getOUTPUTTEXT_NAME() {
		return OUTPUTTEXT_NAME;
	}
   
	/**
	 * @param oUTPUTTEXT_NAME
	 *            the oUTPUTTEXT_NAME to set
	 */
	public void setOUTPUTTEXT_NAME(String oUTPUTTEXT_NAME) {
		OUTPUTTEXT_NAME = oUTPUTTEXT_NAME;
	}

	/**
	 * @return the pLACEHOLDER_NAME
	 */
	public String getPLACEHOLDER_NAME() {
		return PLACEHOLDER_NAME;
	}

	/**
	 * @param pLACEHOLDER_NAME
	 *            the pLACEHOLDER_NAME to set
	 */
	public void setPLACEHOLDER_NAME(String pLACEHOLDER_NAME) {
		PLACEHOLDER_NAME = pLACEHOLDER_NAME;
	}

	/**
	 * @return the sIGNUP_PAGE_NAME
	 */
	public String getSIGNUP_PAGE_NAME() {
		return SIGNUP_PAGE_NAME;
	}

	/**
	 * @param sIGNUP_PAGE_NAME the sIGNUP_PAGE_NAME to set
	 */
	public void setSIGNUP_PAGE_NAME(String sIGNUP_PAGE_NAME) {
		SIGNUP_PAGE_NAME = sIGNUP_PAGE_NAME;
	}

	/**
	 * @return the oUTPUTTEXT_MOBILE
	 */
	public String getOUTPUTTEXT_MOBILE() {
		return OUTPUTTEXT_MOBILE;
	}

	/**
	 * @param oUTPUTTEXT_MOBILE the oUTPUTTEXT_MOBILE to set
	 */
	public void setOUTPUTTEXT_MOBILE(String oUTPUTTEXT_MOBILE) {
		OUTPUTTEXT_MOBILE = oUTPUTTEXT_MOBILE;
	}

	/**
	 * @return the pLACEHOLDER_MOBILE
	 */
	public String getPLACEHOLDER_MOBILE() {
		return PLACEHOLDER_MOBILE;
	}

	/**
	 * @param pLACEHOLDER_MOBILE the pLACEHOLDER_MOBILE to set
	 */
	public void setPLACEHOLDER_MOBILE(String pLACEHOLDER_MOBILE) {
		PLACEHOLDER_MOBILE = pLACEHOLDER_MOBILE;
	}

	/**
	 * @return the oUTPUTTEXT_EMAIL
	 */
	public String getOUTPUTTEXT_EMAIL() {
		return OUTPUTTEXT_EMAIL;
	}

	/**
	 * @param oUTPUTTEXT_EMAIL the oUTPUTTEXT_EMAIL to set
	 */
	public void setOUTPUTTEXT_EMAIL(String oUTPUTTEXT_EMAIL) {
		OUTPUTTEXT_EMAIL = oUTPUTTEXT_EMAIL;
	}

	/**
	 * @return the pLACEHOLDER_EMAIL
	 */
	public String getPLACEHOLDER_EMAIL() {
		return PLACEHOLDER_EMAIL;
	}

	/**
	 * @param pLACEHOLDER_EMAIL the pLACEHOLDER_EMAIL to set
	 */
	public void setPLACEHOLDER_EMAIL(String pLACEHOLDER_EMAIL) {
		PLACEHOLDER_EMAIL = pLACEHOLDER_EMAIL;
	}

	/**
	 * @return the oUTPUTTEXT_USERNAME
	 */
	public String getOUTPUTTEXT_USERNAME() {
		return OUTPUTTEXT_USERNAME;
	}

	/**
	 * @param oUTPUTTEXT_USERNAME the oUTPUTTEXT_USERNAME to set
	 */
	public void setOUTPUTTEXT_USERNAME(String oUTPUTTEXT_USERNAME) {
		OUTPUTTEXT_USERNAME = oUTPUTTEXT_USERNAME;
	}

	/**
	 * @return the pLACEHOLDER_USERNAME
	 */
	public String getPLACEHOLDER_USERNAME() {
		return PLACEHOLDER_USERNAME;
	}

	/**
	 * @param pLACEHOLDER_USERNAME the pLACEHOLDER_USERNAME to set
	 */
	public void setPLACEHOLDER_USERNAME(String pLACEHOLDER_USERNAME) {
		PLACEHOLDER_USERNAME = pLACEHOLDER_USERNAME;
	}

	/**
	 * @return the oUTPUTTEXT_PASSWORD
	 */
	public String getOUTPUTTEXT_PASSWORD() {
		return OUTPUTTEXT_PASSWORD;
	}

	/**
	 * @param oUTPUTTEXT_PASSWORD the oUTPUTTEXT_PASSWORD to set
	 */
	public void setOUTPUTTEXT_PASSWORD(String oUTPUTTEXT_PASSWORD) {
		OUTPUTTEXT_PASSWORD = oUTPUTTEXT_PASSWORD;
	}

	/**
	 * @return the pLACEHOLDER_PASSWORD
	 */
	public String getPLACEHOLDER_PASSWORD() {
		return PLACEHOLDER_PASSWORD;
	}

	/**
	 * @param pLACEHOLDER_PASSWORD the pLACEHOLDER_PASSWORD to set
	 */
	public void setPLACEHOLDER_PASSWORD(String pLACEHOLDER_PASSWORD) {
		PLACEHOLDER_PASSWORD = pLACEHOLDER_PASSWORD;
	}

	/**
	 * @return the oUTPUTTEXT_CONFIRMPASSWORD
	 */
	public String getOUTPUTTEXT_CONFIRMPASSWORD() {
		return OUTPUTTEXT_CONFIRMPASSWORD;
	}

	/**
	 * @param oUTPUTTEXT_CONFIRMPASSWORD the oUTPUTTEXT_CONFIRMPASSWORD to set
	 */
	public void setOUTPUTTEXT_CONFIRMPASSWORD(String oUTPUTTEXT_CONFIRMPASSWORD) {
		OUTPUTTEXT_CONFIRMPASSWORD = oUTPUTTEXT_CONFIRMPASSWORD;
	}

	/**
	 * @return the pLACEHOLDER_CONFIRMPASSWORD
	 */
	public String getPLACEHOLDER_CONFIRMPASSWORD() {
		return PLACEHOLDER_CONFIRMPASSWORD;
	}

	/**
	 * @param pLACEHOLDER_CONFIRMPASSWORD the pLACEHOLDER_CONFIRMPASSWORD to set
	 */
	public void setPLACEHOLDER_CONFIRMPASSWORD(String pLACEHOLDER_CONFIRMPASSWORD) {
		PLACEHOLDER_CONFIRMPASSWORD = pLACEHOLDER_CONFIRMPASSWORD;
	}


}
