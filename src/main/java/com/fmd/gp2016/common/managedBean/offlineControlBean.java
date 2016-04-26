/**
 * @author mohamed265
 * Created On : Apr 25, 2016 9:23:10 AM
 */
package com.fmd.gp2016.common.managedBean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.fmd.gp2016.common.dto.Command;
import com.fmd.gp2016.common.dto.MessageDto;
import com.fmd.gp2016.common.entity.Device;
import com.fmd.gp2016.common.entity.FileSystemStructure;
import com.fmd.gp2016.common.entity.ServerToClientMessage;
import com.fmd.gp2016.common.entity.filesystemstructure.ComputerFilesSystem;
import com.fmd.gp2016.common.entity.filesystemstructure.FMDPartion;
import com.fmd.gp2016.common.service.DeviceService;
import com.fmd.gp2016.common.util.CommandConstant;
import com.fmd.gp2016.common.util.Constants;
import com.fmd.gp2016.common.util.JSONDecoding;
import com.fmd.gp2016.common.util.JsonHandler;
import com.fmd.gp2016.common.util.jsf.annotation.SpringViewScoped;

/**
 * @author mohamed265
 */
@Named("offlineControl")
@SpringViewScoped
public class offlineControlBean extends BaseBean {

	@Autowired
	public DeviceService deviceServices;

	private Device device;

	private Integer deviceID;

	private boolean isPartition = false;

	private ComputerFilesSystem computer;

	private List<FMDPartion> partitions;

	private Integer ffsId;

	private List<FileSystemStructure> list;

	private FileSystemStructure fss;

	private String metaInformation;

	@PostConstruct
	public void init() throws JSONException {

		if (!readDeviceId())
			return;

		device = deviceServices.getDeviceById(deviceID);

		list = deviceServices.getAllFileSystemStructureByDevice(device);

		metaInformation = "";
	}

	public String change() throws JSONException {
		fss = getSelectedFSS(ffsId);

		if (fss == null) {
			metaInformation = "";
			computer = new ComputerFilesSystem();
			partitions = null;
			return "";
		}

		isPartition = fss.getPath().equals(CommandConstant.computerPartions);
		metaInformation = "taken in " + fss.getTakeIn();
		if (isPartition) {
			partitions = JSONDecoding.decodeJsonOFPartions(new JSONObject(fss.getStructure()));
			for (int i = 0; i < partitions.size(); i++) {
				partitions.get(i).setTotalSpace(partitions.get(i).getTotalSpace() / (1024 * 1024 * 1024));
				partitions.get(i).setUsableSpace(partitions.get(i).getUsableSpace() / (1024 * 1024 * 1024));
			}
		} else
			computer = JSONDecoding.decodeJsonOFPathContent(new JSONObject(fss.getStructure()));
		return "";
	}

	public String get(String filename) {
		String content = CommandConstant.filetransfer;
		System.out.println(filename);
		Command command = new Command(content, new String[] { filename, computer.path });
		MessageDto msg = new MessageDto(deviceID, getSessionUserID(), JsonHandler.getCommandJson(command),
				Constants.SERVER_TO_CLIENT);
		ServerToClientMessage scm = new ServerToClientMessage();
		scm.setDevice(device);
		scm.setUser(getSessionUser());
		scm.setContent(JsonHandler.getMessageDtoJson(msg));
		// TODO save Message and preview msg
		System.out.println("In save " + scm);
		deviceServices.saveServerToClientMessage(scm);
		addInfoMessage("message saved Successfully");
		return "";
	}

	public String delete(String fileName) {
		String content = CommandConstant.removeDirectory;
		Command command = new Command(content,
				new String[] { computer.path + (device.getType() ? "\\" : "/") + fileName });
		MessageDto msg = new MessageDto(deviceID, getSessionUserID(), JsonHandler.getCommandJson(command),
				Constants.SERVER_TO_CLIENT);
		ServerToClientMessage scm = new ServerToClientMessage();
		scm.setDevice(device);
		scm.setUser(getSessionUser());
		scm.setContent(JsonHandler.getMessageDtoJson(msg));
		deviceServices.saveServerToClientMessage(scm);
		addInfoMessage("message saved Successfully");
		return "";
	}

	public String findDeviceLocation() {
		String content = CommandConstant.deviceLocation;
		Command command = new Command(content, null);
		MessageDto msg = new MessageDto(deviceID, getSessionUserID(), JsonHandler.getCommandJson(command),
				Constants.SERVER_TO_CLIENT);
		ServerToClientMessage scm = new ServerToClientMessage();
		scm.setDevice(device);
		scm.setUser(getSessionUser());
		scm.setContent(JsonHandler.getMessageDtoJson(msg));
		deviceServices.saveServerToClientMessage(scm);
		addInfoMessage("message saved Successfully");
		return "";
	}

	private boolean readDeviceId() {
		try {
			deviceID = Integer.parseInt(
					FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("device_id"));
		} catch (Exception e) {
			redirectToHomePage();
			return false;
		}

		if (deviceID == null) {
			redirectToHomePage();
			return false;
		}
		return true;
	}

	private FileSystemStructure getSelectedFSS(Integer id) {
		if (id != null)
			for (FileSystemStructure ffs : list) {
				if (ffs.getId().equals(id))
					return ffs;
			}
		return null;
	}

	public boolean isPartition() {
		return isPartition;
	}

	public ComputerFilesSystem getComputer() {
		return computer;
	}

	public Integer getFfsId() {
		return ffsId;
	}

	public List<FileSystemStructure> getList() {
		return list;
	}

	public void setFfsId(Integer ffsId) {
		this.ffsId = ffsId;
	}

	public List<FMDPartion> getPartitions() {
		return partitions;
	}

	public FileSystemStructure getFss() {
		return fss;
	}

	public String getMetaInformation() {
		return metaInformation;
	}
}
