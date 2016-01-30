/**
 * @author mohamed265
 * Created On : Jan 27, 2016 1:14:47 PM
 */
package com.fmd.gp2016.web.socket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mohamed265
 *
 */
public class DevicePool {

	private static Map<Integer, DeviceThread> map;

	static {
		map = Collections.synchronizedMap(new HashMap<Integer, DeviceThread>());
	}

	public static void addDeviceThread(DeviceThread deviceThread) {
		map.put(deviceThread.getDevice().getId(), deviceThread);
	}

	public static void removeDeviceThread(DeviceThread deviceThread) {
		try {
			if (map.containsKey(deviceThread.getDevice().getId())) {
				map.remove(deviceThread.getDevice().getId());
			}
		} catch (Exception e) {
		}
	}

	public static DeviceThread getDeviceThread(Integer deviceId) {
		try {
			return map.get(deviceId);
		} catch (Exception e) {
			return null;
		}
	}

	public static ArrayList<DeviceThread> getUserConectedDevices(Integer userId) {
		ArrayList<DeviceThread> list = null;
		for (Object key : map.keySet()) {
			DeviceThread deviceThread = map.get(key);
			try {
				if (deviceThread.getDevice().getUser().getId().equals(userId))
					list.add(deviceThread);
			} catch (Exception e) {
				System.out.println("DevicePool Exception " + e);
			}
		}
		return list;
	}

}
