package com.example.demo.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.demo.dto.LoginDto;
import com.example.demo.mapper.JoinMapper;
import com.example.demo.mapper.LoginMapper;

@Service
public class JoinServiceImpl implements JoinService {

	@Autowired
	private SqlSession sqlSession;
	
	
	@Override
	public int JoinUser(String user_name, String user_birth,String user_email,String user_pw) throws Exception{
		JoinMapper mapper = sqlSession.getMapper(JoinMapper.class);
		return mapper.JoinUser(user_name, user_birth, user_email, user_pw);
	}
	
	@Override
	public int IdCheck(String user_email) throws Exception{
		JoinMapper mapper = sqlSession.getMapper(JoinMapper.class);
		return mapper.IdCheck(user_email);
	}
}
