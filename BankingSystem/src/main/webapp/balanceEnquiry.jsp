<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String username = (String) session.getAttribute("username");
%>
<%
String user_id = (String) request.getAttribute("user_id");
String firstname = (String) session.getAttribute("firstname");
String lastname = (String) session.getAttribute("lastname");
String accountNumber = (String) session.getAttribute("accountNumber");
String acc_balance = (String) session.getAttribute("accBalance");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title><%=username%> | Balance Enquiry</title>
<link rel="stylesheet" href="style.css" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>
</head>
<body>
	<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
	if (loggedIn == null || !loggedIn) {
		response.sendRedirect("login.jsp");
		return;
	}
	%>
	<nav class="navbar bg-body-tertiary">
		<div class="container-fluid" id="main-title">
			<a class="navbar-brand navBranding">Online Banking Application</a>
			<div class="balanceNav">
				<a href="main.jsp"><button class="btn btn-primary" type="submit">Back</button></a>
				<form class="d-flex" role="search" action="LogoutServlet">
					<button class="btn btn-secondary" type="submit">Logout</button>
				</form>
			</div>
		</div>
	</nav>

	<div class="balanceDiv">
		<div class="innerBalanceDiv">

			<div class="name">
				<p style="color: black;">
					<span style="color: orange">firstname: </span>
					<%=firstname%></p>
				<p style="color: black;">
					<span style="color: orange">lastname: </span>
					<%=lastname%></p>
			</div>

			<p style="color: black;">
				<span style="color: orange">username: </span>
				<%=username%></p>
			<p style="color:black;">
				<span style="color: orange">Account Number: </span><%=accountNumber%></p>
			<p style="text-align: center; margin-top: 20px; color:black;">
				<b>Rs. </b><span style="font-family: Josefin Sans; font-size: 30px; color: orange"">
					<%=acc_balance%></span>/-
			</p>
		</div>
	</div>
</body>
</html>
