package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.JoinService;
import com.example.demo.service.LoginService;

@Controller
@RequestMapping("/join/*")
public class JoinController {
	
	@Autowired
	private JoinService js;
	
	//회원가입
	@RequestMapping("/user-join") 
	@ResponseBody
	public String UserJoin(@RequestParam("user_name") String user_name,@RequestParam("user_birth") String user_birth
			,@RequestParam("user_email") String user_email,@RequestParam("user_pw") String user_pw,@RequestParam("user_pw_check") String user_pw_check) {
		
		String resultMsg = null;
		
		try {
			int idCheck=js.IdCheck(user_email); //등록된 아이디인지 체크
			int joinCheck=-1;
			
			if(idCheck==1) { //리턴된 아이디가 있다면 실행 로직
				resultMsg="<script> alert('이미 있는 아이디입니다.'); location.href=history.back(); </script>";
			}else if(user_name=="" || user_birth=="" || user_email=="" || user_pw=="" ){ //공백인 데이터 유무 확인
				resultMsg="<script> alert('가입정보를 입력해주세요.'); location.href=history.back(); </script>";
			}else { // 정규식을 통해 형식에 맞는 데이터들 입력 유무 확인
				if(user_pw.matches("[0-9|a-z|A-Z\\s]*") || !(user_birth.matches("[0-9]*")) || user_birth.length()!=8 || 
						user_pw.length()<8 || user_name.length()<2 || !(user_name.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*")) || 
						!(user_email.matches("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$")) ||
						!(user_pw.equals(user_pw_check))) {
					resultMsg="<script> alert('가입정보를 정확히 입력해주세요.'); location.href=history.back(); </script>";
				}else { //회원가입 로직
					joinCheck=js.JoinUser(user_name, user_birth, user_email, user_pw);
					System.out.println("이름 : "+user_name+" 생일 : "+user_birth+" 이메일 : "+user_email+" 비밀번호 : "+user_pw+" 가입 완료");
					resultMsg="<script> alert('회원가입 성공했습니다. 로그인 해주세요.'); location.href='/index' </script>";
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultMsg;
	}
	
	
}
