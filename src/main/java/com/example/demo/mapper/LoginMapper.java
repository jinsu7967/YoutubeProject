package com.example.demo.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.FindPwCheckDto;
import com.example.demo.dto.LoginDto;


@Repository
public interface LoginMapper {
	
	public ArrayList<LoginDto> LoginCheck(@Param("user_email") String email,@Param("user_pw") String pw) throws Exception;
	
	public ArrayList<LoginDto> PwFind(@Param("user_email") String user_email,@Param("user_name") String user_name,@Param("user_birth") String user_birth) throws Exception;
	
	public ArrayList<FindPwCheckDto> FindUserCheck(@Param("user_email") String user_email,@Param("user_name") String user_name,@Param("user_birth") String user_birth) throws Exception;
	
	public ArrayList<LoginDto> userInfo(@Param("user_email") String user_email) throws Exception;
}
