/**
 * @author Ahmed
 * Created On : Feb 12, 2016 1:30:51 PM
 */
package com.fmd.gp2016.common.managedBean;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import com.fmd.gp2016.common.entity.Device;
import com.fmd.gp2016.common.service.DeviceService;
import com.fmd.gp2016.common.util.file.UserFiles;
import com.fmd.gp2016.common.util.jsf.annotation.SpringViewScoped;

@Named("deviceSetting")
@SpringViewScoped
public class DeviceSettingBean extends BaseBean {

	private String oldPassword;
	private String newPassword;
	private String reNewPassword;
	private String deviceNewName;
	private String confirmationPassword;
	private int deviceID;
	private Device device = new Device();
	private UserFiles userFiles;

	@Autowired
	private DeviceService deviceService;

	@PostConstruct
	public void init() throws JSONException {
		deviceID = Integer.parseInt(
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("device_id"));
		System.out.println(deviceID);
		device = deviceService.getDeviceById(deviceID);
		System.out.println(device);
		userFiles = new UserFiles(getSessionUserID(), deviceService);
	}
	
	public void change() {
		userFiles = new UserFiles(getSessionUserID(), deviceService);
	}

	public String changePassword() {
		if (oldPassword.equals(device.getPassword())) {
			if (!newPassword.equals(reNewPassword)) {
				addErrorMessage("The new password and Re new Password should be the same");
			} else {
				device.setPassword(newPassword);
				deviceService.updateDevice(device);
				addSuccessfulMessage("updated successful");
			}
		} else
			addErrorMessage("The password is not true");

		return "";
	}

	public String changeDeviceName() {
		if (confirmationPassword.equals(device.getPassword())) {
			device.setName(deviceNewName);
			deviceService.updateDevice(device);
			addSuccessfulMessage("updated successful");
		} else {
			addErrorMessage("The password is not true");
		}

		return "";
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getReNewPassword() {
		return reNewPassword;
	}

	public void setReNewPassword(String reNewPassword) {
		this.reNewPassword = reNewPassword;
	}

	public String getDeviceNewName() {
		return deviceNewName;
	}

	public void setDeviceNewName(String deviceNewName) {
		this.deviceNewName = deviceNewName;
	}

	public String getConfirmationPassword() {
		return confirmationPassword;
	}

	public void setConfirmationPassword(String confirmationPassword) {
		this.confirmationPassword = confirmationPassword;
	}

	public int getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(int deviceID) {
		this.deviceID = deviceID;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public UserFiles getUserFiles() {
		return userFiles;
	}

	public void download(String filename) {
		userFiles.download(filename);
	}

	public String delete(String fileName) {
		return userFiles.delete((fileName));
	}

}
