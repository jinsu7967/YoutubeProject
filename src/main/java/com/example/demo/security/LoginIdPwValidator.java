package com.example.demo.security;

import java.security.Principal;
import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.demo.dto.LoginDto;
import com.example.demo.mapper.LoginMapper;

@Service
public class LoginIdPwValidator implements UserDetailsService {
	
	@Autowired
	private SqlSession sqlSession;
	
	//엔코더 빈 등록
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//로그인 과정
	@Override
	public UserDetails loadUserByUsername(String insertedId) throws UsernameNotFoundException{
		
		LoginMapper mapper = sqlSession.getMapper(LoginMapper.class);
		
		ArrayList<LoginDto> user = null;
		try {
			
			ArrayList<LoginDto> userInfo = mapper.userInfo(insertedId);
			String userPw = userInfo.get(0).getPw();
			user = mapper.LoginCheck(insertedId,userPw);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (user==null) {
			return null;
		}
		
		String pw = user.get(0).getPw();
		System.out.println("id="+insertedId);
		System.out.println("pw="+pw);
		
		
		return User.builder()
				.username(insertedId)
				.password(pw)
				.roles("user")
				.build();
	}

	
}

