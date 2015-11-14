package com.fmd.gp2016.webservice;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.fmd.gp2016.common.entity.User;
import com.fmd.gp2016.common.service.UserService;

/**
 * @author mohamed265 LoginWebService
 */
@WebServlet(urlPatterns = { "/login" })
public class LoginWebService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// http://localhost:8080/fmd/login?username=mohamed265&password=1234

	@Autowired
	private UserService userService;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map<String, String[]> map = request.getParameterMap();

		User user = null;

		String password = request.getParameter("password");

		if (map.containsKey("username")) {
			String username = request.getParameter("username");
			user = userService.loginByUsername(username, password);
		}

		if (map.containsKey("email")) {
			String email = request.getParameter("email");
			user = userService.loginByEmail(email, password);
		}

		System.out.println(user == null ? "null" : user.getId());
		/// response.getOutputStream().write(user);////////
		// response.getOutputStream().flush();
		// response.getOutputStream().close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
