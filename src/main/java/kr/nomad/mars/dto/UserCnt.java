package kr.nomad.mars.dto;

public class UserCnt {

	String userId = "";
	int bcnt = 30;
	int pcnt = 4;
	String regDate = "";
	int cntSeq = 0;
	int wcnt = 4;

	String pcntText = "";
	String bcntText = "";
	String wcntText = "";
	int bloodStatus =0;
	int pressStatus =0;

	public static int P_DAY = 30; // 매일
	public static int P_WEEK = 4; // 1주
	public static int P_MONTH = 1; // 1개월
	public static int P_MONTHS = 2; // 1개월2번

	public static int B_DAY = 30; // 매일
	public static int B_WEEK = 4; // 1주
	public static int B_MONTH = 1; // 1개월
	public static int B_MONTHS = 2; // 1개월2번

	public static int W_DAY = 30; // 매일
	public static int W_WEEK = 4; // 1주
	public static int W_MONTH = 1; // 1개월
	public static int W_MONTHS = 2; // 1개월2번

	public String getPcntText() {
		
		if (this.pcnt == P_DAY) {
			return "매일";
		} else if (this.pcnt == P_WEEK) {
			return "1주";
		} else if (this.pcnt == P_MONTH) {
			return "1개월";
		}else if (this.pcnt == P_MONTHS) {
			return "1개월 2번";
		}else {
			return "";
		}
	}

	public String getBcntText() {
		if (this.bcnt == B_DAY) {
			return "매일";
		} else if (this.bcnt == B_WEEK) {
			return "1주";
		} else if (this.bcnt == B_MONTH) {
			return "1개월";
		}else if (this.bcnt == B_MONTHS) {
			return "1개월 2번";
		}else {
			return "";
		}
	}

	public String getWcntText() {
		if (this.wcnt == W_DAY) {
			return "매일";
		} else if (this.wcnt == W_WEEK) {
			return "1주";
		} else if (this.wcnt == W_MONTH) {
			return "1개월";
		}else if (this.wcnt == W_MONTHS) {
			return "1개월 2번";
		}else {
			return "";
		}
	}
	
	

	public int getBloodStatus() {
		return bloodStatus;
	}

	public void setBloodStatus(int bloodStatus) {
		this.bloodStatus = bloodStatus;
	}

	public int getPressStatus() {
		return pressStatus;
	}

	public void setPressStatus(int pressStatus) {
		this.pressStatus = pressStatus;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getBcnt() {
		return bcnt;
	}

	public void setBcnt(int bcnt) {
		this.bcnt = bcnt;
	}

	

	public int getPcnt() {
		return pcnt;
	}

	public void setPcnt(int pcnt) {
		this.pcnt = pcnt;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getCntSeq() {
		return cntSeq;
	}

	public void setCntSeq(int cntSeq) {
		this.cntSeq = cntSeq;
	}

	public int getWcnt() {
		return wcnt;
	}

	public void setWcnt(int wcnt) {
		this.wcnt = wcnt;
	}



}

