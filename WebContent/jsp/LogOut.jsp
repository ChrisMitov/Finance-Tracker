<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Finance tracker - Log out</title>
<%@ include file="partials/headerLogIn&Register.jsp"%>
<script type="text/javascript" src = "resources/js/disableBack.js"></script>
</head>
<body>

	<div class="login">
		<div class="login-bottom">

			<h2>You have logged out successfully!</h2>
			<h2>Thank you for visiting!</h2>
			<form method="get" action="./logout">

				<div class="col-md-6 login-do">
					<a href="./login" class="hvr-shutter-in-horizontal">To log in
						page</a>
				</div>

				<div class="clearfix"></div>
			</form>
		</div>
	</div>
	<!---->
	<%@ include file="./partials/footer.jsp"%>
	<!---->
	<!--scrolling js-->
	<script src="js/jquery.nicescroll.js"></script>
	<script src="js/scripts.js"></script>
	<!--//scrolling js-->
</body>
</html>