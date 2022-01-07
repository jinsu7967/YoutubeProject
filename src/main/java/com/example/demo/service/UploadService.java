package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.UploadDto;


public interface UploadService {
	
	public int UploadContent(@Param("content_name") String content_name,@Param("content_date") Date content_date,String user_email,@Param("file_name") String file_name) throws Exception;
	
	public ArrayList<UploadDto> ContentList(@Param("user_email") String user_email,@Param("pageStart") int pageStart, @Param("perPageNum") int perPageNum) throws Exception;
	
	public void ContentDelete(@Param("content_num") String content_num) throws Exception;
	
	public void ContentUpdate(@Param("content_num") String content_num,@Param("content_name") String content_name,@Param("content_date") Date content_date) throws Exception;
	
	public void CountUpdate(@Param("content_num") String content_num) throws Exception;
	
	public int boardListCnt(String content_writer) throws Exception;
	
	public ArrayList<UploadDto> ContentDetail(@Param("content_num") String content_num) throws Exception;
}
