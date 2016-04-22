package kr.nomad.mars.dto;

import java.util.ArrayList;
import java.util.List;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;

public class Day {
	
	int primarySeq =0;
	int daySeq = 0;
	int sort = 0;
	int pseq = 0;
	int askind = 0;
	String comment = "";
	int move = 0;
	int ansType = 0;
	int daygroup=0;
	int isLast = 0;
	List answerList = new ArrayList();
	String askText = "";
	String ansTypeText ="";
	int genderType = 0;
	
	public static int ASK_INFO = 1; // 질문
	public static int ASK_QUESTION = 2; // 답변
	
	public static int Type_INPUT=0; //입력
	public static int Type_SELEC=1; //선택
	public static int Type_TOASK=3; //질문에서 질문으로
	public static int Type_ALSEL=4; //다중선택
	
	public String getAskText() {

		if (this.askind == ASK_INFO) {
			return "질문";
		} else if (this.askind == ASK_QUESTION) {
			return "답변";
		} 
		else {
			return "";
		}
	}
	
	
	public String getAnsTypeText() {
		
		if (this.ansType == Type_INPUT) {
			return "입력";
		} else if (this.ansType == Type_SELEC) {
			return "선택";
		} else if (this.ansType == Type_TOASK) {
			return "다음질문으로";
		} else if (this.ansType == Type_ALSEL) {
			return "다중선택";
		} 
		else {
			return "";
		}
	}
	
	

	public int getGenderType() {
		return genderType;
	}


	public void setGenderType(int genderType) {
		this.genderType = genderType;
	}


	public void setAnsTypeText(String ansTypeText) {
		this.ansTypeText = ansTypeText;
	}


	public int getPrimarySeq() {
		return this.daySeq;
	}
	
	public int getDaygroup() {
		return daygroup;
	}


	public void setDaygroup(int daygroup) {
		this.daygroup = daygroup;
	}


	public List getAnswerList() {
		return answerList;
	}
	public void setAnswerList(List answerList) {
		this.answerList = answerList;
	}
	public int getDaySeq() {
		return daySeq;
	}
	public void setDaySeq(int daySeq) {
		this.daySeq = daySeq;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
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
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getMove() {
		return move;
	}
	public void setMove(int move) {
		this.move = move;
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
	

}
