<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Finance tracker - Make Transaction</title>
<jsp:include page="partials/head.jsp" />
<link href="resources/css/select.css" rel='stylesheet' type='text/css' />
</head>
<body>
	<jsp:include page="partials/header.jsp" />
	<div id="page-wrapper" class="gray-bg dashbard-1">
		<div class="content-main">

			<!--banner-->
			<div class="banner">
				<h2>
					<a href="./">Home</a> <i class="fa fa-angle-right"></i> <a
						href="./account">Account</a> <i class="fa fa-angle-right"></i> <span>Transaction</span>
				</h2>
			</div>
			<!--//banner-->
			<!--grid-->
			<div class="grid-form">
				<div class="grid-form1"
					style="overflow: hidden; height: 1%; position: relative;">
					<h1 style="text-align: center; margin-left: -50px">Make a
						transaction:</h1>

					<br />
					<form method="post" action="./transaction" class="demo">
						<table>
							<tr>
								<th><label style="font-size: 28px">From account: </label></th>
								<td><select class="option3" name="fromAccount">
										<c:forEach var="account" items="${accounts}">
											<option value="${account.id}">${account.title}</option>
										</c:forEach>
								</select></td>
							</tr>
							<tr>
								<th><label style="font-size: 28px">To account: </label></th>
								<td><select class="option4" name="toAccount">
										<c:forEach var="account" items="${accounts}">
											<option value="${account.id}">${account.title}</option>
										</c:forEach>
								</select></td>
							</tr>
							<tr>
								<th><label style="font-size: 28px">How much: </label></th>
								<td><input id="new_sum" name="sum" type="number"
									class="form-control" placeholder="Balance" value=""
									style="width: 100px; margin-left: 10px; "></td>
							</tr>
						</table>
						<c:if test="${not empty wrongSum}">
							<p style="color: red">${wrongSum}</p>
						</c:if>
						<br /> <input type="submit" value="Submit "
							class="btn btn-lg btn-danger" style="margin-left: 215px;">
					</form>
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

