<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Finance tracker - Overview</title>
<jsp:include page="partials/head.jsp" />
<link href="resources/css/select.css" rel='stylesheet' type='text/css' />
<script src="https://www.amcharts.com/lib/3/amcharts.js"></script>
<script src="https://www.amcharts.com/lib/3/serial.js"></script>
<script src="https://www.amcharts.com/lib/3/themes/light.js"></script>
<style type="text/css">
#chartdiv {
	width: 100%;
	height: 435px;
	font-size: 12px;
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
					<a href="index.html">Home</a> <i class="fa fa-angle-right"></i> <span>Overview</span>
				</h2>
			</div>
			<!--//banner-->
			<!--grid-->
			<div class="grid-form">
				<div class="grid-form1" style="overflow: hidden; height: 1%;">
					<form name="" action="./overviewPeriod" method="get" class="demo">
						<span style="float: left;"> <label> Choose period:
						</label> <select class="option3" name="month">
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
								style="margin: 0 auto; margin-left: 10px; display: block;" /></span> <br />
							<br />
						</span>
					</form>
					<br /> <span></span> <br /> <br /> <br />
					<div id="chartdiv"></div>
					<div class="container-fluid">
						<div class="row text-center" style="overflow: hidden;">
							<div class="col-sm-3"
								style="float: none !important; display: inline-block;">
								<label class="text-left">Angle:</label> <input
									class="chart-input" data-property="angle" type="range" min="0"
									max="89" value="30" step="1" />
							</div>

							<div class="col-sm-3"
								style="float: none !important; display: inline-block;">
								<label class="text-left">Depth:</label> <input
									class="chart-input" data-property="depth3D" type="range"
									min="1" max="120" value="60" step="1" />
							</div>
						</div>
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
	<!--//scrolling js-->
	<script type="text/javascript">
		$
				.get("./overviewRest")
				.success(
						function(data) {
							var chart = AmCharts
									.makeChart(
											"chartdiv",
											{
												"theme" : "light",
												"type" : "serial",
												"dataProvider" : data,
												"valueAxes" : [ {
													"stackType" : "3d",
													"unit" : "",
													"position" : "left",
													"title" : "Sum per day",
												} ],
												"startDuration" : 1,
												"graphs" : [
														{
															"balloonText" : "Daily income on [[category]]: <b>[[value]]</b>",
															"fillAlphas" : 0.9,
															"lineAlpha" : 0.2,
															"title" : "income",
															"type" : "column",
															"valueField" : "incomeNumber"
														},
														{
															"balloonText" : "Daily expense on [[category]]: <b>[[value]]</b>",
															"fillAlphas" : 0.9,
															"lineAlpha" : 0.2,
															"title" : "expense",
															"type" : "column",
															"valueField" : "expenseNumber"
														} ],
												"plotAreaFillAlphas" : 0.1,
												"depth3D" : 60,
												"angle" : 30,
												"categoryField" : "name",
												"categoryAxis" : {
													"gridPosition" : "start"
												},
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
										chart.startDuration = 0;

										if (property == 'topRadius') {
											target = chart.graphs[0];
											if (this.value == 0) {
												this.value = undefined;
											}
										}

										target[property] = this.value;
										chart.validateNow();
									});
						})
	</script>
</body>
</html>

