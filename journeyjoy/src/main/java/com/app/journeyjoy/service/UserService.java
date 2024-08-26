package com.app.journeyjoy.service;

import com.app.journeyjoy.dto.ApiResponse;
import com.app.journeyjoy.dto.AuthDTO;
import com.app.journeyjoy.dto.UserRespDTO;
import com.app.journeyjoy.entities.User;

public interface UserService {
	UserRespDTO  authenticateUser(AuthDTO dto);
	UserRespDTO addNewUser(UserRespDTO userdto);
	
}
