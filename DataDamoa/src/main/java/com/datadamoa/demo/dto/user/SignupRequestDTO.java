package com.datadamoa.demo.dto.user;
import lombok.Data;
import lombok.Getter;

import com.datadamoa.demo.enums.Gender.GenderType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Past;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Data
public class SignupRequestDTO {	
	/**
	 * username : String 
	 * password : String
	 * email : EmailFormat & String 
	 * isEmailVerify : Boolean 
	 * dateOfBirth : DATE()
	 * genderType : ENUM [MALE,FEMALE,OTHER]
	 * */
	
	@NotBlank(message="username cannot be blank")
	@Size(min=2,max=5,message="username should be between 2 and 5 characters")
	private String username;
	
	@NotBlank(message="profileName cannot be blank")
	@Size(min=2,max=10,message="profileName should be between 2 and 5 characters")
	private String profileName;
	
	@NotBlank(message="password cannot be blank")
	@Size(min=8,max=15,message="password should be between 8 and 15 characters")
	private String password;
	
	/**
	 *	TODO : Email 인증 로직 구현 후 주석 해제 
	 * */
	//@AssertTrue(message="isEmailVerify cannot be false")
	
	private boolean isEmailVerify = false;
	
	@Email(message="Invalid email address")
	private String email;
	
	@Past(message="Date of birth must be in the past")
	private Date dateOfBirth;
	
	/**
	 * TODO : Enum Validation interface, Class 개발 및 적용 필요   
	 * */
	private GenderType gender;

}
