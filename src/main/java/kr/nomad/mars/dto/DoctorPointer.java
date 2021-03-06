package kr.nomad.mars.dto;

public class DoctorPointer {

	int comSeq = 0;
	String diseaseId = "";
	String comment = "";
	int sort = 0;
	int askind = 0;
	int pseq = 0;
	int move = 0;
	String value = "";
	int ansType = 0;
	String qtype = "";
	int ansvalue = 0;
	int isLast = 0;
	
	String askText = "";
	String valueText = "";
	
	public static int ASK_QUESTION = 1; // 질문
	public static int ASK_ANSWER = 2; // 답변
	
	public static int VALUE_INPUT = 0; // 입력
	public static int VALUE_SELECT = 1; // 선택
	
	public String getAskText() {

		if (this.askind == ASK_QUESTION) {
			return "질문";
		} else if (this.askind == ASK_ANSWER) {
			return "답변";
		} else {
			return "";
		}
	}
	
	public String getValueText() {

		if (this.ansType == VALUE_INPUT) {
			return "입력";
		} else if (this.ansType == VALUE_SELECT) {
			return "선택";
		}else{
			return "";
		}
	}
	
	

	public int getComSeq() {
		return comSeq;
	}

	public void setComSeq(int comSeq) {
		this.comSeq = comSeq;
	}

	public String getDiseaseId() {
		return diseaseId;
	}

	public void setDiseaseId(String diseaseId) {
		this.diseaseId = diseaseId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}



	public int getAskind() {
		return askind;
	}

	public void setAskind(int askind) {
		this.askind = askind;
	}

	public int getPseq() {
		return pseq;
	}

	public void setPseq(int pseq) {
		this.pseq = pseq;
	}

	public int getMove() {
		return move;
	}

	public void setMove(int move) {
		this.move = move;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getAnsType() {
		return ansType;
	}

	public void setAnsType(int ansType) {
		this.ansType = ansType;
	}

	public String getQtype() {
		return qtype;
	}

	public void setQtype(String qtype) {
		this.qtype = qtype;
	}

	public int getAnsvalue() {
		return ansvalue;
	}

	public void setAnsvalue(int ansvalue) {
		this.ansvalue = ansvalue;
	}

	public int getIsLast() {
		return isLast;
	}

	public void setIsLast(int isLast) {
		this.isLast = isLast;
	}
	
	
}
