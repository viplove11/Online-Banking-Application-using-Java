package com.company.servlet;

//import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.company.Dao.*;

/**
 * Servlet implementation class withdrawServlet
 */
@WebServlet("/withdrawServlet")
public class withdrawServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static AccountDaoInterface userDao = new AccountDaoInterfaceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public withdrawServlet() {
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
		double amount = Double.parseDouble(request.getParameter("WithdrawAmount"));

		HttpSession session1 = request.getSession();
		if (userDao.withdrawAmount(accountNumber, amount)) {
			session1.setAttribute("WithdrawMessage", "done");
			response.sendRedirect("main.jsp");
		} else {
			session1.setAttribute("WithdrawMessage", "failed");
			response.sendRedirect("withdraw.jsp");
		}

		// Removed the line that removes the attribute here
	}

}
