package com.example.demo.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.LoginDto;


@Repository
public interface JoinMapper {
	
	public int JoinUser(@Param("user_name") String user_name,@Param("user_birth") String user_birth,@Param("user_email") String user_email,@Param("user_pw") String user_pw) throws Exception;
	
	public int IdCheck(@Param("user_email") String user_email) throws Exception;
}
