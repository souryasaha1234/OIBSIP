package com.oasis.service;

import com.oasis.dao.UserDao;
import com.oasis.factory.UserDaoFactory;

public class UserServiceImpl implements UserService {
	UserDao userdao = UserDaoFactory.getUserDao();
	String status = "";
	@Override
	public void transactionHistoryService(int accNo) {
		userdao.transactionHistory(accNo);
	}

	@Override
	public String withdrawService(int accNo, int amount) {
		status = userdao.withdraw(accNo, amount);
		return status;
	}

	@Override
	public String depositService(int accNo, int amount) {
		status = userdao.deposit(accNo, amount);
		return status;
	}

	@Override
	public String transferService(int fromAccNo, int toAccNo, int amount) {
		status = userdao.transfer(fromAccNo, toAccNo, amount);
		return status;
	}
	@Override
	public String loginService(int accNo, int pass) {
		status = userdao.login(accNo, pass);
		return status;
	}

}
