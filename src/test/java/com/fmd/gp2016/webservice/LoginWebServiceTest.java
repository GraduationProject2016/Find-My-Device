/**
 * @author mohamed265
 * Created On : Nov 17, 2015 6:18:10 PM
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
public class LoginWebServiceTest {

	@Test
	public void loginWithUserName() throws IOException, ClassNotFoundException {
		String username = "TEST";
		String password = "TEST";

		String serviceUrl = "http://localhost:8080/fmd/webService/login/username/" + username + "/" + password;

		String str = WebServiceConnector.getResponeString(serviceUrl);
		JsonObject jsonObject = JsonHandler.getJsonObjec(str);

		Assert.assertEquals(jsonObject.getString(Constants.STATES), Constants.SUCCESS);
	}

	@Test
	public void loginWithEmail() throws IOException, ClassNotFoundException {
		String email = "TEST";
		String password = "TEST";

		String serviceUrl = "http://localhost:8080/fmd/webService/login/email/" + email + "/" + password;

		String str = WebServiceConnector.getResponeString(serviceUrl);
		JsonObject jsonObject = JsonHandler.getJsonObjec(str);

		Assert.assertEquals(jsonObject.getString(Constants.STATES), Constants.SUCCESS);
	}

	@Test
	public void loginFail() throws IOException, ClassNotFoundException {
		String email = "TEST_FMD";
		String password = "TEST";

		String serviceUrl = "http://localhost:8080/fmd/webService/login/email/" + email + "/" + password;

		String str = WebServiceConnector.getResponeString(serviceUrl);
		JsonObject jsonObject = JsonHandler.getJsonObjec(str);

		Assert.assertEquals(jsonObject.getString(Constants.STATES), Constants.FAIL);
	}
}
