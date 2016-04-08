<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Finance tracker - Overview</title>
<jsp:include page="partials/head.jsp" />
<script src="https://www.amcharts.com/lib/3/amcharts.js"></script>
<script src="https://www.amcharts.com/lib/3/serial.js"></script>
<script src="https://www.amcharts.com/lib/3/themes/light.js"></script>
<link href="resources/css/table.css" rel='stylesheet' type='text/css' />
<style type="text/css">
#chartdiv {
	width: 100%;
	height: 500px;
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
					<a href="index.html">Home</a> <i class="fa fa-angle-right"></i> <span>Overview</span>
				</h2>
			</div>
			<!--//banner-->
			<!--grid-->
			<div class="grid-form">
				<div class="grid-form1" style="overflow: hidden; height: 1%;">
					<div id="chartdiv"></div>
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
		$.get("./overviewExpense").success(
				function(data) {
					var chart = AmCharts.makeChart( "chartdiv", {
						  "type": "serial",
						  "theme": "light",
						  "dataProvider": data,
						  "valueAxes": [ {
						    "gridColor": "#FFFFFF",
						    "gridAlpha": 0.2,
						    "dashLength": 0
						  } ],
						  "gridAboveGraphs": true,
						  "startDuration": 1,
						  "graphs": [ {
						    "balloonText": "[[category]]: <b>[[value]]</b>",
						    "fillAlphas": 0.8,
						    "lineAlpha": 0.2,
						    "type": "column",
						    "valueField": "sum"
						  } ],
						  "chartCursor": {
						    "categoryBalloonEnabled": false,
						    "cursorAlpha": 0,
						    "zoomable": false
						  },
						  "categoryField": "day",
						  "categoryAxis": {
						    "gridPosition": "start",
						    "gridAlpha": 0,
						    "tickPosition": "start",
						    "tickLength": 20
						  },
						  "export": {
						    "enabled": true
						  }
						} );
				});
	</script>
</body>
</html>

