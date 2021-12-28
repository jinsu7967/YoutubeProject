package com.example.demo.controller;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginDto;
import com.example.demo.service.LoginService;


@Controller
@RequestMapping("/login/*")
public class LoginController {
	
	@Autowired
	private LoginService ls;
	
	@RequestMapping("/loginCheck")
	@ResponseBody
	public String LoginCheck(@RequestParam("user_email") String user_email, @RequestParam("user_pw") String user_pw, Model model,
			HttpSession session) throws Exception{
		
		
		
		ArrayList<LoginDto> user=ls.LoginCheck(user_email, user_pw);
		System.out.println("로그인 정보는 "+user);
		String resultMsg ="";
		
		if(user.isEmpty()) {
			System.out.println("로그인 실패");
			resultMsg ="<script> alert('로그인 실패 하였습니다 다시 로그인 해주세요.'); location.href='/login' </script>";
			return resultMsg;
		}else {
			session.setAttribute("user_email", user_email);
			System.out.println("로그인 성공");
			resultMsg ="<script> alert('로그인 성공'); location.href='/index' </script>";
			return resultMsg;
		}
		
		
		
	}
	
	@RequestMapping("/logout")
	@ResponseBody
	public String Logout(HttpSession session) {
		
		String resultMsg ="";
		
		session.invalidate();
		System.out.println("로그아웃");
		resultMsg ="<script> alert('로그아웃 되었습니다'); location.href='/index' </script>";
		return resultMsg;
	}
	
	
}
