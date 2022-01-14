package com.example.demo.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class ContentDto {
	
	private int content_num;
	private String content_name;
	private Date content_date;
	private int content_count;
	private String file_name;
	private String content_writer;
	
	public ContentDto() {}
	
	

	public ContentDto(int content_num, String content_name, Date content_date, int content_count, String file_name, String content_writer) {
		super();
		this.content_num = content_num;
		this.content_name = content_name;
		this.content_date = content_date;
		this.content_count = content_count;
		this.file_name = file_name;
		this.content_writer = content_writer;
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



	public String getContent_writer() {
		return content_writer;
	}



	public void setContent_writer(String content_writer) {
		this.content_writer = content_writer;
	}



	@Override
	public String toString() {
		return "ContentDto [content_num=" + content_num + ", content_name=" + content_name + ", content_date="
				+ content_date + ", content_count=" + content_count + ", file_name=" + file_name + ", content_writer="
				+ content_writer + "]";
	}



	
	
	
}
