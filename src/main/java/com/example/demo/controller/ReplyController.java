package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ReplyDto;
import com.example.demo.service.ReplyService;

@Controller
@RequestMapping("/reply/*")
public class ReplyController {
	
	@Autowired
	private ReplyService rs;
	
	//댓글 작성
	@RequestMapping("/write")
	public String Write(@RequestParam("contentNum") String content_num,@RequestParam("replyWriter") String replyWriter,
		@RequestParam("replyContent") String replyContent,@RequestParam("replyDate") String replyDate,Model model) throws Exception{
		
		//댓글 내용이 비어있다면 실행되지 않는 로직
		if(!(replyContent.isEmpty())) {
			rs.ReplyWrite(content_num, replyWriter, replyContent, replyDate);
		}

		return "redirect:/mypage/content-detail?contentNum="+content_num;
		
	}
	
	//댓글 수정
	@RequestMapping("/update")
	public String Update(@RequestParam("content_num") String content_num,@RequestParam("rno") String rno,@RequestParam("content") String content) throws Exception{
		
		rs.ReplyUpdate(rno, content_num, content);
		
		return "redirect:/mypage/content-detail?contentNum="+content_num;
	}
	
	//댓글 삭제
	@RequestMapping("/delete")
	public String Delete(@RequestParam("rno") String rno,@RequestParam("content_num") String content_num,Model model ) throws Exception{
		
		rs.ReplyDelete(rno,content_num);
		
		return "redirect:/mypage/content-detail?contentNum="+content_num;
	}
	
	//좋아요
	@RequestMapping("reply-up")
	public String ReplyUp(@RequestParam("contentNum") String content_num,@RequestParam("rno") String rno,Model model) throws Exception{
		
		int replyUp = rs.ReplyUp(rno,content_num);
		System.out.println("성공여부 "+replyUp);
		
		return "redirect:/mypage/content-detail?contentNum="+content_num;
	}

	//싫어요
	@RequestMapping("reply-down")
	public String ReplyDown(@RequestParam("contentNum") String content_num,@RequestParam("rno") String rno,Model model) throws Exception{
		
		rs.ReplyDown(rno, content_num);
		
		return "redirect:/mypage/content-detail?contentNum="+content_num;
	}
	
	
}
