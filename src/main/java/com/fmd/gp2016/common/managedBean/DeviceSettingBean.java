/**
 * @author Ahmed
 * Created On : Feb 12, 2016 1:30:51 PM
 */
package com.fmd.gp2016.common.managedBean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.json.JSONException;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.springframework.beans.factory.annotation.Autowired;

import com.fmd.gp2016.common.dto.Command;
import com.fmd.gp2016.common.dto.MessageDto;
import com.fmd.gp2016.common.entity.ClientToServerMessage;
import com.fmd.gp2016.common.entity.Device;
import com.fmd.gp2016.common.entity.DeviceLocation;
import com.fmd.gp2016.common.entity.ServerToClientMessage;
import com.fmd.gp2016.common.service.DeviceService;
import com.fmd.gp2016.common.util.JsonHandler;
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
	private List<DeviceLocation> deviceLocation;
	private String locationId;
	private MapModel simpleModel;
	
	private List<ServerToClientMessage> commands;

	private int responceTime, videoRecordTime, audioRecordTime;

	@Autowired
	private DeviceService deviceService;

	@PostConstruct
	public void init() throws JSONException {
		deviceID = Integer.parseInt(
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("device_id"));
		System.out.println(deviceID);
		device = deviceService.getDeviceById(deviceID);

		responceTime = device.getResponceTime();
		videoRecordTime = device.getVideoRecordTime();
		audioRecordTime = device.getAudioRecordTime();
		
		commands = (deviceService.getAllMessagesByDevice(device));
		
		for (int i = 0; i < commands.size(); i++) {
			String temp = "";
			MessageDto ms = JsonHandler.getMessageDtoObject(commands.get(i).getContent());
			Command co = JsonHandler.getCommandObject(ms.getContent());
			if(co.getCommand().equals("filetransfer")){
				temp ="Retrive (" +co.getParms()[1]+"\\" +co.getParms()[0] + " )"; 
			}else{
				temp = "Delete (" + co.getParms()[1] +"\\" +co.getParms()[0] + " )";
			}
			commands.get(i).setContent(temp);
		}
		
		System.out.println(device);
		userFiles = new UserFiles(getSessionUserID(), deviceService);
		deviceLocation = deviceService.findAllDeviceLocationByDevice(new Device(deviceID));
		simpleModel = new DefaultMapModel();
		for (int i = 0; deviceLocation != null && i < deviceLocation.size(); i++) {
			DeviceLocation dl = deviceLocation.get(i);
			simpleModel.addOverlay(
					new Marker(new LatLng(Double.parseDouble(dl.getLatitude()), Double.parseDouble(dl.getLongitude())),
							dl.getTakeIn().toString()));
		}
		try {
			locationId = deviceLocation.get(deviceLocation.size() - 1).toDisplayForm();
		} catch (Exception e) {
			locationId = null;
		}
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

	public String updateConfigration() {
		device.setResponceTime(responceTime);
		device.setAudioRecordTime(audioRecordTime);
		device.setVideoRecordTime(videoRecordTime);
		deviceService.updateDevice(device);
		addSuccessfulMessage("updated successful");

		return "";

	}
	
	public String deleteCommand(ServerToClientMessage command){
		deviceService.deleteMessagesByMessage(command);
		addSuccessfulMessage("Deleted successful");
		redirect("devicesetting.xhtml?device_id=" + deviceID);
		
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

	public List<DeviceLocation> getDeviceLocation() {
		return deviceLocation;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public MapModel getSimpleModel() {
		return simpleModel;
	}

	public void setSimpleModel(MapModel simpleModel) {
		this.simpleModel = simpleModel;
	}

	public int getResponceTime() {
		return responceTime;
	}

	public void setResponceTime(int responceTime) {
		this.responceTime = responceTime;
	}

	public int getVideoRecordTime() {
		return videoRecordTime;
	}

	public void setVideoRecordTime(int videoRecordTime) {
		this.videoRecordTime = videoRecordTime;
	}

	public int getAudioRecordTime() {
		return audioRecordTime;
	}

	public void setAudioRecordTime(int audioRecordTime) {
		this.audioRecordTime = audioRecordTime;
	}

	/**
	 * @return the commands
	 */
	public List<ServerToClientMessage> getCommands() {
		return commands;
	}

	/**
	 * @param commands the commands to set
	 */
	public void setCommands(List<ServerToClientMessage> commands) {
		this.commands = commands;
	}

}
