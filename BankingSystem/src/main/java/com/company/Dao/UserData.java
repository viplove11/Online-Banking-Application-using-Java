package com.company.Dao;

public class UserData {
	// User personal data
	private String firstname;
	private String lastname;
	private String username;
	private String password;
	private int AccNo;

	// User transactional Data
	private String transactionType;
	private String amount;
	private String transferAccountNumber;
	private String transactionDate;
	private double accBalance;

	public String getFirstname() {
		return firstname;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getTransferAccountNumber() {
		return transferAccountNumber;
	}

	public void setTransferAccountNumber(String transferAccountNumber) {
		this.transferAccountNumber = transferAccountNumber;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAccNo() {
		return AccNo;
	}

	public void setAccNo(int accNo) {
		AccNo = accNo;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public double getAccBalance() {
		return accBalance;
	}

	public void setAccBalance(double accBalance) {
		this.accBalance = accBalance;
	}

	@Override
	public String toString() {
		return "Transaction Type: " + transactionType + ", Amount: " + amount + ", Transfer Account Number: "
				+ transferAccountNumber + ", Transaction Date: " + transactionDate;
	}
}
