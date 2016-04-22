package kr.nomad.mars.dto;

public class UserCol {
	
	int colSeq = 0;
	String userId = "";
	int col = 0;
	int ldl = 0;
	int tg = 0;
	int hdl = 0;
	String regDate = "";
	String date = "";
	String comment="";
	
	
	
	
	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getColSeq() {
		return colSeq;
	}
	public void setColSeq(int colSeq) {
		this.colSeq = colSeq;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public int getLdl() {
		return ldl;
	}
	public void setLdl(int ldl) {
		this.ldl = ldl;
	}
	public int getTg() {
		return tg;
	}
	public void setTg(int tg) {
		this.tg = tg;
	}
	public int getHdl() {
		return hdl;
	}
	public void setHdl(int hdl) {
		this.hdl = hdl;
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
	

}
