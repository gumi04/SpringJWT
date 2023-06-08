package com.example.services;

import com.example.models.dto.UserDto;
import com.example.models.entity.UserEntity;

import java.util.List;

/**
 * The interface User service.
 */
public interface UserService {
	/**
	 * Save user user entity.
	 *
	 * @param user the user
	 * @return the user entity
	 */
	UserEntity saveUser(UserDto user);

	/**
	 * Delete user.
	 *
	 * @param id the id
	 */
	void deleteUser(Long id);

	/**
	 * Gets all.
	 *
	 * @return the all
	 */
	List<UserEntity> getAll();

}
