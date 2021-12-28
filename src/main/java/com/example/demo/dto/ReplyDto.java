package com.example.demo.dto;

import java.util.Date;

public class ReplyDto {
	private int rno;
	private int content_num;
	private String writer;
	private String content;
	private Date regDate;
	
	public ReplyDto() {}
	
	public ReplyDto(int rno, int content_num, String writer, String content, Date regDate) {
		super();
		this.rno = rno;
		this.content_num = content_num;
		this.writer = writer;
		this.content = content;
		this.regDate = regDate;
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

	@Override
	public String toString() {
		return "ReplyDto [rno=" + rno + ", content_num=" + content_num + ", writer=" + writer + ", content=" + content
				+ ", regDate=" + regDate + "]";
	}
	
	
	
}
