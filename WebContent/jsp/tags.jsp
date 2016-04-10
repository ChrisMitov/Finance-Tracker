<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page errorPage="Error.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<title>Finance tracker - Tags</title>
<jsp:include page="partials/head.jsp" />
</head>
<body>
	<jsp:include page="partials/header.jsp" />
		<div id="page-wrapper" class="gray-bg dashbard-1">
			<div class="content-main">

				<!--banner-->
				<div class="banner">
					<h2>
						<a href="index.html">Home</a><i class="fa fa-angle-right"></i><a href="./category">Category</a> <i class="fa fa-angle-right"></i> <span>Tags</span>
					</h2>
				</div>
				<!--//banner-->
				<!--grid-->
				<div class="grid-form">
					<div class="grid-form1" style="height: 650px; position: relative;">
						<h1 style="text-align: center; margin-left: -50px">Tags for ${category_name}
						</h1>
						<br />
						<c:forEach var="tag" items="${tags}">
							<div
								style="float: left; width: 200px; padding: 10px 30px; margin-right: 50px;  margin-bottom: 25px; text-align: center; background: url(resources/images/green.jpg); position: relative;">
								<h4
									style="position: absolute; vertical-align: middle; display: inline-block; margin-right: 50px; margin-left: -30px">${tag.name}</h4>
								<br />
								<form method="POST" action="./tag/remove">
									<input type="hidden" name="id" value="${tag.id}" /> <input
										type="submit" value="Remove" class="btn btn-xs btn-danger"
										style="width: 75px; margin: 0 auto; display: block;" />
								</form>
							</div>
						</c:forEach>
						<div style="float: left; width: 200px; padding: 10px 30px;">
						<input type="hidden" name="category_name" value="${category_name}" />
							<a href="./addTag"> <img src="resources/images/plus.png">
							</a>
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
</body>
</html>