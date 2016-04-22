package kr.nomad.mars.dto;

public class UserMedi {

	int mediSeq = 0;
	String userId = "";
	String mediname = "";
	String meditime = "";
	String medihospital = "";
	int mediweek1=0;
	int mediweek2=0;
	int mediweek3=0;
	int mediweek4=0;
	int mediweek5=0;
	int mediweek6=0;
	int mediweek7=0;
	int medialert = 0;
	String alertText = "";
	
	int eatLog = 0;
	

	public static int NON_NOTICE = 0; // 알림안함
	public static int NOTICE = 1; // 알림

	public String getAlertText() {
		if (this.medialert == NON_NOTICE) {
			return "알림안함";
		} else if (this.medialert == NOTICE) {
			return "알림";
		} else {
			return "";
		}
	}

	

	public int getMediSeq() {
		return mediSeq;
	}

	public void setMediSeq(int mediSeq) {
		this.mediSeq = mediSeq;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMediname() {
		return mediname;
	}

	public void setMediname(String mediname) {
		this.mediname = mediname;
	}

	public String getMeditime() {
		return meditime;
	}

	public void setMeditime(String meditime) {
		this.meditime = meditime;
	}

	public int getMedialert() {
		return medialert;
	}

	public void setMedialert(int medialert) {
		this.medialert = medialert;
	}

	public int getMediweek1() {
		return mediweek1;
	}

	public void setMediweek1(int mediweek1) {
		this.mediweek1 = mediweek1;
	}

	public int getMediweek2() {
		return mediweek2;
	}

	public void setMediweek2(int mediweek2) {
		this.mediweek2 = mediweek2;
	}

	public int getMediweek3() {
		return mediweek3;
	}

	public void setMediweek3(int mediweek3) {
		this.mediweek3 = mediweek3;
	}

	public int getMediweek4() {
		return mediweek4;
	}

	public void setMediweek4(int mediweek4) {
		this.mediweek4 = mediweek4;
	}

	public int getMediweek5() {
		return mediweek5;
	}

	public void setMediweek5(int mediweek5) {
		this.mediweek5 = mediweek5;
	}

	public int getMediweek6() {
		return mediweek6;
	}

	public void setMediweek6(int mediweek6) {
		this.mediweek6 = mediweek6;
	}

	public int getMediweek7() {
		return mediweek7;
	}

	public void setMediweek7(int mediweek7) {
		this.mediweek7 = mediweek7;
	}

	public String getMedihospital() {
		return medihospital;
	}

	public void setMedihospital(String medihospital) {
		this.medihospital = medihospital;
	}
	public int getEatLog() {
		return eatLog;
	}
	public void setEatLog(int eatLog) {
		this.eatLog = eatLog;
	}

}
