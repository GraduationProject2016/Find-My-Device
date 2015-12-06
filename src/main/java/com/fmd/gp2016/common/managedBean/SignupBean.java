/**
 * @author Neama Fouad
 * Created On : Nov 30, 2015 10:22:50 AM
 */
package com.fmd.gp2016.common.managedBean;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.fmd.gp2016.common.entity.User;
import com.fmd.gp2016.common.service.UserService;
import com.fmd.gp2016.common.util.Constants;
import com.fmd.gp2016.common.util.language.ArabicLanguage;

/**
 * @author Neama Fouad
 * @autor Amany Mohamed
 */

@ViewScoped
@Named(value = "signup")
public class SignupBean {

	@Autowired
	private UserService userService;

	private User user;

	private String password;

	private String confirmationPassword;
	private String errorMessage;

	@PostConstruct
	public void inti() {
		user = new User();
		ArabicLanguage ar = new ArabicLanguage();
	}

	public String save() {
		if (password.equals(confirmationPassword)) {
			user.setPassword(password);
		} else {
			System.out.println("password can't matched");
		}
		if (!userService.isUniqeUsername(user.getUserName())) {
			errorMessage = "Error: username is taken";
			// FacesContext context = FacesContext.getCurrentInstance();
			// FacesMessage fMessage = new
			// FacesMessage("Error: username is taken");
			// context.addMessage(null, fMessage);
		}
		user.setActive(false);

		userService.save(user);
		if (user.getStatus().equals(Constants.SUCCESS)) {
			System.out.println("Wl3a ya ba");

		} else {
			System.out.println("M4 Wl3a ya yla");
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

}
