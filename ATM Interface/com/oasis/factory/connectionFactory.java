package com.oasis.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectionFactory {
	private static Connection con = null;
	static {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/oasis_atm", "root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection() {
		return con;
	}
}
