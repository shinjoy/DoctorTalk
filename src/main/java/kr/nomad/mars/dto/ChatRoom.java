package kr.nomad.mars.dto;

import java.util.List;

public class ChatRoom {
	int chatRoomSeq = 0;
	int chatRoomType = 0;
	int groupSeq = 0;
	int groupType = 0;
	String lastMsgSeq = "";
	String ownerId = "";
	String ownerName = "";
	String regDate = "";
	String replyId = "";
	String replyName = "";
	String target = "";
	String title = "";
	List memberList = null;
	List lastChat = null;
	
	public int getChatRoomSeq() {
		return chatRoomSeq;
	}
	public void setChatRoomSeq(int chatRoomSeq) {
		this.chatRoomSeq = chatRoomSeq;
	}
	public int getChatRoomType() {
		return chatRoomType;
	}
	public void setChatRoomType(int chatRoomType) {
		this.chatRoomType = chatRoomType;
	}
	public int getGroupSeq() {
		return groupSeq;
	}
	public void setGroupSeq(int groupSeq) {
		this.groupSeq = groupSeq;
	}
	public int getGroupType() {
		return groupType;
	}
	public void setGroupType(int groupType) {
		this.groupType = groupType;
	}
	public String getLastMsgSeq() {
		return lastMsgSeq;
	}
	public void setLastMsgSeq(String lastMsgSeq) {
		this.lastMsgSeq = lastMsgSeq;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getReplyId() {
		return replyId;
	}
	public void setReplyId(String replyId) {
		this.replyId = replyId;
	}
	public String getReplyName() {
		return replyName;
	}
	public void setReplyName(String replyName) {
		this.replyName = replyName;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List getMemberList() {
		return memberList;
	}
	public void setMemberList(List memberList) {
		this.memberList = memberList;
	}
	public List getLastChat() {
		return lastChat;
	}
	public void setLastChat(List lastChat) {
		this.lastChat = lastChat;
	}
	
}
