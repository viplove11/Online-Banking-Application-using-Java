package com.company.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.company.util.DBUtil;

public class AccountDaoInterfaceImpl implements AccountDaoInterface {

	@Override
	public boolean registerUser(UserData user) {
		String query = "INSERT INTO user(firstname, lastname, username, password, accountNumber) VALUES (?,?,?,?,?)";
		try {
			Connection connection = DBUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setString(1, user.getFirstname());
			statement.setString(2, user.getLastname());
			statement.setString(3, user.getUsername());
			statement.setString(4, user.getPassword());
			statement.setInt(5, (int) user.getAccNo());

			int rowAffected = statement.executeUpdate();
			return rowAffected > 0;
		} catch (Exception e) {
			System.out.print("Yha error aa rha hai register krne mai");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean isValidUser(String username, String password, int account) {
		String query = "SELECT id, firstname, lastname  FROM user WHERE username = ? AND password = ? AND accountNumber = ? ";
		try {
			Connection connection = DBUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setString(1, username);
			statement.setString(2, password);
			statement.setInt(3, account);

			ResultSet result = statement.executeQuery();
			System.out.println(result);
			return result.next();

		} catch (Exception e) {
			System.out.print("Yha error aa rha hai login krne mai");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deposit(String accountNumber, double amount) {
		try {
			// Step 1: Insert transaction into the transactions table
			String insertTransactionQuery = "INSERT INTO transactions (account_number, transaction_type, amount, transfer_account_number) VALUES (?, ?, ?, ?)";
			Connection connection = DBUtil.getConnection();
			PreparedStatement transactionStatement = connection.prepareStatement(insertTransactionQuery);

			// Set transaction type, account number, and amount
			transactionStatement.setString(1, accountNumber);
			transactionStatement.setString(2, "deposit"); // Can change to "withdraw" or "transfer" dynamically
			transactionStatement.setDouble(3, amount);

			// For deposit or withdraw, transfer_account_number is null
			transactionStatement.setNull(4, java.sql.Types.VARCHAR);

			// If it's a transfer transaction, use the following line instead:
			// transactionStatement.setString(4, transferAccountNumber);

			transactionStatement.executeUpdate();

			// Step 2: Get the current balance from the user table
			String getBalanceQuery = "SELECT acc_balance FROM user WHERE accountNumber = ?";
			PreparedStatement getBalanceStatement = connection.prepareStatement(getBalanceQuery);

			getBalanceStatement.setString(1, accountNumber);
			ResultSet resultSet = getBalanceStatement.executeQuery();

			double currentBalance = 0.0;
			if (resultSet.next()) {
				currentBalance = resultSet.getDouble("acc_balance"); // Retrieve the current balance
			}

			// Step 3: Update the balance by adding the deposit amount
			double newBalance = currentBalance + amount;
			String updateBalanceQuery = "UPDATE user SET acc_balance = ? WHERE accountNumber = ?";
			PreparedStatement updateStatement = connection.prepareStatement(updateBalanceQuery);

			updateStatement.setDouble(1, newBalance); // Set the new balance
			updateStatement.setString(2, accountNumber); // For the correct account number
			updateStatement.executeUpdate();

			// Step 4: Close resources
			resultSet.close();
			transactionStatement.close();
			getBalanceStatement.close();
			updateStatement.close();
			connection.close();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean withdrawAmount(String accountNumber, double amount) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			// Step 1: Get the current balance from the user table
			String getBalanceQuery = "SELECT acc_balance FROM user WHERE accountNumber = ?";
			connection = DBUtil.getConnection();
			ps = connection.prepareStatement(getBalanceQuery);
			ps.setString(1, accountNumber);
			rs = ps.executeQuery();

			double currentBalance = 0.0;
			if (rs.next()) {
				currentBalance = rs.getDouble("acc_balance");
			}

			// Step 2: Check if the account has sufficient balance
			if (currentBalance < amount) {
				System.out.println("Insufficient balance for withdrawal.");
				return false; // Insufficient balance
			}

			// Step 3: Insert transaction into the transactions table
			String insertTransactionQuery = "INSERT INTO transactions (account_number, transaction_type, amount) VALUES (?, 'withdraw', ?)";
			PreparedStatement transactionStatement = connection.prepareStatement(insertTransactionQuery);

			transactionStatement.setString(1, accountNumber);
			transactionStatement.setDouble(2, amount);
			transactionStatement.executeUpdate();

			// Step 4: Update the balance by subtracting the withdrawal amount
			double newBalance = currentBalance - amount;
			String updateBalanceQuery = "UPDATE user SET acc_balance = ? WHERE accountNumber = ?";
			PreparedStatement updateStatement = connection.prepareStatement(updateBalanceQuery);

			updateStatement.setDouble(1, newBalance); // Set the new balance
			updateStatement.setString(2, accountNumber); // For the correct account number
			updateStatement.executeUpdate();

			// Step 5: Close resources
			rs.close();
			ps.close();
			transactionStatement.close();
			updateStatement.close();
			connection.close();

			return true; // Withdrawal successful

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean transferAmount(String senderAccountNumber, String recipientAccountNumber, double transferAmount) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection(); // Assuming DBUtil handles all exceptions internally
			conn.setAutoCommit(false);

			String checkBalanceQuery = "SELECT acc_balance FROM user WHERE accountNumber = ?";
			pstmt = conn.prepareStatement(checkBalanceQuery);
			pstmt.setString(1, senderAccountNumber);
			rs = pstmt.executeQuery();

			if (!rs.next() || rs.getDouble("acc_balance") < transferAmount) {
				conn.rollback(); // Rollback if sender has insufficient funds or not found
				return false;
			}

			String deductAmountQuery = "UPDATE user SET acc_balance = acc_balance - ? WHERE accountNumber = ?";
			updateBalance(pstmt, deductAmountQuery, transferAmount, senderAccountNumber, conn);

			String addAmountQuery = "UPDATE user SET acc_balance = acc_balance + ? WHERE accountNumber = ?";
			updateBalance(pstmt, addAmountQuery, transferAmount, recipientAccountNumber, conn);

			String insertTransactionQuery = "INSERT INTO transactions (account_number, transaction_type, amount, transfer_account_number, transaction_date) "
					+ "VALUES (?, 'money transfer', ?, ?, NOW())";
			recordTransaction(pstmt, insertTransactionQuery, senderAccountNumber, transferAmount,
					recipientAccountNumber, conn);

			conn.commit();
			return true;

		} catch (Exception e) {
			System.err.println("SQL Exception: " + e.getMessage());
			rollback(conn);
			return false;
		} finally {
			closeResources(rs, pstmt, conn);
		}
	}

	private void updateBalance(PreparedStatement pstmt, String query, double amount, String accountNumber,
			Connection conn) throws SQLException {
		pstmt = conn.prepareStatement(query);
		pstmt.setDouble(1, amount);
		pstmt.setString(2, accountNumber);
		int rowsAffected = pstmt.executeUpdate();
		if (rowsAffected != 1) {
			throw new SQLException("Update failed for account number: " + accountNumber);
		}
	}

	private void recordTransaction(PreparedStatement pstmt, String query, String senderAccountNumber, double amount,
			String recipientAccountNumber, Connection conn) throws SQLException {
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, senderAccountNumber);
		pstmt.setDouble(2, amount);
		pstmt.setString(3, recipientAccountNumber);
		int rowsAffected = pstmt.executeUpdate();
		if (rowsAffected != 1) {
			throw new SQLException("Transaction recording failed for sender: " + senderAccountNumber);
		}
	}

	private void rollback(Connection conn) {
		try {
			if (conn != null) {
				conn.rollback();
			}
		} catch (SQLException ex) {
			System.err.println("Rollback failed: " + ex.getMessage());
		}
	}

	private void closeResources(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.err.println("Error closing resources: " + e.getMessage());
		}
	}

	@Override
	public ResultSet fetchTransactionHistory(String accountNumber) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT transaction_id, transaction_type, amount, transfer_account_number, transaction_date FROM transactions WHERE account_number = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, accountNumber);
			rs = pstmt.executeQuery();
			return rs; // Return the ResultSet directly
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		// Return null if there's an exception
		return null;
	}

	@Override
	public ResultSet getUserDetails(String accountNumber) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT id, firstname, lastname, username, accountNumber, acc_balance FROM user WHERE accountNumber = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, accountNumber);
			rs = pstmt.executeQuery();
			return rs; // Return the ResultSet directly
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		// Return null if there's an exception
		return null;
	}

}
