package kr.nomad.mars.dto;

import java.util.ArrayList;
import java.util.List;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;

public class EperiodGroup {
	
	int kcase=0;
	List caseList = new ArrayList();
	int count = 0;
	public int getKcase() {
		return kcase;
	}
	public void setKcase(int kcase) {
		this.kcase = kcase;
	}
	public List getCaseList() {
		return caseList;
	}
	public void setCaseList(List caseList) {
		this.caseList = caseList;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
