package com.example.demo.dto;

public class LoginDto {
	private String email;
	private String pw;
	
	public LoginDto() {}
	
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
	
	@Override
	public String toString() {
		return "LoginDto [email=" + email + ", pw=" + pw + "]";
	}
	
	public LoginDto(String email, String pw) {
		super();
		this.email = email;
		this.pw = pw;
	}
	
	
}
