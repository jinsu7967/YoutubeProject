package com.example.demo.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.ContentDto;


public interface ContentService {
	
	
	public ArrayList<ContentDto> ContentList() throws Exception;
	
	public ArrayList<ContentDto> SearchContent(@Param("keyword") String keyword) throws Exception;
}
