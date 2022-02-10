package com.example.demo.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.ContentDto;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.PlaylistDto;


@Repository
public interface PlaylistMapper {
	
	public void PlaylistCreate(@Param("email") String email,@Param("playlist_name") String playlist_name) throws Exception;
	
	public void playlistAdd(@Param("playlist_name") String playlist_name,@Param("email") String email,@Param("content_num") String content_num) throws Exception;
	
	public ArrayList<PlaylistDto> MyPlaylistName(@Param("email") String email) throws Exception;
	
}
