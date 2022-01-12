package com.example.demo.dto;

import java.util.Date;

public class ReplyDto {
	private int rno;
	private int content_num;
	private String writer;
	private String content;
	private Date regDate;
	private int reply_up;
	private int reply_down;
	
	public ReplyDto() {}
	
	
	public ReplyDto(int rno, int content_num, String writer, String content, Date regDate, int reply_up, int reply_down) {
		super();
		this.rno = rno;
		this.content_num = content_num;
		this.writer = writer;
		this.content = content;
		this.regDate = regDate;
		this.reply_up = reply_up;
		this.reply_down = reply_down;
	}


	

	public int getRno() {
		return rno;
	}


	public void setRno(int rno) {
		this.rno = rno;
	}


	public int getContent_num() {
		return content_num;
	}


	public void setContent_num(int content_num) {
		this.content_num = content_num;
	}


	public String getWriter() {
		return writer;
	}


	public void setWriter(String writer) {
		this.writer = writer;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Date getRegDate() {
		return regDate;
	}


	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}


	public int getReply_up() {
		return reply_up;
	}


	public void setReply_up(int reply_up) {
		this.reply_up = reply_up;
	}


	public int getReply_down() {
		return reply_down;
	}


	public void setReply_down(int reply_down) {
		this.reply_down = reply_down;
	}


	@Override
	public String toString() {
		return "ReplyDto [rno=" + rno + ", content_num=" + content_num + ", writer=" + writer + ", content=" + content + ", regDate=" + regDate + ", reply_up="
				+ reply_up + ", reply_down=" + reply_down + "]";
	}
}
