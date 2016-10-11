package com.hbkj.model;

public class AdviceBean {
	private int adviceId;
	private String content = null;
	private int adviceAgreeCount;
	private String adviceLevel = null;
	private int userId;
	public int getAdviceId() {
		return adviceId;
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
