package com.example.demo.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;


public interface JoinService {
	
	
	public int JoinUser(@Param("user_name") String user_name,@Param("user_birth") String user_birth,@Param("user_email") String user_email,@Param("user_pw") String user_pw) throws Exception;
	
	public int IdCheck(@Param("user_email") String user_email) throws Exception;
}
