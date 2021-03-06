<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Finance tracker - Accounts</title>
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
					<a href="index.html">Home</a> <i class="fa fa-angle-right"></i> <span>Account</span>
				</h2>
			</div>
			<!--//banner-->
			<!--grid-->
			<div class="grid-form">
				<div class="grid-form1" style="overflow: hidden; height: 1%;">
					<table class="one">
						<tr>
							<th colspan="4">Title</th>
							<th colspan="4">Balance</th>
							<th colspan="4"></th>
							<th colspan="4"></th>
						</tr>
						<c:forEach var="account" items="${accounts}">
							<tr>
								<td colspan="4">${account.title}</td>
								<td colspan="4">${account.sum}<c:if
										test="${account.sum < 0}">
										<p style="color: red; font-size: 12px;">Negative balance</p>
									</c:if></td>
								<td colspan="4">
									<form action="./editAccount">
										<input type="hidden" name="id" value="${account.id}" /> <input
											type="submit" value="Edit" class="btn btn-s btn-info"
											style="width: 75px; margin: 0 auto; display: block;" />
									</form>
								</td>
								<td colspan="4">
									<form method="get" action="./accountRemove">
										<input type="hidden" name="id" value="${account.id}" /> <input
											type="submit" value="Remove" class="btn btn-s btn-danger"
											style="width: 75px; margin: 0 auto; display: block;" />
									</form>
								</td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="8">
								<form action="./addAccount">
									<input type="submit" value="Add"
										class="btn btn-lg btn-success warning_1"
										style="width: 150px; margin: 0 auto; display: block;" />
								</form>
							</td>
							<td colspan="8">
								<form action="./transaction">
									<input type="submit" value="Make transaction"
										class="btn btn-lg btn-primary"
										style="width: 150px; margin: 0 auto; display: block;" />
								</form>
							</td>
						</tr>

					</table>
				</div>
				<c:if test="${operationsAccount != null}">
					<script>
						var message = "${operationsAccount}";
						alert(message);
					</script>
				</c:if>
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

