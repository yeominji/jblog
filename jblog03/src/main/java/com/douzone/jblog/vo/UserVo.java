package com.douzone.jblog.vo;

public class UserVo {


   
   private String id;
   private String name;
   private String password;
   private String join_date;
   private String role;

   
   public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}
public String getId() {
	return id;
}
public String getName() {
	return name;
}
public String getPassword() {
	return password;
}
public String getJoin_date() {
	return join_date;
}
public void setId(String id) {
	this.id = id;
}
public void setName(String name) {
	this.name = name;
}
public void setPassword(String password) {
	this.password = password;
}
public void setJoin_date(String join_date) {
	this.join_date = join_date;
}

@Override
public String toString() {
	return "UserVo [id=" + id + ", name=" + name + ", password=" + password + ", join_date=" + join_date + ", role="
			+ role + "]";
}

   
   
   
   
}

   
   
   
   