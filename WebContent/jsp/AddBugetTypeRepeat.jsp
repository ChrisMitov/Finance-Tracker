<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@page errorPage="Error.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<title>Finance tracker - Add budget</title>
<jsp:include page="partials/head.jsp" />
</head>
<body>
	<jsp:include page="partials/header.jsp" />
	<div id="page-wrapper" class="gray-bg dashbard-1">
		<div class="content-main">

			<!--banner-->
			<div class="banner">
				<h2>
					<a href="./">Home</a> <i class="fa fa-angle-right"></i> <span>Budget</span>
				</h2>
			</div>
			<!--//banner-->
			<!--grid-->
			<div class=" profile">

				<div class="profile-bottom">
					<h3>
						<i class="fa fa-archive">${" "}</i>Add budget
					</h3>
					<form method="post" action="./repeatBudget">
						<div class="profile-bottom-top">

							<div class="col-md-8 profile-text">

								<table>
									<tr>
										<td>Title:</td>
										<td><input type="text" placeholder="Title" name="title" /></td>
									</tr>
									<tr>
										<td>Sum:</td>
										<td><input type="text" placeholder="Sum" name="sum" /></td>
									</tr>
									<tr>
										<td>Start date:</td>
										<td><input type="date" placeholder="Start date"
											name="start" /></td>
									</tr>
									<tr>
										<td>Repeat type: ON_REPEAT</td>
										<td>
										<td>Start date:</td>
										<td><input type="date" placeholder="Start date"
											name="start" /></td>

										<td>
										<td>End date:</td>
										<td><input type="date" placeholder="Start date"
											name="end" /></td>


									</tr>
								</table>

							</div>
							<div class="clearfix"></div>
						</div>
						<div class="profile-btn">
							<input type="submit" value="Add budget" class="btn btn-warning" />
							<div class="clearfix"></div>
						</div>
						<c:if test="${not empty emptyField}">
							<p style="color: red">
								<c:out value="${emptyField}"></c:out>
						</c:if>
					</form>
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