<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
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
					<form method="post" action="./MyAddBudget">
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
										<td>End date:</td>
										<td><input type="date" placeholder="End date" name="end" /></td>
									</tr>
									<!--  <tr>
										<td>Repeat type:</td>
										<td><select name="repeat">
												<option value="blanck"></option>
												<option value="NO_REPEAT">NO_REPEAT</option>
												<option value="DAILY">DAILY</option>
												<option value="WEEKLY">WEEKLY</option>
												<option value="MONTHLY">MONTHLY</option>
												<option value="MONTHLY">YEARLY</option>
										</select></td>
									</tr>-->
									<tr>
										<td>Choose account:</td>
										<td><div class="account">
												<c:forEach var="account" items="${accounts}">
													<input type="checkbox" name="selected"
														value="${account.id}"> ${account.title}<br>
												</c:forEach>
											</div></td>
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
						<c:if test="${not empty dates}">
							<p style="color: red">
								<c:out value="${dates}"></c:out>
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