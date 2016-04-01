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
<title>Register</title>
<%@ include file="./partials/headerLogIn&Register.jsp"%>
</head>
<body>
	<div class="login">
		<h1>Finance Tracker</h1>
		<div class="login-bottom">
			<h2>Register</h2>
			<c:if test="${not empty sessionScope.userId}">
				<p>${sessionScope.userId}</p>
			</c:if>
			<form mtheod="post" action="../RegisterServlet"></form>
			<div class="col-md-6">
				<div class="login-mail">
					<input type="text" placeholder="First name" name="firstName">
					<i class="fa fa-child"></i>
				</div>
				<div class="login-mail">
					<input type="text" placeholder="Last name" name="lastName">
					<i class="fa fa-child"></i>
				</div>
				<div class="login-mail">
					<input type="text" placeholder="Email" name="email"> <i
						class="fa fa-envelope"></i>
				</div>
				<div class="login-mail">
					<input type="password" placeholder="Password" name="password">
					<i class="fa fa-lock"></i>
				</div>
				<div class="login-mail">
					<input type="password" placeholder="Repeated password"
						name="password2"> <i class="fa fa-lock"></i>
				</div>
				<!-- <a class="news-letter" href="#">
						 <label class="checkbox1"><input type="checkbox" name="checkbox" ><i> </i>I agree with the terms</label>
					   </a> -->
				</form>
			</div>

			<div class="col-md-6 login-do">
				<label class="hvr-shutter-in-horizontal login-sub"> <input
					type="submit" value="Submit">
				</label>
				<p>Already register</p>
				<a href="LogIn.jsp" class="hvr-shutter-in-horizontal">Login</a>
			</div>
			<c:if test="${not empty emailError}">
				<p style="color: red">
					<c:out value="${emailError}"></c:out>
			</c:if>
			<c:if test="${not empty passwordMissmatch}">
				<p style="color: red">
					<c:out value="${passwordMissmatch}"></c:out>
			</c:if>
			<div class="clearfix"></div>
		</div>
	</div>

	<%@ include file="./partials/footer.jsp"%>


	<!--scrolling js-->
	<script src="../resources/js/jquery.nicescroll.js"></script>
	<script src="../resources/js/scripts.js"></script>
	<!--//scrolling js-->
</body>
</html>