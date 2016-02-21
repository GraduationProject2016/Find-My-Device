/**
 * @author mohamed265
 * Created On : Feb 14, 2016 10:04:42 PM
 */
package com.fmd.gp2016.common.util.file;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import com.fmd.gp2016.common.entity.Device;
import com.fmd.gp2016.common.service.DeviceService;
import com.fmd.gp2016.common.util.Constants;

/**
 * @author mohamed265
 *
 */
public class UserFiles {

	private FileDownload fileDownload;

	private Integer userId;

	private HashMap<Integer, ArrayList<String>> filesDevice;

	private HashMap<String, String> filePath;

	private ArrayList<Device> devicesList;

	private ArrayList<String> files;

	public UserFiles(Integer user, DeviceService deviceService) {
		System.out.println("UserFile Constract");
		userId = user;
		devicesList = (ArrayList<Device>) deviceService.getAllUserDevicesByUserId(userId);
		fileDownload = new FileDownload();
		filePath = new HashMap<>();
		files = new ArrayList<String>();
		init();
	}

	public void init() {
		for (Device device : devicesList) {
			filesDevice = new HashMap<>();
			File file = new File(Constants.UPLOAD_PATH + userId + "\\" + device.getId());
			filesDevice.put(device.getId(), new ArrayList<String>());
			if (file.exists()) {
				for (String string : file.list()) {
					filesDevice.get(device.getId()).add(string);
					filePath.put(string, Constants.UPLOAD_PATH + userId + "\\" + device.getId() + "\\" + string);
					files.add(string);
				}
			}
		}
		System.out.println("UserFile Init " + files.size());
	}

	public void download(String filename) {
		System.out.println(filename + " " + getFilePath(filename));
		try {
			fileDownload.download(getFilePath(filename));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String delete(String fileName) {
		fileDownload.delete(getFilePath(fileName));
		files.remove(fileName);
		filePath.remove(fileName);
		for (Integer key : filesDevice.keySet()) {
			filesDevice.get(key).remove(fileName);
		}
		return "";
	}

	public String getFilePath(String fileName) {
		return filePath.get(fileName);
	}

	public ArrayList<String> getFilesDeviceList(Integer deviceID) {
		return filesDevice.get(deviceID);
	}

	public ArrayList<Device> getDevicesList() {
		return devicesList;
	}

	public HashMap<Integer, ArrayList<String>> getFilesDevice() {
		return filesDevice;
	}

	public ArrayList<String> getFiles() {
		return files;
	}

	public FileDownload getFileDownload() {
		return fileDownload;
	}

	public void setFileDownload(FileDownload fileDownload) {
		this.fileDownload = fileDownload;
	}

}
