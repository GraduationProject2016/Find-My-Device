/**
 * @author mohamed265
 * Created On : Nov 17, 2015 8:27:55 PM
 */
package com.fmd.gp2016.web.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.fmd.gp2016.common.entity.User;
import com.fmd.gp2016.common.service.UserService;
import com.fmd.gp2016.common.util.Constants;

/**
 * @author mohamed265
 */
@Path("/signup")
public class SignupWebService {

	@Autowired
	private UserService userService;

	public SignupWebService() {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	@GET
	@Path("/{" + Constants.NAME + "}" + "/{" + Constants.USER_NAME + "}" + "/{" + Constants.EMAIL + "}" + "/{"
			+ Constants.MOBILE_NO + "}" + "/{" + Constants.PASSWORD + "}")
	@Produces(MediaType.APPLICATION_JSON)
	public User signup(@PathParam(Constants.NAME) String name, @PathParam(Constants.USER_NAME) String username,
			@PathParam(Constants.EMAIL) String email, @PathParam(Constants.MOBILE_NO) String mobileno,
			@PathParam(Constants.PASSWORD) String password) {
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setUserName(username);
		user.setMobileNo(mobileno);
		user.setPassword(password);
		user.setActive(false);
		userService.save(user);
		return user;
	}

}
