package com.fmd.gp2016.webservice;

import java.io.IOException;
import java.io.PrintWriter;

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
 * @author mohamed265 SignupWebService
 */
@WebServlet(urlPatterns = { "/signup" })
public class SignupWebService extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Autowired
	private UserService userService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}
	// http://localhost:8080/fmd/signup?name=mohamed&username=mohamed265&password=1234&email=mohamed_fouad265@yahoo.com&mobileno=01007523776

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = new User();
		user.setId(null);
		user.setName(request.getParameter("name"));
		user.setUserName(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		user.setEmail(request.getParameter("email"));
		user.setMobileNo(request.getParameter("mobileno"));
		user.setActive(false);
		PrintWriter writer = response.getWriter();
		
		try {
			userService.save(user);
			System.out.println("Successfully");
			writer.println("Sign Up Succsessfully");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Falier");
			writer.println("Sign Up fail");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
