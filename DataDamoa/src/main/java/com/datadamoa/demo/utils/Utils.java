package com.datadamoa.demo.utils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Utils {

	/**
	 * 비밀번호를 해시화 하는 메서드
	 * */ 
	public static String passwordEncoding(String rowPassword) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(rowPassword);
	} 
}
