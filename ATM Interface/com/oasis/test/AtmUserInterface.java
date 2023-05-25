package com.oasis.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.oasis.factory.UserServiceFactory;
import com.oasis.factory.connectionFactory;
import com.oasis.service.UserService;

public class AtmUserInterface {
	static {
		connectionFactory.getConnection();
	}
	public static void main(String[] args) {
		BufferedReader br = null;
		int option = 0;
		int accNo = 0, amount = 0, fromAccNo = 0, toAccNo = 0, pass = 0;
		String status = "", choice = "";
		br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("***********************************");
		System.out.println("Welcome to our ATM Interface");
		System.out.println("***********************************");
		UserService userService = UserServiceFactory.getUserService();
		try {
			do {
				System.out.println("Enter account no.:: ");
				accNo = Integer.parseInt(br.readLine());
				System.out.println("Enter password:: ");
				pass = Integer.parseInt(br.readLine());
				status = userService.loginService(accNo, pass);
				if(status.equalsIgnoreCase("success")) {
					System.out.println("Status: Logged in Successfully !!");
					break;
				}
				if(status.equalsIgnoreCase("incorrect")) {
					System.out.println("Wrong PIN !!");
					System.out.println("Try again [yes/no]");
					System.out.print("Enter your chice [yes/no]in any case: ");
					while(true) {
						choice = br.readLine();
						if(choice.equalsIgnoreCase("yes")) {
							break;
						}
						else if(choice.equalsIgnoreCase("no")){
							System.out.println("Thank you visit again");
							System.exit(0);
						}
						else {
							System.out.println("Enter [yes/no]in any case");
						}
					}
				}
				else if(status.equalsIgnoreCase("failure"))
					System.out.println("Status: ogged in Failed!!");
				else if(status.equalsIgnoreCase("not existed")) {
					System.out.println("Account not existed!!");
					System.out.println("Try again [yes/no]");
					
					System.out.print("Enter your chice [yes/no]in any case: ");
					while(true) {
						choice = br.readLine();
						if(choice.equalsIgnoreCase("yes")) {
							break;
						}
						else if(choice.equalsIgnoreCase("no")){
							System.out.println("Thank you visit again");
							System.exit(0);
						}
						else {
							System.out.println("Enter [yes/no]in any case");
						}
					}
				}
			} while (true);	
			do {
				
				System.out.println();
				System.out.println("***********************************");
				System.out.println("This ATM machine facilities:: ");
				System.out.println("***********************************");
				System.out.println();
				System.out.println("***********************************");
				System.out.println("1. Transaction History: ");
				System.out.println("2. Cash WithDrawl");
				System.out.println("3. Cash deposit");
				System.out.println("4. Bank Transfer");
				System.out.println("5. Quit");
				System.out.print("Enter your chice(1, 2, 3, 4, 5): ");
				option = Integer.parseInt(br.readLine());
				switch (option) {
				
				case 1:
					//Transaction History
					System.out.println();
					System.out.println("*****Transaction History*****");
					userService.transactionHistoryService(accNo);
					break;
					
				case 2:
					//Cash WithDrawl
					System.out.println();
					System.out.println("*****Cash WithDrawl*****");
					System.out.println("Enter amount:: ");
					amount = Integer.parseInt(br.readLine());
					status = userService.withdrawService(accNo, amount);
					if(status.equalsIgnoreCase("success"))
						System.out.println("Status: Cash WithDrawn Successfully !!");
					else if(status.equalsIgnoreCase("low balance"))
						System.out.println("Status: Low Balance !!");
					else if(status.equalsIgnoreCase("failure"))
						System.out.println("Status: Cash WithDrawl Failed!!");
					else if(status.equalsIgnoreCase("not existed"))
						System.out.println("Status: User not existed!!");
					break;
					
				case 3:
					//Cash Deposit
					System.out.println();
					System.out.println("*****Cash Deposit*****");
//					System.out.println("Enter account no.:: ");
//					accNo = Integer.parseInt(br.readLine());
					System.out.println("Enter amount:: ");
					amount = Integer.parseInt(br.readLine());
					status = userService.depositService(accNo, amount);
					if(status.equalsIgnoreCase("success"))
						System.out.println("Status: Cash Deposited Successfully !!");
					else if(status.equalsIgnoreCase("failure"))
						System.out.println("Status: Cash Deposit Failed!!");
					else if(status.equalsIgnoreCase("not existed"))
						System.out.println("Status: User not existed!!");
					break;
					
				case 4:
					//Bank Transfer
					System.out.println();
					System.out.println("*****Bank Transfer*****");
//					System.out.println("Enter account no to transfer 'from'.:: ");
//					fromAccNo = Integer.parseInt(br.readLine());
					fromAccNo = accNo;
					System.out.println("Enter account no to transfer 'to'.:: ");
					toAccNo = Integer.parseInt(br.readLine());
					System.out.println("Enter amount:: ");
					amount = Integer.parseInt(br.readLine());
					status = userService.transferService(fromAccNo, toAccNo, amount);
					if(status.equalsIgnoreCase("success"))
						System.out.println("Status: Cash Transfer Successfully !!");
					else if(status.equalsIgnoreCase("failure"))
						System.out.println("Status: Cash Transfer Failed!!");

					break;
					
				case 5: 					
					System.out.println("****Thanks for using***");
					System.exit(0);
					break;
					
				default:
					System.out.println("Enter valid choice");
					break;
				}
					
			} while (true);
			} catch (Exception e) {
				// TODO: handle exception
			}
	}

}
