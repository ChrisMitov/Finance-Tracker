<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>

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
				<div class="grid-form">
					<div class="grid-form1">
						<h3 id="forms-example" class="">Add category</h3>
						<form name="category" action="./category" method="post">
						<div>
							<label for="user_name">Name: </label> <input
								id="category_name" name="name" type="text"  class="form-control"
								placeholder="Category name" value="">
						</div>
						<br />
						<input type = "submit" value ="Submit "class="btn btn-lg btn-danger" >
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

    