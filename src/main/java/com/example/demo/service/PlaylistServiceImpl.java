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
	public void PlaylistAdd(String playlist_name,String email,String content_num) throws Exception{
		PlaylistMapper mapper = sqlSession.getMapper(PlaylistMapper.class);
		mapper.PlaylistAdd(playlist_name,email,content_num);
	}
	
	@Override
	public ArrayList<PlaylistDto> MyPlaylistName(String email) throws Exception{
		PlaylistMapper mapper = sqlSession.getMapper(PlaylistMapper.class);
		return mapper.MyPlaylistName(email);
	}
	
	@Override
	public void PlaylistDelete(String playlist_num) throws Exception{
		PlaylistMapper mapper = sqlSession.getMapper(PlaylistMapper.class);
		mapper.PlaylistDelete(playlist_num);
	}
	
	@Override
	public void PlaylistUpdate(String playlist_num,String playlist_name) throws Exception{
		PlaylistMapper mapper = sqlSession.getMapper(PlaylistMapper.class);
		mapper.PlaylistUpdate(playlist_num,playlist_name);
	}
	
	@Override
	public ArrayList<ContentDto> Myplaylist(int playlist_num) throws Exception{
		PlaylistMapper mapper = sqlSession.getMapper(PlaylistMapper.class);
		return mapper.Myplaylist(playlist_num);
	}
	
	@Override
	public void MyplaylistDelete(String content_num) throws Exception {
	PlaylistMapper mapper = sqlSession.getMapper(PlaylistMapper.class);
	mapper.MyplaylistDelete(content_num);
	}
	

	
}
