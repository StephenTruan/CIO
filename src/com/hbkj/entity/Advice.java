package com.hbkj.entity;

public class Advice {
	private int adviceId;
	private String content = null;
	private int adviceAgreeCount;
	private String adviceLevel = null;
	private int userId;
	private String sector;
	public int getAdviceId() {
		return adviceId;
	}
	
	public Advice(int adviceId, String content, int adviceAgreeCount, String adviceLevel, int userId, String sector) {
		super();
		this.adviceId = adviceId;
		this.content = content;
		this.adviceAgreeCount = adviceAgreeCount;
		this.adviceLevel = adviceLevel;
		this.userId = userId;
		this.sector = sector;
	}

	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public void setAdviceId(int adviceId) {
		this.adviceId = adviceId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getAdviceAgreeCount() {
		return adviceAgreeCount;
	}
	public void setAdviceAgreeCount(int adviceAgreeCount) {
		this.adviceAgreeCount = adviceAgreeCount;
	}
	public String getAdviceLevel() {
		return adviceLevel;
	}
	public void setAdviceLevel(String adviceLevel) {
		this.adviceLevel = adviceLevel;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
}
