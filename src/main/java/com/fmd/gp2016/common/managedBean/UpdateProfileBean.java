/**
 * @author memo
 * Created On : Dec 21, 2015 8:46:43 AM
 */
package com.fmd.gp2016.common.managedBean;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.fmd.gp2016.common.entity.User;
import com.fmd.gp2016.common.service.UserService;
import com.fmd.gp2016.common.util.jsf.annotation.SpringViewScoped;

/**
 * @author Neama Fouad
 *
 */

@SpringViewScoped
@Named(value = "updateProfile")
public class UpdateProfileBean extends BaseBean {

	@Autowired
	private UserService userService;

	private User user;
	private String username, email, phone;
	private Boolean page_mode_is_view;

	@PostConstruct
	public void init() {
		user = getSessionUser();
		username = user.getUserName();
		email = user.getEmail();
		phone = user.getMobileNo();

		page_mode_is_view = true;
	}

	public User getUser() {
		return user;
	}

	public Boolean getPage_mode_is_view() {
		return page_mode_is_view;
	}

	public String setPage_mode_is_view(Boolean page_mode_is_view) {
		this.page_mode_is_view = page_mode_is_view;
		return "";
	}

	public String save() {
		if (!user.getUserName().equals(username) && !userService.isUniqeUsername(user.getUserName())) {
			addErrorMessage(getSessionLanguage().getERROR_UNIQUE_USERNAME());
			return "";
		}
		if (!user.getEmail().equals(email) && !userService.isUniqeEmail(user.getEmail())) {
			addErrorMessage(getSessionLanguage().getERROR_UNIQUE_EMAIL());
			return "";
		}
		if (!user.getMobileNo().equals(phone) && !userService.isUniqeMobileNumber(user.getMobileNo())) {
			addErrorMessage(getSessionLanguage().getERROR_UNIQUE_PHONE());
			return "";
		}
		userService.update(user);
		addSuccessfulMessage(getSessionLanguage().getSUCCESSFUL_MESSAGE());
		page_mode_is_view = true;
		return "";
	}

}
