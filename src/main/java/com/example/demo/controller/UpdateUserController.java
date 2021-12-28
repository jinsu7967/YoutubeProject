package com.example.demo.controller;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	
	@RequestMapping("/UserCheck")
	public String UserCheck(@RequestParam("user_name") String user_name,Model model
			,@RequestParam("user_email") String user_email,@RequestParam("user_pw") String user_pw,HttpServletResponse response) throws Exception{
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
	
		
		ArrayList<UpdateUserDto> userInfo =uus.UserCheck(user_name, user_email, user_pw);
		
		if(userInfo.isEmpty()) {
			out.println("<script>alert('본인인증 실패했습니다.'); location.href=history.back(); </script>");
		}else {
			model.addAttribute("userInfo", userInfo);
			out.println("<script>alert('본인인증 성공.'); </script>");
			System.out.println("회원 : "+model+"님이 맞습니다");
			
		}
		
		return "user-update";
	}
	
	@RequestMapping("/UserUpdate")
	public void UpdateUser(@RequestParam("user_email") String user_email,@RequestParam("user_pw") String user_pw,@RequestParam("user_name") String user_name,Model model,HttpServletResponse response) throws Exception{
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		int a=uus.UserUpdate(user_email,user_pw,user_name);
		
		System.out.println(a);
		
		System.out.println(user_email);
		System.out.println(user_pw);
		System.out.println(user_name);
		
		System.out.println("정보변경 성공");
		out.println("<script>alert('정보변경 완료.'); location.href='/index' </script>");
	}
	
	
}
