<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE HTML>
<html>
<head>
<title>Finance tracker - Add budget</title>
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
					<form method="post" action="./addBudget">
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
										<td>Repeat type:</td>
										<td><select name="repeat">
												<option value="blanck"></option>
												<option value="NO_REPEAT">NO_REPEAT</option>
												<option value="DAILY">DAILY</option>
												<option value="WEEKLY">WEEKLY</option>
												<option value="MONTHLY">MONTHLY</option>
												<option value="MONTHLY">YEARLY</option>
										</select></td>
									</tr>
								</table>

							</div>
							<div class="clearfix"></div>
						</div>
						<div class="profile-btn">
							<input type="submit" value="Add budget" class="btn bg-red" />
							<div class="clearfix"></div>
						</div>
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