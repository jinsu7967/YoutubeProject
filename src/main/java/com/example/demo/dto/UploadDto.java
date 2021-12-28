package com.example.demo.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;


public class UploadDto {
	private int rownum;
	private int content_num;
	private String content_name;
	private Date content_date;
	private int content_count;
	private String file_name;
	
	public UploadDto() {}
	
	public int getRownum() {
		return rownum;
	}
	public void setRownum(int rownum) {
		this.rownum = rownum;
	}
	public int getContent_num() {
		return content_num;
	}
	public void setContent_num(int content_num) {
		this.content_num = content_num;
	}
	public String getContent_name() {
		return content_name;
	}
	public void setContent_name(String content_name) {
		this.content_name = content_name;
	}
	public Date getContent_date() {
		return content_date;
	}
	public void setContent_date(Date content_date) {
		this.content_date = content_date;
	}
	public int getContent_count() {
		return content_count;
	}
	public void setContent_count(int content_count) {
		this.content_count = content_count;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public UploadDto(int rownum,int content_num, String content_name, Date content_date, int content_count, String file_name) {
		super();
		this.rownum = rownum;
		this.content_num = content_num;
		this.content_name = content_name;
		this.content_date = content_date;
		this.content_count = content_count;
		this.file_name = file_name;
	}

	@Override
	public String toString() {
		return "UploadDto [content_num=" + content_num + ", content_name=" + content_name + ", content_date="
				+ content_date + ", content_count=" + content_count + ", file_name=" + file_name +"]"+"\n";
	}


	
	
	
	
	
	
}
