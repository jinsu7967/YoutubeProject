package com.example.demo.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.ReplyDto;


public interface ReplyService {
	
	public ArrayList<ReplyDto> ReplyList(@Param("content_num") String content_num) throws Exception;
	
	public void ReplyWrite(@Param("content_num") String content_num,@Param("writer") String writer ,@Param("content") String content ,@Param("regDate") String regDate) throws Exception;
	
	public void ReplyUpdate(@Param("rno") String rno,@Param("content_num") String content_num ) throws Exception;
	
	public void ReplyDelete(@Param("rno") String rno, @Param("content_num") String content_num,@Param("writer") String replyWriter) throws Exception;
}
