package com.example.demo.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ContentDto;
import com.example.demo.dto.LoginDto;
import com.example.demo.mapper.ContentMapper;
import com.example.demo.mapper.JoinMapper;
import com.example.demo.mapper.LoginMapper;

@Service
public class PlaylistServiceImpl implements PlaylistService {

	@Autowired
	private SqlSession sqlSession;
	
	
	
	
	
	
	
}
