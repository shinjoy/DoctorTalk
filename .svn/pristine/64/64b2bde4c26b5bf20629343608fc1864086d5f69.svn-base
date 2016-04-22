package kr.nomad.mars.dto;

public class Qna {

	int qnaSeq = 0;
	String userId = "";
	String title = "";
	String contentsHtml = "";
	String contentsText = "";
	String linkUrl = "";
	String fileName = "";
	int depth = 0;
	int pseq = 0;
	int cateKind = 0;
	
	String cateText ="";
	
	public static int CATE_PRESS = 1; // 혈압
	public static int CATE_BLOOD = 2; // 혈당
	public static int CATE_COL = 3; // 콜레스테롤/당화혈색소
	public static int CATE_MEDICINE = 4; // 복약관리
	public static int CATE_MEDICAL = 5; // 문진정보
	

	
	
	public String getCateText() {
		
		if (this.cateKind == CATE_PRESS) {
			return "혈압";
		} else if (this.cateKind == CATE_BLOOD) {
			return "혈당";
		} else if (this.cateKind == CATE_COL) {
			return "콜레스테롤/당화혈색소";
		} else if (this.cateKind == CATE_MEDICINE) {
			return "복약관리";
		} else if (this.cateKind == CATE_MEDICAL) {
			return "문진정보";
		} 
		else {
			return "";
		}	
	}

	public int getQnaSeq() {
		return qnaSeq;
	}

	public void setQnaSeq(int qnaSeq) {
		this.qnaSeq = qnaSeq;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContentsHtml() {
		return contentsHtml;
	}

	public void setContentsHtml(String contentsHtml) {
		this.contentsHtml = contentsHtml;
	}

	public String getContentsText() {
		return contentsText;
	}

	public void setContentsText(String contentsText) {
		this.contentsText = contentsText;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getPseq() {
		return pseq;
	}

	public void setPseq(int pseq) {
		this.pseq = pseq;
	}

	public int getCateKind() {
		return cateKind;
	}

	public void setCateKind(int cateKind) {
		this.cateKind = cateKind;
	}

	
}
