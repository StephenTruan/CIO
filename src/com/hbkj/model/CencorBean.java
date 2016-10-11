package com.hbkj.model;

public class CencorBean {
	private int cencorId;
	private String cencorName = null;
	private String password = null;
	private String sector = null;
	public int getCencorId() {
		return cencorId;
	}
	public void setCencorId(int cencorId) {
		this.cencorId = cencorId;
	}
	public String getCencorName() {
		return cencorName;
	}
	public void setCencorName(String cencorName) {
		this.cencorName = cencorName;
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
}
