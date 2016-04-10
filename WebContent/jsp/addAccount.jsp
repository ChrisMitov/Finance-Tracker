<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<%@page errorPage="Error.jsp"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE HTML>
<html>
<head>
<title>Finance tracker - Add account</title>
<jsp:include page="partials/head.jsp" />
</head>
<body>
	<jsp:include page="partials/header.jsp" />
	<div id="page-wrapper" class="gray-bg dashbard-1">
		<div class="content-main">

			<!--banner-->
			<div class="banner">
				<h2>
					<a href="./">Home</a> <i class="fa fa-angle-right"></i> <span>Account</span>
				</h2>
			</div>
			<!--//banner-->
			<!--grid-->
			<div class="grid-form">
				<div class="grid-form1">
					<h3 id="forms-example" class="">Add account</h3>
					<form name="category" action="./addAccount" method="post">
						<div>
							<label for="user_name">Title: </label> <input id="account_title"
								name="name" type="text" class="form-control"
								placeholder="Account title" value="" style="width: 50%;">
							<label for="user_name">Balance: </label> <input id="account_sum"
								name="sum" type="number" class="form-control"
								placeholder="Balance" value="" style="width: 10%;">
						</div>
						<br /> <input type="submit" value="Submit "
							class="btn btn-lg btn-danger">
					</form>
					<c:if test="${not empty error}">
						<p style="color: red">${error}</p>
					</c:if>
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

