package com.example.demo.controller;

import java.io.PrintWriter;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ContentDto;
import com.example.demo.dto.FindPwCheckDto;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.ReplyDto;
import com.example.demo.dto.TestDto;
import com.example.demo.dto.UploadDto;
import com.example.demo.paging.Criteria;
import com.example.demo.paging.Paging;
import com.example.demo.service.ContentService;
import com.example.demo.service.LoginService;
import com.example.demo.service.PlaylistService;
import com.example.demo.service.ReplyService;
import com.example.demo.service.UploadService;

@Controller
public class YoutubeController {
	
	@Autowired
	private UploadService us;
	@Autowired
	private LoginService ls;
	@Autowired
	private ReplyService rs;
	@Autowired
	private ContentService cs;
	@Autowired
	private PlaylistService ps;
	
	
	

	@RequestMapping("/")
	public String Main(Model model) throws Exception{
		
		ArrayList<ContentDto> allContentList = cs.ContentList();
		model.addAttribute("allContentList",allContentList);
		System.out.println(allContentList);
		
		return "index";
	}
	
	@ResponseBody
	@RequestMapping("/search-test")
	public ArrayList<ContentDto> SearchTest(@RequestBody String keyword,Model model) throws Exception{
		
		System.out.println(keyword);
		
		ArrayList<ContentDto> searchContentList = cs.SearchContent(keyword);
		System.out.println(searchContentList);
		
		return searchContentList;
	}
	
	
	@RequestMapping("/search")
	public String Search(@RequestParam("keyword") String keyword,Model model) throws Exception{
		
		System.out.println("키워드는"+keyword);
		ArrayList<ContentDto> searchContentList = cs.SearchContent(keyword);
		if(searchContentList.isEmpty()) {
			System.out.println("결과없음");
		}
		model.addAttribute("allContentList",searchContentList);
		System.out.println(searchContentList);
		
		return "index";
		
	}
	
	@RequestMapping("/join")
	public String Join(Model model) throws Exception{
		
		
		return "join";
	}
	
	@RequestMapping("/login")
	public String Login(Model model) throws Exception{
		
		
		return "login";
	}
	
	@RequestMapping("/mypage")
	public String Mypage(Model model,HttpSession session,String user_email,Criteria cri,Principal principal) throws Exception{
		
		user_email = principal.getName();
		
		int boardListCnt = us.boardListCnt(user_email);
		System.out.println("boardListCnt : "+boardListCnt);
		
		
		Paging paging = new Paging();
		System.out.println(cri);
		paging.setCri(cri);
		paging.setTotalCount(boardListCnt);
		
		int pageStart = cri.getPageStart();
		int perPageNum = cri.getPerpageNum();
		
		System.out.println("pageStart : "+pageStart);
		System.out.println("perPageNum : "+perPageNum);
		
		
		ArrayList<UploadDto> contentList = us.ContentList(user_email, pageStart, perPageNum);
		
		
		System.out.println(contentList);
		System.out.println(paging);
		
		
		model.addAttribute("contentList",contentList);
		model.addAttribute("paging",paging);
		model.addAttribute("user",user_email);
		
		
		return "mypage";
	}
	
	@RequestMapping("/update-user-check")
	public String UpdateUserCheck(Model model,HttpSession session) throws Exception{
		
		String user_email=null;
		user_email=(String) session.getAttribute("user_email");
		System.out.println(user_email);
		model.addAttribute("user_email",user_email);
		
		return "update-user-check";
	}
	
	@RequestMapping("/user-update")
	public String UserUpdate(Model model) throws Exception{
		
		
		return "user-update";
	}
	
	
	
	@RequestMapping("/find-pw")
	public String FindPwForm(Model model) throws Exception{
		
		
		return "find-pw";
	}
	
	@RequestMapping("/find")
	public String FindPw(@RequestParam("user_email") String user_email,@RequestParam("user_name") String user_name,@RequestParam("user_birth") String user_birth,Model model,HttpServletResponse response) throws Exception{
		
		System.out.println(user_email);
		System.out.println(user_name);
		System.out.println(user_birth);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		ArrayList<LoginDto> pw =ls.PwFind(user_email,user_name,user_birth);
		ArrayList<FindPwCheckDto> pwUserCheck = ls.FindUserCheck(user_email, user_name, user_birth);
		System.out.println(pwUserCheck);
		
		if(user_email=="" || user_name=="" || user_birth=="") {
			
			out.println("<script>alert('회원정보를 입력해주세요.'); location.href=history.back(); </script>");
			return "find-pw";
			
		}else if(pwUserCheck.isEmpty()) {
			
			out.println("<script>alert('회원정보가 다릅니다.'); location.href=history.back(); </script>");
			return "find-pw";
			
		}else {
		
			String pw_info=pw.get(0).getPw();
			
			System.out.println(pw_info);
		
			model.addAttribute("user_pw",pw_info);
			
			return "find-pw-user";
		}
		
		
	}

	@RequestMapping("/mypage-update")
	public String MypageUpdate(@RequestParam("content_num") String content_num,@RequestParam("content_name") String content_name,Model model) throws Exception{
		System.out.println(content_num);
		model.addAttribute("content_num",content_num);
		model.addAttribute("content_name",content_name);
		
		
		return "mypage-update";
	}
	
	@RequestMapping("/playlist-add")
	public String PlaylistAdd(@RequestParam("content_num") String content_num,@RequestParam("playlist_name") String playlist_name,@RequestParam("email") String email,Principal principal) throws Exception{
		
		
		System.out.println(playlist_name);
		ps.playlistAdd(playlist_name, email, content_num);
		
		return "redirect:/mypage/content-detail?contentNum="+content_num;
		
	}
	
	@RequestMapping("playlist-create")
	public String playlistCreate(@RequestParam("playlistName") String playlistName,Principal principal) throws Exception{
		String email=principal.getName();
		ps.PlaylistCreate(email, playlistName);
		
		
		return "redirect:/mypage";
	}
	
	
	
	
}
