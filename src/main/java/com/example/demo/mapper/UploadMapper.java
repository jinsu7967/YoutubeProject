package com.example.demo.mapper;

import java.util.ArrayList;
import java.util.Date;

import org.apache.ibatis.annotations.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.LoginDto;
import com.example.demo.dto.UploadDto;


@Repository
public interface UploadMapper {
	
	public int UploadContent(@Param("content_name") String content_name,@Param("content_date") Date content_date,String user_email,@Param("file_name") String file_name) throws Exception;
	
	public ArrayList<UploadDto> ContentList(@Param("user_email") String user_email ,@Param("pageStart") int pageStart, @Param("perPageNum") int perPageNum) throws Exception;
	
	public void ContentDelete(@Param("content_num") String content_num) throws Exception;
	
	public void ContentUpdate(@Param("content_num") String content_num,@Param("content_name") String content_name,@Param("content_date") Date content_date) throws Exception;
	
	public void CountUpdate(@Param("content_num") String content_num) throws Exception;
	
	public int boardListCnt() throws Exception;
	
	public ArrayList<UploadDto> ContentDetail(@Param("content_num") String content_num) throws Exception;
}
