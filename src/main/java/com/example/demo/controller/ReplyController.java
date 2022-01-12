package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
		
		
		rs.ReplyWrite(content_num, replyWriter, replyContent, replyDate);
		
		
		return "redirect:/mypage/content-detail?contentNum="+content_num;
		
	}
	
	@RequestMapping("/update")
	public String Update(@RequestParam("updateContent") String content,@RequestParam("replyRno") String replyRno,@RequestParam("replyConNum") String replyConNum,Model model) throws Exception{
		
		rs.ReplyUpdate(replyRno,replyConNum, content);
		
		return "redirect:/mypage/content-detail?contentNum="+replyConNum;
	}
	
	@RequestMapping("/delete")
	public String Delete(@RequestParam("replyWriter") String replyWriter,@RequestParam("rno") String rno,
			@RequestParam("contentNum") String content_num,Model model ) throws Exception{
		
		
		rs.ReplyDelete(rno,content_num,replyWriter);
		
		return "redirect:/mypage/content-detail?contentNum="+content_num;
	}
	
	@RequestMapping("reply-up")
	public String ReplyUp(@RequestParam("contentNum") String content_num,@RequestParam("rno") String rno,Model model) throws Exception{
		
		System.out.println(rno);
		System.out.println(content_num);
		
		int a = rs.ReplyUp(rno, content_num);
		
		System.out.println(a);
		
		
		return "redirect:/mypage/content-detail?contentNum="+content_num;
	}
	
	@RequestMapping("reply-down")
	public String ReplyDown(@RequestParam("contentNum") String content_num,@RequestParam("rno") String rno,Model model) throws Exception{
		
		rs.ReplyDown(rno, content_num);
		
		return "redirect:/mypage/content-detail?contentNum="+content_num;
	}
	
	
}
