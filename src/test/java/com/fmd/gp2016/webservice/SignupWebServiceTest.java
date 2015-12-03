/**
 * @author mohamed265
 * Created On : Nov 14, 2015 12:12:44 PM
 */
package com.fmd.gp2016.webservice;

import java.io.IOException;

import javax.json.JsonObject;

import org.junit.Assert;
import org.junit.Test;

import com.fmd.gp2016.common.util.Constants;
import com.fmd.gp2016.common.util.JsonHandler;
import com.fmd.gp2016.common.util.WebServiceConnector;

/**
 * @author mohamed265
 */
public class SignupWebServiceTest {

	// http://localhost:8080/fmd/signup?name=mohamed&username=mohamed265&password=1234&email=mohamed_fouad265@yahoo.com&mobileno=01007523776

	@Test
	public void signup() throws IOException {
		String username = "TEST";
		String password = "TEST";
		String name = "TEST";
		String mobileno = "TEST";
		String email = "TEST";

		String serviceUrl = "http://localhost:8080/fmd/webService/signup/" + name + "/" + username + "/" + email + "/"
				+ mobileno + "/" + password;

		String str = WebServiceConnector.getResponeString(serviceUrl);
		JsonObject jsonObject = JsonHandler.getJsonObjec(str);

		Assert.assertEquals(jsonObject.getString(Constants.STATES), Constants.SUCCESS);
	}

}
