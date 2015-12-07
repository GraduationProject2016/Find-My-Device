/**
 * @author Amany Mohamed
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
import com.sun.xml.bind.CycleRecoverable.Context;

/**
 * @author Neama Fouad
 * @autor Amany Mohamed
 */
@Named("login")
public class LoginBean {

	@Autowired
	private UserService userService;

	private User user;

	private String password;


	@PostConstruct
	public void inti() {
		user = new User();
		ArabicLanguage ar = new ArabicLanguage();
		//ar.
	}

	public String validate(){
		if(user.getEmail()!=""){
			userService.loginByEmail(user.getEmail(),password);
			if (user.getStatus().equals(Constants.SUCCESS)) {
				System.out.println("Login success");

			} else {
				System.out.println("Login fail");
			}
		}
		else if (user.getUserName()!=""){
			userService.loginByUsername(user.getUserName(), password);
			if (user.getStatus().equals(Constants.SUCCESS)) {
				System.out.println("Login success");

			} else {
				System.out.println("Login fail");
			}
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
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

}
