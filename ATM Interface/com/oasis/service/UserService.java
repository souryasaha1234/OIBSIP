package com.oasis.service;

public interface UserService {
	public void transactionHistoryService(int accNo);
	public String withdrawService(int accNo, int amount); 
	public String depositService(int accNo, int amount); 
	public String transferService(int fromAccNo, int toAccNo, int amount); 
	public String loginService(int accNo, int pass); 	
}
