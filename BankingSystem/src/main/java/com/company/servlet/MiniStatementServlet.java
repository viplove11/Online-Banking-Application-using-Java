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
 * Servlet implementation class MiniStatementServlet
 */
@WebServlet("/MiniStatementServlet")
public class MiniStatementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static AccountDaoInterface userDao = new AccountDaoInterfaceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MiniStatementServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Get the session to retrieve accountNumber
		HttpSession session = request.getSession(false);
		String accountNumber = (String) session.getAttribute("accountNumber");

		System.out.println("Account number = " + accountNumber);

		ResultSet rs = userDao.fetchTransactionHistory(accountNumber);

		try {
			// Set ResultSet in request scope
			request.setAttribute("transactionResultSet", rs);

			// Forward to the JSP page for display
			RequestDispatcher dispatcher = request.getRequestDispatcher("/miniStatement.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
