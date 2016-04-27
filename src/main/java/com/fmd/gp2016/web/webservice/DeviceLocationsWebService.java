/**
 * @author Ahmed
 * Created On : Apr 27, 2016 3:56:17 PM
 */
package com.fmd.gp2016.web.webservice;

import java.util.List;

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
 * @author Ahmed
 *
 */
@Path("/DeviceLocations/{deviceId}")
public class DeviceLocationsWebService {
	@Autowired
	private DeviceService userDevicesService;

	
	public DeviceLocationsWebService() {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<DeviceLocation> getLocations(
			@PathParam("deviceId") String deviceId) {
		List<DeviceLocation> deviceLocation= userDevicesService.findAllDeviceLocationByDevice(new Device(Integer.parseInt(deviceId)));
		return deviceLocation;
	}
	
	
	

}
