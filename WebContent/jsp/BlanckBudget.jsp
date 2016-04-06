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
					<a href="index.html">Home</a> <i class="fa fa-heart-o"></i> <span>Budget</span>
				</h2>
			</div>
			<!--//banner-->
			<!--gallery-->
			<div class=" profile">

				<div class="profile-bottom-empty">
					<h3>
						<i class="fa fa-frown-o"></i>${" "}There are no budgets here yet.
						<br />
					</h3>
					<form method="get" action="./addBudget">
						<br /> <br /> <img src="resources/images/empty.png" align="middle">
						<div class="clearfix"></div>

						<div class="profile-btn">
							<input type="submit" value="Add budget" class="btn bg-red" />
							<div class="clearfix"></div>
						</div>
					</form>
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