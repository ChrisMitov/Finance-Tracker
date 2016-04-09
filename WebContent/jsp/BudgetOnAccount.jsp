<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:useBean id="now" class="java.util.Date" />
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
						<div id="content1" class="demo">
							<form name="" action="./charts" method="get" class="demo">
								<span style="float: left"><label>Choose format to
										display: </label> <select class="option3" name="display">
										<option value="blanck"></option>
										<option value="sum">By sums</option>
										<option value="acc">By accounts</option>
								</select> </span>
							</form>
							<div id="chartdiv"
								style="width: 100%; height: 500px; font-size: 11px;"></div>
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
		var chart = AmCharts.makeChart("chartdiv", {
			"type" : "funnel",
			"theme" : "light",
			"dataProvider" : [ {
				"title" : "Website visits",
				"value" : 300
			}, {
				"title" : "Downloads",
				"value" : 123
			}, {
				"title" : "Requested price list",
				"value" : 98
			}, {
				"title" : "Contaced for more info",
				"value" : 72
			}, {
				"title" : "Purchased",
				"value" : 35
			}, {
				"title" : "Contacted for support",
				"value" : 15
			}, {
				"title" : "Purchased additional products",
				"value" : 8
			} ],
			"balloon" : {
				"fixedPosition" : true
			},
			"valueField" : "value",
			"titleField" : "title",
			"marginRight" : 240,
			"marginLeft" : 50,
			"startX" : -500,
			"rotate" : true,
			"labelPosition" : "right",
			"balloonText" : "[[title]]: [[value]]n[[description]]",
			"export" : {
				"enabled" : true
			}
		});
	</script>
</body>
</html>