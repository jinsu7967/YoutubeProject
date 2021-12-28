package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.LoginDto;
import com.example.demo.dto.UploadDto;
import com.example.demo.mapper.JoinMapper;
import com.example.demo.mapper.LoginMapper;
import com.example.demo.mapper.UploadMapper;

@Service
public class UploadServiceImpl implements UploadService {

	@Autowired
	private SqlSession sqlSession;
	
	
	@Override
	public int UploadContent(String content_name,Date content_date,String user_email,String file_name) throws Exception {
		UploadMapper mapper = sqlSession.getMapper(UploadMapper.class);
		return mapper.UploadContent(content_name, content_date, user_email, file_name);
	}
	
	@Override
	public ArrayList<UploadDto> ContentList(String user_email,int pageStart,int perPageNum) throws Exception{
		UploadMapper mapper = sqlSession.getMapper(UploadMapper.class);
		return mapper.ContentList(user_email,pageStart,perPageNum);
	}
	
	@Override
	public void ContentDelete(String content_num) throws Exception{
		UploadMapper mapper = sqlSession.getMapper(UploadMapper.class);
		mapper.ContentDelete(content_num);
	}
	
	@Override
	public void ContentUpdate(String content_num,String content_name,Date content_date) throws Exception{
		UploadMapper mapper = sqlSession.getMapper(UploadMapper.class);
		mapper.ContentUpdate(content_num, content_name, content_date);
	}
	
	@Override
	public void CountUpdate(String content_num) throws Exception{
		UploadMapper mapper = sqlSession.getMapper(UploadMapper.class);
		mapper.CountUpdate(content_num);
	}
	
	@Override
	public int boardListCnt() throws Exception{
		UploadMapper mapper = sqlSession.getMapper(UploadMapper.class);
		return mapper.boardListCnt();
	}
	
	@Override
	public ArrayList<UploadDto> ContentDetail(String content_num) throws Exception{
		UploadMapper mapper = sqlSession.getMapper(UploadMapper.class);
		return mapper.ContentDetail(content_num);
	}
	
	
	
}
