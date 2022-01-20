package com.example.demo.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Transactional
	public int UserUpdate(String user_email,String user_pw,String user_name) throws Exception{
		UpdateUserMapper mapper = sqlSession.getMapper(UpdateUserMapper.class);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user_pw = encoder.encode(user_pw);
		return mapper.UserUpdate(user_email,user_pw,user_name);
	}
	
	@Override
	public ArrayList<UpdateUserDto> PwCheck(String user_email) throws Exception{
		UpdateUserMapper mapper = sqlSession.getMapper(UpdateUserMapper.class);
		return mapper.PwCheck(user_email);
	}
}
