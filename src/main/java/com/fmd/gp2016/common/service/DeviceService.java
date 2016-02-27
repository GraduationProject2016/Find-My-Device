/**
 * @author mohamed265
 * Created On : Nov 27, 2015 10:49:20 PM
 */
package com.fmd.gp2016.common.service;

import java.util.ArrayList;
import java.util.List;

import com.fmd.gp2016.common.entity.Device;
import com.fmd.gp2016.common.entity.DeviceLocation;
import com.fmd.gp2016.common.entity.User;

/**
 * @author mohamed265
 *
 */
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

	public ArrayList<DeviceLocation> findAllDeviceLocationByDevice(Device device);

}
