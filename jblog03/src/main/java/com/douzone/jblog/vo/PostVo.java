package com.douzone.jblog.vo;

public class PostVo {
 private Long no;
 private String title;
 private String cotents;
 private String regDate;
 private int categoryNo;
public void setNo(Long no) {
	this.no = no;
}
public void setTitle(String title) {
	this.title = title;
}
public void setCotents(String cotents) {
	this.cotents = cotents;
}
public void setRegDate(String regDate) {
	this.regDate = regDate;
}
public void setCategoryNo(int categoryNo) {
	this.categoryNo = categoryNo;
}
@Override
public String toString() {
	return "PostVo [no=" + no + ", title=" + title + ", cotents=" + cotents + ", regDate=" + regDate + ", categoryNo="
			+ categoryNo + "]";
}
public Long getNo() {
	return no;
}
public String getTitle() {
	return title;
}
public String getCotents() {
	return cotents;
}
public String getRegDate() {
	return regDate;
}
public int getCategoryNo() {
	return categoryNo;
}
 
 
 
 
}
