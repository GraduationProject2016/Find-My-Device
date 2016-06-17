
package com.fmd.gp2016.common.util.language;

import javax.faces.bean.ApplicationScoped;

@ApplicationScoped
public class EnglishLanguage implements Language {

	private String SIGNUP_PAGE_NAME = "Registration Page";
	private String SIGNIN_PAGE_NAME = "Login Page";
	private String USER_DEVICES_PAGE_NAME = "Devices Page";
	private String UPDATE_PROFILE_PAGE_NAME = "Profile Page";
	private String DOWNLOAD_PAGE_NAME = "Downloads Page";

	private String DOWNLOAD_LINK = "Download link";
	private String OUTPUTTEXT_NAME = "Name";
	private String PLACEHOLDER_NAME = "Enter your Name";
	private String OUTPUTTEXT_MOBILE = "Mobile Number";
	private String PLACEHOLDER_MOBILE = "Enter Your Mobile Number";
	private String OUTPUTTEXT_EMAIL = "Email";
	private String PLACEHOLDER_EMAIL = "Enter your Email";
	private String OUTPUTTEXT_USERNAME = "Username";
	private String PLACEHOLDER_USERNAME = "Enter your username";
	private String OUTPUTTEXT_PASSWORD = "Password";
	private String PLACEHOLDER_PASSWORD = "Enter your password";
	private String OUTPUTTEXT_CONFIRMPASSWORD = "Confirm Password ";
	private String PLACEHOLDER_CONFIRMPASSWORD = "Confirm password";
	private String SUCCESSFUL_MESSAGE = "Successful operation";
	private String ERROR_MESSAGE = "Please make sure that the password is correct";
	private String ERROR_PASSWORD_MATCHING = "Please make sure that the two passwords matches each other";
	private String ERROR_UNIQUE_USERNAME = "Please change username because it is related to another user";
	private String ERROR_UNIQUE_EMAIL = "Please change email because it is related to another user";
	private String ERROR_UNIQUE_PHONE = "Please change mobile number because it is related to another user";
	private String ERROR_SIGNUP = "There is an error in registeration process";
	private String ERROR_LOGIN = "There is an error in login process";
	private String EMPTY_DEVICES = "There is no devices";
	private String ALL_DEVICES = "All your devices";
	private String DEVICE_CONTROL = "Control";
	private String DEVICE_DELETE = "Delete";
	private String UPDATE_PROFILE_VALUE = "Edit Profile";

	private String MODEL_HEADER = "operation for device";
	private String DEVICE_PASSWORD = "Enter the password of these device";
	private String dir = "ltr";

	private String DEVICE_NAME = "Device Name";
	private String DEVICE_LASTACTIVEDATE = "Last Active Date";
	private String DEVICE_TYPE = "Device Type";
	private String DEVICE_OPERATINS = "Operations on Device";

	private String deviceSettingPage = "Device Setting";

	private String ABOUT_PAGE_NAME = "About Us";
	private String BACKGROUND = "As we live in modern era of speed and technology, everyone seeks to a fast access to the information and having multiple devices containing different data. "
			+ "The user now become in a need of a new way to have all this information instantly wherever it is in which device it is. By that, his life routine will be much simpler. "
			+ "Furthermore availability of the Internet everywhere and at high speed and low cost will change the shape of life, so we think that building our graduation project will facilitate the communication with your devices even you are away.";
	private String PRO_DEFINITION = "We help people to access their lost or stolen electronic devices like tablet, mobile and laptops, via web app and pre-installed app on these devices.";
	private String SUGGEST_SOLUTION = "Developing system which mange you to find your devices, retrieve files from uncontrollable devices, determined location, record sound and video via web browser and pre-installed client on device.";
	private String ADDR_BACKGROUND = "Background";
	private String ADDR_PRO_DEFINITION = "Problem Definition";
	private String ADDR_SUGGEST_SOLUTION = "Suggest Solution";
	private String HOW_SIGNUP = "HOW DO I CREATE A NEW  ACCOUNT!";
	private String HOW_LOGIN = "HOW DO I LOGIN TO MY ACCOUNT IF I HAVE AN ACCOUNT!";
	private String HOW_UPDATE = "HOW DO I UPDATE OR EDIT MY DATA!";
	private String HOW_CONTROL_Delete = "HOW DO I CONTROL OR DELETE A DEVICE FROM MY AVAILABLE DEVICES!";
	private String USERDEVICE_PICTURE = "resources/img/userdevice.png";
	private String HOW_DOWNLOAD = "HOW DO I DOWNLOAD THE LINKS OF DIFFERENT APPLICATIONS!";
	private String USER_MANUAL = "User Manual Page";

	/* user manual part */
	private String SIGNUP_PICTURE = "resources/img/signup.PNG";
	private String LOGIN_PICTURE = "resources/img/login.png";
	private String UPDATE_PICTURE = "resources/img/update.png";
	private String DOWNLOAD_PICTURE = "resources/img/download.png";
	private String LISTOFDEVICES = "resources/img/listofdevices.png";
	private String HOW_CONFIGURATION = "resources/img/configuration.png";
	private String HOW_FINDLOCATION = "resources/img/findlocation.png";
	private String HOW_DETAILS = "resources/img/details.png";
	private String HOW_CONTROL = "resources/img/control.png";
	private String HOW_COMMAND = "resources/img/command.png";
	private String HOW_RECORDVIDEO = "resources/img/record.png";
	private String HOW_RECORDVOICE = "resources/img/recordVideo.png";
	private String HOW_RECONFIRMPASSWORD = "resources/img/reconfirmpassword.png";

	@Override
	public String getDir() {
		return dir;
	}

	@Override
	public String getDOWNLOAD_PAGE_NAME() {
		return DOWNLOAD_PAGE_NAME;
	}

	@Override
	public String getDOWNLOAD_LINK() {
		return DOWNLOAD_LINK;
	}

	@Override
	public String getDEVICE_NAME() {
		return DEVICE_NAME;
	}

	@Override
	public String getLISTOFDEVICES() {
		return LISTOFDEVICES;
	}

	@Override
	public String getHOW_CONFIGURATION() {
		return HOW_CONFIGURATION;
	}

	@Override
	public String getHOW_FINDLOCATION() {
		return HOW_FINDLOCATION;
	}

	@Override
	public String getHOW_DETAILS() {
		return HOW_DETAILS;
	}

	@Override
	public String getHOW_CONTROL() {
		return HOW_CONTROL;
	}

	@Override
	public String getHOW_COMMAND() {
		return HOW_COMMAND;
	}

	@Override
	public String getHOW_RECORDVIDEO() {
		return HOW_RECORDVIDEO;
	}

	@Override
	public String getHOW_RECORDVOICE() {
		return HOW_RECORDVOICE;
	}

	@Override
	public String getHOW_RECONFIRMPASSWORD() {
		return HOW_RECONFIRMPASSWORD;
	}

	@Override
	public String getDEVICE_LASTACTIVEDATE() {
		return DEVICE_LASTACTIVEDATE;
	}

	@Override
	public String getDEVICE_TYPE() {
		return DEVICE_TYPE;
	}

	@Override
	public String getDEVICE_OPERATINS() {
		return DEVICE_OPERATINS;
	}

	@Override
	public String getUPDATE_PROFILE_PAGE_NAME() {
		return UPDATE_PROFILE_PAGE_NAME;
	}

	@Override
	public String getDEVICE_CONTROL() {
		return DEVICE_CONTROL;
	}

	@Override
	public String getDEVICE_DELETE() {
		return DEVICE_DELETE;
	}

	@Override
	public String getEMPTY_DEVICES() {
		return EMPTY_DEVICES;
	}

	@Override
	public String getALL_DEVICES() {
		return ALL_DEVICES;
	}

	@Override
	public String getSIGNIN_PAGE_NAME() {
		return SIGNIN_PAGE_NAME;
	}

	@Override
	public String getUSER_DEVICES_PAGE_NAME() {
		return USER_DEVICES_PAGE_NAME;
	}

	@Override
	public String getERROR_MESSAGE() {
		return ERROR_MESSAGE;
	}

	@Override
	public String getSUCCESSFUL_MESSAGE() {
		return SUCCESSFUL_MESSAGE;
	}

	@Override
	public String getERROR_SIGNUP() {
		return ERROR_SIGNUP;
	}

	@Override
	public String getERROR_LOGIN() {
		return ERROR_LOGIN;
	}

	@Override
	public String getERROR_UNIQUE_EMAIL() {
		return ERROR_UNIQUE_EMAIL;
	}

	@Override
	public String getERROR_UNIQUE_USERNAME() {
		return ERROR_UNIQUE_USERNAME;
	}

	@Override
	public String getERROR_PASSWORD_MATCHING() {
		return ERROR_PASSWORD_MATCHING;
	}

	@Override
	public String getOUTPUTTEXT_NAME() {
		return OUTPUTTEXT_NAME;
	}

	public String getPLACEHOLDER_NAME() {
		return PLACEHOLDER_NAME;
	}

	@Override
	public String getSIGNUP_PAGE_NAME() {
		return SIGNUP_PAGE_NAME;
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
	public String getERROR_UNIQUE_PHONE() {
		return ERROR_UNIQUE_PHONE;
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
	public String getUPDATE_PROFILE_VALUE() {
		return UPDATE_PROFILE_VALUE;
	}

	@Override
	public String getDEVICE_PASSWORD() {
		return DEVICE_PASSWORD;
	}

	@Override
	public String getMODEL_HEADER() {
		return MODEL_HEADER;
	}

	@Override
	public String getBACKGROUND() {
		return BACKGROUND;
	}

	@Override
	public String getPRO_DEFINITION() {
		return PRO_DEFINITION;
	}

	@Override
	public String getSUGGEST_SOLUTION() {
		return SUGGEST_SOLUTION;
	}

	@Override
	public String getADDR_BACKGROUND() {
		return ADDR_BACKGROUND;
	}

	@Override
	public String getADDR_PRO_DEFINITION() {
		return ADDR_PRO_DEFINITION;
	}

	@Override
	public String getADDR_SUGGEST_SOLUTION() {
		return ADDR_SUGGEST_SOLUTION;
	}

	@Override
	public String getUSER_MANUAL() {
		return USER_MANUAL;
	}

	@Override
	public String getSIGNUP_PICTURE() {
		return SIGNUP_PICTURE;
	}

	@Override
	public String getLOGIN_PICTURE() {
		return LOGIN_PICTURE;
	}

	@Override
	public String getUPDATE_PICTURE() {
		return UPDATE_PICTURE;
	}

	@Override
	public String getHOW_SIGNUP() {
		return HOW_SIGNUP;
	}

	@Override
	public String getHOW_LOGIN() {
		return HOW_LOGIN;
	}

	@Override
	public String getHOW_UPDATE() {
		return HOW_UPDATE;
	}

	@Override
	public String getHOW_CONTROL_Delete() {
		return HOW_CONTROL_Delete;
	}

	@Override
	public String getHOW_DOWNLOAD() {
		return HOW_DOWNLOAD;
	}

	@Override
	public String getDOWNLOAD_PICTURE() {
		return DOWNLOAD_PICTURE;
	}

	@Override
	public String getABOUT_PAGE_NAME() {
		return ABOUT_PAGE_NAME;
	}

	@Override
	public String getUSERDEVICE_PICTURE() {
		return USERDEVICE_PICTURE;
	}

	@Override
	public String getDeviceSettingPage() {
		return deviceSettingPage;
	}

	@Override
	public String getDeviceDetails() {
		return "Details";
	}

	@Override
	public String getChangeDevicePassword() {
		return "Change Password";
	}

	@Override
	public String getChangeDeviceName() {
		return "Change Name";
	}

	@Override
	public String getHomePageName() {
		return "Home";
	}

	@Override
	public String getprofileName() {
		return "Profile";
	}

	@Override
	public String getDownloadName() {
		return "Downloads";
	}

	@Override
	public String getUserManualName() {
		return "User Manual";
	}

	@Override
	public String getAboutUsName() {
		return ABOUT_PAGE_NAME;
	}

	@Override
	public String getlogOutName() {
		return "logout";
	}

	@Override
	public String getThereIsNoFile() {
		return "there is no file";
	}

	@Override
	public String getDeleteConfirmation() {
		return "Delete Confirmation";
	}

	@Override
	public String getAreYouSure() {
		return "Are You Sure";
	}

	@Override
	public String getYes() {
		return "Yes";
	}

	@Override
	public String getNO() {
		return "No";
	}

	@Override
	public String getDeviceName() {
		return "Device Name";
	}

	@Override
	public String getMacAdName() {
		return "Mac Address";
	}

	@Override
	public String getLastActiveName() {
		return "Last Active Date";
	}

	@Override
	public String getOldPassword() {
		return "Old Password";
	}

	@Override
	public String getNewPassword() {
		return "New Password";
	}

	@Override
	public String getReNewPassword() {
		return "ReNew Password";
	}

	@Override
	public String getChangeName() {
		return "Change";
	}

	@Override
	public String getFiles() {
		return "Files";
	}

	@Override
	public String getDeviceLocation() {
		return "Location";
	}

	@Override
	public String getNOW() {
		return "Now";
	}

	@Override
	public String getThereIsAnErrorInRecording() {
		return "There Is An Error In Recording";
	}

	@Override
	public String getRecordingNow() {
		return "Recording Now";
	}

	public String getRecordVoice() {
		return "Record Voice";
	}

	public String getRecordVedieo() {
		return "Record Vedio";
	}

	@Override
	public String getMessageSent() {
		return "Message Sent";
	}

	@Override
	public String getOfflineMode() {
		return "Offline Mode";
	}

	@Override
	public String getConfigration() {
		return "Configration";
	}

	@Override
	public String getResponceTime() {
		return "Responce Time";
	}

	@Override
	public String getVideoRecordTime() {
		return "Video Record Time";
	}

	@Override
	public String getAudioRecordTime() {
		return "Audio Record Time";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fmd.gp2016.common.util.language.Language#getCommands()
	 */
	@Override
	public String getCommands() {
		// TODO Auto-generated method stub
		return "Commands";
	}

	@Override
	public String getArabicLanguageName() {
		return "Arabic";
	}

	@Override
	public String getEnglishLanguageName() {
		return "English";
	}

	@Override
	public String getSignupName() {
		return "Sign up";
	}

	@Override
	public String getLoginLink() {
		return "Already have an account";
	}

	@Override
	public String getSignLink() {
		return "New User ?";
	}

	@Override
	public String getLoginName() {
		return "Login";
	}
}
