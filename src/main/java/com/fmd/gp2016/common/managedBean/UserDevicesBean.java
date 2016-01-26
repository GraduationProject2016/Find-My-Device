package com.fmd.gp2016.common.managedBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
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
	private Device selected = new Device();
	private List<Device> devices;

	private String[] passwords;
	private String password;

	@PostConstruct
	public void init() {
		devices = new ArrayList<Device>();
		devices = userDevicesService.getAllUserDevicesByUserId(getSessionUserID());
		passwords = new String[devices.size()];
		password = "";
	}

	public String control() throws IOException {
		System.out.println(" password" + password + " " + selected);

		if (!isAuzorizedUser(password)) {
			// addSuccessfulMessage(getSessionLanguage().getERROR_MESSAGE());
			System.out.println("!IS Auzorized");

		} else {
			// addSuccessfulMessage(getSessionLanguage().getSUCCESSFUL_MESSAGE());
			System.out.println("++++IS Auzorized");
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().redirect("device.xhtml?" + Constants.DEVICE_ID + "=" + selected.getId());
		}
		return "";
	}

	boolean isAuzorizedUser(String pass) {
		System.out.println("auz" + selected.getPassword());
		return pass.equals(selected.getPassword());
	}

	public String delete() throws IOException {
		if (!isAuzorizedUser(password)) {
			// addSuccessfulMessage(getSessionLanguage().getERROR_MESSAGE());
		} else {
			userDevicesService.deleteDevice(selected.getId());
			// addSuccessfulMessage(getSessionLanguage().getSUCCESSFUL_MESSAGE());
		}
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().redirect("userDevices.xhtml");
		return "";
	}

	public void setSelected(Device temp) {
		selected = temp;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
