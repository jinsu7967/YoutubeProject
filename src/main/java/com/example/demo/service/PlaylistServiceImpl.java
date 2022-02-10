package com.example.demo.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ContentDto;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.PlaylistDto;
import com.example.demo.mapper.ContentMapper;
import com.example.demo.mapper.JoinMapper;
import com.example.demo.mapper.LoginMapper;
import com.example.demo.mapper.PlaylistMapper;

@Service
public class PlaylistServiceImpl implements PlaylistService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void PlaylistCreate(String email,String playlist_name) throws Exception{
		PlaylistMapper mapper = sqlSession.getMapper(PlaylistMapper.class);
		mapper.PlaylistCreate(email, playlist_name);
	}
	
	@Override
	public void playlistAdd(String playlist_name,String email,String content_num) throws Exception{
		PlaylistMapper mapper = sqlSession.getMapper(PlaylistMapper.class);
		mapper.playlistAdd(playlist_name,email,content_num);
	}
	
	@Override
	public ArrayList<PlaylistDto> MyPlaylistName(String email) throws Exception{
		PlaylistMapper mapper = sqlSession.getMapper(PlaylistMapper.class);
		return mapper.MyPlaylistName(email);
	}
	
}
