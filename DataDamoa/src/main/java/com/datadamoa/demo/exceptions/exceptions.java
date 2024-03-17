package com.datadamoa.demo.exceptions;

public class exceptions {
	/**
	 * 유저가 이미 존재하는경우 
	 * */
	public static class UserAlreadyExistsException extends RuntimeException {
		public UserAlreadyExistsException(String message) {
			super(message);
		}
	}
	
	/**
	 * 이메일이 중복되는경우 
	 * */
	public static class EmailAlreadyExistsException extends RuntimeException{
		public EmailAlreadyExistsException(String message) {
			super(message);
		}
	}
	
	
	/**
	 * 닉네임이 중복되는경우 
	 * */
	public static class UserProfileNameAlreadyExistsException extends RuntimeException{
		public UserProfileNameAlreadyExistsException(String message) {
			super(message);
		}
	}
	
	/**
	 * 닉네임이 중복되는경우 
	 * */
	public static class ServiceException extends RuntimeException{
		public ServiceException(String message) {
			super(message);
		}
	}
	
	
	
}
