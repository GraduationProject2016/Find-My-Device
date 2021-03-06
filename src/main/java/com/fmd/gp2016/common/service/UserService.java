package com.fmd.gp2016.common.service;

import java.util.List;

import com.fmd.gp2016.common.entity.User;

public interface UserService {

	public void save(User user);

	public void update(User user);

	public List<User> getAllUsers();

	public void delete(User user);

	public User getUserById(int id);

	public User loginByUsername(String username, String password);

	public User loginByEmail(String email, String password);

	public Boolean isUniqeUsername(String username);

	public Boolean isUniqeEmail(String email);

	public Boolean isUniqeMobileNumber(String phone);
}
