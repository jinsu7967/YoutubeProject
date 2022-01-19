package com.example.demo.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.UpdateUserDto;


public interface UpdateUserService {
	
	
	public ArrayList<UpdateUserDto> UserCheck(@Param("user_name") String user_name,@Param("user_email") String user_email,@Param("user_pw") String user_pw) throws Exception;
	
	public int UserUpdate(@Param("user_email") String user_email,@Param("user_pw") String user_pw, @Param("user_name") String user_name) throws Exception;
	
	public ArrayList<UpdateUserDto> PwCheck(@Param("user_email") String user_email) throws Exception;
}
