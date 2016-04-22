package kr.nomad.mars.dto;

public class Alert {
	int messageSeq = 0;
	String userId = "";
	String userName = "";
	String messageType = "";
	String keySeq = "";
	String contents = "";
	String photo = "";
	String userPhoto = "";
	String regDate = "";
	String companyName = "";
	int viewCheck = 0;
	int companySeq = 0;
	
	public int getMessageSeq() {
		return messageSeq;
	}
	public void setMessageSeq(int messageSeq) {
		this.messageSeq = messageSeq;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getUserPhoto() {
		return userPhoto;
	}
	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getKeySeq() {
		return keySeq;
	}
	public void setKeySeq(String keySeq) {
		this.keySeq = keySeq;
	}
	public int getViewCheck() {
		return viewCheck;
	}
	public void setViewCheck(int viewCheck) {
		this.viewCheck = viewCheck;
	}
	public int getCompanySeq() {
		return companySeq;
	}
	public void setCompanySeq(int companySeq) {
		this.companySeq = companySeq;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
}
