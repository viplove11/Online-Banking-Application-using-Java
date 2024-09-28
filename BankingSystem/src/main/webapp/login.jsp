<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Login Page</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<nav class="navbar bg-body-tertiary">
		<div class="container-fluid" id="main-title">
			<a class="navbar-brand navBranding">Online Banking Application</a>
			<div class="loginNav">
				<a href="index.html"><button class="btn btn-warning"
						type="submit">Home</button></a>
				<a href="register.jsp"><button class="btn btn-primary"
						type="submit">Register</button></a>
			</div>
		</div>
	</nav>
	<div class="container">
		<div
			class="loginMainDiv d-flex justify-content-center align-items-center">
			<div class="loginForm p-4 border rounded">
				<form action="loginServlet" method="post">
					<h2 class="text-center">Login</h2>

					<%
					// Handle the registration status and show success alert
					String registerStatus = request.getParameter("register");
					String accNo = request.getParameter("acc");
					if (registerStatus != null && registerStatus.equals("true")) {
						try {
							int num = Integer.parseInt(accNo);
					%>
					<div class="alert alert-success" role="alert">
						Registered Successfully ✔<br>Account Number: <b><%=num%></b>
					</div>
					<%
					} catch (NumberFormatException e) {
					out.println("<div class='alert alert-warning' role='alert'>Invalid Account Number</div>");
					}
					}
					%>

					<%
					// Handle login failure and show error alert
					String loginStatus = request.getParameter("loginStatus");
					if (loginStatus != null && loginStatus.equals("false")) {
					%>
					<div class="alert alert-danger" role="alert">Invalid
						Credentials 😢</div>
					<%
					}
					%>

					<div class="mb-3">
						<label for="username" class="form-label">Username</label> <input
							type="text" class="form-control" id="username" name="username"
							required>
					</div>
					<div class="mb-3">
						<label for="account" class="form-label">Account Number</label> <input
							type="text" class="form-control" id="account" name="account"
							required>
					</div>
					<div class="mb-3">
						<label for="password" class="form-label">Password</label> <input
							type="password" class="form-control" id="password"
							name="password" required>
					</div>
					<button type="submit" class="btn btn-success w-100">Login</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
