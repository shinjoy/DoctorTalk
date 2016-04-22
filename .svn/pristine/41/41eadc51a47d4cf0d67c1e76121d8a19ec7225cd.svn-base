package kr.nomad.mars.dto;

import java.util.ArrayList;
import java.util.List;

public class Period {
	
	int preSeq = 0;
	int sort = 0;
	String diseaseId = "";
	String comment = "";
	int kind = 0;
	int pseq = 0;
	int askind = 0;
	int move = 0;
	int isLast = 0;
	String value = "";
	
	List answerList = new ArrayList();
	
	
	public static int ASK_INFO = 1; // 안내글
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

//		if (this.ansType == VALUE_INPUT) {
//			return "입력";
//		} else if (this.ansType == VALUE_SELECT) {
//			return "선택";
//		} else {
//			return "";
//		}
		
		return "";
	}
	
	public List getAnswerList() {
		return answerList;
	}
	public void setAnswerList(List answerList) {
		this.answerList = answerList;
	}
	public int getPreSeq() {
		return preSeq;
	}
	public void setPreSeq(int preSeq) {
		this.preSeq = preSeq;
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
	public int getKind() {
		return kind;
	}
	public void setKind(int kind) {
		this.kind = kind;
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


	public int getIsLast() {
		return isLast;
	}

	public void setIsLast(int isLast) {
		this.isLast = isLast;
	}
	

}
