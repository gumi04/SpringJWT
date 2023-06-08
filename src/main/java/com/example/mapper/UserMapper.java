package com.example.mapper;

import com.example.constants.ERole;
import com.example.models.dto.UserDto;
import com.example.models.entity.RoleEntity;
import com.example.models.entity.UserEntity;

import java.util.stream.Collectors;

public class UserMapper {

	private UserMapper(){
		super();
	}

	public static UserDto toDto(final UserEntity entity){
		UserDto dto = new UserDto();
		dto.setId(entity.getId());
		dto.setEmail(entity.getEmail());
		dto.setUsername(entity.getUsername());
		dto.setRoles(entity.getRoles()
				.stream()
				.map(role -> role.getName().name())
				.collect(Collectors.toSet()));
		return dto;
	}

	public static UserEntity toEntity(final UserDto dto){
		UserEntity entity = new UserEntity();
		entity.setEmail(dto.getEmail());
		entity.setUsername(dto.getUsername());
		entity.setPassword(dto.getPassword());
		entity.setRoles(dto.getRoles()
				.stream()
				.map(role -> RoleEntity.builder().name(ERole.valueOf(role)).build()).collect(Collectors.toSet()));
		return entity;
	}
}
