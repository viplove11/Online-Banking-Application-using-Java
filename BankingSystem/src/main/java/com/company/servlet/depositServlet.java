package com.company.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.company.Dao.*;

/**
 * Servlet implementation class depositServlet
 */
@WebServlet("/depositServlet")
public class depositServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static AccountDaoInterface userdao = new AccountDaoInterfaceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public depositServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accountNumber = (String) request.getSession().getAttribute("account");
		double amount = Double.parseDouble(request.getParameter("amount"));

		// Get a DB connection

		HttpSession session = request.getSession(false);
		if (userdao.deposit(accountNumber, amount)) {
		    session.setAttribute("depositMessage", "done");
		    response.sendRedirect("main.jsp");
		} else {
		    session.setAttribute("depositMessage", "failed");
		    RequestDispatcher dispatcher = request.getRequestDispatcher("deposit.jsp");
		    dispatcher.forward(request, response);
		}

	}

}
