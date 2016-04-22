package kr.nomad.mars.dto;

public class UserMediLog {
	int mlogSeq = 0;
	int medSeq = 0;
	String regDate = "";
	public int getMlogSeq() {
		return mlogSeq;
	}
	public void setMlogSeq(int mlogSeq) {
		this.mlogSeq = mlogSeq;
	}
	public int getMedSeq() {
		return medSeq;
	}
	public void setMedSeq(int medSeq) {
		this.medSeq = medSeq;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

}
