package com.example.demo.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.demo.dto.LoginDto;
import com.example.demo.dto.ReplyDto;
import com.example.demo.mapper.JoinMapper;
import com.example.demo.mapper.LoginMapper;
import com.example.demo.mapper.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private SqlSession sqlSession;
	
	
	@Override
	public ArrayList<ReplyDto> ReplyList(String content_num) throws Exception{
		ReplyMapper mapper = sqlSession.getMapper(ReplyMapper.class);
		return mapper.ReplyList(content_num);
	}


	@Override
	public void ReplyWrite(String content_num, String writer, String content, String regDate) throws Exception {
		ReplyMapper mapper = sqlSession.getMapper(ReplyMapper.class);
		mapper.ReplyWrite(content_num, writer, content, regDate);
	}


	@Override
	public void ReplyUpdate(String rno, String content_num,String content) throws Exception {
		ReplyMapper mapper = sqlSession.getMapper(ReplyMapper.class);
		mapper.ReplyUpdate(rno,content_num,content);
	}
	
	@Override
	public void ReplyDeleteAll(String content_num) throws Exception{
		ReplyMapper mapper = sqlSession.getMapper(ReplyMapper.class);
		mapper.ReplyDeleteAll(content_num);
	}


	@Override
	public void ReplyDelete(String rno, String content_num) throws Exception {
		ReplyMapper mapper = sqlSession.getMapper(ReplyMapper.class);
		mapper.ReplyDelete(rno,content_num);
	}
	
	@Override
	public int ReplyCount(String content_num) throws Exception {
		ReplyMapper mapper = sqlSession.getMapper(ReplyMapper.class);
		return mapper.ReplyCount(content_num);
	}
	
	@Override
	public int ReplyUp(String content_num, String rno) throws Exception {
		ReplyMapper mapper = sqlSession.getMapper(ReplyMapper.class);
		 return mapper.ReplyUp(content_num,rno);
	}
	
	@Override
	public int ReplyDown(String content_num,String rno) throws Exception {
		ReplyMapper mapper = sqlSession.getMapper(ReplyMapper.class);
		return mapper.ReplyDown(content_num,rno);
		
	}
	
	
}
