package kr.nomad.mars.dto;

public class UserReport {

	int repSeq = 0;
	String userId = "";
	int type = 0;
	int beforeEatBloodCnt = 0;
	int afterEatBloodCnt = 0;
	int beforeSleepBloodCnt = 0;
	int totalBloodCnt = 0;
	int totalPressureCnt = 0;
	int totalWeightCnt = 0;
	int beforeEatBlood = 0;
	int goalBloodCnt = 0;
	int goalPressureCnt = 0;
	int goalWeightCnt = 0;
	int afterEatBlood = 0;
	int beforeSleepBlood = 0;
	int spressure = 0;
	int bpressure = 0;
	double bmi = 0;
	int goalBeforeEatSblood = 0;
	int goalBeforeEatBblood = 0;
	int goalAfterEatSblood = 0;
	int goalAfterEatBblood = 0;
	int goalBeforeSleepSblood = 0;
	int goalBeforeSleepBblood = 0;
	int goalSpressure = 0;
	int goalBpressure = 0;
	double goalSbmi = 0;
	double goalBbmi = 0;
	String regDate = "";

	
	
	public int getGoalBloodCnt() {
		return goalBloodCnt;
	}
	public void setGoalBloodCnt(int goalBloodCnt) {
		this.goalBloodCnt = goalBloodCnt;
	}
	public int getGoalPressureCnt() {
		return goalPressureCnt;
	}
	public void setGoalPressureCnt(int goalPressureCnt) {
		this.goalPressureCnt = goalPressureCnt;
	}
	public int getGoalWeightCnt() {
		return goalWeightCnt;
	}
	public void setGoalWeightCnt(int goalWeightCnt) {
		this.goalWeightCnt = goalWeightCnt;
	}
	public int getRepSeq() {
		return repSeq;
	}
	public void setRepSeq(int repSeq) {
		this.repSeq = repSeq;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getBeforeEatBloodCnt() {
		return beforeEatBloodCnt;
	}
	public void setBeforeEatBloodCnt(int beforeEatBloodCnt) {
		this.beforeEatBloodCnt = beforeEatBloodCnt;
	}
	public int getAfterEatBloodCnt() {
		return afterEatBloodCnt;
	}
	public void setAfterEatBloodCnt(int afterEatBloodCnt) {
		this.afterEatBloodCnt = afterEatBloodCnt;
	}
	public int getBeforeSleepBloodCnt() {
		return beforeSleepBloodCnt;
	}
	public void setBeforeSleepBloodCnt(int beforeSleepBloodCnt) {
		this.beforeSleepBloodCnt = beforeSleepBloodCnt;
	}
	public int getTotalBloodCnt() {
		return totalBloodCnt;
	}
	public void setTotalBloodCnt(int totalBloodCnt) {
		this.totalBloodCnt = totalBloodCnt;
	}
	public int getTotalPressureCnt() {
		return totalPressureCnt;
	}
	public void setTotalPressureCnt(int totalPressureCnt) {
		this.totalPressureCnt = totalPressureCnt;
	}
	public int getTotalWeightCnt() {
		return totalWeightCnt;
	}
	public void setTotalWeightCnt(int totalWeightCnt) {
		this.totalWeightCnt = totalWeightCnt;
	}
	public int getBeforeEatBlood() {
		return beforeEatBlood;
	}
	public void setBeforeEatBlood(int beforeEatBlood) {
		this.beforeEatBlood = beforeEatBlood;
	}
	public int getAfterEatBlood() {
		return afterEatBlood;
	}
	public void setAfterEatBlood(int afterEatBlood) {
		this.afterEatBlood = afterEatBlood;
	}
	public int getBeforeSleepBlood() {
		return beforeSleepBlood;
	}
	public void setBeforeSleepBlood(int beforeSleepBlood) {
		this.beforeSleepBlood = beforeSleepBlood;
	}
	public int getSpressure() {
		return spressure;
	}
	public void setSpressure(int spressure) {
		this.spressure = spressure;
	}
	public int getBpressure() {
		return bpressure;
	}
	public void setBpressure(int bpressure) {
		this.bpressure = bpressure;
	}
	
	public int getGoalBeforeEatSblood() {
		return goalBeforeEatSblood;
	}
	public void setGoalBeforeEatSblood(int goalBeforeEatSblood) {
		this.goalBeforeEatSblood = goalBeforeEatSblood;
	}
	public int getGoalBeforeEatBblood() {
		return goalBeforeEatBblood;
	}
	public void setGoalBeforeEatBblood(int goalBeforeEatBblood) {
		this.goalBeforeEatBblood = goalBeforeEatBblood;
	}
	public int getGoalAfterEatSblood() {
		return goalAfterEatSblood;
	}
	public void setGoalAfterEatSblood(int goalAfterEatSblood) {
		this.goalAfterEatSblood = goalAfterEatSblood;
	}
	public int getGoalAfterEatBblood() {
		return goalAfterEatBblood;
	}
	public void setGoalAfterEatBblood(int goalAfterEatBblood) {
		this.goalAfterEatBblood = goalAfterEatBblood;
	}
	public int getGoalBeforeSleepSblood() {
		return goalBeforeSleepSblood;
	}
	public void setGoalBeforeSleepSblood(int goalBeforeSleepSblood) {
		this.goalBeforeSleepSblood = goalBeforeSleepSblood;
	}
	public int getGoalBeforeSleepBblood() {
		return goalBeforeSleepBblood;
	}
	public void setGoalBeforeSleepBblood(int goalBeforeSleepBblood) {
		this.goalBeforeSleepBblood = goalBeforeSleepBblood;
	}
	public int getGoalSpressure() {
		return goalSpressure;
	}
	public void setGoalSpressure(int goalSpressure) {
		this.goalSpressure = goalSpressure;
	}
	public int getGoalBpressure() {
		return goalBpressure;
	}
	public void setGoalBpressure(int goalBpressure) {
		this.goalBpressure = goalBpressure;
	}
	
	public void setGoalSbmi(int goalSbmi) {
		this.goalSbmi = goalSbmi;
	}

	public void setGoalBbmi(int goalBbmi) {
		this.goalBbmi = goalBbmi;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public double getBmi() {
		return bmi;
	}
	public void setBmi(double bmi) {
		this.bmi = bmi;
	}
	public double getGoalSbmi() {
		return goalSbmi;
	}
	public void setGoalSbmi(double goalSbmi) {
		this.goalSbmi = goalSbmi;
	}
	public double getGoalBbmi() {
		return goalBbmi;
	}
	public void setGoalBbmi(double goalBbmi) {
		this.goalBbmi = goalBbmi;
	}

	
}
