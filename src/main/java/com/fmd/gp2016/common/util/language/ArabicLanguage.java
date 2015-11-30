/**
 * @author memo
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


	private String SIGNUP_PAGE_NAME = "صفحة التسجيل";

	private String OUTPUTTEXT_NAME = "الاسم";
	private String PLACEHOLDER_NAME = "ادخل الاسم";
	
	private String OUTPUTTEXT_MOBILE="رقم الموبايل";
	private String PLACEHOLDER_MOBILE = "ادخل رقم الموبيل";

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


}
