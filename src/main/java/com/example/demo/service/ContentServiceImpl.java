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
public class ContentServiceImpl implements ContentService {

	@Autowired
	private SqlSession sqlSession;
	
	
	@Override
		public ArrayList<ContentDto> ContentList() throws Exception{
		ContentMapper mapper = sqlSession.getMapper(ContentMapper.class);
		return mapper.ContentList();
	}
	
	@Override
		public ArrayList<ContentDto> SearchContent(String keyword) throws Exception{
		ContentMapper mapper = sqlSession.getMapper(ContentMapper.class);
		return mapper.SearchContent(keyword);
	}
	
	
}
