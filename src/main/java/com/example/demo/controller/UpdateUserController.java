package com.example.demo.controller;

import java.io.PrintWriter;
import java.security.Principal;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.dto.UpdateUserDto;
import com.example.demo.service.JoinService;
import com.example.demo.service.LoginService;
import com.example.demo.service.UpdateUserService;

@Controller
@RequestMapping("/update-user-check/*")
public class UpdateUserController {
	
	@Autowired
	private UpdateUserService uus;
	
	//개인정보 변경 전 본인 확인
	@RequestMapping("/user-check")
	public String UserCheck(@RequestParam("user_name") String user_name,Model model,@RequestParam("user_email") String user_email
			,@RequestParam("user_pw") String user_pw,HttpServletResponse response,Principal principal) throws Exception{
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		ArrayList<UpdateUserDto> user_pw_check = uus.PwCheck(user_email);
		
		if(encoder.matches(user_pw,user_pw_check.get(0).getPw())) {
			user_pw = user_pw_check.get(0).getPw();
			ArrayList<UpdateUserDto> userInfo = uus.UserCheck(user_name,user_email,user_pw);
			model.addAttribute("userInfo", userInfo);
			System.out.println(user_name);
			System.out.println(user_email);
			System.out.println(user_pw);
			System.out.println(userInfo);
			out.println("<script>alert('본인인증 성공.'); </script>");
		}else {
			out.println("<script>alert('본인인증 실패했습니다.'); location.href=history.back(); </script>");
		}

		return "user-update";
	}
	
	//개인정보 변경
	@RequestMapping("/user-update")
	public void UpdateUser(@RequestParam("user_email") String user_email,@RequestParam("user_pw") String user_pw,@RequestParam("user_name") String user_name,Model model,HttpServletResponse response) throws Exception{
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		
		if(user_pw.length()<8 || user_pw.matches("[0-9|a-z|A-Z\\\\s]*") || !(user_name.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*"))) {
			out.println("<script>alert('정보변경 실패 입력내용을 확인해주세요.'); location.href=history.back(); </script>");
		}else {
			int userUpdate = uus.UserUpdate(user_email,user_pw,user_name);
			if(userUpdate == 1 ) {
				System.out.println("정보변경 성공");
			}else {
				System.out.println("정보변경 실패");
			}
			
			out.println("<script>alert('정보변경 완료.'); location.href='/' </script>");
		}
		
	}
	
	
}
