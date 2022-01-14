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
	
	@RequestMapping("/write")
	public String Write(@RequestParam("contentNum") String content_num,@RequestParam("replyWriter") String replyWriter,
		@RequestParam("replyContent") String replyContent,@RequestParam("replyDate") String replyDate,
		HttpServletRequest req,Model model) throws Exception{
		
		if(replyContent.isEmpty()) {
			
		}else {
			rs.ReplyWrite(content_num, replyWriter, replyContent, replyDate);
		}
		
		
		
		return "redirect:/mypage/content-detail?contentNum="+content_num;
		
	}
	
	@RequestMapping("/update")
	public String Update(@RequestParam("content_num") String content_num,@RequestParam("rno") String rno,@RequestParam("content") String content) throws Exception{
		System.out.println("성공");
		rs.ReplyUpdate(rno, content_num, content);
		
		return "redirect:/mypage/content-detail?contentNum="+content_num;
	}
	
	@RequestMapping("/delete")
	public String Delete(@RequestParam("rno") String rno,
			@RequestParam("content_num") String content_num,Model model ) throws Exception{
		
		
		rs.ReplyDelete(rno,content_num);
		
		return "redirect:/mypage/content-detail?contentNum="+content_num;
	}
	
	@RequestMapping("reply-up")
	public String ReplyUp(@RequestParam("contentNum") String content_num,@RequestParam("rno") String rno,Model model) throws Exception{
		
		System.out.println(rno);
		System.out.println(content_num);
		int replyUp = rs.ReplyUp(rno,content_num);
		System.out.println("replyup :"+replyUp);
		
		return "redirect:/mypage/content-detail?contentNum="+content_num;
	}

	
	@RequestMapping("reply-down")
	public String ReplyDown(@RequestParam("contentNum") String content_num,@RequestParam("rno") String rno,Model model) throws Exception{
		
		rs.ReplyDown(rno, content_num);
		
		return "redirect:/mypage/content-detail?contentNum="+content_num;
	}
	
	
}
