package com.datadamoa.demo.dto.user;
import jakarta.validation.Valid;
import java.util.UUID;
import java.util.Date;
import lombok.Data;
@Data
public class SignupResponseDTO {
	private UUID id;
	private String username;
	private String profileName;
	private Date createDate;
}
