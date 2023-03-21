package com.devmountain.noteApp.services;

import java.util.List;

import javax.transaction.Transactional;

import com.devmountain.noteApp.dtos.UserDto;

public interface UserService {

	List<String> addUser(UserDto userDto);

	List<String> userLogin(UserDto userDto);

}