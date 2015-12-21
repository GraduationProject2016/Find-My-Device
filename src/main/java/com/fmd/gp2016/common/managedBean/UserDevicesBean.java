package com.fmd.gp2016.common.managedBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.fmd.gp2016.common.entity.Device;
import com.fmd.gp2016.common.service.DeviceService;
import com.fmd.gp2016.common.util.Constants;
import com.fmd.gp2016.common.util.jsf.annotation.SpringViewScoped;

/**
 * @author IbrahimAli
 */

@SpringViewScoped
@Named(value = "userdevices")
public class UserDevicesBean extends BaseBean {

	@Autowired
	private DeviceService userDevicesService;

	private Device device = new Device();
	private List<Device> devices;

	private String[] passwords;

	@PostConstruct
	public void init() {
		devices = new ArrayList<Device>();
		devices = userDevicesService.getAllUserDevicesByUserId(getSessionUserID());
		passwords = new String[devices.size()];
	}

	public String control(int device_id, int i) {
		device = userDevicesService.getDeviceById(device_id);
		if (!isAuzorizedUser(i)) {
			addSuccessfulMessage(getSessionLanguage().getERROR_MESSAGE());
		} else {
			addSuccessfulMessage(getSessionLanguage().getSUCCESSFUL_MESSAGE());
		}
		return "";
	}

	boolean isAuzorizedUser(int i) {
		return passwords[i].equals(device.getPassword());
	}

	public String delete(int device_id, int i) throws IOException {
		device = userDevicesService.getDeviceById(device_id);
		if (!isAuzorizedUser(i)) {
			addSuccessfulMessage(getSessionLanguage().getERROR_MESSAGE());
		} else {
			userDevicesService.deleteDevice(device_id);

			addSuccessfulMessage(getSessionLanguage().getSUCCESSFUL_MESSAGE());
		}
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().redirect("userDevices.xhtml");
		return "";
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	public String[] getPasswords() {
		return passwords;
	}

	public void setPasswords(String[] passwords) {
		this.passwords = passwords;
	}

}
