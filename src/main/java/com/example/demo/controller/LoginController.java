package com.example.demo.controller;

import java.io.PrintWriter;
import java.security.Principal;
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

//Security 사용으로 사용하지는 않음
@Controller
@RequestMapping("/login/*")
public class LoginController {
	
	@Autowired
	private LoginService ls;
	
	@RequestMapping("/loginCheck")
	@ResponseBody
	public void LoginCheck(Model model,
			HttpSession session,Principal principal) throws Exception{
		
		model.addAttribute("user",principal.getName());
		
		System.out.println(principal.getName());
		
	}
	
	@RequestMapping("/logout")
	@ResponseBody
	public String Logout(HttpSession session) {
		
		String resultMsg ="";
		
		session.invalidate();
		System.out.println("로그아웃");
		resultMsg ="<script> alert('로그아웃 되었습니다'); location.href='/' </script>";
		return resultMsg;
	}
	
	
}
