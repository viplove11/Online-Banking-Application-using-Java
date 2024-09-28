
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String username = (String) session.getAttribute("username");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title><%=username%> | Amount Transfer</title>
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
			<div class="transferNav">
				<a href="main.jsp"><button class="btn btn-primary" type="submit">Back</button></a>
				<form class="d-flex" role="search" action="LogoutServlet">
					<button class="btn btn-secondary" type="submit">Logout</button>
				</form>
			</div>
		</div>
	</nav>
	<div class="transferDiv">
		<form action="AmountTransferServlet" method="post"
			class="transferform">
			<h4 style="text-align: center; font-family: Josefin Sans;">Amount
				Transfer</h4>
			<label>Enter reciepents Account number: </label> <input
				class="form-control" placeholder="91X XX" type="text"
				name="recAccNumber" /> <label>Amount to tranfer</label> <input
				class="form-control" placeholder="Rs. 2000" type="text"
				name="transferAmount" />

			<button class="btn btn-success" style="font-weight: 600;">Transfer
				Amount</button>
		</form>
	</div>
</body>
</html>
