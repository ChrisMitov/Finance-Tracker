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
					style="overflow: hidden; height: 1%; position: relative;">
					<div id="wrapper1">
						<div id="content1" class="demo">
							<form name="" action="./incomePeriod" method="get" class="demo">
								<span style="float: left;"> <label> Choose
										period: </label> <select class="option3" name="month">
										<c:forEach var="i" begin="1" end="12">
											<c:choose>
												<c:when test="${ month == i }">
													<option value="<c:out value = "${i}" />" selected><c:out
															value="${i}" /></option>
												</c:when>
												<c:otherwise>
													<option value="<c:out value = "${i}" />"><c:out
															value="${i}" /></option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
								</select> <select class="option3" name="year">
										<c:forEach var="i" begin="${year-5}" end="${year+5}">
											<c:choose>
												<c:when test="${ year == i }">
													<option value="<c:out value = "${i}" />" selected><c:out
															value="${i}" /></option>
												</c:when>
												<c:otherwise>
													<option value="<c:out value = "${i}" />"><c:out
															value="${i}" /></option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
								</select> <span style="float: right"><input type="submit"
										value="Change" class="btn btn-sm btn-danger warning_1"
										style="margin: 0 auto; margin-left: 10px; display: block;" /></span>
								</span> <span style="float: left"><label>Choose account
										to display: </label> <select class="option3" name="fromAccount">
										<option value="0">All accounts</option>
										<c:forEach var="account" items="${accounts}">
											<option value="${account.id}">${account.title}</option>
										</c:forEach>
								</select> </span>
							</form>
							<span
								style="float: right; margin-right: 30px; margin-top: -15px;">
								<form action="./addIncome">
									<input type="submit" value="Add Income"
										class="btn btn-lg btn-success warning_1"
										style="width: 150px; margin: 0 auto; display: block;" />
								</form>
							</span>
							<div id="chartdiv"></div>
						</div>
						<div id="sidebar1">
							<c:forEach var="income" items="${incomes}">
								<div
									style="margin-top: 10px; border: 1px solid #888; background-color: #FFCC33; overflow: hidden; height: 1%;">
									<span
										style="font-weight: bold; float: left; margin-left: 20px;"><fmt:formatDate
											value="${income.date}" pattern="dd-MMM-yyyy" /></span> <span
										style="float: right; margin-right: 20px;">Sum:
										${income.sum} ${currency}</span> <span></span> <br /> <span
										style="float: left; margin-left: 20px;">${income.description}</span>
									<span style="float: right; margin-right: 20px;">Category:
										${income.category.name}</span> <br /> <span
										style="float: left; margin-left: 20px;">
										<form action="./editIncome">
											<input type="hidden" name="id" value="${income.id}" /> <input
												type="submit" value="Edit" class="btn btn-sm  btn-info"
												style="margin-bottom: -20px;" />
										</form>
									</span> <span style="float: right; margin-right: 20px;">
										<form method="POST" action="./removeIncome">
											<input type="hidden" name="id" value="${income.id}" /> <input
												type="submit" value="Remove" class="btn btn-sm btn-danger"
												style="margin-bottom: -20px;" />
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
		$.get("./showIncome").success(
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

