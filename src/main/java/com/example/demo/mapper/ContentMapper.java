package com.example.demo.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.ContentDto;
import com.example.demo.dto.LoginDto;


@Repository
public interface ContentMapper {
	
	public ArrayList<ContentDto> ContentList() throws Exception;

	public ArrayList<ContentDto> SearchContent(@Param("keyword") String keyword) throws Exception;
}
