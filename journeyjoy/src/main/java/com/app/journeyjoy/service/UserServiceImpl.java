package com.app.journeyjoy.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	
	@Override
	public UserRespDTO authenticateUser(AuthDTO dto) {
		User user=userRepository.findByEmailAndPassword(dto.getEmail(), dto.getPwd())
				.orElseThrow(()->new InvalidCredentialsException("Invalid email or password !!1"));
		
		return mapper.map(user, UserRespDTO.class);
	}


	@Override
	public ApiResponse addNewUser(UserRespDTO userdto) {
		User user=mapper.map(userdto, User.class);
		
		user.setRole(Role.ROLE_CUSTOMER);
		userRepository.save(user);
		return new ApiResponse("New User is Added");
	}

}
