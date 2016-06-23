
package com.fmd.gp2016.webservice;

import javax.json.JsonObject;

import org.junit.Assert;
import org.junit.Test;

import com.fmd.gp2016.common.util.Constants;
import com.fmd.gp2016.common.util.JsonHandler;
import com.fmd.gp2016.common.util.WebServiceConnector;

public class DeviceWebServiceTest {

	@Test
	public void register_success() {
		String password = "TEST";
		String name = "TEST";
		String macAddress = "TEST";

		String serviceUrl = "http://localhost:8080/fmd/webService/device/register/" + name + "/" + password + "/"
				+ macAddress + "/" + Constants.DEVICE_TYPE_ANDROID + "/" + 35;

		// 35 is user id tester should change it to an existing record id

		String str = WebServiceConnector.getResponeString(serviceUrl);
		JsonObject jsonObject = JsonHandler.getJsonObjec(str);

		Assert.assertEquals(jsonObject.getString(Constants.STATES), Constants.SUCCESS);
	}

	@Test
	public void register_fail() {
		String password = "TEST";
		String name = "TEST";
		String macAddress = "TEST";

		String serviceUrl = "http://localhost:8080/fmd/webService/device/register/" + name + "/" + password + "/"
				+ macAddress + "/" + Constants.DEVICE_TYPE_ANDROID + "/" + 5516;

		// 5516 is user id tester should change it to a non-existing record id

		String str = WebServiceConnector.getResponeString(serviceUrl);
		JsonObject jsonObject = JsonHandler.getJsonObjec(str);

		Assert.assertEquals(jsonObject.getString(Constants.STATES), Constants.FAIL);
	}

}
