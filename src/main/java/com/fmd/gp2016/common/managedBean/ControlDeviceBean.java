/**
 * @author mohamed265
 * Created On : Jan 31, 2016 8:16:08 PM
 */
package com.fmd.gp2016.common.managedBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.fmd.gp2016.common.dto.Command;
import com.fmd.gp2016.common.dto.MessageDto;
import com.fmd.gp2016.common.entity.Device;
import com.fmd.gp2016.common.filesystemstructure.ComputerFilesSystem;
import com.fmd.gp2016.common.filesystemstructure.FMDPartion;
import com.fmd.gp2016.common.service.DeviceService;
import com.fmd.gp2016.common.util.CommandConstant;
import com.fmd.gp2016.common.util.Constants;
import com.fmd.gp2016.common.util.JSONDecoding;
import com.fmd.gp2016.common.util.JsonHandler;
import com.fmd.gp2016.common.util.jsf.annotation.SpringViewScoped;
import com.fmd.gp2016.web.socket.DevicePool;
import com.fmd.gp2016.web.socket.DeviceThread;

/**
 * @author Ahmed Yehia
 *
 */
@Named("controlDevice")
@SpringViewScoped
public class ControlDeviceBean extends BaseBean {

	private DeviceThread deviceThread;
	private ComputerFilesSystem computer;
	private List<FMDPartion> partitions;
	private int deviceID;
	private int userID;
	private static int viewId = 0;
	private final static String User_Device = "userDevices.xhtml";
	private Stack<String> paths;

	@Autowired
	public DeviceService deviceServices;

	@PostConstruct
	public void init() throws JSONException {
		deviceID = Integer.parseInt(
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("device_id"));
		paths = new Stack<>();
		ArrayList<Device> devices = (ArrayList<Device>) deviceServices.getAllUserDevicesByUserId(getSessionUserID());
		Device dev = new Device();

		dev = deviceServices.getDeviceById(deviceID);

		System.out.println("++++++++++++++++++++++++++++");
		System.out.println(devices);
		System.out.println("++++++++++++++++++++++++++++");
		System.out.println(dev);
		System.out.println("++++++++++++++++++++++++++++");

		if (dev == null)
			redirect(User_Device + "?msg=there is no device by thise details ");
		else {

			int coun = 0;

			for (int i = 0; i < devices.size(); i++) {
				if (!devices.get(i).getId().equals(dev.getId()))
					coun++;
			}
			if (coun == devices.size())
				redirect(User_Device + "?msg=thise device is not belongs to you");
			else {
				userID = viewId = (viewId % (2 << 25) == 0 ? 1 : viewId + 1);
				System.out.println("view id : " + viewId);

				deviceThread = DevicePool.getDeviceThread(deviceID);
				if (deviceThread == null) {
					redirect(User_Device + "?msg=thise device is not connected ");
				} else {
					String content = CommandConstant.computerDesktop;
					Command command = new Command(content, null);
					MessageDto msg = new MessageDto(deviceID, userID, JsonHandler.getCommandJson(command),
							Constants.SERVER_TO_CLIENT);

					deviceThread.send(JsonHandler.getMessageDtoJson(msg), viewId);

					MessageDto msgdto = deviceThread.readOneMessage(viewId);

					computer = JSONDecoding.decodeJsonOFPathContent(new JSONObject(msgdto.getContent()));
					partitions = new ArrayList<>();
				}
			}
		}
	}

	public String open(String path) throws JSONException {

		String content = CommandConstant.computerPathJson;

		Command command = new Command(content, new String[] { computer.path + "\\" + path });

		MessageDto msg = new MessageDto(deviceID, userID, JsonHandler.getCommandJson(command),
				Constants.SERVER_TO_CLIENT);
		deviceThread.send(JsonHandler.getMessageDtoJson(msg), viewId);
		MessageDto msgdto = deviceThread.readOneMessage(viewId);
		computer = JSONDecoding.decodeJsonOFPathContent(new JSONObject(msgdto.getContent()));
		partitions = null;
		paths.push(computer.path);
		return "";
	}
	
	public String openPath() throws JSONException {

		String content = CommandConstant.computerPathJson;

		Command command = new Command(content, new String[] { computer.path});

		MessageDto msg = new MessageDto(deviceID, userID, JsonHandler.getCommandJson(command),
				Constants.SERVER_TO_CLIENT);
		deviceThread.send(JsonHandler.getMessageDtoJson(msg), viewId);
		MessageDto msgdto = deviceThread.readOneMessage(viewId);
		computer = JSONDecoding.decodeJsonOFPathContent(new JSONObject(msgdto.getContent()));
		partitions = null;
		return "";
	}
	

	public String openMyDoc() throws JSONException {
		String content = CommandConstant.computerHomeJson;
		Command command = new Command(content, null);

		MessageDto msg = new MessageDto(deviceID, userID, JsonHandler.getCommandJson(command),
				Constants.SERVER_TO_CLIENT);
		deviceThread.send(JsonHandler.getMessageDtoJson(msg), viewId);
		MessageDto msgdto = deviceThread.readOneMessage(viewId);
		computer = JSONDecoding.decodeJsonOFPathContent(new JSONObject(msgdto.getContent()));
		partitions = null;

		return "";
	}

	public String openMyCom() throws JSONException {
		String content = CommandConstant.computerPartions;
		Command command = new Command(content, null);

		MessageDto msg = new MessageDto(deviceID, userID, JsonHandler.getCommandJson(command),
				Constants.SERVER_TO_CLIENT);
		deviceThread.send(JsonHandler.getMessageDtoJson(msg), viewId);
		MessageDto msgdto = deviceThread.readOneMessage(viewId);
		partitions = JSONDecoding.decodeJsonOFPartions(new JSONObject(msgdto.getContent()));
		computer.path = "";
		computer.directories = null;
		computer.files = null;
		computer.numOfFiles = 0;
		computer.numOfFolders = 0;

		return "";

	}

	public String openDeskTop() throws JSONException {
		String content = CommandConstant.computerDesktop;
		Command command = new Command(content, null);
		MessageDto msg = new MessageDto(deviceID, userID, JsonHandler.getCommandJson(command),
				Constants.SERVER_TO_CLIENT);
		deviceThread.send(JsonHandler.getMessageDtoJson(msg), viewId);
		MessageDto msgdto = deviceThread.readOneMessage(viewId);
		computer = JSONDecoding.decodeJsonOFPathContent(new JSONObject(msgdto.getContent()));
		partitions = new ArrayList<>();

		return "";
	}

	public String creatNewDirectory(String folderName) throws JSONException {
		String content = CommandConstant.createNewDirectory;
		System.out.println(folderName);
		Command command = new Command(content, new String[] { computer.path, folderName });
		MessageDto msg = new MessageDto(deviceID, userID, JsonHandler.getCommandJson(command),
				Constants.SERVER_TO_CLIENT);
		deviceThread.send(JsonHandler.getMessageDtoJson(msg), viewId);
		MessageDto msgdto = deviceThread.readOneMessage(viewId);
		computer = JSONDecoding.decodeJsonOFPathContent(new JSONObject(msgdto.getContent()));

		return "";
	}

	public String get(String filename) throws JSONException {
		String content = CommandConstant.filetransfer;
		System.out.println(filename);
		Command command = new Command(content, new String[] { filename, computer.path });
		MessageDto msg = new MessageDto(deviceID, userID, JsonHandler.getCommandJson(command),
				Constants.SERVER_TO_CLIENT);
		deviceThread.send(JsonHandler.getMessageDtoJson(msg), viewId);
		deviceThread.readOneMessage(viewId);

		return "";
	}

	public String delete(String fileName) throws JSONException {
		String content = CommandConstant.removeDirectory;
		Command command = new Command(content, new String[] { computer.path + "/" + fileName });
		MessageDto msg = new MessageDto(deviceID, userID, JsonHandler.getCommandJson(command),
				Constants.SERVER_TO_CLIENT);
		deviceThread.send(JsonHandler.getMessageDtoJson(msg), viewId);
		MessageDto msgdto = deviceThread.readOneMessage(viewId);
		// computer = JSONDecoding.decodeJsonOFPathContent(new
		// JSONObject(msgdto.getContent()));
		open("");

		return "";
	}

	public String back() throws JSONException {
		if (!paths.isEmpty()) {
			System.out.println(paths);
			computer.path = paths.pop();
			System.out.println(computer.path);
			System.out.println(paths);
			openPath();
		}
		return "";

	}

	public ComputerFilesSystem getComputer() {
		return computer;
	}

	public void setComputer(ComputerFilesSystem computer) {
		this.computer = computer;
	}

	public int getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(int deviceID) {
		this.deviceID = deviceID;
	}

	public List<FMDPartion> getPartitions() {
		return partitions;
	}

	public void setPartitions(List<FMDPartion> partitions) {
		this.partitions = partitions;
	}

}
