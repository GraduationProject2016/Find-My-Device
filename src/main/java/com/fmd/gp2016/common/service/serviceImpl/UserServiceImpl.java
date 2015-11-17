package com.fmd.gp2016.common.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fmd.gp2016.common.dao.UserDao;
import com.fmd.gp2016.common.entity.User;
import com.fmd.gp2016.common.service.UserService;
import com.fmd.gp2016.common.util.Constants;

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
		try {
			userDao.save(user);
			user.setStatus(Constants.SUCCESS);
		} catch (Exception e) {
			user.setStatus(Constants.FAIL);// + e.toString());
		}
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
		User temp = userDao.loginByUsername(username, password);
		if (temp == null) {
			temp = new User();
			temp.setStatus(Constants.FAIL);
		} else
			temp.setStatus(Constants.SUCCESS);
		return temp;
	}

	@Override
	public User loginByEmail(String email, String password) {
		User temp = userDao.loginByEmail(email, password);
		if (temp == null) {
			temp = new User();
			temp.setStatus(Constants.FAIL);
		} else
			temp.setStatus(Constants.SUCCESS);
		return temp;
	}

}
