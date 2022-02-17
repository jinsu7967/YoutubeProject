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
import com.example.demo.dto.PlaylistDto;
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
	
	
	
	//접속시 페이지
	@RequestMapping("/")
	public String Main(Model model) throws Exception{
		
		ArrayList<ContentDto> allContentList = cs.ContentList();
		model.addAttribute("allContentList",allContentList);
		
		return "index";
	}
	
	//유튜브 메인페이지의 검색 기능
	@ResponseBody
	@RequestMapping("/search")
	public ArrayList<ContentDto> SearchTest(@RequestBody String keyword,Model model) throws Exception{
		
		ArrayList<ContentDto> searchContentList = cs.SearchContent(keyword);
		
		return searchContentList;
	}
	
	//회원가입 Form 페이지 
	@RequestMapping("/join")
	public String Join(Model model) throws Exception{
		
		return "join";
	}
	
	//로그인 Form 페이지 
	@RequestMapping("/login")
	public String Login(Model model) throws Exception{
		
		return "login";
	}
	
	//마이페이지 
	@RequestMapping("/mypage")
	public String Mypage(Model model,HttpSession session,String user_email,Criteria cri,Principal principal) throws Exception{
		
		user_email = principal.getName();
		
		int boardListCnt = us.boardListCnt(user_email);
		
		Paging paging = new Paging();
		
		paging.setCri(cri);
		paging.setTotalCount(boardListCnt);
		
		int pageStart = cri.getPageStart();
		int perPageNum = cri.getPerpageNum();
		
		ArrayList<UploadDto> contentList = us.ContentList(user_email, pageStart, perPageNum);
		ArrayList<PlaylistDto> playlist = ps.MyPlaylistName(user_email);
		
		//마이페이지 사용 될 데이터
		model.addAttribute("contentList",contentList);
		model.addAttribute("paging",paging);
		model.addAttribute("user",user_email);
		
		//내가 생성한 재생목록
		model.addAttribute("playlist",playlist);
		
		return "mypage";
	}
	
	//본인확인 Form 페이지
	@RequestMapping("/update-user-check")
	public String UpdateUserCheck(Model model,HttpSession session) throws Exception{
		
		return "update-user-check";
	}
	
	//비밀번호 찾기 Form 페이지
	@RequestMapping("/find-pw")
	public String FindPwForm(Model model) throws Exception{
		
		return "find-pw";
	}
	
	//개인정보변경 기능
	@RequestMapping("/find")
	public String FindPw(@RequestParam("user_email") String user_email,@RequestParam("user_name") String user_name,@RequestParam("user_birth") String user_birth,Model model,HttpServletResponse response) throws Exception{
		
		
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
			
			model.addAttribute("user_email",user_email);
			model.addAttribute("user_pw",pw_info);
			
			return "find-pw-user";
		}
		
		
	}
	//재생목록 수정
	@RequestMapping("/mypage-update")
	public String MypageUpdate(@RequestParam("content_num") String content_num,@RequestParam("content_name") String content_name,Model model) throws Exception{
		
		model.addAttribute("content_num",content_num);
		model.addAttribute("content_name",content_name);
		
		
		return "mypage-update";
	}
	//재생목록에 컨텐츠 추가
	@RequestMapping("/playlist-add")
	public String PlaylistAdd(@RequestParam("content_num") String content_num,@RequestParam("playlist_name") String playlist_name,@RequestParam("email") String email,Principal principal) throws Exception{
		
		ps.PlaylistAdd(playlist_name, email, content_num);
		
		return "redirect:/mypage/content-detail?contentNum="+content_num;
		
	}
	
	//재생목록 생성
	@RequestMapping("playlist-create")
	public String playlistCreate(@RequestParam("playlistName") String playlistName,Principal principal) throws Exception {
		
		String email=principal.getName();
		ps.PlaylistCreate(email, playlistName);
		
		return "redirect:/mypage";
	}
	
	//재생목록 삭제
	@RequestMapping("/playlist-delete")
	public String playlistDelete(@RequestParam("playlist_num") String playlist_num) throws Exception{
		
		ps.PlaylistDelete(playlist_num);
		
		return "redirect:/mypage";
	}
	
	//재생목록 수정
	@RequestMapping("/playlist-update")
	public String playlistUpdate(@RequestParam("playlist_num")String playlist_num,@RequestParam("playlist_name") String playlist_name) throws Exception {
		
		ps.PlaylistUpdate(playlist_num,playlist_name);
		
		return "redirect:/mypage";
	}
	
	//재생목록에 추가된 컨텐츠 목록 삭제
	@RequestMapping("/myplaylistcontent-delete")
	public String MyplaylistContetnDelete(@RequestParam("content_num") String content_num) throws Exception {
		
		ps.MyplaylistDelete(content_num);
		
		return "redirect:/mypage";
	}
	
	//재생목록에 추가되어있는 컨텐츠 목록 정보
	@ResponseBody
	@RequestMapping("/myplaylist")
	public ArrayList<ContentDto> Myplaylist(@RequestBody PlaylistDto playlistDto,Model model) throws Exception{
		
		String playlist_name=playlistDto.getPlaylist_name();
		String email=playlistDto.getEmail();
		
		ArrayList<ContentDto> playlistData =ps.Myplaylist(playlist_name, email);
		
		
		return playlistData;
	}
	
	
}
