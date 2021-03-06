
package com.fmd.gp2016.common.service;

import java.util.List;

import com.fmd.gp2016.common.entity.Device;
import com.fmd.gp2016.common.entity.DeviceLocation;
import com.fmd.gp2016.common.entity.FileSystemStructure;
import com.fmd.gp2016.common.entity.ServerToClientMessage;
import com.fmd.gp2016.common.entity.User;

public interface DeviceService {

	public void saveDevice(Device device);

	public List<Device> getAll();

	public Device getDeviceById(Integer id);

	public void deleteDevice(Integer id);

	public List<Device> getAllUserDevicesByUser(User user);

	public List<Device> getAllUserDevicesByUserId(Integer id);

	public void updateDevice(Device dev);

	public void updateLastActiveIn(Device dev);

	public void addDeviceLocation(DeviceLocation deviceLocation);

	public List<DeviceLocation> findAllDeviceLocationByDevice(Device device);

	public Boolean isRegisteredDevice(String mac_address);

	public void addORUpdateFileSytemStructure(FileSystemStructure fileSystemStructure);

	public List<FileSystemStructure> getAllFileSystemStructureByDevice(Device device);

	public List<ServerToClientMessage> getAllMessagesByDevice(Device device);

	public void deleteMessagesByMessage(ServerToClientMessage scm);

	public void saveServerToClientMessage(ServerToClientMessage scm);

}
