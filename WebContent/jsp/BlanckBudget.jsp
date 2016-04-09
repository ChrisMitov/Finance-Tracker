<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>

<!--
Author: W3layouts
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE HTML>
<html>
<head>
<title>Finance tracker - Edit profile</title>
<jsp:include page="partials/head.jsp" />
</head>
<body>
	<jsp:include page="partials/header.jsp" />
	<div id="page-wrapper" class="gray-bg dashbard-1">
		<div class="content-main">

			<!--banner-->
			<div class="banner">
				<h2>
					<a href="./">Home</a> <i class="fa fa-angle-right"></i> <span>Budget</span>
				</h2>
			</div>
			<!--//banner-->
			<!--grid-->
			<div class="grid-form">
				<div class="grid-form1"
					style="width: 1200px; overflow: hidden; height: 1%; display: table-cell; text-align: center; vertical-align: middle;">
					<h3>
						<i class="fa fa-frown-o"></i>${" "}There are no budgets here yet.
						<br />
					</h3>
					<img src="resources/images/empty.png">
					<form action="./addBudget">
						<input type="submit" value="Add budget"
							class="btn btn-lg btn-success warning_1"
							style="width: 200px; margin: 0 auto; display: block;" />
					</form>

					<div id="chartdiv"></div>
				</div>

			</div>
		</div>
	</div>
	<%@ include file="./partials/footer.jsp"%>
	</div>
	<div class="clearfix"></div>
	</div>

	<!---->

	<!--scrolling js-->
	<script src="../resources/js/jquery.nicescroll.js"></script>
	<script src="../resources/js/scripts.js"></script>
	<!--//scrolling js-->
</body>
</html>