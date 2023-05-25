package com.oasis.factory;

import com.oasis.service.UserService;
import com.oasis.service.UserServiceImpl;

public class UserServiceFactory {
	public static UserService userservice;
	static {
		userservice = new UserServiceImpl();
	}
	public static UserService getUserService() {
		return userservice;
	}
}
