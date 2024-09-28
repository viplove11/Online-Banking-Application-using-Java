package com.company.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.company.Dao.*;

/**
 * Servlet implementation class AmountTransferServlet
 */
@WebServlet("/AmountTransferServlet")
public class AmountTransferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AccountDaoInterface userDao = new AccountDaoInterfaceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AmountTransferServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		String senderAccountNumber = (String) session.getAttribute("accountNumber");
		String recipientAccountNumber = request.getParameter("recAccNumber");

		double transferAmount = 0;
		try {
			transferAmount = Double.parseDouble(request.getParameter("transferAmount"));
		} catch (NumberFormatException e) {
			session.setAttribute("transferMessage", "Invalid transfer amount. Please enter a valid number.");
			response.sendRedirect("amountTransfer.jsp");
			return;
		}

		System.out.println("s = " + senderAccountNumber);
		System.out.println("r = " + recipientAccountNumber);
		System.out.println("a = " + transferAmount);

		// Validate inputs
		if (senderAccountNumber == null || recipientAccountNumber == null || recipientAccountNumber.isEmpty()
				|| transferAmount <= 0) {
			session.setAttribute("transferMessage", "Invalid input. Please try again.");
			response.sendRedirect("amountTransfer.jsp");
			return;
		}

		// Process transfer
		boolean transferSuccess = userDao.transferAmount(senderAccountNumber, recipientAccountNumber, transferAmount);

		if (transferSuccess) {
			session.setAttribute("transferMessage", "Transfer successful!");
			response.sendRedirect("main.jsp");
		} else {
			session.setAttribute("transferMessage", "Transfer failed. Please check the account number or balance.");
			response.sendRedirect("amountTransfer.jsp");
		}
	}

}
