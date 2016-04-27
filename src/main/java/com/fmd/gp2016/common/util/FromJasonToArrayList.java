/**
 * @author Ahmed
 * Created On : Apr 27, 2016 4:36:03 PM
 */
package com.fmd.gp2016.common.util;

import java.io.IOException;
/**
 * @author Ahmed
 *
 */
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

public class FromJasonToArrayList {
	
	public static <T> List<T> jsonToList(String jsonString, final Class<T> objectClass)
			throws org.codehaus.jackson.JsonParseException, org.codehaus.jackson.map.JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		List<T> navigation = objectMapper.readValue(jsonString,
				objectMapper.getTypeFactory().constructCollectionType(List.class, objectClass));
		return navigation;
	}

}
//http://localhost:8080/fmd/webService/DeviceLocations/{deviceId}
//http://localhost:8080/fmd/webService/userDevices/{userId}