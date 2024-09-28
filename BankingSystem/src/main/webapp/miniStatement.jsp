<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.lang.Exception"%>
<%
String username = (String) session.getAttribute("username");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title><%=username%> | Mini Statement</title>
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
	<!-- Navigation Bar -->
	<nav class="navbar bg-body-tertiary">
		<div class="container-fluid" id="main-title">
			<a class="navbar-brand navBranding">Online Banking Application</a>
			<div class="miniNav">
				<a href="main.jsp">
					<button class="btn btn-primary" type="button">Back</button>
				</a>
				<form class="d-flex" role="search" action="LogoutServlet">
					<button class="btn btn-secondary" type="submit">Logout</button>
				</form>
			</div>
		</div>
	</nav>

	<!-- Transaction History -->


	<h5
		style="font-family: 'Josefin Sans'; margin-top: 30px; margin-bottom: 50px; text-align: center;">Transaction
		History</h5>
	<!-- Transaction Table -->
	<table class="miniTable table table-hover container">

		<thead>
			<tr>
				<th class="minith">Transaction ID</th>
				<th class="minith">Transaction Type</th>
				<th class="minith">Amount</th>
				<th class="minith">Transfer Account Number</th>
				<th class="minith">Transaction Date</th>
			</tr>
		</thead>
		<tbody>
			<%
			// Retrieve the ResultSet from request scope
			ResultSet rs = (ResultSet) request.getAttribute("transactionResultSet");

			// Check if the ResultSet is not null
			if (rs != null) {
				try {
					// Iterate through the ResultSet and print the transaction details
					while (rs.next()) {
				String transactionId = rs.getString("transaction_id");
				String transactionType = rs.getString("transaction_type");
				String amount = rs.getString("amount");
				String transferAccountNumber = rs.getString("transfer_account_number");
				String transactionDate = rs.getString("transaction_date");
			%>
			<tr class="minitr">
				<td class="minitd"><%=transactionId != null ? transactionId : "N/A"%></td>
				<td class="minitd"><%=transactionType != null ? transactionType : "N/A"%></td>
				<td class="minitd"><%=amount != null ? amount : "N/A"%></td>
				<td class="minitd"><%=transferAccountNumber != null ? transferAccountNumber : "N/A"%></td>
				<td class="minitd"><%=transactionDate != null ? transactionDate : "N/A"%></td>
			</tr>
			<%
			}
			} catch (Exception e) {
			%>
			<tr class="minitr">
				<td class="minitd" colspan="4">Error retrieving transactions: <%=e.getMessage()%></td>
			</tr>
			<%
			} finally {
			// Ideally, close the ResultSet and other resources in the DAO code
			}
			} else {
			%>
			<tr class="minitr">
				<td colspan="4">No transactions found</td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>
</body>
</html>
