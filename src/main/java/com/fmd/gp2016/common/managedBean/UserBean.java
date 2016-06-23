
package com.fmd.gp2016.common.managedBean;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fmd.gp2016.common.entity.User;
import com.fmd.gp2016.common.service.UserService;

@Component("userBean")
public class UserBean {

	@Autowired
	UserService userService;

	@PostConstruct
	public void init() {

	}

	public void save() {
		User user = new User();
		user.setName("FMD");
		user.setPassword("DMF");
		user.setEmail("DMF");
		user.setMobileNo("DMF");
		user.setUserName("DMF");
		user.setActive(true);
		userService.save(user);

		System.out.println(user.getId());
		System.out.println(user.getStatus());
	}

}
