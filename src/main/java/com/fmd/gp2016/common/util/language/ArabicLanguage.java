/**
 * @author Ibrahim Ali
 * Created On : Dec 7, 2015 9:30:37 AM
 */
package com.fmd.gp2016.common.util.language;

/**
 * @author Ibrahim Ali
 *
 */
public class ArabicLanguage implements Language {

	private String SIGNUP_PAGE_NAME = "صفحه التسجيل";

	private String OUTPUTTEXT_NAME = "الاسم";
	private String PLACEHOLDER_NAME = "أدخل الاسم";
	private String OUTPUTTEXT_MOBILE = "رقم الموبايل";
	private String PLACEHOLDER_MOBILE = "أدخل رقم الموبايل";
	private String OUTPUTTEXT_EMAIL = "البريد الالكترونى";
	private String PLACEHOLDER_EMAIL = "أدخل البريد الالكترونى";
	private String OUTPUTTEXT_USERNAME = "أسم المستخدم";
	private String PLACEHOLDER_USERNAME = "أدخل اسم المستخدم";
	private String OUTPUTTEXT_PASSWORD = "الرقم السرى";
	private String PLACEHOLDER_PASSWORD = "أدخل الرقم السرى";
	private String OUTPUTTEXT_CONFIRMPASSWORD = "تأكيد الرقم السرى";
	private String PLACEHOLDER_CONFIRMPASSWORD = "تأكيد الرقم السرى";
	private String ERROR_PASSWORD_MATCHING = "من فضلك تأكد من تطابق كلمه المرور";
	private String dir = "rtl";

	@Override
	public String getERROR_PASSWORD_MATCHING() {
		return ERROR_PASSWORD_MATCHING;
	}

	@Override
	public String getSIGNUP_PAGE_NAME() {
		return SIGNUP_PAGE_NAME;
	}

	@Override
	public String getOUTPUTTEXT_NAME() {
		return OUTPUTTEXT_NAME;
	}

	@Override
	public String getPLACEHOLDER_NAME() {
		return PLACEHOLDER_NAME;
	}

	@Override
	public String getOUTPUTTEXT_MOBILE() {
		return OUTPUTTEXT_MOBILE;
	}

	@Override
	public String getPLACEHOLDER_MOBILE() {
		return PLACEHOLDER_MOBILE;
	}

	@Override
	public String getOUTPUTTEXT_EMAIL() {
		return OUTPUTTEXT_EMAIL;
	}

	@Override
	public String getPLACEHOLDER_EMAIL() {
		return PLACEHOLDER_EMAIL;
	}

	@Override
	public String getOUTPUTTEXT_USERNAME() {
		return OUTPUTTEXT_USERNAME;
	}

	@Override
	public String getPLACEHOLDER_USERNAME() {
		return PLACEHOLDER_USERNAME;
	}

	@Override
	public String getOUTPUTTEXT_PASSWORD() {
		return OUTPUTTEXT_PASSWORD;
	}

	@Override
	public String getPLACEHOLDER_PASSWORD() {
		return PLACEHOLDER_PASSWORD;
	}

	@Override
	public String getOUTPUTTEXT_CONFIRMPASSWORD() {
		return OUTPUTTEXT_CONFIRMPASSWORD;
	}

	@Override
	public String getPLACEHOLDER_CONFIRMPASSWORD() {
		return PLACEHOLDER_CONFIRMPASSWORD;
	}

	@Override
	public String getDir() {
		return dir;
	}

	@Override
	public String getArabicLanguageName() {

		return "العربيه";
	}

	@Override
	public String getEnglishLanguageName() {
		return "الانجليزيه";
	}

	@Override
	public String getSignupName() {
		return "تسجيل";
	}

	@Override
	public String getLoginLink() {

		return "لديك حساب بالفعل";
	}

	@Override
	public String getSignLink() {

		return "مستخدم جديد ؟";
	}

	@Override
	public String getLoginName() {

		return "دخول";
	}

}
