
package com.fmd.gp2016.common.managedBean;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.fmd.gp2016.common.entity.User;
import com.fmd.gp2016.common.service.UserService;
import com.fmd.gp2016.common.util.Constants;
import com.fmd.gp2016.common.util.jsf.annotation.SpringViewScoped;

@SpringViewScoped
@Named(value = "signup")
public class SignupBean extends BaseBean {

	@Autowired
	private UserService userService;

	private User user;

	private String password;

	private String confirmationPassword;
	private String errorMessage;
	private String errorMessageEmail;

	@PostConstruct
	public void inti() {
		user = new User();
	}

	public String save() {
		if (password.equals(confirmationPassword)) {
			user.setPassword(password);
		} else {
			addErrorMessage(getSessionLanguage().getERROR_PASSWORD_MATCHING());
			return "";
		}

		if (!userService.isUniqeUsername(user.getUserName())) {
			addErrorMessage(getSessionLanguage().getERROR_UNIQUE_USERNAME());
			return "";
		}
		if (!userService.isUniqeEmail(user.getEmail())) {
			addErrorMessage(getSessionLanguage().getERROR_UNIQUE_EMAIL());
			return "";
		}
		if (!userService.isUniqeMobileNumber(user.getMobileNo())) {
			addErrorMessage(getSessionLanguage().getERROR_UNIQUE_PHONE());
			return "";
		}

		user.setActive(true);
		userService.save(user);

		if (user.getStatus().equals(Constants.SUCCESS)) {
			setSessionUser(user);
			redirectToHomePage();
		} else {
			addErrorMessage(getSessionLanguage().getERROR_SIGNUP());
		}

		return "";
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmationPassword() {
		return confirmationPassword;
	}

	public void setConfirmationPassword(String confirmationPassword) {
		this.confirmationPassword = confirmationPassword;
	}

	public User getUser() {
		return user;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessageEmail() {
		return errorMessageEmail;
	}

	public void setErrorMessageEmail(String errorMessageEmail) {
		this.errorMessageEmail = errorMessageEmail;
	}

}
