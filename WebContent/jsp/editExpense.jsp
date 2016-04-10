<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:useBean id="now" class="java.util.Date" />
<!DOCTYPE HTML>
<html>
<head>
<title>Finance tracker - Edit expense</title>
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
						href="./expenses">Expense</a> <i class="fa fa-angle-right"></i> <span>Edit
						expense</span>
				</h2>
			</div>
			<!--//banner-->
			<!--grid-->
			<div class="grid-form">
				<div class="grid-form1">
					<h3 id="forms-example" class="">Edit expense</h3>
					<form name="expenses" action="./editExpense" method="post"
						class="demo">
						<table>
							<tr>
								<th><label style="font-size: 28px" for="sum">Sum: </label></th>
								<td><input id="sum" name="sum" type="number"
									class="form-control" placeholder="Sum" value="${expense.sum}"
									style="width: 100px;"></td>
							</tr>
							<tr>
								<th><label style="font-size: 28px" for="description">Description:
								</label></th>
								<td><input id="description" name="description" type="text"
									class="form-control" placeholder="Description"
									value="${expense.description}" style="width: 400px;"></td>
							</tr>
							<tr>
								<th><label style="font-size: 28px" for="account">Choose
										account: </label></th>
								<td><select class="option3" name="account" style="margin-left: -2px;">
										<c:forEach var="account" items="${accounts}">
											<option value="${account.id}">${account.title}</option>
										</c:forEach>
								</select></td>
							</tr>
							<tr>
								<th><label style="font-size: 28px" for="category">Choose
										category: </label></th>
								<td><select class="option4" id="cat_id" name="category"
									onchange="refreshTags()" style="margin-left: -2px;">
										<c:forEach var="category" items="${categories}">
											<option value="${category.id}">${category.name}</option>
										</c:forEach>
								</select></td>
							</tr>
							<tr>
								<th><label style="font-size: 28px" for="date">Date:
								</label></th>
								<td><fmt:formatDate value="${expense.date}"
										pattern="yyyy-MM-dd" var="myDate" /> <input id="date"
									name="date" type="date" class="form-control"
									placeholder="YYYY-MM-DD" value='${myDate}'
									style="width: 200px; color: black;"></td>
							</tr>
							<tr>
								<th><label style="font-size: 28px" for="account">Tags:
								</label></th>
								<td><div id="tags">
										<c:forEach var="tag" items="${tags}">
											<input id="tagsCat" name="tags" type="checkbox"
												value="${ tag.name}">
											<input type="hidden" name="_tags" value="on">
											<label for="tag${tag.name}">${tag.name}</label>
										</c:forEach>
									</div></td>
							</tr>
						</table>
						<br /> <input type="submit" value="Submit "
							class="btn btn-lg btn-danger" style="margin-left: 215px;">
					</form>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="partials/footer.jsp" />
	<div class="clearfix"></div>
	<script type="text/javascript">
		function refreshTags() {
			var category = $("#cat_id").val();

			$
					.ajax({
						url : './showTags?catId=' + category,
						type : 'GET',
						dataType : "json",
						success : function(data) {
							$("#tags").empty();
							$
									.each(
											data,
											function(index, tag) {
												var html = ' <input id="tagsCat" name="tags" type="checkbox" value="' + tag.name + '"> ';
												html += ' <input type="hidden" name="_tags" value="on"> ';
												html += ' <label for="tag' + tag.name + '">'
														+ tag.name
														+ '</label> ';
												$("#tags").append(html);
											});
						}
					});
		}
	</script>
	<script src="resources/js/jquery.nicescroll.js"></script>
	<script src="resources/js/scripts.js"></script>
</body>
</html>

