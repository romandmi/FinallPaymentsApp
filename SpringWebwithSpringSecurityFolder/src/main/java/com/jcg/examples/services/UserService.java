package com.jcg.examples.services;

import com.jcg.examples.models.User;

import java.util.List;

/**
 *
 * This service helps to work with database of users
 *
 */
public interface UserService {
	/**
	 * This method find user by his login in database
	 * @param log user's login
	 * @return user
	 */
	User findByLog(String log);

	/**
	 * This method find user by his id in database
	 * @param id user's id
	 * @return user
	 */
	User findById(long id);

	/**
	 * This method save user in database
	 * @param user user
	 */
    void save(User user);

	/**
	 * This method update user in database
	 * @param user user
	 */
	void update(User user);

	/**
	 * This method delete user by his id in database
	 * @param id user's id
	 */
	void deleteById(Long id);

	/**
	 * This method select all user from database
	 * @return list of users
	 */
	List<User> selectAll();
}

