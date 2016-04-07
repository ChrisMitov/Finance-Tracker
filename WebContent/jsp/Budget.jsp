<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Finance tracker - Budget</title>
<jsp:include page="partials/head.jsp" />
<link href="resources/css/table.css" rel='stylesheet' type='text/css' />
</head>
<body>
	<jsp:include page="partials/header.jsp" />
	<div id="page-wrapper" class="gray-bg dashbard-1">
		<div class="content-main">

			<!--banner-->
			<div class="banner">
				<h2>
					<a href="index.html">Home</a><i class="fa fa-angle-right"></i> <span>Budget</span>
				</h2>
			</div>
			<!--//banner-->
			<!--grid-->
			
			<div class="content-top">


				<div class="col-md-4 ">
					<div class="content-top-1">
						<div class="col-md-6 top-content">
							<table class="one">
								<tr>
									<th colspan="3">Title</th>
									<th colspan="3">Amount</th>
									<th colspan="3">Repeat type</th>
									<th colspan="3"></th>
									<th colspan="3"></th>
								</tr>
								<c:forEach var="budget" items="${budgets}">
									<tr>
										<td colspan="3">${budget.title}</td>
										<td colspan="3">${budget.totalAmount}</td>
										<td colspan="3">${budget.repeatType}</td>
										<td colspan="3">
											<form action="./editBudget">
												<input type="hidden" name="budgetid" value="${budget.id}" />
												<input type="submit" value="Edit" class="btn btn-s btn-info"
													style="width: 75px; margin: 0 auto; display: block;" />
											</form>
										</td>
										<td colspan="3">
											<form method="POST" action="./removeBudget">
												<input type="hidden" name="id" value="${budget.id}" /> <input
													type="submit" value="Remove" class="btn btn-s btn-danger"
													style="width: 75px; margin: 0 auto; display: block;" />
											</form>
										</td>
									</tr>
								</c:forEach>
								<tr>
									<td colspan="15">
										<form action="./addBudget">
											<input type="submit" value="Add new budget"
												class="btn btn-lg btn-success warning_1"
												style="width: 150px; margin: 0 auto; display: block;" />
										</form>
									</td>
								</tr>
							</table>
						</div>

						<div class="clearfix"></div>
					</div>
				</div>
				<div class="col-md-8 content-top-2">
					<!---start-chart---->
					<!----->
					<div class="content-graph">
						<div class="content-color">
							<div class="content-ch">
								<p>
									<i></i>Chrome
								</p>
								<span>100%</span>
								<div class="clearfix"></div>
							</div>
							<div class="content-ch1">
								<p>
									<i></i>Safari
								</p>
								<span> 50%</span>
								<div class="clearfix"></div>
							</div>
						</div>
						<!--graph-->
						<link rel="stylesheet" href="../resources/css/graph.css">
						<!--//graph-->
						<script src="js/jquery.flot.js"></script>
						<script>
							$(document)
									.ready(
											function() {

												// Graph Data ##############################################
												var graphData = [
														{
															// Visits
															data : [
																	[ 6, 1300 ],
																	[ 7, 1600 ],
																	[ 8, 1900 ],
																	[ 9, 2100 ],
																	[ 10, 2500 ],
																	[ 11, 2200 ],
																	[ 12, 2000 ],
																	[ 13, 1950 ],
																	[ 14, 1900 ],
																	[ 15, 2000 ] ],
															color : '#999999'
														},
														{
															// Returning Visits
															data : [
																	[ 6, 500 ],
																	[ 7, 600 ],
																	[ 8, 550 ],
																	[ 9, 600 ],
																	[ 10, 800 ],
																	[ 11, 900 ],
																	[ 12, 800 ],
																	[ 13, 850 ],
																	[ 14, 830 ],
																	[ 15, 1000 ] ],
															color : '#999999',
															points : {
																radius : 4,
																fillColor : '#7f8c8d'
															}
														} ];

												// Lines Graph #############################################
												$
														.plot(
																$('#graph-lines'),
																graphData,
																{
																	series : {
																		points : {
																			show : true,
																			radius : 5
																		},
																		lines : {
																			show : true
																		},
																		shadowSize : 0
																	},
																	grid : {
																		color : '#7f8c8d',
																		borderColor : 'transparent',
																		borderWidth : 20,
																		hoverable : true
																	},
																	xaxis : {
																		tickColor : 'transparent',
																		tickDecimals : 2
																	},
																	yaxis : {
																		tickSize : 1000
																	}
																});

												// Bars Graph ##############################################
												$
														.plot(
																$('#graph-bars'),
																graphData,
																{
																	series : {
																		bars : {
																			show : true,
																			barWidth : .9,
																			align : 'center'
																		},
																		shadowSize : 0
																	},
																	grid : {
																		color : '#7f8c8d',
																		borderColor : 'transparent',
																		borderWidth : 20,
																		hoverable : true
																	},
																	xaxis : {
																		tickColor : 'transparent',
																		tickDecimals : 2
																	},
																	yaxis : {
																		tickSize : 1000
																	}
																});

												// Graph Toggle ############################################
												$('#graph-bars').hide();

												$('#lines')
														.on(
																'click',
																function(e) {
																	$('#bars')
																			.removeClass(
																					'active');
																	$(
																			'#graph-bars')
																			.fadeOut();
																	$(this)
																			.addClass(
																					'active');
																	$(
																			'#graph-lines')
																			.fadeIn();
																	e
																			.preventDefault();
																});

												$('#bars')
														.on(
																'click',
																function(e) {
																	$('#lines')
																			.removeClass(
																					'active');
																	$(
																			'#graph-lines')
																			.fadeOut();
																	$(this)
																			.addClass(
																					'active');
																	$(
																			'#graph-bars')
																			.fadeIn()
																			.removeClass(
																					'hidden');
																	e
																			.preventDefault();
																});

												// Tooltip #################################################
												function showTooltip(x, y,
														contents) {
													$(
															'<div id="tooltip">'
																	+ contents
																	+ '</div>')
															.css({
																top : y - 16,
																left : x + 20
															}).appendTo('body')
															.fadeIn();
												}

												var previousPoint = null;

												$('#graph-lines, #graph-bars')
														.bind(
																'plothover',
																function(event,
																		pos,
																		item) {
																	if (item) {
																		if (previousPoint != item.dataIndex) {
																			previousPoint = item.dataIndex;
																			$(
																					'#tooltip')
																					.remove();
																			var x = item.datapoint[0], y = item.datapoint[1];
																			showTooltip(
																					item.pageX,
																					item.pageY,
																					y
																							+ ' visitors at '
																							+ x
																							+ '.00h');
																		}
																	} else {
																		$(
																				'#tooltip')
																				.remove();
																		previousPoint = null;
																	}
																});

											});
						</script>
						<div class="graph-container">

							<div id="graph-lines"></div>
							<div id="graph-bars"></div>
						</div>

					</div>
				</div>
				<div class="clearfix"></div>

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

</body>
</html>