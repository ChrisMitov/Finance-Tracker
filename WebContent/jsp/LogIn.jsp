<!--
Author: W3layouts
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Log in</title>
<%@ include file="./partials/headerLogIn&Register.jsp" %>
</head>
<body>
	<div class="login">
	<c:if test="${not empty sessionScope.userId}">
			<p>${sessionScope.userId}</p>
		</c:if>
		<h1>Finance Tracker</h1>
		<div class="login-bottom">
			<h2>Login</h2>
			<form method="post" action="../LogInServlet">
				<div class="col-md-6">
					<div class="login-mail">
						<input type="text" placeholder="Email" name="username"> <i
							class="fa fa-envelope"></i>
					</div>
					<div class="login-mail">
						<input type="password" placeholder="Password" name="password">
						<i class="fa fa-lock"></i>
					</div>
					<!--  <a class="news-letter " href="#">
						 <label class="checkbox1"><input type="checkbox" name="checkbox" ><i> </i>Forget Password</label>
					   </a> -->


				</div>
				<div class="col-md-6 login-do">
					<label class="hvr-shutter-in-horizontal login-sub"> <input
						type="submit" value="Log in">
					</label>
					<p>Do not have an account?</p>
					<a href="Register.jsp" class="hvr-shutter-in-horizontal">Sign
						up</a>
				</div>

				<div class="clearfix"></div>
			</form>
			<c:if test="${not empty wrongUser}">
				<p style="color: red">${wrongUser}</p>
			</c:if>
		</div>
	</div>
	<!---->
		<%@ include file="./partials/footer.jsp" %>
	<!---->
	<!--scrolling js-->
	<script src="../resources/js/jquery.nicescroll.js"></script>
	<script src="../resources/js/scripts.js"></script>
	<!--//scrolling js-->
</body>
</html>