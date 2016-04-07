<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:useBean id="now" class="java.util.Date" />
<!DOCTYPE HTML>
<html>
<head>
<title>Finance tracker - Incomes</title>
<jsp:include page="partials/head.jsp" />
<jsp:include page="./partials/pieHeader.jsp" />
<link href="resources/css/select.css" rel='stylesheet' type='text/css' />
<link href="resources/css/table.css" rel='stylesheet' type='text/css' />
<link href="resources/css/double_table.css" rel='stylesheet'
	type='text/css' />
</head>
<body>
	<jsp:include page="partials/header.jsp" />
	<div id="page-wrapper" class="gray-bg dashbard-1">
		<div class="content-main">

			<!--banner-->
			<div class="banner">
				<h2>
					<a href="index.html">Home</a> <i class="fa fa-angle-right"></i> <span>Income</span>
				</h2>
			</div>
			<!--//banner-->
			<!--grid-->
			<div class="grid-form">
				<div class="grid-form1"
					style=" width: 1200px; overflow: hidden; height: 1%; display: table-cell; text-align: center; vertical-align: middle;">
					<span
						style="font-size: 24px;">There's
						no incomes </span> <br />  <br /> <img src="resources/images/empty.png">
					<form action="./addIncome">
						<input type="submit" value="Add Income"
							class="btn btn-lg btn-success warning_1"
							style="width: 200px; margin: 0 auto; display: block;" />
					</form>

					<div id="chartdiv"></div>
				</div>

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
</body>
</html>

