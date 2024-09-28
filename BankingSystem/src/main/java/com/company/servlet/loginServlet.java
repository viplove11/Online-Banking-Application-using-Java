package com.company.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.company.Dao.AccountDaoInterface;
import com.company.Dao.AccountDaoInterfaceImpl;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static AccountDaoInterface userDao = new AccountDaoInterfaceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public loginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String account = request.getParameter("account");
		int accountNumber = Integer.parseInt(account);

		// Check if the user credentials are valid
		if (userDao.isValidUser(username, password, accountNumber)) {
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			session.setAttribute("account", account);
			session.setAttribute("loggedIn", true);

			// Set cache control headers
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);

			// Redirect to MainServlet instead of main.jsp
			response.sendRedirect("MainServlet");
		} else {
			// Redirect back to login page with an error status
			response.sendRedirect("login.jsp?loginStatus=false");
		}
	}

}
