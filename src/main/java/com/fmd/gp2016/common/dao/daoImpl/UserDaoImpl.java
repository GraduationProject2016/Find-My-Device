package com.fmd.gp2016.common.dao.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fmd.gp2016.common.dao.UserDao;
import com.fmd.gp2016.common.entity.User;

/**
 * @author mohamed265
 * @author Ibrahim Ali
 */

@Repository("UserDao")
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public void save(User user) throws Exception {
		em.persist(user);
	}

	@Override
	public List<User> getAllUsers() {
		return em.createNamedQuery("User.getAll").getResultList();
	}

	@Override
	public User getUserById(int id) {
		Query query = em.createNamedQuery("User.getUserById", User.class);
		query.setParameter("ID", id);
		try {
			return (User) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	@Transactional
	public void delete(User user) {
		Query query = em.createNamedQuery("User.deleteUser");
		query.setParameter("ID", user.getId());
		query.executeUpdate();
		// User use = em.getReference(User.class, user.getId());
		// em.remove(use);
	}

	@Override
	public User loginByUsername(String username, String password) {
		Query query = em.createNamedQuery("User.loginUsername");
		query.setParameter("USERNAME", username);
		query.setParameter("PASSWORD", password);
		try {
			return (User) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public User loginByEmail(String email, String password) {
		Query query = em.createNamedQuery("User.loginEmail");
		query.setParameter("EMAIL", email);
		query.setParameter("PASSWORD", password);
		try {
			return (User) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String selecColumntByIDNative(String columnName, Object columnValue) {
		Query query = em.createNativeQuery(
				"SELECT " + columnName + " FROM user WHERE " + columnName + " = '" + columnValue + "'");
		try {
			return (String) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
}