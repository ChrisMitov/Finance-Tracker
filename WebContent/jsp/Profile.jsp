<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>

<!--
Author: W3layouts
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE HTML>
<html>
<head>
<title>Edit profile</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="Minimal Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<script type="application/x-javascript">
	
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 

</script>
<link href="../resources/css/bootstrap.min.css" rel='stylesheet'
	type='text/css' />
<!--<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />-->
<!-- Custom Theme files -->
<link href="../resources/css/style.css" rel='stylesheet' type='text/css' />
<link href="../resources/css/font-awesome.css" rel="stylesheet">
<script src="../resources/js/jquery.min.js">
	
</script>
<script src="../resources/js/bootstrap.min.js">
	
</script>

<!-- Mainly scripts -->
<script src="../resources/js/jquery.metisMenu.js"></script>
<script src="../resources/js/jquery.slimscroll.min.js"></script>
<!-- Custom and plugin javascript -->
<link href="../resources/css/custom.css" rel="stylesheet">
<script src="../resources/js/custom.js"></script>
<script src="../resources/js/screenfull.js"></script>
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
	<%@ include file="./partials/header.jsp"%>
	<div id="page-wrapper" class="gray-bg dashbard-1">
		<div class="content-main">

			<!--banner-->
			<div class="banner">
				<h2>
					<a href="index.html">Home</a> <i class="fa fa-heart-o"></i> <span>Profile</span>
				</h2>
			</div>
			<!--//banner-->
			<!--gallery-->
			<div class=" profile">

				<div class="profile-bottom">
					<h3>
						<i class="fa fa-magic"></i>Edit your profile
					</h3>
					<form method="post" action="../EditProfile">
						<div class="profile-bottom-top">

							<div class="col-md-8 profile-text">
								<h5>${userName}</h5>
								<h5>${lastName}</h5>

								<table>
									<tr>
										<td>Name:</td>
										<td>:</td>
										<td>${userName}</td>
										<td><input type="text" placeholder="New first name"
											name="newFirstName" /></td>
									</tr>
									<tr>
										<td>Name:</td>
										<td>:</td>
										<td>${lastName}</td>
										<td><input type="text" placeholder="New last name"
											name="newLastName" /></td>
									</tr>
									<tr>
										<td>Email</td>
										<td>:</td>
										<td>${email}</td>
										<td><input type="text" pattern="[^ @]*@[^ @]*"
											placeholder="New email" name="newEmail" /></td>
									</tr>
									<tr>
										<td>Password</td>
										<td>:</td>
										<td>****</td>
										<td><input type="password" placeholder="New password"
											name="newPassword" /></td>
									</tr>
									<tr>
										<td>Currency</td>
										<td>:</td>
										<td>${currency}</td>
										<td><select name="newCurrency">
												<option value="blanck"></option>
												<option value="BGN">BGN</option>
												<option value="EUR">EUR</option>
												<option value="UDS">UDS</option>
										</select></td>
									</tr>
									<tr>
										<td>Joined on</td>
										<td>:</td>
										<td>${startDate}</td>
									</tr>
								</table>

							</div>
							<div class="clearfix"></div>
						</div>
						<div class="profile-btn">
							<input type="submit" value="Save changes" class="btn bg-red" />
							<div class="clearfix"></div>
						</div>
						<c:if test="${not empty passwordError}">
							<p style="color: red">
								<c:out value="${passwordError}"></c:out>
						</c:if>
					</form>
				</div>
			</div>

			<%@ include file="./partials/footer.jsp"%>
		</div>
		<div class="clearfix"></div>
	</div>

	<!---->

	<!--scrolling js-->
	<script src="../resources/js/jquery.nicescroll.js"></script>
	<script src="../resources/js/scripts.js"></script>
	<!--//scrolling js-->
</body>
</html>