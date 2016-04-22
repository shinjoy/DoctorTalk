package kr.nomad.mars.dto;

import java.util.ArrayList;
import java.util.List;

public class Eperiod {
	
	int primarySeq =0;
	int eperSeq =0;
	int kcase = 0;
	int sort = 0;
	String diseaseId = "";
	String comment = "";
	int pseq = 0;
	int askind = 0;
	int move = 0;
	String value = "";
	List answerList = new ArrayList();
	int ansType = 0;
	int isLast = 0;
	
	
	String askText = "";
	String valueText = "";

	public static int ASK_INFO = 1; // 안내글
	public static int ASK_QUESTION = 2; // 질문
	public static int ASK_ANSWER = 3; // 답변

	public static int VALUE_INPUT = 0; // 입력
	public static int VALUE_SELECT = 1; // 선택

	public String getAskText() {

		if (this.askind == ASK_INFO) {
			return "질문";
		} else if (this.askind == ASK_QUESTION) {
			return "답변";
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
		} else {
			return "";
		}
	}
	
	
	public int getAnsType() {
		return ansType;
	}
	public void setAnsType(int ansType) {
		this.ansType = ansType;
	}
	public int getIsLast() {
		return isLast;
	}
	public void setIsLast(int isLast) {
		this.isLast = isLast;
	}
	public List getAnswerList() {
		return answerList;
	}
	public void setAnswerList(List answerList) {
		this.answerList = answerList;
	}
	public int getEperSeq() {
		return eperSeq;
	}
	public void setEperSeq(int eperSeq) {
		this.eperSeq = eperSeq;
	}
	
	public int getKcase() {
		return kcase;
	}
	public int getPrimarySeq() {
	
		return this.eperSeq;
	}
	/*public void setPrimarySeq(int primarySeq) {
		this.primarySeq = primarySeq;
	}*/
	public void setKcase(int kcase) {
		this.kcase = kcase;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
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
	public int getPseq() {
		return pseq;
	}
	public void setPseq(int pseq) {
		this.pseq = pseq;
	}
	public int getAskind() {
		return askind;
	}
	public void setAskind(int askind) {
		this.askind = askind;
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
	
	

}
