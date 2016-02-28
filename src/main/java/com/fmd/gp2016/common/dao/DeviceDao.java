/**
 * @author mohamed265
 * Created On : Nov 27, 2015 10:21:58 PM
 */
package com.fmd.gp2016.common.dao;

import java.util.ArrayList;
import java.util.List;

import com.fmd.gp2016.common.entity.Device;
import com.fmd.gp2016.common.entity.DeviceLocation;
import com.fmd.gp2016.common.entity.User;

/**
 * @author mohamed265
 */
public interface DeviceDao {

	public void saveDevice(Device device);

	public void isMacAddressUnique(String macAddress);

	public List<Device> getAll();

	public Device getDeviceById(Integer id);

	public void deleteDevice(Integer id);

	public List<Device> getAllUserDevicesByUser(User user);

	public List<Device> getAllUserDevicesByUserId(Integer id);

	public String selecColumntByIDNative(String columnName, Object columnValue);

	public void updateDevice(Device dev);

	public void saveDeviceLocation(DeviceLocation deviceLocation);

	public List<DeviceLocation> getAllDeviceLocation(Integer deviceid);

}
