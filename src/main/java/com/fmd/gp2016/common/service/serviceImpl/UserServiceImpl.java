package com.fmd.gp2016.common.service.serviceImpl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fmd.gp2016.common.dao.UserDao;
import com.fmd.gp2016.common.entity.User;
import com.fmd.gp2016.common.service.UserService;

/**
 * @author mohamed265
 * @author Ibrahim Ali
 */

@Service("UserService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Override
	public void save(User user) {
		userDao.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	@Override
	public void delete(User user) {
		userDao.delete(user);
	}

	@Override
	public User getUserById(int id) {
		return userDao.getUserById(id);
	}

	@Override
	public User loginByUsername(String username, String password) {
		return userDao.loginByUsername(username, password);
	}

	@Override
	public User loginByEmail(String email, String password) {
		return userDao.loginByEmail(email, password);
	}

}
