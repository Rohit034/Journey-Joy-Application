package com.app.journeyjoy.dto;
//fname,lname , adddress,phonenumber,

import java.time.LocalDate;

import javax.validation.constraints.Email;

import com.app.journeyjoy.entities.Role;
import com.app.journeyjoy.entities.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRespDTO {

	@JsonProperty(access = Access.READ_ONLY)
	private Long id;

	private String firstName;
	private String lastName;
	private String address;
	private String phoneno;
	private LocalDate dob;
	@Email(message = "Invalid Email!!!")
	private String email;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	private Role role;
	
	public UserRespDTO(User user){
		this.id=user.getId();
		this.firstName=user.getFirstName();
		this.lastName=user.getLastName();
		this.address=user.getAddress();
		this.phoneno=user.getPhoneno();
		this.dob=user.getDob();
		this.email=user.getEmail();
		this.password=user.getPassword();
		this.role=user.getRole();
	}
}
