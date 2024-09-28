<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Registration Page</title>
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
						<a href="login.jsp"><button class="btn btn-info"
						type="submit">Login</button></a>
				
			</div>
		</div>
	</nav>
	<div class="loginMainDiv">
		<div class="loginForm">
			<form action="registerServlet" method="post">
				<h2>Register</h2>
				<%
				String registerStatus = request.getParameter("register");
				if (registerStatus != null && registerStatus.equals("false")) {
				%>
				<div class="alert alert-danger" role="alert">Registration
					Failed ❌</div>
				<%
				}
				%>
				<%
				String register = request.getParameter("register");
				if (register != null && register.equals("true")) {
				%>
				<div class="alert alert-success" role="alert">Registered
					Successful!!</div>
				<%
				}
				%>
				<div class="mb-3">
					<label for="username" class="form-label">First Name</label> <input
						type="text" class="form-control" id="firstname" name="firstname"
						required>
				</div>
				<div class="mb-3">
					<label for="username" class="form-label">Last Name</label> <input
						type="text" class="form-control" id="lastname" name="lastname"
						required>
				</div>
				<div class="mb-3">
					<label for="username" class="form-label">Username</label> <input
						type="text" class="form-control" id="username" name="username"
						required>
				</div>
				<div class="mb-3">
					<label for="password" class="form-label">Password</label> <input
						type="password" class="form-control" id="password" name="password"
						required>
				</div>
				<button type="submit" class="btn btn-primary">Register</button>
			</form>
		</div>
	</div>
	</div>
	</div>
</body>
</html>
