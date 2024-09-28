package com.company.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static final String URL = "jdbc:mysql://localhost:3306/BankingSystem?allowPublicKeyRetrieval=true&useSSL=false"; // Use
																																// your
																																// DB
																																// name
	private static final String USER = "root"; // Replace with your MySQL username
	private static final String PASSWORD = "viplove@1109"; // Replace with your MySQL password
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver"; // Correct MySQL JDBC driver class

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER); // Load the MySQL JDBC driver
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
}
