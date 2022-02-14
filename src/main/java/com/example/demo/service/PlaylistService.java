package com.example.demo.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.ContentDto;
import com.example.demo.dto.PlaylistDto;


public interface PlaylistService {
	
	public void PlaylistCreate(@Param("email") String email,@Param("playlist_name") String playlist_name) throws Exception;
	
	public void PlaylistAdd(@Param("playlist_name") String playlist_name,@Param("email") String email,@Param("content_num") String content_num) throws Exception;
	
	public ArrayList<PlaylistDto> MyPlaylistName(@Param("email") String email) throws Exception;
	
	public void PlaylistDelete(@Param("playlist_num") String playlist_num) throws Exception;
	
	public void PlaylistUpdate(@Param("playlist_num") String playlist_num,@Param("playlist_name") String playlist_name) throws Exception;
	
	public ArrayList<ContentDto> Myplaylist(@Param("playlist_name") String playlist_name,@Param("email") String email) throws Exception;
}
