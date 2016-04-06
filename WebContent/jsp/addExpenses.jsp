<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Finance tracker - Add Expense</title>
<jsp:include page="partials/head.jsp" />
<link href="resources/css/select.css" rel='stylesheet' type='text/css' />
</head>
<body>
	<jsp:include page="partials/header.jsp" />
	<div id="page-wrapper" class="gray-bg dashbard-1">
		<div class="content-main">

			<!--banner-->
			<div class="banner">
				<h2>
					<a href="./">Home</a> <i class="fa fa-angle-right"></i> <a
						href="./expense">Expense</a> <i class="fa fa-angle-right"></i><span>Add
						Expense</span>
				</h2>
			</div>
			<!--//banner-->
			<!--grid-->
			<div class="grid-form">
				<div class="grid-form1">
					<h3 id="forms-example" class="">Add expenses</h3>
					<form name="expenses" action="./addExpenses" method="post"
						class="demo">
						<table>
							<tr>
								<th><label style="font-size: 28px" for="sum">Sum: </label></th>
								<td><input id="sum" name="sum" type="number"
									class="form-control" placeholder="Sum" value=""
									style="width: 100px;"></td>
							</tr>
							<tr>
								<th><label style="font-size: 28px" for="description">Description:
								</label></th>
								<td><input id="description" name="description" type="text"
									class="form-control" placeholder="Description" value=""
									style="width: 400px;"></td>
							</tr>
							<tr>
								<th><label style="font-size: 28px" for="account">Choose
										account: </label></th>
								<td><select class="option3" name="account">
										<c:forEach var="account" items="${accounts}">
											<option value="${account.id}">${account.title}</option>
										</c:forEach>
								</select></td>
							</tr>
							<tr>
								<th><label style="font-size: 28px" for="category">Choose
										category: </label></th>
								<td><select class="option4" name="category">
										<c:forEach var="category" items="${categories}">
											<option value="${category.id}">${category.name}</option>
										</c:forEach>
								</select></td>
							</tr>
							<tr>
								<th><label style="font-size: 28px" for="date">Date:
								</label></th>
								<td><input id="date" name="date" type="date"
									class="form-control" placeholder="YYYY-MM-DD" value=""
									style="width: 200px;"></td>
							</tr>
							<tr>
								<th><label style="font-size: 28px" for="repeat">Repeat:
								</label></th>
								<td><select class="option3" name="repeat">
										<c:forEach var="repeat" items="${repeats}">
											<option value="${repeat}">${repeat}</option>
										</c:forEach>
								</select></td>
							</tr>
							<tr>
								<th><label style="font-size: 28px" for="account">Tags:
								</label></th>
								<td><c:forEach var="tag" items="${tags}">
										<span style="display: inline-block;"> <label
											for="${tag.name}"
											style="color: black; display: inline-block;">${tag.name}</label>
											<input id="${tag.id}" name="tags" type="checkbox"
											class="form-control" value="${tag.name}"></span>
									</c:forEach></td>
							</tr>
						</table>
						<br /> <input type="submit" value="Submit "
							class="btn btn-lg btn-danger" style="margin-left: 215px;">
					</form>
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
