/**
 * @author mohamed265
 * Created On : Nov 27, 2015 10:49:39 PM
 */
package com.fmd.gp2016.common.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fmd.gp2016.common.dao.DeviceDao;
import com.fmd.gp2016.common.entity.Device;
import com.fmd.gp2016.common.entity.User;
import com.fmd.gp2016.common.service.DeviceService;
import com.fmd.gp2016.common.util.Constants;

/**
 * @author mohamed265
 */
@Service("DeviceService")
public class DeviceServiceImpl implements DeviceService {

	@Autowired
	private DeviceDao deviceDao;

	@Override
	public void saveDevice(Device device) {
		try {
			deviceDao.saveDevice(device);
			device.setStatus(Constants.SUCCESS);
		} catch (Exception e) {
			device.setStatus(Constants.FAIL);
		}

		// TODO add stolen devices logic
	}

	@Override
	public List<Device> getAll() {
		return deviceDao.getAll();
	}

	@Override
	public Device getDeviceById(Integer id) {
		return deviceDao.getDeviceById(id);
	}

	@Override
	public void deleteDevice(Integer id) {
		deviceDao.deleteDevice(id);
	}

	@Override
	public List<Device> getAllUserDevicesByUser(User user) {
		return deviceDao.getAllUserDevicesByUser(user);
	}

	@Override
	public List<Device> getAllUserDevicesByUserId(Integer id) {
		return deviceDao.getAllUserDevicesByUserId(id);
	}

}
