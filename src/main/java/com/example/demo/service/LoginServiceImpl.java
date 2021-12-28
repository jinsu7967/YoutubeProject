package com.example.demo.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.FindPwCheckDto;
import com.example.demo.dto.LoginDto;
import com.example.demo.mapper.LoginMapper;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private SqlSession sqlSession;
	
	
	@Override
	public ArrayList<LoginDto> LoginCheck(String email,String pw) throws Exception{
		LoginMapper mapper = sqlSession.getMapper(LoginMapper.class);
		return mapper.LoginCheck(email,pw);
	}
	
	@Override
	public ArrayList<LoginDto> PwFind(String user_email,String user_name,String user_birth) throws Exception{
		LoginMapper mapper = sqlSession.getMapper(LoginMapper.class);
		return mapper.PwFind(user_email,user_name,user_birth);
	} 
	
	@Override
	public ArrayList<FindPwCheckDto> FindUserCheck(String user_email,String user_name,String user_birth) throws Exception{
		LoginMapper mapper = sqlSession.getMapper(LoginMapper.class);
		return mapper.FindUserCheck(user_email,user_name,user_birth);
	} 
}
