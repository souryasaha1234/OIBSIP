package com.oasis.bean;

public class AccountUsers {
	int accNo = 0;
	int pass = 0; 
	String accholderName = ""; 
	String branchName = "";
	String transactId = "";
	public int getAccNo() {
		return accNo;
	}
	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}
	public int getPass() {
		return pass;
	}
	public void setPass(int pass) {
		this.pass = pass;
	}
	public String getAccholderName() {
		return accholderName;
	}
	public void setAccholderName(String accholderName) {
		this.accholderName = accholderName;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getTransactId() {
		return transactId;
	}
	public void setTransactId(String transactId) {
		this.transactId = transactId;
	}
}
