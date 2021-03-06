package kr.nomad.mars.dto;

public class UserCvrisk {
	
	int cvSeq = 0;
	String userId = "";
	int cvNum = 0;
	String regDate = "";
	int userTage =0;
	int col =0;
	int hdl =0;
	int splessure =0;
	int dplessure =0;
	int smoke =0;
	int userAge =0;
	
	String smokeText = "";
	
	public static int NON_SMOKER = 0; // 비흡연자
	public static int SMOKER = 1; // 흡연자
	
	public String getSmokeText() {
		if (this.smoke == NON_SMOKER) {
			return "비흡연자";
		} else if (this.smoke == SMOKER) {
			return "흡연자";
		} else {
			return "";
		}
	}
	
	
	
	
	public int getSplessure() {
		return splessure;
	}
	public void setSplessure(int splessure) {
		this.splessure = splessure;
	}
	public int getDplessure() {
		return dplessure;
	}
	public void setDplessure(int dplessure) {
		this.dplessure = dplessure;
	}
	public int getSmoke() {
		return smoke;
	}
	public void setSmoke(int smoke) {
		this.smoke = smoke;
	}
	public int getUserAge() {
		return userAge;
	}
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}
	public int getUserTage() {
		return userTage;
	}
	public void setUserTage(int userTage) {
		this.userTage = userTage;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public int getHdl() {
		return hdl;
	}
	public void setHdl(int hdl) {
		this.hdl = hdl;
	}
	public int getCvSeq() {
		return cvSeq;
	}
	public void setCvSeq(int cvSeq) {
		this.cvSeq = cvSeq;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getCvNum() {
		return cvNum;
	}
	public void setCvNum(int cvNum) {
		this.cvNum = cvNum;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	

}
