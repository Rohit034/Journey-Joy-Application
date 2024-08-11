package com.app.journeyjoy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.journeyjoy.dto.ApiResponse;
import com.app.journeyjoy.dto.AuthDTO;
import com.app.journeyjoy.dto.UserRespDTO;
import com.app.journeyjoy.service.DestinationService;
import com.app.journeyjoy.service.HotelService;
import com.app.journeyjoy.service.TourService;
import com.app.journeyjoy.service.UserService;



@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private TourService tourService;
	
	@Autowired
	private DestinationService destinationService;
	
	@Autowired
	private HotelService hotelService;
	
	

	
	@PostMapping("/signin")
	public ResponseEntity<?> userSignIn(@RequestBody AuthDTO dto){
		try {
			UserRespDTO respDto=userService.authenticateUser(dto);
			return ResponseEntity.ok(respDto);
			
		}catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(e.getMessage()));
		}
	}
	
	@PostMapping("/userRegistration")
	public ResponseEntity<?> userRegistration(@RequestBody UserRespDTO udto){
		try {
		ApiResponse registeruser=userService.addNewUser(udto);
		return ResponseEntity.status(HttpStatus.CREATED).body(registeruser);
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(e.getMessage()));
		}
	}
}
