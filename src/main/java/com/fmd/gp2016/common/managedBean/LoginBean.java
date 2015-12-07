/**
 * @author Amany Mohamed
 * Created On : Nov 30, 2015 10:22:50 AM
 */
package com.fmd.gp2016.common.managedBean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.fmd.gp2016.common.entity.User;
import com.fmd.gp2016.common.service.UserService;

/**
 * @author Neama Fouad
 * @autor Amany Mohamed
 */
@Named("login")
public class LoginBean {

	@Autowired
	private UserService userService;

	private String input;

	private String password;

	@PostConstruct
	public void inti() {
	}

	private boolean isEmail() {
		int atIndex = input.indexOf('@');

		if (atIndex == -1)
			return false;

		int lastDotIndex = input.lastIndexOf('.');

		return atIndex < lastDotIndex && lastDotIndex - atIndex != 1 && lastDotIndex != input.length() - 1
				&& atIndex != 0;
	}

	public String validate() {
		User user = null;
		if (isEmail())
			user = userService.loginByEmail(input, password);
		else
			user = userService.loginByUsername(input, password);
            System.out.println("Hello 1");
		FacesContext context = FacesContext.getCurrentInstance();
		if (user == null)
			context.addMessage(null, new FacesMessage("Unknown login, try again"));
		else
			context.getExternalContext().getSessionMap().put("user", user);
		    System.out.println("Hello 2 " + user.getStatus());
		return "userhome?faces-redirect=true";
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "index?faces-redirect=true";
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}