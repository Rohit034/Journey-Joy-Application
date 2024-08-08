package com.app.journeyjoy.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.journeyjoy.dto.AuthDTO;
import com.app.journeyjoy.dto.UserRespDTO;
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
		
		return null;
	}

}
