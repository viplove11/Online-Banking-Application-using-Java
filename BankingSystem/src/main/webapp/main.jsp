<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Online Banking Application by vs code</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="style.css" />
</head>
<body class="container-fluid mainBodyStyle">

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

	<%
	String username = (String) session.getAttribute("username");
	String accountNumber = (String) session.getAttribute("account");
	%>

	<nav class="navbar bg-body-tertiary">
		<div class="container-fluid" id="main-title">
			<a style="font-size: 20px" class="navbar-brand navBranding">Welcome,
				<span style="color: orange; font-weight: 400;"><%=username%></span>
			</a>
			<form class="d-flex" role="search" action="LogoutServlet">
				<button class="btn btn-secondary" type="submit">Logout</button>
			</form>
		</div>
	</nav>

	<p
		style="font-size: 20px; color: black; font-weight: 400; font-family: Josefin Sans; margin-left: 10px;">
		Account Number: <span style="color: orange; font-weight: 400;"><%=accountNumber%></span>
	</p>


	<div class="container">
		<%
		String depositStatus = (String) session.getAttribute("depositMessage");
		if (depositStatus != null) {
		%>
		<div class="alert alert-success" role="alert">
			<%=depositStatus.equals("done") ? "Deposit successful!" : "Deposit failed!"%>
		</div>
		<%
		// Clear the message after displaying it
		session.removeAttribute("depositMessage");
		}
		%>

		<!-- with draw success -->
		<%
		String withdrawMessage = (String) session.getAttribute("WithdrawMessage");
		if (withdrawMessage != null) {
		%>
		<div
			class="alert alert-<%=withdrawMessage.equals("done") ? "success" : "danger"%>"
			role="alert">
			<%=withdrawMessage.equals("done") ? "Withdrawal successful!" : "Withdrawal failed!"%>
		</div>
		<%
		// Clear the message after displaying it
		session.removeAttribute("WithdrawMessage");
		}
		%>

		<!-- amount transfer status  -->
		<%
		String transferMessage = (String) session.getAttribute("transferMessage");
		if (transferMessage != null) {
		%>
		<div class="alert alert-success" role="alert">
			<%=transferMessage.equals("Transfer successful!") ? "Amount Trasfered successful!" : "Amount Transfered failed!"%>
		</div>
		<%
		// Clear the message after displaying it
		session.removeAttribute("transferMessage");
		}
		%>

		<div class="container divAboveGrid">
			<div class="grid-container">
				<a href="deposit.jsp">
					<div id="deposit">Deposit Amount</div>
				</a> <a href="withdraw.jsp">
					<div id="withdraw">Withdraw Amount</div>
				</a> <a href="amountTransfer.jsp">
					<div id="amount">Amount Transfer</div>
				</a> <a href="MiniStatementServlet">
					<div id="mini">Mini Statement</div>
				</a> <a href="BalanceEnquiry">
					<div id="balance">Balance Enquiry</div>
				</a>
			</div>
		</div>
	</div>
	<!-- JavaScript to auto-dismiss the alert after 3 seconds -->
	<script>
		setTimeout(function() {
			var alerts = document.querySelectorAll('.alert');
			alerts.forEach(function(alert) {
				alert.classList.remove('show');
				alert.classList.add('fade');
			});
		}, 3000); // 3000 milliseconds = 3 seconds
	</script>
</body>
</html>
