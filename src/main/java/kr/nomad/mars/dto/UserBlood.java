package kr.nomad.mars.dto;

public class UserBlood {

	int bloSeq = 0;
	String userId = "";
	int bloodTime = 0;
	int bloodKind = 0;
	int bloodNum = 0;
	String regDate = "";
	String date = "";
	String bloodTimeText = "";
	int equip = 0;

	String equipText = "";

	public static int MORNING = 1; // 아침
	public static int LUNCH = 2; // 점심
	public static int DINNER = 3; // 저녁

	public static int EQUIP_NON = 0; // 장비 사용안함
	public static int EQUIP_USE = 1; // 장비 사용
	String comment="";
	
	
	
	
	public String getComment() {
	
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getBloodTimeText() {
		if (this.bloodTime == MORNING) {
			return "아침";
		} else if (this.bloodTime == LUNCH) {
			return "점심";
		} else if (this.bloodTime == DINNER) {
			return "저녁";
		} else {
			return "";
		}
	}

	public String getEquipText() {
		if (this.equip == EQUIP_NON) {
			return "장비 사용안함";
		} else if (this.equip == EQUIP_USE) {
			return "장비 사용";
		} else {
			return "";
		}
	}

	public int getBloSeq() {
		return bloSeq;
	}

	public void setBloSeq(int bloSeq) {
		this.bloSeq = bloSeq;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getBloodTime() {
		return bloodTime;
	}

	public void setBloodTime(int bloodTime) {
		this.bloodTime = bloodTime;
	}

	public int getBloodKind() {
		return bloodKind;
	}

	public void setBloodKind(int bloodKind) {
		this.bloodKind = bloodKind;
	}

	public int getBloodNum() {
		return bloodNum;
	}

	public void setBloodNum(int bloodNum) {
		this.bloodNum = bloodNum;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getDate() {
		if (regDate.length() > 8) {
			return regDate.substring(5, 10).replace("-", ".");
		} else {
			return regDate.substring(5, 7);
		}
	}

	public int getEquip() {
		return equip;
	}

	public void setEquip(int equip) {
		this.equip = equip;
	}
}