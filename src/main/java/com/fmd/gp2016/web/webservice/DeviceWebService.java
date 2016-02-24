/**
 * @author mohamed265
 * Created On : Nov 27, 2015 9:27:04 PM
 */
package com.fmd.gp2016.web.webservice;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.fmd.gp2016.common.entity.Device;
import com.fmd.gp2016.common.service.DeviceService;
import com.fmd.gp2016.common.service.UserService;
import com.fmd.gp2016.common.util.Constants;

/**
 * @author mohamed265
 */
@Path("/device")
public class DeviceWebService {

	@Autowired
	private UserService userService;

	@Autowired
	private DeviceService deviceService;

	public DeviceWebService() {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	@Path("/register/{" + Constants.NAME + "}" + "/{" + Constants.PASSWORD + "}" + "/{" + Constants.MAC_ADDRESS + "}"
			+ "/{" + Constants.DEVICE_TYPE + "}" + "/{" + Constants.ID + "}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Device register(@PathParam(Constants.NAME) String name, @PathParam(Constants.PASSWORD) String password,
			@PathParam(Constants.MAC_ADDRESS) String macAddress, @PathParam(Constants.DEVICE_TYPE) String type,
			@PathParam(Constants.ID) String userId) {
		Device dev = new Device();
		dev.setUser(userService.getUserById(Integer.parseInt(userId)));
		dev.setName(name);
		dev.setPassword(password);
		dev.setMacAddress(macAddress);
		dev.setLastActiveDate(new Date());
		dev.setOnline(true);
		dev.setActive(true);
		dev.setType((type.equals(Constants.DEVICE_TYPE_ANDROID) ? Constants.DEVICE_TYPE_ANDROID_DB
				: Constants.DEVICE_TYPE_DESKTOP_DB));
		deviceService.saveDevice(dev);
		return dev;
	}

	@Path("/devicefounded/{" + Constants.MAC_ADDRESS + "}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Device devicefounded(@PathParam(Constants.MAC_ADDRESS) String macAddress) {
		Device dev = new Device();
		if (deviceService.isRegisteredDevice(macAddress)) {
			dev.setStatus("founded");
		} else {
			dev.setStatus("not founded");
		}
		return dev;
	}
}
