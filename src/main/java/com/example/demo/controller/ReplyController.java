package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String Update(@RequestParam("replyContent") String content,Model model) throws Exception{
		
		
		
		return "";
	}
	
	@RequestMapping("/delete")
	public String Delete(@RequestParam("replyWriter") String replyWriter,@RequestParam("rno") String rno,
			@RequestParam("contentNum") String content_num,Model model ) throws Exception{
		
		
		rs.ReplyDelete(rno,content_num,replyWriter);
		
		return "redirect:/mypage/content-detail?contentNum="+content_num;
	}
	
	
}
