package com.company.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.ResultSet;
import com.company.Dao.*;

/**
 * Servlet implementation class BalanceEnquiry
 */
@WebServlet("/BalanceEnquiry")
public class BalanceEnquiry extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static AccountDaoInterface userDao = new AccountDaoInterfaceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BalanceEnquiry() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String accountNumber = (String) session.getAttribute("accountNumber");

		System.out.println("Account number = " + accountNumber);

		ResultSet userRs = userDao.getUserDetails(accountNumber);
		System.out.print(userRs);

		try {
			// Set user details attributes
			if (userRs != null && userRs.next()) {
				String firstname = userRs.getString("firstname");
		        String lastname = userRs.getString("lastname");
		        String username = userRs.getString("username");
		        String accountNum = userRs.getString("accountNumber");
		        String accBalance = userRs.getString("acc_balance");

		        // Print the user details to console for debugging
		        System.out.println("User Details:");
		        System.out.println("First Name: " + firstname);
		        System.out.println("Last Name: " + lastname);
		        System.out.println("Username: " + username);
		        System.out.println("Account Number: " + accountNum);
		        System.out.println("Account Balance: " + accBalance);
		        
		        session.setAttribute("firstname", firstname);
		        session.setAttribute("lastname", lastname);
		        session.setAttribute("username", username);
		        session.setAttribute("accountNumber", accountNum);
		        session.setAttribute("accBalance", accBalance);

			}

			// Forward to the JSP page for display
			RequestDispatcher dispatcher = request.getRequestDispatcher("balanceEnquiry.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close resources (optional: close ResultSet in the DAO method)
		}
	}
}
