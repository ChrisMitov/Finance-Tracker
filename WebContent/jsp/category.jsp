<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Finance tracker - Categories</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="Minimal Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<script type="application/x-javascript">
	
	
	
	
	
	
	
	
	
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 









</script>
<link href="resources/css/bootstrap.min.css" rel='stylesheet'
	type='text/css' />
<!-- Custom Theme files -->
<link href="resources/css/style.css" rel='stylesheet' type='text/css' />
<link href="resources/css/font-awesome.css" rel="stylesheet">
<script src="resources/js/jquery.min.js">
	
</script>
<script src="resources/js/bootstrap.min.js">
	
</script>

<!-- Mainly scripts -->
<script src="resources/js/jquery.metisMenu.js"></script>
<script src="resources/js/jquery.slimscroll.min.js"></script>
<!-- Custom and plugin javascript -->
<link href="resources/css/custom.css" rel="stylesheet">
<script src="resources/js/custom.js"></script>
<script src="resources/js/screenfull.js"></script>
<script>
	$(function() {
		$('#supported').text('Supported/allowed: ' + !!screenfull.enabled);

		if (!screenfull.enabled) {
			return false;
		}

		$('#toggle').click(function() {
			screenfull.toggle($('#container')[0]);
		});

	});
</script>


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
					<div class="grid-form1" style="height: 650px; position: relative;">
						<h1 style="text-align: center; margin-left: -50px">Categories:
						</h1>
						<br />
						<c:forEach var="category" items="${categories}">
							<div
								style="float: left; width: 200px; padding: 10px 30px; margin-right: 50px;  margin-bottom: 25px; text-align: center; background: url(resources/images/silver-gray.jpg); position: relative;">
								<h4
									style="position: absolute; vertical-align: middle; display: inline-block; margin-right: 50px; margin-left: -30px">${category.name}</h4>
								<br />
								<form method="POST" action="./category/remove">
									<input type="hidden" name="id" value="${category.id}" /> <input
										type="submit" value="Remove" class="btn btn-xs btn-danger"
										style="width: 75px; margin: 0 auto; display: block;" />
								</form>
								<form method="POST" action="./tags">
									<input type="hidden" name="id" value="${category.id}" /> <input
										type="submit" value="Show tags" class="btn btn-xs btn-info"
										style="width: 75px; margin: 0 auto; display: block;" />
								</form>
							</div>
						</c:forEach>
						<div style="float: left; width: 200px; padding: 10px 30px;">
							<a href="./addCategory"> <img src="resources/images/plus.png">
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

