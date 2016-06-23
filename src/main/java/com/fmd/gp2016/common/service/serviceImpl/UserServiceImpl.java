package com.fmd.gp2016.common.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fmd.gp2016.common.dao.UserDao;
import com.fmd.gp2016.common.entity.User;
import com.fmd.gp2016.common.service.UserService;
import com.fmd.gp2016.common.util.Constants;

@Service("UserService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public void save(User user) {

		Boolean isUniqueEmail = isUniqeEmail(user.getEmail());
		Boolean isUniqueUsername = isUniqeUsername(user.getUserName());
		Boolean isUniqueMobile = isUniqeMobileNumber(user.getMobileNo());

		if (isUniqueUsername && isUniqueEmail && isUniqueMobile) {
			try {
				userDao.save(user);
				user.setStatus(Constants.SUCCESS);
			} catch (Exception e) {
				System.err.println("User Dao Save");
				e.printStackTrace();
				user.setStatus(Constants.FAIL);
			}

		} else {
			String failMessage = Constants.FAIL;

			if (!isUniqueEmail)
				failMessage += "|" + Constants.EmailNotUniqe + "|";

			if (!isUniqueUsername)
				failMessage += "|" + Constants.UsernameNotUniqe + "|";

			if (!isUniqueMobile)
				failMessage += "|" + Constants.MobileNumberNotUniqe + "|";

			user.setStatus(failMessage);
		}

	}

	@Override
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	@Override
	public void delete(User user) {
		try {
			userDao.delete(user);
			user.setStatus(Constants.SUCCESS);
		} catch (Exception e) {
			user.setStatus(Constants.FAIL);
		}
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

	@Override
	public Boolean isUniqeUsername(String username) {
		return (userDao.selecColumntByIDNative("username", username) == null ? true : false);
	}

	@Override
	public Boolean isUniqeEmail(String email) {
		return (userDao.selecColumntByIDNative("email", email) == null ? true : false);
	}

	@Override
	public Boolean isUniqeMobileNumber(String phone) {
		return (userDao.selecColumntByIDNative("mobileNo", phone) == null ? true : false);
	}
}
