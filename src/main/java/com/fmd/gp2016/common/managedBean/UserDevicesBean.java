package com.fmd.gp2016.common.managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.fmd.gp2016.common.entity.Device;
import com.fmd.gp2016.common.service.DeviceService;

/**
 * @author IbrahimAli
 */

@ViewScoped
@Named(value = "userdevices")
public class UserDevicesBean {

	@Autowired
	private DeviceService userDevicesService;

	private Device device = new Device();
	private List<Device> devices;

	private String devicePassword;

	@PostConstruct
	public void init() {
		devices = new ArrayList<Device>();
		devices = userDevicesService.getAllUserDevicesByUserId(1);
	}

	public void control(int device_id) {
		device = userDevicesService.getDeviceById(device_id);
		if (!devicePassword.equals(device.getPassword())) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error password....", null));
		} else {
			FacesMessage meg = new FacesMessage("OK TMAM YA BASHA");
			FacesContext.getCurrentInstance().addMessage(null, meg);
		}
	}

	public void delete(int device_id) {
		userDevicesService.deleteDevice(device_id);

		FacesMessage meg = new FacesMessage("Devices deleted successfully");
		FacesContext.getCurrentInstance().addMessage(null, meg);

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

	public String getDevicePassword() {
		return devicePassword;
	}

	public void setDevicePassword(String devicePassword) {
		this.devicePassword = devicePassword;
	}

}
