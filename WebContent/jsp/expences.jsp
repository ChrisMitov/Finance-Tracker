<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Finance tracker - Expenses</title>
<jsp:include page="partials/head.jsp" />
<jsp:include page="./partials/pieHeader.jsp" />
<link href="resources/css/select.css" rel='stylesheet' type='text/css' />
<link href="resources/css/table.css" rel='stylesheet' type='text/css' />
<style type="text/css">
#wrapper1 {
	margin-right: 400px;
}

#content1 {
	float: left;
	width: 100%;
}

#sidebar1 {
	float: right;
	width: 400px;
	margin-right: -400px;
}

#cleared1 {
	clear: both;
}
</style>
</head>
<body>
	<jsp:include page="partials/header.jsp" />
	<div id="page-wrapper" class="gray-bg dashbard-1">
		<div class="content-main">

			<!--banner-->
			<div class="banner">
				<h2>
					<a href="index.html">Home</a> <i class="fa fa-angle-right"></i> <span>Expense</span>
				</h2>
			</div>
			<!--//banner-->
			<!--grid-->
			<div class="grid-form">
				<div class="grid-form1"
					style="overflow: hidden; height: 1%; position: relative;">
					<div id="wrapper1">
						<div id="content1" class="demo">
							<span style="float: left"><label>Choose account to
									display: </label> <select class="option3" name="fromAccount">
									<option value="blank">All accounts</option>
									<c:forEach var="account" items="${accounts}">
										<option value="${account.id}">${account.title}</option>
									</c:forEach>
							</select> </span> <span style="float: right; margin-right: 30px; margin-top: -15px;">
								<form action="./addExpense">
									<input type="submit" value="Add Expense"
										class="btn btn-lg btn-success warning_1"
										style="width: 150px; margin: 0 auto; display: block;" />
								</form>
							</span>
							<div id="chartdiv"></div>
						</div>
						<div id="sidebar1">
							<c:forEach var="expense" items="${ expenses}">
								<div
									style="margin-top: 10px; border: 1px solid #888; background-color: #FFCC33; overflow: hidden; height: 1%;">
									<span style="font-weight: bold"><fmt:formatDate
											value="${expense.date}" pattern="dd-MMM-yy" /></span> <span
										style="float: right;"> ${expense.sum} ${currency}</span> <span></span>
									<br /> <span>${expense.description}</span> <span
										style="float: left; margin-left: 10px;">
										<form action="./editExpense" >
											<input type="hidden" name="id" value="${expense.id}" /> <input
												type="submit" value="Edit" class="btn btn-sm  btn-info"style="margin-bottom: -20px;" />
										</form>
									</span> <span style="float: right; margin-right: 10px;">
										<form method="POST" action="./removeExpense">
											<input type="hidden" name="id" value="${expense.id}" /> <input
												type="submit" value="Remove" class="btn btn-sm btn-danger" style="margin-bottom: -20px;" />
										</form>
									</span>
								</div>
							</c:forEach>

							<div id="cleared1"></div>
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
	<script type="text/javascript">
		$.get("./showExpenses").success(
				function(data) {
					var chart = AmCharts.makeChart("chartdiv", {
						"type" : "pie",
						"theme" : "light",
						"innerRadius" : "40%",
						"gradientRatio" : [ -0.4, -0.4, -0.4, -0.4, -0.4, -0.4,
								0, 0.1, 0.2, 0.1, 0, -0.2, -0.5 ],
						"dataProvider" : data,
						"balloonText" : "[[value]]",
						"valueField" : "sum",
						"titleField" : "name",
						"balloon" : {
							"drop" : true,
							"adjustBorderColor" : false,
							"color" : "#FFFFFF",
							"fontSize" : 16
						},
						"export" : {
							"enabled" : true
						}
					});
				});
	</script>
</body>
</html>

