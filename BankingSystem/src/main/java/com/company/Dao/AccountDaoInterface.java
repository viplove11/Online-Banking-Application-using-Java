package com.company.Dao;

import java.sql.ResultSet;

public interface AccountDaoInterface {

	boolean registerUser(UserData user);

	boolean isValidUser(String username, String password, int account);

	boolean deposit(String accountNumber, double amount);

	boolean withdrawAmount(String accountNumber, double amount);

	boolean transferAmount(String senderAccountNumber, String recipientAccountNumber, double transferAmount);

	public ResultSet fetchTransactionHistory(String accountNumber);

	ResultSet getUserDetails(String accountNumber);

}
