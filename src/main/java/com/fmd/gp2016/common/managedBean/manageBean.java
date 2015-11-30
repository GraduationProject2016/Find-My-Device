/**
 * @author memo
 * Created On : Nov 30, 2015 10:22:50 AM
 */
package com.fmd.gp2016.common.managedBean;

import javax.annotation.PostConstruct;
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
@Named("signup")
public class manageBean {

	@Autowired
	private UserService userService;

	private User user;

	private String password;

	private String confirmationPassword;

	@PostConstruct
	public void inti() {
		user = new User();
		ArabicLanguage ar = new ArabicLanguage();
		//ar.
	}

	public String save() {
		if (password.equals(confirmationPassword)) {
			user.setPassword(password);
			user.setActive(false);
			userService.save(user);
			if (user.getStatus().equals(Constants.SUCCESS)) {
				System.out.println("Wl3a ya ba");

			} else {
				System.out.println("M4 Wl3a ya yla");
			}
		} else
			System.out.println("password not mtched");
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

}
