package com.datadamoa.demo.model;
import lombok.Getter;
import lombok.Setter; 
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Column;
import java.util.UUID;
import java.util.Date;
import java.sql.Timestamp;

import jakarta.persistence.Enumerated;
import java.util.Calendar;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.datadamoa.demo.enums.Gender.GenderType;
@Getter
@Setter
@Entity
@Table(name="AppUser", uniqueConstraints= {
		@UniqueConstraint(columnNames= {"username","email","profileName"}) 
})
public class User {
	@Id
	@Column(name="id",columnDefinition="UUID",updatable=false)
	private UUID id = UUID.randomUUID();
	
	@Column(name="username",length=20,nullable=false)
	private String username;
	
	@Column(name="email",length=255, nullable=false)
	private String email;
	
	@Column(name="isEmailVerify",nullable=false)
	private boolean isEmailVerify=false;
	
	@Column(name="createDate",nullable=false,updatable=false)
	private Timestamp createDate = new Timestamp(System.currentTimeMillis());
	
	@Column(name="expiredDate",nullable=false)
	private Timestamp expiredDate = this.calculateExpiredDate();
	
	@Column(name="updateDate",nullable=false)
	private Timestamp updateDate = new Timestamp(System.currentTimeMillis());
	
	@Column(name="crawlExecuteCount")
	private Integer crawlExecuteCount = 0;
	
	@Column(name="loginToken",length=255)
	private String loginToken;
	
	@Column(name="resetPasswordToken",length=255)
	private String resetPasswordToken;
	
	@Column(name="liveStatus",nullable=false)
	private boolean liveStatus = false;
	
	@Column(name="dateOfBirth")
	private Date dateOfBirth;
	
	/**
	 * Enum Value of Gender
	 * - MALE: 남성
	 * - FEMALE: 여성
	 * - OTHER: 기타
	 */
	@Enumerated(EnumType.STRING)
	@Column(name="gender")
	private GenderType gender;
	
	@Column(name="profileName",length=50,nullable=false)
	private String profileName;

	@Column(name="password", nullable=false)
	private String password;
	   
	// 한 달 후의 Timestamp 계산
	private Timestamp calculateExpiredDate(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, 1);
		return new Timestamp(calendar.getTimeInMillis());
	}

}
