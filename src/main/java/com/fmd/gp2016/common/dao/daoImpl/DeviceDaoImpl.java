/**
 * @author mohamed265
 * Created On : Nov 27, 2015 10:22:37 PM
 */
package com.fmd.gp2016.common.dao.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fmd.gp2016.common.dao.DeviceDao;
import com.fmd.gp2016.common.entity.Device;
import com.fmd.gp2016.common.entity.DeviceLocation;
import com.fmd.gp2016.common.entity.FileSystemStructure;
import com.fmd.gp2016.common.entity.ServerToClientMessage;
import com.fmd.gp2016.common.entity.User;

/**
 * @author mohamed265
 */
@Repository("DeviceDao")
public class DeviceDaoImpl implements DeviceDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public void saveDevice(Device device) {
		em.persist(device);
	}

	@Override
	public void isMacAddressUnique(String macAddress) {
		// TODO Implement worning algorithem

	}

	@Override
	public List<Device> getAll() {
		return em.createNamedQuery("Device.getAll").getResultList();
	}

	@Override
	public Device getDeviceById(Integer id) {
		Query query = em.createNamedQuery("Device.getDeviceById");
		query.setParameter("ID", id);
		try {
			return (Device) query.getSingleResult();
		} catch (Exception e) {
			System.err.println("get Device by id");
			return null;
		}
	}

	@Override
	@Transactional
	public void deleteDevice(Integer id) {
		Query query = em.createNamedQuery("Device.deleteDevice");
		query.setParameter("ID", id);
		query.executeUpdate();
	}

	@Override
	public List<Device> getAllUserDevicesByUser(User user) {
		Query query = em.createNamedQuery("Device.getAllByUser");
		query.setParameter("USER", user);
		return query.getResultList();
	}

	@Override
	public List<Device> getAllUserDevicesByUserId(Integer id) {
		Query query = em.createNamedQuery("Device.getAllByUserID");
		query.setParameter("ID", id);
		return query.getResultList();
	}

	@Override
	public String selecColumntByIDNative(String columnName, Object columnValue) {
		Query query = em.createNativeQuery(
				"SELECT " + columnName + " FROM device WHERE " + columnName + " = '" + columnValue + "'");
		try {
			return (String) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	@Transactional
	public void updateDevice(Device dev) {
		em.merge(dev);
	}

	@Override
	@Transactional
	public void saveDeviceLocation(DeviceLocation deviceLocation) {
		em.merge(deviceLocation);
	}

	@Override
	public List<DeviceLocation> getAllDeviceLocation(Integer deviceid) {
		Query query = em.createNamedQuery("DeviceLocation.findAllByDeviceId");
		query.setParameter("DEVICEID", deviceid);
		return query.getResultList();
	}

	@Override
	public FileSystemStructure getFileSystemStructure(Integer deviceid, String path) {
		Query query = em.createNamedQuery("FileSystemStructure.findFSSByDeviceIdAndPath");
		query.setParameter("DEVICEID", deviceid);
		query.setParameter("PATH", path);
		try {
			return (FileSystemStructure) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	@Transactional
	public FileSystemStructure addOrUpdateFileSystemStructure(FileSystemStructure fss) {
		return em.merge(fss);
	}

	@Override
	public List<FileSystemStructure> getAllFileSystemStructureByDeviceId(Integer deviceID) {
		Query query = em.createNamedQuery("FileSystemStructure.findAllFSSByDeviceId");
		query.setParameter("DEVICEID", deviceID);
		try {
			return query.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<ServerToClientMessage> getAllMessagesByDeviceId(Integer deviceId) {
		Query query = em.createNamedQuery("ServerToClientMessage.getAllServerToClientMessageByDeviceID");
		query.setParameter("DEVICEID", deviceId);
		try {
			return query.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	@Transactional
	public void deleteMessagesByMessageId(Long id) {
		Query query = em.createNamedQuery("ServerToClientMessage.deleteServerToClientMessage");
		query.setParameter("ID", id);
		query.executeUpdate();
	}

	@Override
	@Transactional
	public void saveServerToClientMessage(ServerToClientMessage scm) {
		System.out.println("In Dao " + scm);
		em.merge(scm);
	}
}
