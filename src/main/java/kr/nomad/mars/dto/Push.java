package kr.nomad.mars.dto;

public class Push {
	int seq = 0;
	String os = "";
	String pushKey = "";
	String msg = "";
	String msgType = "";
	String msgKey = "";
	int pushType = 0;
	String userid = "";
	int badge = 0;
	int status = 0;
	String regDate = "";
	String serviceId = "";
	String sender = "";
	
	public static String MSG_TYPE_NOTICE = "1";			// 공지사항
	public static String MSG_TYPE_EAT_MED = "2";		// 복약알림
	public static String MSG_TYPE_NOT_CHECKED = "3";	// 기간내 미입력
	public static String MSG_TYPE_CHANGE_PERIOD = "4";	// 관리주기 변경
	public static String MSG_TYPE_CV_RISK = "5";		// CV-RISK
	
	public static String MSG_TYPE_WEEKREPORT = "6";		//주간레포트
	public static String MSG_TYPE_MONTHREPORT = "7";		// 월간레포트
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getPushKey() {
		return pushKey;
	}
	public void setPushKey(String pushKey) {
		this.pushKey = pushKey;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getMsgKey() {
		return msgKey;
	}
	public void setMsgKey(String msgKey) {
		this.msgKey = msgKey;
	}
	public int getPushType() {
		return pushType;
	}
	public void setPushType(int pushType) {
		this.pushType = pushType;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getBadge() {
		return badge;
	}
	public void setBadge(int badge) {
		this.badge = badge;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	
}
