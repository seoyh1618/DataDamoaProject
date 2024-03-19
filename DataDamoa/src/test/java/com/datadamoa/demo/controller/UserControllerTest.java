package com.datadamoa.demo.controller;
import com.datadamoa.demo.controller.UserController;
import com.datadamoa.demo.service.UserService;
import com.datadamoa.demo.dto.user.SignupRequestDTO;
import com.datadamoa.demo.dto.user.SignupResponseDTO;
import com.datadamoa.demo.enums.Gender.GenderType;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Date;
import org.mockito.Mockito;
import java.util.UUID;
import java.time.Instant;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Calendar;
import org.springframework.http.MediaType;
// Controller단에서 Test를 진행 할 수 있도록 설정
@WebMvcTest(controllers=UserController.class)
public class UserControllerTest {	
	
	//mockMvc 객체를 주입 받아 처리 할 수 있도록 진행 
	@Autowired
	private MockMvc mockMvc;
	
	//WebapplicationContext를 설정하여 웹에 필요한 구조들을 가져와 테스트 할 수 있도록 진행 
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	//userService에 모의 객체를 받아 제어 및 Test할 수 있도록 설정 
	@MockBean
	private UserService userService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	public void UserSignUp_ValidTest_RequestOk() throws Exception {
		SignupRequestDTO signupRequestDTO = new SignupRequestDTO();
		signupRequestDTO.setUsername("홍길동");
		signupRequestDTO.setPassword("12345678");
		signupRequestDTO.setProfileName("홀길동");
		signupRequestDTO.setEmail("test1234@gmail.com");
		signupRequestDTO.setEmailVerify(false);
		
		
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(1998, 9, 9);
		signupRequestDTO.setDateOfBirth(calendar.getTime());
		
		signupRequestDTO.setGender(GenderType.MALE);
		
	    SignupResponseDTO signupResponseDTO = new SignupResponseDTO();
        signupResponseDTO.setId(UUID.randomUUID());
        signupResponseDTO.setUsername(signupRequestDTO.getUsername());
        signupResponseDTO.setProfileName(signupResponseDTO.getProfileName());
        signupResponseDTO.setCreateDate(new Date());


        // userService.signupUser 메서드가 호출될 때, signupResponseDTO를 반환하도록 설정합니다.
        Mockito.when(userService.sigupUser(Mockito.any(SignupRequestDTO.class)))
                .thenReturn(signupResponseDTO);

        // mockMvc를 이용하여 /signup 엔드포인트로 POST 요청을 보내고, 응답 상태가 OK(200)인지 검증합니다.
        mockMvc.perform(post("http://localhost:9090/apis/users/signup")
                .contentType(MediaType.APPLICATION_JSON) // 요청 타입을 JSON으로 설정합니다.
                .content(objectMapper.writeValueAsString(signupRequestDTO))
                .with(csrf())
        		.with(httpBasic("admin","admin")))
                .andExpect(status().isOk()); // 응답 상태가 200 OK인지 검증합니다.
                
	}
	
	
 
}
