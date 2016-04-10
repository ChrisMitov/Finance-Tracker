<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:useBean id="now" class="java.util.Date" />
<!DOCTYPE HTML>
<html>
<head>
<title>Finance tracker - Report</title>
<jsp:include page="partials/head.jsp" />
<link href="resources/css/double_table.css" rel='stylesheet'
	type='text/css' />
<link href="resources/css/select.css" rel='stylesheet' type='text/css' />
</head>
<body>
	<jsp:include page="partials/header.jsp" />
	<div id="page-wrapper" class="gray-bg dashbard-1">
		<div class="content-main">

			<!--banner-->
			<div class="banner">
				<h2>
					<a href="./">Home</a> <i class="fa fa-angle-right"></i> <span>Report</span>
				</h2>
			</div>
			<!--//banner-->
			<!--grid-->
			<div class="grid-form">
				<div class="grid-form1"
					style="overflow: hidden; height: 1%; position: relative;">
					<form name="" action="./report" method="POST" class="demo">
						<label> Choose period: </label> <select class="option3"
							name="month">
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
						</select> <br /> <span></span> <br /> <br /> <span style="float: left"><label>Choose
								type to display: </label> <select class="option3" name="reportType">
								<option value="0">Both</option>
								<c:forEach var="type" items="${types}">
									<option value="${type}">${type}</option>
								</c:forEach>
						</select> </span> <br /> <span></span> <br /> <br /> <input type="image"
							src="resources/images/pdf.png" alt="Submit Form"
							style="width: 150px; height: 150px;" />
					</form>
					<div id="chartdiv"></div>

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

