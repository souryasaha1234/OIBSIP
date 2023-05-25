package com.oasis.factory;

import com.oasis.dao.UserDao;
import com.oasis.dao.UserDaoImpl;

public class UserDaoFactory {
	public static UserDao userdao;
	static {
		userdao = new UserDaoImpl();
	}
	public static UserDao getUserDao() {
		return userdao;
	}
}
