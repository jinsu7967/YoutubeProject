package com.example.demo.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.demo.dto.LoginDto;
import com.example.demo.dto.UpdateUserDto;
import com.example.demo.mapper.JoinMapper;
import com.example.demo.mapper.LoginMapper;
import com.example.demo.mapper.UpdateUserMapper;

@Service
public class UpdateUserServiceImpl implements UpdateUserService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public ArrayList<UpdateUserDto> UserCheck(String user_name,String user_email,String user_pw) throws Exception{
		UpdateUserMapper mapper = sqlSession.getMapper(UpdateUserMapper.class);
		return mapper.UserCheck(user_name, user_email, user_pw);
	}
	
	@Override
	public int UserUpdate(String user_email,String user_pw,String user_name) throws Exception{
		UpdateUserMapper mapper = sqlSession.getMapper(UpdateUserMapper.class);
		return mapper.UserUpdate(user_email,user_pw,user_name);
	}
}
