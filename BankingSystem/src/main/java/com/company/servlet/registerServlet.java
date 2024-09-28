package com.company.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

import com.company.Dao.*;

/**
 * Servlet implementation class registerServlet
 */
@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static AccountDaoInterface userDao = new AccountDaoInterfaceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public registerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		generating random account number
		int min = 10000; // Minimum 5-digit number
		int max = 99999; // Maximum 5-digit number

		// Create an instance of the Random class
		Random random = new Random();

		// Generate a random 5-digit number in the range [10000, 99999]
		int acc_number = random.nextInt((max - min) + 1) + min;

		// Print the random 5-digit number
//		System.out.println("Generated Random 5-Digit Number: " + randomNumber);

		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		UserData user = new UserData();
		user.setFirstname(firstname);
		user.setLastname(lastname);
		user.setUsername(username);
		user.setPassword(password);
		user.setAccNo(acc_number);

		if (userDao.registerUser(user)) {
			response.sendRedirect("login.jsp?register=true&acc=" + acc_number);
		} else {
			response.sendRedirect("register.jsp?register=false");
		}

//		response.getWriter().append("i am at registerServlet.java"+firstname+" "+lastname+" "+email+" "+password);

	}

}
