package com.example.controller;

import com.example.models.dto.ResponseDefault;
import com.example.models.dto.UserDto;
import com.example.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.valueOf;

/**
 * The type User controller.
 */
@RestController
@RequestMapping("/v1")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * Hello string.
	 *
	 * @return the string
	 */
	@GetMapping(value = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDefault> hello() {
		Integer status = HttpStatus.OK.value();
		ResponseDefault responseDefault = new ResponseDefault(status, "mensaje", "Hello world! not security");
		return new ResponseEntity<>(responseDefault, valueOf(status));
	}

	/**
	 * Hello security string.
	 *
	 * @return the string
	 */
	@GetMapping(value = "/helloSecured", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ResponseDefault> helloSecurity() {
		Integer status = HttpStatus.OK.value();
		ResponseDefault responseDefault = new ResponseDefault(status, "mensaje", "Hello world! security");
		return new ResponseEntity<>(responseDefault, valueOf(status));
	}

	/**
	 * Create response entity.
	 *
	 * @param user the user
	 * @return the response entity
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "/createU", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDefault> create(@Valid @RequestBody UserDto user) {
		Integer status = HttpStatus.OK.value();
		ResponseDefault responseDefault = new ResponseDefault(status, "Usuario creado", userService.saveUser(user));
		return new ResponseEntity<>(responseDefault, valueOf(status));
	}

	/**
	 * Delete user string.
	 *
	 * @param id the id
	 * @return the string
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(value = "/deleteU", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDefault> deleteUser(@RequestParam Long id) {
		Integer status = HttpStatus.OK.value();
		userService.deleteUser(id);
		ResponseDefault responseDefault = new ResponseDefault(status, "Usuario borrado", null);
		return new ResponseEntity<>(responseDefault, valueOf(status));

	}
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDefault> getAll() {
		Integer status = HttpStatus.OK.value();
		ResponseDefault responseDefault = new ResponseDefault(status, "Usuario obtenidos", userService.getAll());
		return new ResponseEntity<>(responseDefault, valueOf(status));
	}

}
