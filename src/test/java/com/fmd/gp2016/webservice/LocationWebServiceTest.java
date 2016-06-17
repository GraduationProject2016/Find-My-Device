package com.fmd.gp2016.webservice;

import java.io.IOException;

import org.junit.Test;

import com.fmd.gp2016.common.util.WebServiceConnector;

public class LocationWebServiceTest {

	@Test
	public void location() throws IOException {

		String deviceID = "1";
		String lat = "1.01010";
		String lng = "1.18181";

		String serviceUrl = "http://localhost:8080/fmd/webService/location/" + deviceID + "/" + lat + "/" + lng;

		WebServiceConnector.getResponeString(serviceUrl);
	}

}
