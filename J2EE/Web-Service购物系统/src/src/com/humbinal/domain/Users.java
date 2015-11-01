package com.humbinal.domain;

public class Users {

	/*Id number primary key,//用户ID
	name varchar2(50) not null,//用户密码
	password varchar2(50) not null,//密码
	email varchar2(100) not null,//邮箱
	tel varchar2(20) not null,//电话
	grade number(2) default 1 not null
*/
	private int id;
	private String name;
	private String password;
	private String email;
	private String tel;
	private int grade;
	
	
	public Users(int id, String password) {
		super();
		this.id = id;
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
}
