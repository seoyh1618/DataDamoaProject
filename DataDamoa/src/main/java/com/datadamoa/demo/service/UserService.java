package com.datadamoa.demo.service;
import lombok.Getter;
import lombok.Setter;
import com.datadamoa.demo.repository.UserRepository;
import com.datadamoa.demo.dto.user.SignupRequestDTO;
import com.datadamoa.demo.dto.user.SignupResponseDTO;
import com.datadamoa.demo.utils.Utils;
import com.datadamoa.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
public class UserService {
	
	private UserRepository userRepository;
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	/**
	 * username : String 
	 * password : String
	 * email : EmailFormat & String 
	 * isEmailVerify : Boolean 
	 * dateOfBirth : DATE()
	 * genderType : ENUM [MALE,FEMALE,OTHER]
	 * */
	public SignupResponseDTO sigupUser(SignupRequestDTO signupRequestDTO) {
		User user = new User();
		
		user.setUsername(signupRequestDTO.getUsername());
		String rowPassword = signupRequestDTO.getPassword();
		String encodedPassword = Utils.passwordEncoding(rowPassword);
		
		user.setPassword(encodedPassword);
		
		user.setEmail(signupRequestDTO.getEmail());
		
		user.setEmailVerify(signupRequestDTO.isEmailVerify());
		
		user.setDateOfBirth(signupRequestDTO.getDateOfBirth());
		
		user.setGender(signupRequestDTO.getGender());
		user.setProfileName(signupRequestDTO.getProfileName());
		
		User signupedUser = userRepository.save(user);
		
		SignupResponseDTO signupResponseDTO = new SignupResponseDTO();
		signupResponseDTO.setId(signupedUser.getId());
		signupResponseDTO.setCreateDate(signupedUser.getCreateDate());
		signupResponseDTO.setUsername(signupedUser.getUsername());
		signupResponseDTO.setProfileName(signupedUser.getProfileName());
		
		return signupResponseDTO;
	}
	
}
