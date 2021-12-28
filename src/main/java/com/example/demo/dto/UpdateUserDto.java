package com.example.demo.dto;

public class UpdateUserDto {
	private String email;
	private String pw;
	private String name;
	
	public UpdateUserDto() {}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "UpdateUserDto [email=" + email + ", pw=" + pw + ", name=" + name + "]";
	}
	
	public UpdateUserDto(String email, String pw, String name) {
		super();
		this.email = email;
		this.pw = pw;
		this.name = name;
	}
	
	
	
	
	
	
}
