/**
 * @author mohamed265
 * Created On : Jan 31, 2016 8:16:08 PM
 */
package com.fmd.gp2016.common.managedBean;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.json.JSONException;
import org.json.JSONObject;

import com.fmd.gp2016.common.dto.Command;
import com.fmd.gp2016.common.dto.MessageDto;
import com.fmd.gp2016.common.filesystemstructure.ComputerFilesSystem;
import com.fmd.gp2016.common.util.CommandConstant;
import com.fmd.gp2016.common.util.Constants;
import com.fmd.gp2016.common.util.JSONDecoding;
import com.fmd.gp2016.common.util.JsonHandler;
import com.fmd.gp2016.common.util.jsf.annotation.SpringViewScoped;
import com.fmd.gp2016.web.socket.DevicePool;
import com.fmd.gp2016.web.socket.DeviceThread;

/**
 * @author mohamed265
 *
 */
@Named("controlDevice")
@SpringViewScoped
public class ControlDeviceBean extends BaseBean {

	private DeviceThread deviceThread;
	private ComputerFilesSystem computer;

	@PostConstruct
	public void init() throws JSONException {
		int deviceId = 1;
		int userId = 2;
		deviceThread = DevicePool.getDeviceThread(deviceId);

		String content = CommandConstant.computerDesktop;
		Command command = new Command(content, null);
		MessageDto msg = new MessageDto(deviceId, userId, JsonHandler.getCommandJson(command),
				Constants.SERVER_TO_CLIENT);
		deviceThread.send(JsonHandler.getMessageDtoJson(msg));
		MessageDto msgdto = deviceThread.readOneMessage();
		computer = JSONDecoding.decodeJsonOFPathContent(new JSONObject(msgdto.getContent()));

	}

	public String open(String path) throws JSONException {
		String content = CommandConstant.computerPathJson;
		Command command = new Command(content, new String[] { computer.path + "\\" + path });

		MessageDto msg = new MessageDto(1, 2, JsonHandler.getCommandJson(command),
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

}
