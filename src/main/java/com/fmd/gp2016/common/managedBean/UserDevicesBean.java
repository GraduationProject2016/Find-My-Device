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
import com.fmd.gp2016.web.socket.DevicePool;
import com.fmd.gp2016.web.socket.DeviceThread;

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

	private String deletePassword, controlPassword;

	@PostConstruct
	public void init() {
		devices = new ArrayList<Device>();
		devices = userDevicesService.getAllUserDevicesByUserId(getSessionUserID());
		deletePassword = controlPassword = "";
		checkMsg();

		for (DeviceThread dt : DevicePool.getUserConectedDevices(getSessionUserID())) {
			for (Device d : devices) {
				if (d.getId().equals(dt.getDevice().getId())) {
					d.setStatus(getSessionLanguage().getNOW());
				}
			}
		}
		for (Device d : devices) {
			if (d.getStatus() == null)
				d.setStatus(d.getLastActiveDate().toString());
		}

	}

	public String checkMsg() {

		String msg = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("msg");
		System.out.println(msg);
		addErrorMessage(getSessionLanguage().getERROR_MESSAGE());

		return "";
	}

	public String control() throws IOException {
		if (!isAuzorizedUser(controlPassword)) {
			controlPassword = "";
			addErrorMessage(getSessionLanguage().getERROR_MESSAGE());
		} else {
			addSuccessfulMessage(getSessionLanguage().getSUCCESSFUL_MESSAGE());
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext()
					.redirect("controldevice.xhtml?" + Constants.DEVICE_ID + "=" + selected.getId());
		}
		return "";
	}

	boolean isAuzorizedUser(String pass) {
		return pass.equals(selected.getPassword());
	}

	public String delete() throws IOException {
		if (!isAuzorizedUser(deletePassword)) {
			deletePassword = "";
			addErrorMessage(getSessionLanguage().getERROR_MESSAGE());
		} else {
			userDevicesService.deleteDevice(selected.getId());
			devices = userDevicesService.getAllUserDevicesByUserId(getSessionUserID());
			addSuccessfulMessage(getSessionLanguage().getSUCCESSFUL_MESSAGE());
		}
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

	public String getDeletePassword() {
		return deletePassword;
	}

	public void setDeletePassword(String deletePassword) {
		this.deletePassword = deletePassword;
	}

	public String getControlPassword() {
		return controlPassword;
	}

	public void setControlPassword(String controlPassword) {
		this.controlPassword = controlPassword;
	}

}
