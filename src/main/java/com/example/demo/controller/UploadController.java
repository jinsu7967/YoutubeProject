package com.example.demo.controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.example.demo.dto.LoginDto;
import com.example.demo.dto.ReplyDto;
import com.example.demo.dto.UploadDto;
import com.example.demo.service.LoginService;
import com.example.demo.service.ReplyService;
import com.example.demo.service.UploadService;


@Controller
@RequestMapping("/mypage/*")
public class UploadController {
	
	@Autowired
	private UploadService us;
	@Autowired
	private ReplyService rs;
	
	@RequestMapping("/upload")
	public String Upload(@RequestParam("content_name") String content_name,
			@RequestParam("content_date") @DateTimeFormat(pattern ="yyyy-MM-dd") Date content_date,
			String user_email,Model model,HttpSession session,@RequestParam("file-name") MultipartFile mFile,String file_name) throws Exception {
		
		UUID uuid = UUID.randomUUID();
		mFile.transferTo(new File("C:\\Users\\gridone\\eclipse-workspace\\YoutubeProject\\src\\main\\resources\\static\\upload\\"+uuid+"_"+mFile.getOriginalFilename()));
		file_name=uuid+"_"+mFile.getOriginalFilename();
		
		int result = -1;
		user_email = (String) session.getAttribute("user_email");
		result = us.UploadContent(content_name, content_date, user_email,file_name);
		System.out.println("여부는 "+result);
		
		return "redirect:/mypage";
	}
	
	@RequestMapping("/delete")
	public String Delete(@RequestParam("content_num") String content_num) throws Exception {
		
		rs.ReplyDeleteAll(content_num);
		us.ContentDelete(content_num);
		System.out.println(content_num+"번 삭제");
		
		return "redirect:/mypage";
	}
	
	@RequestMapping("/update")
	public String Update(@RequestParam("content_num") String content_num,@RequestParam("content_name") String content_name,@RequestParam("content_date") @DateTimeFormat(pattern ="yyyy-MM-dd") Date content_date) throws Exception {
		
		us.ContentUpdate(content_num, content_name, content_date);
		System.out.println(content_num+"번의 제목을 "+content_name+"으로 변경");
		
		return "redirect:/mypage";
	}
	
	@RequestMapping("/content-detail")
	public String ContentDetail(@RequestParam("contentNum") String content_num,Model model,HttpSession session) throws Exception{
		
		us.CountUpdate(content_num);
		String user_email=(String) session.getAttribute("user_email");
		ArrayList<UploadDto> contentDetail = us.ContentDetail(content_num);
		System.out.println(contentDetail);
		
		model.addAttribute("contentDetail",contentDetail);
		model.addAttribute("user_email",user_email);
		model.addAttribute("contentNum",content_num);
		
		//댓글 조회
		ArrayList<ReplyDto> reply = rs.ReplyList(content_num);
		model.addAttribute("reply",reply);
		
		System.out.println(reply);
		
		return "content-detail";
	}
	
	
	
	
	
}
