<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE HTML>
<html>
<head>
<title>Finance tracker - Add tag</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="Minimal Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<script type="application/x-javascript">
	
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 

</script>
<link href="resources/css/bootstrap.min.css" rel='stylesheet' type='text/css' />
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
						<a href="./">Home</a> <i class="fa fa-angle-right"></i> <span>Tags</span>
					</h2>
				</div>
				<!--//banner-->
				<!--grid-->
				<div class="grid-form">
					<div class="grid-form1">
						<h3 id="forms-example" class="">Add tag</h3>
						<form name="category" action="./addTag" method="post">
						<div>
							<label for="user_name">Name: </label> <input
								id="category_name" name="name" type="text"  class="form-control"
								placeholder="Tag name" value="" style = "width: 350px;">
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

    