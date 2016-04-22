package kr.nomad.mars.dto;

public class ReportHistory {
	int reportSeq = 0;
	String userId = "";
	String reportDate = "";
	int reportType = 0;
	int reportIdx = 0;
	int bloodCount = 0;
	int bloodTargetCount = 0;
	int bloodPercent = 0;
	int pressureCount = 0;
	int pressureTargetCount = 0;
	int pressurePercent = 0;
	int weightCount = 0;
	int weightTargetCount = 0;
	int weightPercent = 0;
	String regDate = "";
	
	public int getReportSeq() {
		return reportSeq;
	}
	public void setReportSeq(int reportSeq) {
		this.reportSeq = reportSeq;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getReportDate() {
		return reportDate;
	}
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}
	public int getReportType() {
		return reportType;
	}
	public void setReportType(int reportType) {
		this.reportType = reportType;
	}
	public int getReportIdx() {
		return reportIdx;
	}
	public void setReportIdx(int reportIdx) {
		this.reportIdx = reportIdx;
	}
	public int getBloodCount() {
		return bloodCount;
	}
	public void setBloodCount(int bloodCount) {
		this.bloodCount = bloodCount;
	}
	public int getBloodTargetCount() {
		return bloodTargetCount;
	}
	public void setBloodTargetCount(int bloodTargetCount) {
		this.bloodTargetCount = bloodTargetCount;
	}
	public int getBloodPercent() {
		return bloodPercent;
	}
	public void setBloodPercent(int bloodPercent) {
		this.bloodPercent = bloodPercent;
	}
	public int getPressureCount() {
		return pressureCount;
	}
	public void setPressureCount(int pressureCount) {
		this.pressureCount = pressureCount;
	}
	public int getPressureTargetCount() {
		return pressureTargetCount;
	}
	public void setPressureTargetCount(int pressureTargetCount) {
		this.pressureTargetCount = pressureTargetCount;
	}
	public int getPressurePercent() {
		return pressurePercent;
	}
	public void setPressurePercent(int pressurePercent) {
		this.pressurePercent = pressurePercent;
	}
	public int getWeightCount() {
		return weightCount;
	}
	public void setWeightCount(int weightCount) {
		this.weightCount = weightCount;
	}
	public int getWeightTargetCount() {
		return weightTargetCount;
	}
	public void setWeightTargetCount(int weightTargetCount) {
		this.weightTargetCount = weightTargetCount;
	}
	public int getWeightPercent() {
		return weightPercent;
	}
	public void setWeightPercent(int weightPercent) {
		this.weightPercent = weightPercent;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}


}
