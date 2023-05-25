package com.oasis.dao;

public interface UserDao {
	public void transactionHistory(int accNo); //Select Query
	public String withdraw(int accNo, int amount); //Non Select Query
	public String deposit(int accNo, int amount); //Non Select Query
	public String transfer(int fromAccNo, int toAccNo, int amount); //Non Select Query
	public String login(int accNo, int pass);
}
