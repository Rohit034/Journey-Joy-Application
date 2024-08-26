package com.app.journeyjoy.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.journeyjoy.custom_exceptions.*;
import com.app.journeyjoy.custom_exceptions.InvalidCredentialsException;
import com.app.journeyjoy.dto.ApiResponse;
import com.app.journeyjoy.dto.AuthDTO;
import com.app.journeyjoy.dto.UserRespDTO;
import com.app.journeyjoy.entities.Role;
import com.app.journeyjoy.entities.User;
import com.app.journeyjoy.repository.UserRepository;
@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
		
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public UserRespDTO authenticateUser(AuthDTO dto) {
		User user=userRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword())
				.orElseThrow(()->new InvalidCredentialsException("Invalid email or password !!1"));
		
		return mapper.map(user, UserRespDTO.class);
	}


	@Override
	public UserRespDTO addNewUser(UserRespDTO userdto) {


		User user=mapper.map(userdto, User.class);
		if(userRepository.existsByEmail(userdto.getEmail())) {
			throw new ApiException("Email already exists !!!");
		}
		user.setRole(Role.ROLE_CUSTOMER);
		user.setPassword(encoder.encode(user.getPassword()));
		return mapper.map(userRepository.save(user), UserRespDTO.class);
	}

}
