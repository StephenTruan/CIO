package com.hbkj.entity;

public class User {
	private int userId;
	private String userName = null;
	private String password = null;
	private String sector = null;
	private int groupId;
	public User() {
		// TODO Auto-generated constructor stub
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public User(int userId, String userName, String password, String sector, int groupId) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.sector = sector;
		this.groupId = groupId;
	}
}
