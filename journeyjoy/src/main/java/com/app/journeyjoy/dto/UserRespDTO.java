package com.app.journeyjoy.dto;
//fname,lname , adddress,phonenumber,

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class UserRespDTO {
	private String firstName;
	private String lastName;
	private String address;
	private String phoneno;
	private LocalDate dob;
	private String email;
	private String password;
}
