package com.example.demo.dto;

public class PlaylistDto {
	private String playlist_name;
	private String email;
	private int playlist_num;
	private int content_num;
	
	public PlaylistDto() {}

	public String getPlaylist_name() {
		return playlist_name;
	}

	public void setPlaylist_name(String playlist_name) {
		this.playlist_name = playlist_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPlaylist_num() {
		return playlist_num;
	}

	public void setPlaylist_num(int playlist_num) {
		this.playlist_num = playlist_num;
	}

	public int getContent_num() {
		return content_num;
	}

	public void setContent_num(int content_num) {
		this.content_num = content_num;
	}

	public PlaylistDto(String playlist_name, String email, int playlist_num, int content_num) {
		super();
		this.playlist_name = playlist_name;
		this.email = email;
		this.playlist_num = playlist_num;
		this.content_num = content_num;
	}

	@Override
	public String toString() {
		return "PlaylistDto [playlist_name=" + playlist_name + ", email=" + email + ", playlist_num=" + playlist_num + ", content_num=" + content_num + "]";
	}

	
	
}
