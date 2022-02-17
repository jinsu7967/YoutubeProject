package com.example.demo.controller;

import java.io.File;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.ContentDto;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.PlaylistDto;
import com.example.demo.dto.ReplyDto;
import com.example.demo.dto.UploadDto;
import com.example.demo.service.ContentService;
import com.example.demo.service.LoginService;
import com.example.demo.service.PlaylistService;
import com.example.demo.service.ReplyService;
import com.example.demo.service.UploadService;


@Controller
@RequestMapping("/mypage/*")
public class UploadController {
	
	@Autowired
	private UploadService us;
	@Autowired
	private ReplyService rs;
	@Autowired
	private ContentService cs;
	@Autowired
	private PlaylistService ps;
	
	//컨텐츠 업로드
	@RequestMapping("/upload")
	public String Upload(@RequestParam("content_name") String content_name,
			@RequestParam("content_date") @DateTimeFormat(pattern ="yyyy-MM-dd") Date content_date,
			String user_email,Model model,Principal principal,@RequestParam("file-name") MultipartFile mFile,String file_name) throws Exception {
		
		UUID uuid = UUID.randomUUID(); //랜덤아이디 생성
		
		//내부경로로 업로드 지정 및 UUID + 파일이름 추가
		mFile.transferTo(new File("C:\\Users\\gridone\\eclipse-workspace\\YoutubeProject\\src\\main\\resources\\static\\upload\\"+uuid+"_"+mFile.getOriginalFilename()));
		file_name=uuid+"_"+mFile.getOriginalFilename();
		
		int result = -1;
		user_email = principal.getName(); //로그인된 유저 아이디
		result = us.UploadContent(content_name, content_date, user_email,file_name); //컨텐츠 업로드
		System.out.println(result);
		
		return "redirect:/mypage";
	}
	
	//컨텐츠 삭제
	@RequestMapping("/delete")
	public String Delete(@RequestParam("content_num") String content_num) throws Exception {
		
		rs.ReplyDeleteAll(content_num); //모든 리플 삭제
		us.ContentDelete(content_num); //컨텐츠 삭제
		System.out.println(content_num+"의 컨텐츠 삭제");
		
		return "redirect:/mypage";
	}
	
	//컨텐츠 수정
	@RequestMapping("/update")
	public String Update(@RequestParam("content_num") String content_num,@RequestParam("content_name") String content_name,@RequestParam("content_date") @DateTimeFormat(pattern ="yyyy-MM-dd") Date content_date) throws Exception {
		
		
		us.ContentUpdate(content_num, content_name, content_date);
		
		System.out.println(content_num+"번의 제목을 "+content_name+"으로 변경");
		
		return "redirect:/mypage";
	}
	
	//컨텐츠 상세 조회
	@RequestMapping("/content-detail")
	public String ContentDetail(@RequestParam("contentNum") String content_num,Model model,HttpSession session,Principal principal) throws Exception{
		
		int replyCount = rs.ReplyCount(content_num); //댓글 갯수 확인
		us.CountUpdate(content_num); //조회수 증가 로직
		
		String user_email= principal.getName();
		
		ArrayList<UploadDto> contentDetail = us.ContentDetail(content_num);
		ArrayList<ContentDto> thumList = cs.ContentList();
		ArrayList<PlaylistDto> myPlaylist = ps.MyPlaylistName(user_email);
		
		//컨텐츠 상세조회 필요 데이터
		model.addAttribute("contentDetail",contentDetail);
		model.addAttribute("user_email",user_email);
		model.addAttribute("contentNum",content_num);
		model.addAttribute("replyCount",replyCount);
		//섬네일 리스트
		model.addAttribute("thumList",thumList);
		//나의 플레이리스트 목록 정보
		model.addAttribute("myPlaylist",myPlaylist);
		
		//댓글 조회
		ArrayList<ReplyDto> reply = rs.ReplyList(content_num);
		model.addAttribute("reply",reply);
		
		return "content-detail";
	}
	
	//댓글 수정
	@RequestMapping("/reply/update")
	public String Update(@RequestParam("content_num") String content_num,@RequestParam("rno") String rno,@RequestParam("content") String content) throws Exception{
		
		rs.ReplyUpdate(rno, content_num, content);
		
		return "redirect:/mypage/content-detail?contentNum="+content_num;
	}
	
	//댓글 삭제
	@RequestMapping("/reply/delete")
	public String Delete(@RequestParam("rno") String rno,
			@RequestParam("contentNum") String content_num,Model model ) throws Exception{
		
		rs.ReplyDelete(rno,content_num);
		
		return "redirect:/mypage/content-detail?contentNum="+content_num;
	}
	
	
	
	
	
}
