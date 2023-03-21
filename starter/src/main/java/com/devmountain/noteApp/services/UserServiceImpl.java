package com.devmountain.noteApp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.devmountain.noteApp.dtos.UserDto;
import com.devmountain.noteApp.model.UserEntity;
import com.devmountain.noteApp.repositories.UserRepository;


@Service
public class UserServiceImpl implements UserService {
    @Autowired  
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
	@Transactional
    public List<String> addUser(UserDto userDto) {
        List<String> response = new ArrayList<>();
        UserEntity user = new UserEntity(userDto);
        userRepository.saveAndFlush(user);
        response.add("User added successfully");
        return response;
    }

    @Override
	public List<String> userLogin(UserDto userDto) {
        List<String> response = new ArrayList<>();
        Optional<UserEntity> userOptional = userRepository.findByUsername(userDto.getUsername());
        if(userOptional.isPresent()) {
            if(passwordEncoder.matches(userDto.getPassword(), userOptional.get().getPassword())) {
                response.add("User login successful");
                response.add(String.valueOf(userOptional.get().getId()));
            } else {
                response.add("Username or password is incorrect");
            }
        } else {
            response.add("Username or password is incorrect");
        }
        return response;
    }



}
