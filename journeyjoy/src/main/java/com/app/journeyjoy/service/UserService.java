package com.app.journeyjoy.service;

import com.app.journeyjoy.dto.AuthDTO;
import com.app.journeyjoy.dto.UserRespDTO;

public interface UserService {
	UserRespDTO  authenticateUser(AuthDTO dto);
}
