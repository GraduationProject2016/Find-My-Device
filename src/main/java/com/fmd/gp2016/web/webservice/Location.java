/**
 * @author mohamed265
 * Created On : Feb 27, 2016 10:07:51 AM
 */
package com.fmd.gp2016.web.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.fmd.gp2016.common.entity.Device;
import com.fmd.gp2016.common.entity.DeviceLocation;
import com.fmd.gp2016.common.service.DeviceService;

/**
 * @author mohamed265
 */
@Path("/location")
public class Location {

	@Autowired
	private DeviceService deviceService;

	public Location() {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	@GET
	@Path("/{deviceId}/{latitude}/{longitude}")
	@Produces(MediaType.APPLICATION_JSON)
	public void loginWithEmail(@PathParam("deviceId") String deviceId, @PathParam("latitude") String latitude,
			@PathParam("longitude") String longitude) {
		DeviceLocation dl = new DeviceLocation();
		dl.setLatitude(latitude);
		dl.setLongitude(longitude);
		Device devref = new Device();
		devref.setId(Integer.parseInt(deviceId));
		dl.setDevice(devref);
		deviceService.addDeviceLocation(dl);
	}

}
// System.out.println("Device Id: " + deviceId);
// System.out.println("latitude: " + latitude);
// System.out.println("longitude: " + longitude);
// return "test";