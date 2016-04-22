package kr.nomad.mars.dto;

import java.util.ArrayList;
import java.util.List;

public class Month {

	int monthSeq = 0;
	int sort = 0;
	int pseq = 0;
	int askind = 0;
	String comment = "";
	int move = 0;
	int ansType = 0;
	String month = "";
	String url ="";
	String thumFile ="";
	int isLast = 0;
	List answerList = new ArrayList();
	int primarySeq =0;
	public static int ASK_INFO = 1; // 안내글
	public static int ASK_QUESTION = 2; // 질문
	public static int ASK_ANSWER = 3; // 답변

	public static int VALUE_INPUT = 0; // 입력
	public static int VALUE_SELECT = 1; // 선택

	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getThumFile() {
		return thumFile;
	}

	public void setThumFile(String thumFile) {
		this.thumFile = thumFile;
	}

	public int getPrimarySeq() {
		return this.monthSeq;
	}

	public void setPrimarySeq(int primarySeq) {
		this.primarySeq = primarySeq;
	}

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
	

	public int getMonthSeq() {
		return monthSeq;
	}

	public void setMonthSeq(int monthSeq) {
		this.monthSeq = monthSeq;
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

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
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
	
}
