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
}
