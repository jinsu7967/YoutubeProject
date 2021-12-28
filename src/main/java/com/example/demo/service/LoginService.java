package com.example.demo.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.FindPwCheckDto;
import com.example.demo.dto.LoginDto;

public interface LoginService {
	
	
	public ArrayList<LoginDto> LoginCheck(String email,String pw) throws Exception;
	
	public ArrayList<LoginDto> PwFind(@Param("user_email") String user_email,@Param("user_name") String user_name,@Param("user_birth") String user_birth) throws Exception;
	
	public ArrayList<FindPwCheckDto> FindUserCheck(@Param("user_email") String user_email,@Param("user_name") String user_name,@Param("user_birth") String user_birth) throws Exception;
	
}
