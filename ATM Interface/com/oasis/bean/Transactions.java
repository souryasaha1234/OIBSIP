package com.oasis.bean;

public class Transactions {
	int serialno = 0;
	String dateoftransact = "";
	String description = "";
	int credit = 0; 
	int debit = 0 ;
	int balance = 0;
	public int getSerialno() {
		return serialno;
	}
	public void setSerialno(int serialno) {
		this.serialno = serialno;
	}
	public String getDateoftransact() {
		return dateoftransact;
	}
	public void setDateoftransact(String dateoftransact) {
		this.dateoftransact = dateoftransact;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public int getDebit() {
		return debit;
	}
	public void setDebit(int debit) {
		this.debit = debit;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
}
