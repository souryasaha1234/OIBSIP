package com.oasis.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.oasis.factory.connectionFactory;

public class UserDaoImpl implements UserDao {

	String status = "";
	@Override
	public void transactionHistory(int accNo) {
		Connection con = null;
		Statement st = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		String tid= "";
		try {
			con = connectionFactory.getConnection();
			if(con != null)
				st = con.createStatement();
			if(st != null)
				rs1 = st.executeQuery("SELECT * FROM USERACCOUNTS WHERE ACCNO = " + accNo);
			boolean b = rs1.next();
			if(b == true && rs1 != null) {
				tid = rs1.getString(5);
				if(st != null)
					rs2 = st.executeQuery("SELECT * FROM " + tid);
				if(rs2 != null) {
					System.out.println("***********************************");
					while(rs2.next())
						System.out.println(rs2.getInt(1)+" | "+rs2.getString(2)+" | "+rs2.getString(3)+" | "+rs2.getFloat(4)+" | "+rs2.getFloat(5)+" | "+rs2.getFloat(6));
					System.out.println("***********************************");
				}
			}
			else {
				System.out.println("User not existed");
			}
		} catch (SQLException sqe) {
			if(sqe.getErrorCode()==1)
				System.out.println("Duplicate cannot be inserted to primary key");
			if(sqe.getErrorCode()==1400)
				System.out.println("Null cannot be inserted to primary key");
			if(sqe.getErrorCode()>=900 && sqe.getErrorCode()<=999)
				System.out.println("Invalid Column names or table names or sql query");
			if(sqe.getErrorCode()==12899)
				System.out.println("Do not insert more than column size data to table");
			status = "failure";
			sqe.printStackTrace();
		} catch (Exception e) {
			status = "failure";
			e.printStackTrace();
		}finally {
			try {
				if(rs1 != null && rs2 != null) {
					rs1.close();
					rs2.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if(st != null)
					st.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	@Override
	public String withdraw(int accNo, int amount) {
		Connection con = null;
		Statement st = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		String tid= "";
		try {
			con = connectionFactory.getConnection();
			int slno = 0, balance = 0;
			if(con != null)
				st = con.createStatement();
			if(st != null)
				rs1 = st.executeQuery("SELECT * FROM USERACCOUNTS WHERE ACCNO = " + accNo);
			boolean b = rs1.next();
			if(b == true && rs1 != null) {
				tid = rs1.getString(5);
				if(st != null)
					rs2 = st.executeQuery("SELECT MAX(SERIALNO) FROM " + tid);
				if(rs2 != null) {
					if(rs2.next())
						slno = rs2.getInt(1);
				}				
				if(st != null)
//					rs3 = st.executeQuery("SELECT BALANCE FROM "+ tid +"WHERE SERIALNO = " + slno);
					rs3 = st.executeQuery("SELECT BALANCE FROM "+ tid +" WHERE SERIALNO = " + slno);
				if(rs3 != null) {
					if(rs3.next())
						balance = rs3.getInt(1);
				}
				if(balance >= amount) {
					if(st != null) {
						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
						LocalDateTime now = LocalDateTime.now();
						String datestr = dtf.format(now);
						//INSERT INTO TR1111 VALUES(2,"2005-06-2","CASH WITHDRAWN",500,0,500);
						st.executeUpdate("INSERT INTO "+ tid +" VALUES(" +(slno+1) +",'"+ datestr +"','CASH WITHDRAWN' , 0, "+amount+", "+ (balance - amount) +")");
					}
					status =  "success";
				}
				else {
					status =  "low balance";
				}
			}
			else {
				status =  "not existed";
			}
		} catch (SQLException sqe) {
			if(sqe.getErrorCode()==1)
				System.out.println("Duplicate cannot be inserted to primary key");
			if(sqe.getErrorCode()==1400)
				System.out.println("Null cannot be inserted to primary key");
			if(sqe.getErrorCode()>=900 && sqe.getErrorCode()<=999)
				System.out.println("Invalid Column names or table names or sql query");
			if(sqe.getErrorCode()==12899)
				System.out.println("Do not insert more than column size data to table");
			status = "failure";
			sqe.printStackTrace();
		} catch (Exception e) {
			status = "failure";
			e.printStackTrace();
		}finally {
			try {
				if(rs1 != null && rs2 != null&& rs3 != null) {
					rs1.close();
					rs2.close();
					rs3.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if(st != null)
					st.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return status;
	}

	@Override
	public String deposit(int accNo, int amount) {
		Connection con = null;
		Statement st = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		String tid= "";
		try {
			con = connectionFactory.getConnection();
			int slno = 0, balance = 0;
			if(con != null)
				st = con.createStatement();
			if(st != null)
				rs1 = st.executeQuery("SELECT * FROM USERACCOUNTS WHERE ACCNO = " + accNo);
			boolean b = rs1.next();
			if(b == true && rs1 != null) {
				tid = rs1.getString(5);
				if(st != null)
					rs2 = st.executeQuery("SELECT MAX(SERIALNO) FROM " + tid);
				if(rs2 != null) {
					if(rs2.next())
						slno = rs2.getInt(1);
				}				
				if(st != null)
					rs3 = st.executeQuery("SELECT BALANCE FROM "+ tid +" WHERE SERIALNO = " + slno);
				if(rs3 != null) {
					if(rs3.next())
						balance = rs3.getInt(1);
				}
				if(st != null) {
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDateTime now = LocalDateTime.now();
					String datestr = dtf.format(now);
					
					st.executeUpdate("INSERT INTO "+ tid +" VALUES(" +(slno+1) +",'"+ datestr +"','CASH DEPOSITED' , "+amount+", 0,"+ (balance + amount) +")");
				}
				status =  "success";
			}
			else {
				status =  "not existed";
			}
		} catch (SQLException sqe) {
			if(sqe.getErrorCode()==1)
				System.out.println("Duplicate cannot be inserted to primary key");
			if(sqe.getErrorCode()==1400)
				System.out.println("Null cannot be inserted to primary key");
			if(sqe.getErrorCode()>=900 && sqe.getErrorCode()<=999)
				System.out.println("Invalid Column names or table names or sql query");
			if(sqe.getErrorCode()==12899)
				System.out.println("Do not insert more than column size data to table");
			status = "failure";
			sqe.printStackTrace();
		} catch (Exception e) {
			status = "failure";
			e.printStackTrace();
		}finally {
			try {
				if(rs1 != null && rs2 != null&& rs3 != null) {
					rs1.close();
					rs2.close();
					rs3.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if(st != null)
					st.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return status;
	}

	@Override
	public String transfer(int fromAccNo, int toAccNo, int amount) {
		status = this.withdraw(fromAccNo, amount);
		if(status.equalsIgnoreCase("success")) {
			status = this.deposit(toAccNo, amount);
			if(!status.equalsIgnoreCase("success")) {
				this.deposit(fromAccNo, amount);
				status = "failure";
			}
		}
		else {
			status = "failure";
		}
		return status;
	}
	@Override
	public String login(int accNo, int pass) {
		Connection con = null;
		Statement st = null;
		ResultSet rs1 = null;
		int pwd = 0;
		try {
			con = connectionFactory.getConnection();
			if(con != null)
				st = con.createStatement();
			if(st != null)
				rs1 = st.executeQuery("SELECT * FROM USERACCOUNTS WHERE ACCNO = " + accNo);
			boolean b = rs1.next();
			if(b == true && rs1 != null) {
				pwd = rs1.getInt(2);
				if(pass == pwd) {
					status = "success";				
				}
				else {
					status = "incorrect";					
				}
			}
			else {
				status = "not existed";
			}
		} catch (SQLException sqe) {
			if(sqe.getErrorCode()==1)
				System.out.println("Duplicate cannot be inserted to primary key");
			if(sqe.getErrorCode()==1400)
				System.out.println("Null cannot be inserted to primary key");
			if(sqe.getErrorCode()>=900 && sqe.getErrorCode()<=999)
				System.out.println("Invalid Column names or table names or sql query");
			if(sqe.getErrorCode()==12899)
				System.out.println("Do not insert more than column size data to table");
			status = "failure";
			sqe.printStackTrace();
		} catch (Exception e) {
			status = "failure";
			e.printStackTrace();
		}finally {
			try {
				if(rs1 != null) 
					rs1.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if(st != null)
					st.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return status;
	}

}

/*
SELECT BALANCE FROM T111 WHERE SERIALNO=(SELECT MAX(SERIALNO) FROM TR1111); 
 */
