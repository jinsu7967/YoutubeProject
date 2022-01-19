package com.example.demo.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.LoginDto;
import com.example.demo.dto.UpdateUserDto;


@Repository
public interface UpdateUserMapper {
	
	public ArrayList<UpdateUserDto> UserCheck(@Param("user_name") String user_name,@Param("user_email") String user_email,@Param("user_pw") String user_pw) throws Exception;
	
	public int UserUpdate(@Param("user_email") String user_email,@Param("user_pw") String user_pw,@Param("user_name") String user_name) throws Exception;
	
	public ArrayList<UpdateUserDto> PwCheck(@Param("user_email") String user_email) throws Exception;
}
