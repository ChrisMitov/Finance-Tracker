<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Finance tracker - Budget</title>
<jsp:include page="partials/head.jsp" />
<link href="resources/css/table.css" rel='stylesheet' type='text/css' />
</head>
<body>
	<jsp:include page="partials/header.jsp" />
	<div id="page-wrapper" class="gray-bg dashbard-1">
		<div class="content-main">

			<!--banner-->
			<div class="banner">
				<h2>
					<a href="index.html">Home</a> <i class="fa fa-angle-right"></i> <span>Budget</span>
				</h2>
			</div>
			<!--//banner-->
			<!--grid-->
			<div class="grid-form">
				<div class="grid-form1" style="overflow: hidden; height: 1%;">
					<table class="one">
						<tr>
							<th colspan="4">Title</th>
							<th colspan="4">Amount</th>
							<th colspan="4">Start date</th>
							<th colspan="4">End date</th>
							<th colspan="4">Repeat type</th>
							<th colspan="4"></th>
							<th colspan="4"></th>
						</tr>
						<c:forEach var="budget" items="${budgets}">
							<tr>
								<td colspan="4">${budget.title}</td>
								<td colspan="4">${budget.totalAmount}</td>
								<td colspan="4">${budget.startDate}</td>
								<td colspan="4">${budget.endDate}</td>
								<td colspan="4">${budget.repeatType}</td>
								<td colspan="4">
									<form action="./editBudget">
										<input type="hidden" name="budgetid" value="${budget.id}" /> <input
											type="submit" value="Edit" class="btn btn-s btn-info"
											style="width: 75px; margin: 0 auto; display: block;" />
									</form>
								</td>
								<td colspan="4">
									<form method="POST" action="./removeBudget">
										<input type="hidden" name="id" value="${budget.id}" /> <input
											type="submit" value="Remove" class="btn btn-s btn-danger"
											style="width: 75px; margin: 0 auto; display: block;" />
									</form>
								</td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="8">
								<form action="./addBudget">
									<input type="submit" value="Add new budget"
										class="btn btn-lg btn-success warning_1"
										style="width: 150px; margin: 0 auto; display: block;" />
								</form>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>

		<!--//grid-->
		<!---->
		<jsp:include page="partials/footer.jsp" />
	</div>
	<div class="clearfix"></div>
	</div>

	<!---->
	<!--scrolling js-->
	<script src="resources/js/jquery.nicescroll.js"></script>
	<script src="resources/js/scripts.js"></script>
	<!--//scrolling js-->

</body>
</html>