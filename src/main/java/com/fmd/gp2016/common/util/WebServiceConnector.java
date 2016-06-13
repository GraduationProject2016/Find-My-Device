/**
 * @author mohamed265
 * Created On : Nov 17, 2015 8:16:33 PM
 */
package com.fmd.gp2016.common.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author mohamed265
 */
public class WebServiceConnector {

	public static void main(String[] args) {
		System.out.println(getResponeString("http://localhost:8080/fmd/findlocation.html"));
	}

	public static String getResponeString(String urll) {
		try {
			URL url = new URL(urll);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", "Mozilla/5.0");
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuffer response = new StringBuffer();
			String inputLine = "";
			while ((inputLine = in.readLine()) != null)
				response.append(inputLine);
			in.close();
			return response.toString();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Crash In getResponeString URL IS " + urll);
			return null;
		}

	}

}
