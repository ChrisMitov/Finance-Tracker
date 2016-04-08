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
					style="width: 1200px; overflow: hidden; height: 1%; display: table-cell; text-align: center; vertical-align: middle;">
					<form name="" action="./incomePeriod" method="get" class="demo">
						<span style="float: left;"> <label> Choose period:<c:out
									value="${month}"></c:out>
						</label> <select class="option3" name="month">
								<c:forEach var="i" begin="1" end="12">
									<c:choose>
										<c:when test="${ month != i } ">
											<option value="<c:out value = "${i}" />"><c:out
													value="${i}" /></option>
										</c:when>
										<c:otherwise>
											<option selected value="<c:out value = "${i}" />"><c:out
													value="${i}" /></option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
						</select> <select class="option3" name="year">
								<c:forEach var="i" begin="${year-5}" end="${year+5}">
									<c:choose>
										<c:when test="${ year != i } ">
											<option value="<c:out value = "${i}" />"><c:out
													value="${i}" /></option>
										</c:when>
										<c:otherwise>

											<option value="<c:out value = "${i}" />" selected><c:out
													value="${i}" /></option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
						</select> <span style="float: right"><input type="submit"
								value="Change" class="btn btn-sm btn-danger warning_1"
								style="margin: 0 auto; margin-left: 10px; display: block;" /></span> <br />
							<br />
						</span> <span style="float: left"><label>Choose account to
								display: </label> <select class="option3" name="fromAccount">
								<option value="0">All accounts</option>
								<c:forEach var="account" items="${accounts}">
									<option value="${account.id}">${account.title}</option>
								</c:forEach>
						</select> </span>
					</form>
					<br /> <span></span> <br /> <br /> <br />
					<br />
					<br /> <span style="font-size: 24px;">There's no incomes </span> <br />
					<br /> <img src="resources/images/empty.png">
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

