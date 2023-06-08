package com.example.repository;

import com.example.models.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The interface User repository.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {


	/**
	 * Find by username optional.
	 *
	 * @param username the username
	 * @return the optional
	 */
	Optional<UserEntity> findByUsername(String username);

	/**
	 * Find by id optional.
	 *
	 * @param id the id
	 * @return the optional
	 */
	Optional<UserEntity> findById(Long id);
}
