package com.example.demo.dto;

public class FindPwCheckDto {
	private String email,name,birth;
	
	public FindPwCheckDto() {}
	
	public FindPwCheckDto(String email, String name, String birth) {
		super();
		this.email = email;
		this.name = name;
		this.birth = birth;
	}


	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getBirth() {
		return birth;
	}
	
	public void setBirth(String birth) {
		this.birth = birth;
	}

	@Override
	public String toString() {
		return "FindPwCheckDto [email=" + email + ", name=" + name + ", birth=" + birth + "]";
	}
	
	
 
 
}
