<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:useBean id="now" class="java.util.Date" />
<%@page errorPage="Error.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Finance tracker - Budget</title>
<jsp:include page="partials/head.jsp" />
<jsp:include page="./partials/pieHeader.jsp" />
<link href="resources/css/double_table.css" rel='stylesheet'
	type='text/css' />
<link href="resources/css/budget.css" rel='stylesheet' type='text/css' />
<style type="text/css">
#chartdiv {
	width: 100%;
	height: 435px;
	font-size: 11px;
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
					<a href="index.html">Home</a> <i class="fa fa-angle-right"></i> <span>Budget</span>
				</h2>
			</div>
			<!--//banner-->
			<!--grid-->
			<div class="grid-form">
				<div class="grid-form1"
					style="overflow: hidden; height: 1%; position: relative;">
					<div id="wrapper1">
						<div id="content1">
							<jsp:include page="partials/budgetChartHead.jsp" />
							<div id="chartdiv"></div>
							<div class="container-fluid">
								<div class="row text-center" style="overflow: hidden;">
									<div class="col-sm-3"
										style="float: none !important; display: inline-block;">
										<label class="text-left">Angle:</label> <input
											class="chart-input" data-property="angle" type="range"
											min="0" max="60" value="30" step="1" />
									</div>

									<div class="col-sm-3"
										style="float: none !important; display: inline-block;">
										<label class="text-left">Depth:</label> <input
											class="chart-input" data-property="depth3D" type="range"
											min="1" max="25" value="10" step="1" />
									</div>
									<div class="col-sm-3"
										style="float: none !important; display: inline-block;">
										<label class="text-left">Inner-Radius:</label> <input
											class="chart-input" data-property="innerRadius" type="range"
											min="0" max="80" value="0" step="1" />
									</div>
								</div>
							</div>
						</div>
					</div>
					<div id="sidebar1">
						<c:forEach var="entity" items="${sums}">

							<div class="panel panel-success">
								<div class="panel-heading">Title: ${ entity.key.title}</div>

								<div class="panel-body">
									<span><fmt:formatDate value="${entity.key.startDate}"
											pattern="dd-MMM-yyyy" /> </span> <span
										style="float: right; margin-right: 20px;">Sum:
										${entity.key.totalAmount} ${currency}</span> <span></span> <br /> <span
										style="float: right; margin-right: 20px;">Type: ${ entity.key.repeatType}</span>
									<br /> <br /> <span
										style="font-weight: bold; position: relative; left: 20%;">Money
										per day: ${ entity.value} ${currency}</span> <br /> <span
										style="float: left; margin-left: 100px;">
										<form action="./editBudget">
											<input type="hidden" name="id" value="${entity.key.id}" /> <input
												type="submit" value="Edit" class="btn btn-success"
												style="margin-bottom: -10px;" />
										</form>
									</span> <span class="button">
										<form method="POST" action="./removeBudget">
											<input type="hidden" name="id" value="${entity.key.id}" /> <input
												type="submit" value="Remove" class="btn btn-danger"
												style="margin-bottom: -20px;" />
										</form>
									</span>
								</div>
							</div>
						</c:forEach>
						<form action="./addBudget">
							<input type="submit" value="Add new budget"
								class="btn btn-lg btn-success warning_1"
								style="width: 200px; margin: 0 auto; display: block;" />
						</form>
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
		$
				.get("./budgetAccount")
				.success(
						function(data) {
							var chart = AmCharts
									.makeChart(
											"chartdiv",
											{
												"type" : "pie",
												"theme" : "light",
												"dataProvider" : data,
												"valueField" : "sum",
												"titleField" : "title",
												"outlineAlpha" : 0.4,
												"depth3D" : 15,
												"balloonText" : "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>",
												"angle" : 30,
												"export" : {
													"enabled" : true
												}
											});
							jQuery('.chart-input').off().on(
									'input change',
									function() {
										var property = jQuery(this).data(
												'property');
										var target = chart;
										var value = Number(this.value);
										chart.startDuration = 0;

										if (property == 'innerRadius') {
											value += "%";
										}

										target[property] = value;
										chart.validateNow();
									});
						});
	</script>
</body>
</html>