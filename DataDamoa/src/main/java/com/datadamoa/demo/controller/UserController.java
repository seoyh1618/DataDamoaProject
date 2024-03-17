package com.datadamoa.demo.controller;
import com.datadamoa.demo.service.UserService;
import com.datadamoa.demo.dto.user.SignupRequestDTO;
import com.datadamoa.demo.dto.user.SignupResponseDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import com.datadamoa.demo.exceptions.exceptions.EmailAlreadyExistsException;
import com.datadamoa.demo.exceptions.exceptions.UserAlreadyExistsException;
import com.datadamoa.demo.exceptions.exceptions.UserProfileNameAlreadyExistsException;
import com.datadamoa.demo.exceptions.exceptions.ServiceException;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Controller
@RequestMapping("/apis/users")
public class UserController {
	
	private UserService userService;
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	/**
	 *  TODO : 회원가입 로직  
	 * */
	@PostMapping("/signup")
	public ResponseEntity<String> SignupUser(@RequestBody @Valid SignupRequestDTO signupRequestDTO,BindingResult bindingResult){

		System.out.println("asdaskhdbkasbdkas");
		
		if(bindingResult.hasErrors()) {
			List<String> errors = bindingResult.getAllErrors()
					.stream()
					.map(ObjectError ::getDefaultMessage)
					.collect(Collectors.toList());
			return new ResponseEntity(errors,HttpStatus.BAD_REQUEST);
		}
		try {
			
			SignupResponseDTO signupResponseDTO = userService.sigupUser(signupRequestDTO);
			return new ResponseEntity(signupResponseDTO,HttpStatus.OK);
		}catch(EmailAlreadyExistsException e) {
			return new ResponseEntity<String>("Email already exists", HttpStatus.CONFLICT);
		}catch(UserAlreadyExistsException e) {
			return new ResponseEntity<String>("User already exists", HttpStatus.CONFLICT);
		}catch(UserProfileNameAlreadyExistsException e) {
			return new ResponseEntity<String>("UserProfileName already exists", HttpStatus.CONFLICT);
		}catch(ServiceException e) {
			return new ResponseEntity<String>("Server error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 *  TODO : 로그인 로직 
	 * */
	
	/**
	 * TODO : 유저 정보를 가져오는 GET API 
	 * */
	
	/**
	 * TODO : 유저 정보를 수정하는 UPDATE API 
	 * */
	
	/**
	 * */
	
	
	
	
}
