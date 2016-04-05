<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Finance tracker - Expenses</title>
<jsp:include page="partials/head.jsp" />
<jsp:include page="./partials/pieHeader.jsp" />
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
				<div class="grid-form1" style="height: 650px; position: relative;">
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

