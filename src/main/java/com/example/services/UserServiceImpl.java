package com.example.services;

import com.example.exception.BadRequestException;
import com.example.mapper.UserMapper;
import com.example.models.dto.UserDto;
import com.example.models.entity.UserEntity;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type User service.
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * Save user user entity.
	 *
	 * @param user the user
	 * @return the user entity
	 */
	@Override
	public UserEntity saveUser(UserDto user) {
		UserEntity entity = UserMapper.toEntity(user);
		entity.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(entity);
	}

	/**
	 * Delete user.
	 *
	 * @param id the id
	 */
	@Override
	public void deleteUser(Long id) {
		UserEntity user = userRepository.findById(id).orElseThrow(
				() -> new BadRequestException("Usuario no encontrado"));
		userRepository.delete(user);
	}

	/**
	 * Gets all.
	 *
	 * @return the all
	 */
	@Override
	public List<UserEntity> getAll() {
		return userRepository.findAll();
	}
}
