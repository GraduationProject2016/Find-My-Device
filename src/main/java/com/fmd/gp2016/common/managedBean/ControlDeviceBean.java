/**
 * @author mohamed265
 * Created On : Jan 31, 2016 8:16:08 PM
 */
package com.fmd.gp2016.common.managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.json.JSONException;
import org.json.JSONObject;

import com.fmd.gp2016.common.dto.Command;
import com.fmd.gp2016.common.dto.MessageDto;
import com.fmd.gp2016.common.filesystemstructure.ComputerFilesSystem;
import com.fmd.gp2016.common.filesystemstructure.FMDPartion;
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

	@PostConstruct
	public void init() throws JSONException {
		deviceID = Integer.parseInt(
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("device_id"));
		 
		userID = 1;
		deviceThread = DevicePool.getDeviceThread(deviceID);

		String content = CommandConstant.computerDesktop;
		Command command = new Command(content, null);
		MessageDto msg = new MessageDto(deviceID, userID, JsonHandler.getCommandJson(command),
				Constants.SERVER_TO_CLIENT);
		deviceThread.send(JsonHandler.getMessageDtoJson(msg));
		MessageDto msgdto = deviceThread.readOneMessage();
		computer = JSONDecoding.decodeJsonOFPathContent(new JSONObject(msgdto.getContent()));
		partitions = new ArrayList<>();
		
	}

	public String open(String path) throws JSONException {
		String content = CommandConstant.computerPathJson;
		Command command = new Command(content, new String[] { computer.path + "\\" + path });

		MessageDto msg = new MessageDto(deviceID, userID, JsonHandler.getCommandJson(command), Constants.SERVER_TO_CLIENT);
		deviceThread.send(JsonHandler.getMessageDtoJson(msg));
		MessageDto msgdto = deviceThread.readOneMessage();
		computer = JSONDecoding.decodeJsonOFPathContent(new JSONObject(msgdto.getContent()));
		partitions = null;
		return "";
	}
	
	public String openMyDoc() throws JSONException{
		String content = CommandConstant.computerHomeJson;
		Command command = new Command(content, null);

		MessageDto msg = new MessageDto(deviceID, userID, JsonHandler.getCommandJson(command), Constants.SERVER_TO_CLIENT);
		deviceThread.send(JsonHandler.getMessageDtoJson(msg));
		MessageDto msgdto = deviceThread.readOneMessage();
		computer = JSONDecoding.decodeJsonOFPathContent(new JSONObject(msgdto.getContent()));
		partitions = null;
		return "";
	}
	
	public String openMyCom() throws JSONException{
		String content = CommandConstant.computerPartions;
		Command command = new Command(content, null);
		
		MessageDto msg = new MessageDto(deviceID, userID, JsonHandler.getCommandJson(command), Constants.SERVER_TO_CLIENT);
		deviceThread.send(JsonHandler.getMessageDtoJson(msg));
		MessageDto msgdto = deviceThread.readOneMessage();
		partitions = JSONDecoding.decodeJsonOFPartions(new JSONObject(msgdto.getContent()));
		computer.path = "";
		computer.directories = null;
		computer.files = null;
		return "";
		
	}
	public String openDeskTop() throws JSONException{
		String content = CommandConstant.computerDesktop;
		Command command = new Command(content, null);
		MessageDto msg = new MessageDto(deviceID, userID, JsonHandler.getCommandJson(command),
				Constants.SERVER_TO_CLIENT);
		deviceThread.send(JsonHandler.getMessageDtoJson(msg));
		MessageDto msgdto = deviceThread.readOneMessage();
		computer = JSONDecoding.decodeJsonOFPathContent(new JSONObject(msgdto.getContent()));
		partitions = new ArrayList<>();
		return "";
	}
	
	public String creatNewDirectory(String folderName) throws JSONException{
		String content = CommandConstant.createNewDirectory;
		System.out.println(folderName);
		Command command = new Command(content, new String[] { computer.path , folderName});
		MessageDto msg = new MessageDto(deviceID, userID, JsonHandler.getCommandJson(command),
				Constants.SERVER_TO_CLIENT);
		deviceThread.send(JsonHandler.getMessageDtoJson(msg));
		MessageDto msgdto = deviceThread.readOneMessage();
		computer = JSONDecoding.decodeJsonOFPathContent(new JSONObject(msgdto.getContent()));
		
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
