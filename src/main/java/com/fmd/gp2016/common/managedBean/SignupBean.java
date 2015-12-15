/**
 * @author Neama Fouad
 * Created On : Nov 30, 2015 10:22:50 AM
 */
package com.fmd.gp2016.common.managedBean;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.fmd.gp2016.common.entity.User;
import com.fmd.gp2016.common.service.UserService;
import com.fmd.gp2016.common.util.Constants;
import com.fmd.gp2016.common.util.jsf.annotation.SpringViewScoped;

/**
 * @author Neama Fouad
 * @autor Amany Mohamed
 */

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
			addWarnMessage(getSessionLanguage().getERROR_UNIQUE_USERNAME());
			return "";
		}
		if (!userService.isUniqeEmail(user.getEmail())) {
			addWarnMessage(getSessionLanguage().getERROR_UNIQUE_EMAIL());
			return "";
		}

		user.setActive(true);
		userService.save(user);

		if (user.getStatus().equals(Constants.SUCCESS)) {
			redirectToHomePage();
		} else {
			addErrorMessage(getSessionLanguage().getERROR_SIGNUP());
		}

		return "";
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the confirmationPassword
	 */
	public String getConfirmationPassword() {
		return confirmationPassword;
	}

	/**
	 * @param confirmationPassword
	 *            the confirmationPassword to set
	 */
	public void setConfirmationPassword(String confirmationPassword) {
		this.confirmationPassword = confirmationPassword;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage
	 *            the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the errorMessageEmail
	 */
	public String getErrorMessageEmail() {
		return errorMessageEmail;
	}

	/**
	 * @param errorMessageEmail
	 *            the errorMessageEmail to set
	 */
	public void setErrorMessageEmail(String errorMessageEmail) {
		this.errorMessageEmail = errorMessageEmail;
	}

}
