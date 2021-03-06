package com.fmd.gp2016.common.dao;

import java.util.List;

import com.fmd.gp2016.common.entity.User;

public interface UserDao {

	public void save(User user) throws Exception;

	public void update(User user);

	public List<User> getAllUsers();

	public void delete(User user);

	public User getUserById(int id);

	public User loginByUsername(String username, String password);

	public User loginByEmail(String email, String password);

	public String selecColumntByIDNative(String culomnName, Object columnValue);
}
