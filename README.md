# OIBSIP

## ATM Interface

It is a console base project
Here we have to first log into our account using user id and password. Then we can do our operations there.

The operations in our application are:

    1.Transaction History
        This operation shows a table which is showing transaction history of the user
    2.Withdraw
        This operation makes a withdrawl operaion from user account.
    3.Deposit
        This operation makes a deposit operaion in user account.
    4.Transfer
        This operation transfers money from the user account to another user account.

For that we used mySQL database. For convinience, we have used a user list.
To continue in our project first you have to paste the following query in mySQL command prompt.

###
    drop database oasis_atm;
    create database oasis_atm;
    connect oasis_atm;
    CREATE TABLE USERACCOUNTS(ACCNO INT PRIMARY KEY, PASS INT,ACCHOLDERNAME VARCHAR(26), BRANCHNAME VARCHAR(26), TRANSACTID VARCHAR(8));
    INSERT INTO USERACCOUNTS VALUES(1111, 1111,'AAA', 'DURGAPUR', 'TR1111');
    INSERT INTO USERACCOUNTS VALUES(2222, 2222,'BBB', 'UTTAPARA', 'TR2222');
    INSERT INTO USERACCOUNTS VALUES(3333, 3333,'CCC', 'HINDMOTOR', 'TR3333');
    INSERT INTO USERACCOUNTS VALUES(4444, 4444,'DDD', 'SHRIRAMPUR', 'TR4444');
    INSERT INTO USERACCOUNTS VALUES(5555, 5555,'EEE', 'KONNAGAR', 'TR5555');
    INSERT INTO USERACCOUNTS VALUES(6666, 6666,'FFF', 'JIRAT', 'TR6666');

    CREATE TABLE TR1111(SERIALNO INT PRIMARY KEY,DATEOFTRANSACT DATE,DESCRIPTION VARCHAR(30),CREDIT FLOAT,DEBIT FLOAT,BALANCE FLOAT);
    INSERT INTO TR1111 VALUES(1,"2005-06-2","ACCOUNT CREATION",500,0,500);
    CREATE TABLE TR2222(SERIALNO INT PRIMARY KEY,DATEOFTRANSACT DATE,DESCRIPTION VARCHAR(30),CREDIT FLOAT,DEBIT FLOAT,BALANCE FLOAT);
    INSERT INTO TR2222 VALUES(1,"2006-08-12","ACCOUNT CREATION",3000,0,3000);
    CREATE TABLE TR3333(SERIALNO INT PRIMARY KEY,DATEOFTRANSACT DATE,DESCRIPTION VARCHAR(30),CREDIT FLOAT,DEBIT FLOAT,BALANCE FLOAT);
    INSERT INTO TR3333 VALUES(1,"2007-07-22","ACCOUNT CREATION",8000,0,8000);
    CREATE TABLE TR4444(SERIALNO INT PRIMARY KEY,DATEOFTRANSACT DATE,DESCRIPTION VARCHAR(30),CREDIT FLOAT,DEBIT FLOAT,BALANCE FLOAT);
    INSERT INTO TR4444 VALUES(1,"2008-03-17","ACCOUNT CREATION",9000,0,9000);
    CREATE TABLE TR5555(SERIALNO INT PRIMARY KEY,DATEOFTRANSACT DATE,DESCRIPTION VARCHAR(30),CREDIT FLOAT,DEBIT FLOAT,BALANCE FLOAT);
    INSERT INTO TR5555 VALUES(1,"2009-02-23","ACCOUNT CREATION",5000,0,5000);
    CREATE TABLE TR6666(SERIALNO INT PRIMARY KEY,DATEOFTRANSACT DATE,DESCRIPTION VARCHAR(30),CREDIT FLOAT,DEBIT FLOAT,BALANCE FLOAT);
    INSERT INTO TR6666 VALUES(1,"2007-10-28","ACCOUNT CREATION",4000,0,4000);
###

ID Password of the above demo users are given below for login operation:

###
    USER ID PASSWORD
    AAA 1111 1111
    BBB 2222 2222
    CCC 3333 3333
    DDD 4444 4444
    EEE 5555 5555
    FFF 6666 6666
###

## Number Guessing Game

This project is a server base servlet apllication
To run this application you must use a server.

It takes a number as input from user.
In backend it generates a random number between 1 to 100.
It is 40 seconds game. You have to guess the correct number within the time.
It matches the number with the input and response the user if the number smaller or larger than the correct answer.
It will give chances untill user gives correct input.
When user gives correct input it displays it as correct input.
