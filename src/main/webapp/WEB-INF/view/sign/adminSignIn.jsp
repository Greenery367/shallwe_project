<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="java.security.SecureRandom"%>
<%@ page import="java.math.BigInteger"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/adminLogin.css">
<meta charset="UTF-8">
<title>관리자 로그인</title>
</head>
<body>
	<div class="login-wrapper">
		<div class="upper">
			<div>
				<h2>Admin Login</h2>
			</div>
			<div>
				<a href="${pageContext.request.contextPath}/admin"><img class="img-concert"
					src="../static/images/logo_blue.png"></a>
			</div>
		</div>
		<form method="post" action="sign-in" id="login-form">
			<input type="text" name="id" placeholder="ID"> <input
				type="password" name="password" placeholder="Password"> <label
				for="remember-check"> <input type="checkbox"
				id="remember-check">아이디 저장하기
			</label> <input type="submit" value="Login">
		</form>

	</div>
</body>
</html>