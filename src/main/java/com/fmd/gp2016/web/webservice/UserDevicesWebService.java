/**
 * @author Ahmed
 * Created On : Apr 27, 2016 3:56:17 PM
 */
package com.fmd.gp2016.web.webservice;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.fmd.gp2016.common.entity.Device;
import com.fmd.gp2016.common.service.DeviceService;

/**
 * @author Ahmed
 *
 */
@Path("/userDevices/{userId}")
public class UserDevicesWebService {
	@Autowired
	private DeviceService userDevicesService;

	
	public UserDevicesWebService() {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Device> getDevices(
			@PathParam("userId") String userId) {
		 ArrayList<Device> devices = new ArrayList<Device>();
			devices = (ArrayList<Device>) userDevicesService.getAllUserDevicesByUserId(Integer.parseInt(userId));
		return devices;
	}
	
	
	

}
