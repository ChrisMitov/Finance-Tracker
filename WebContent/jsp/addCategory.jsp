<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@page errorPage="Error.jsp"%>

<!DOCTYPE HTML>
<html>
<head>
<title>Finance tracker - Add category</title>
<jsp:include page="partials/head.jsp" />
</head>
<body>
	<jsp:include page="partials/header.jsp" />
	<div id="page-wrapper" class="gray-bg dashbard-1">
		<div class="content-main">

			<!--banner-->
			<div class="banner">
				<h2>
					<a href="index.html">Home</a> <i class="fa fa-angle-right"></i> <span>Category</span>
				</h2>
			</div>
			<!--//banner-->
			<!--grid-->
			<div class=" profile">

				<div class="profile-bottom">
					<h3>
						<i class="fa fa-cogs">${" "}</i>Add Tags
					</h3>
					<form name="category" action="./category" method="post">
						<div class="profile-bottom-top">

							<div class="col-md-8 profile-text">
								<table>
									<tr>
										<td>Name:</td>
										<td><input id="category_name" name="name" type="text"
											placeholder="Category name" value=""></td>
									</tr>
									<tr>
										<td></td>
										<td></td>
									</tr>
								</table>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="profile-btn">
							<input type="submit" value="Submit "
								class="btn btn-lg btn-danger">
							<div class="clearfix"></div>
						</div>
					</form>
					<c:if test="${not empty error}">
						<p style="color: red">${error}</p>
					</c:if>
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

