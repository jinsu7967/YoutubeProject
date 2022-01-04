package com.example.demo.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.dto.ReplyDto;


@Repository
public interface ReplyMapper {
	
	public ArrayList<ReplyDto> ReplyList(@Param("content_num") String content_num) throws Exception;
	
	public void ReplyWrite(@Param("contentNum") String content_num,@Param("replyWriter") String replyWriter ,@Param("replyContent") String replyContent ,@Param("replyDate") String replyDate) throws Exception;
	
	public void ReplyUpdate(@Param("rno") String rno,@Param("content_num") String content_num,@Param("content") String content) throws Exception;
	
	public void ReplyDelete(@Param("rno") String rno, @Param("content_num") String content_num ,@Param("replyWriter") String replyWriter) throws Exception;
	
	public void ReplyDeleteAll(@Param("content_num") String content_num) throws Exception;
	
}
