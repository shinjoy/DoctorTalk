package kr.nomad.mars.dto;



public class UserWeight {
	
	int weiSeq = 0;
	String userId = "";
	int weightNum = 0;
	double bbmi = 0;
	String regDate = "";
	String date = "";
	double tbw =0;
	double muscle =0;
	double bmd =0;
	double bmi =0;
	double bmione =0;
	int equip =0;
	
	String equipText = "";
	
	public static int EQUIP_NON = 0; // 장비 사용안함
	public static int EQUIP_USE = 1; // 장비 사용
	String comment="";
	
	
	
	
	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
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
	
	
	
	
	public double getBbmi() {
		return bbmi;
	}
	public void setBbmi(double bbmi) {
		this.bbmi = bbmi;
	}
	public double getTbw() {
		return tbw;
	}
	public void setTbw(double tbw) {
		this.tbw = tbw;
	}
	public double getMuscle() {
		return muscle;
	}
	public void setMuscle(double muscle) {
		this.muscle = muscle;
	}
	public double getBmd() {
		return bmd;
	}
	public void setBmd(double bmd) {
		this.bmd = bmd;
	}
	public int getEquip() {
		return equip;
	}
	public void setEquip(int equip) {
		this.equip = equip;
	}
	public int getWeiSeq() {
		return weiSeq;
	}
	public void setWeiSeq(int weiSeq) {
		this.weiSeq = weiSeq;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getDate() {
		if(regDate.length()>8){
			return regDate.substring(5,10).replace("-", ".");
		}else{
			return regDate.substring(5,7);
		}
	}




	public double getBmione() {
		String str = String.format("%.1f" , this.bmi);
		
		return Double.parseDouble(str);
	}




	

}
