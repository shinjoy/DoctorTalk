package kr.nomad.mars.dto;

public class UserBasic {

	int basicSeq = 0;
	String userId = "";
	int gender = 0;
	int height = 0;
	int weight = 0;
	int waist = 0;
	int smoke = 0;
	String heiwieght = "";
	String blood = "";
	String press = "";
	String col = "";
	String regDate = "";

	String haveHistory = "";
	String familyHistory = "";
	String drugHistory = "";
	String oralKind = "";
	String oralAmount = "";
	String oralUse = "";
	String insulinKind = "";
	String insulinAmount = "";
	String insulinUse = "";
	String medicalReserve = "";

	String genderText = "";
	String smokeText = "";
	String bloodText = "";
	
	String comment = "";
	
	int bloodNum = 0;
	int pulse = 0;
	int splessure = 0;
	int dplessure = 0;
	int weightNum = 0;
	double bmi = 0;
	int hdl = 0;
	int ldl = 0;
	int colNum = 0;
	int tg = 0;

	public static int MAN = 1;
	public static int WOMAN = 2;

	public static int NON_SMOKER = 0; // 비흡연자
	public static int SMOKER = 1; // 흡연자
	

	public String getGenderText() {

		if (this.gender == MAN) {
			return "남자";
		} else if (this.gender == WOMAN) {
			return "여자";
		} else {
			return "";
		}
	}

	public String getSmokeText() {
		if (this.smoke == NON_SMOKER) {
			return "비흡연자";
		} else if (this.smoke == SMOKER) {
			return "흡연자";
		} else {
			return "";
		}
	}

	public int getBasicSeq() {
		return basicSeq;
	}

	public void setBasicSeq(int basicSeq) {
		this.basicSeq = basicSeq;
	}

	public int getHdl() {
		return hdl;
	}

	public void setHdl(int hdl) {
		this.hdl = hdl;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getWaist() {
		return waist;
	}

	public void setWaist(int waist) {
		this.waist = waist;
	}

	public int getSmoke() {
		return smoke;
	}

	public void setSmoke(int smoke) {
		this.smoke = smoke;
	}

	public String getBlood() {
		return blood;
	}

	public void setBlood(String blood) {
		this.blood = blood;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getPress() {
		return press;
	}

	public void setPress(String press) {
		this.press = press;
	}

	public String getCol() {
		return col;
	}

	public void setCol(String col) {
		this.col = col;
	}

	public int getBloodNum() {
		return bloodNum;
	}

	public void setBloodNum(int bloodNum) {
		this.bloodNum = bloodNum;
	}

	public int getPulse() {
		return pulse;
	}

	public void setPulse(int pulse) {
		this.pulse = pulse;
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

	public int getWeightNum() {
		return weightNum;
	}

	public void setWeightNum(int weightNum) {
		this.weightNum = weightNum;
	}

	public double getBmi() {
		return bmi;
	}

	public void setBmi(double bmi) {
		this.bmi = bmi;
	}

	public int getLdl() {
		return ldl;
	}

	public void setLdl(int ldl) {
		this.ldl = ldl;
	}

	public int getColNum() {
		return colNum;
	}

	public void setColNum(int colNum) {
		this.colNum = colNum;
	}

	public int getTg() {
		return tg;
	}

	public void setTg(int tg) {
		this.tg = tg;
	}

	public String getHeiwieght() {
		return heiwieght;
	}

	public void setHeiwieght(String heiwieght) {
		this.heiwieght = heiwieght;
	}

	public String getHaveHistory() {
		return haveHistory;
	}

	public void setHaveHistory(String haveHistory) {
		this.haveHistory = haveHistory;
	}

	public String getFamilyHistory() {
		return familyHistory;
	}

	public void setFamilyHistory(String familyHistory) {
		this.familyHistory = familyHistory;
	}

	public String getDrugHistory() {
		return drugHistory;
	}

	public void setDrugHistory(String drugHistory) {
		this.drugHistory = drugHistory;
	}

	public String getOralKind() {
		return oralKind;
	}

	public void setOralKind(String oralKind) {
		this.oralKind = oralKind;
	}

	public String getOralAmount() {
		return oralAmount;
	}

	public void setOralAmount(String oralAmount) {
		this.oralAmount = oralAmount;
	}

	public String getOralUse() {
		return oralUse;
	}

	public void setOralUse(String oralUse) {
		this.oralUse = oralUse;
	}

	public String getInsulinKind() {
		return insulinKind;
	}

	public void setInsulinKind(String insulinKind) {
		this.insulinKind = insulinKind;
	}

	public String getInsulinAmount() {
		return insulinAmount;
	}

	public void setInsulinAmount(String insulinAmount) {
		this.insulinAmount = insulinAmount;
	}

	public String getInsulinUse() {
		return insulinUse;
	}

	public void setInsulinUse(String insulinUse) {
		this.insulinUse = insulinUse;
	}

	public String getMedicalReserve() {
		return medicalReserve;
	}

	public void setMedicalReserve(String medicalReserve) {
		this.medicalReserve = medicalReserve;
	}

	public void setGenderText(String genderText) {
		this.genderText = genderText;
	}

	public void setSmokeText(String smokeText) {
		this.smokeText = smokeText;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
