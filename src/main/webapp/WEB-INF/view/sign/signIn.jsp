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
<link rel="stylesheet" type="text/css" href="/css/login.css">
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
<<<<<<< HEAD
	<div class="login-wrapper">
		<div class="upper">
			<div>
				<h2>Login</h2>
			</div>
			<div>
				<a href="main"><img class="img-concert"
					src="../static/images/logo.png"></a>
			</div>
		</div>
		<form method="post" action="sign-in" id="login-form">
			<input type="text" name="id" placeholder="ID"> <input
				type="password" name="password" placeholder="Password"> <label
				for="remember-check"> <input type="checkbox"
				id="remember-check">아이디 저장하기
			</label> <input type="submit" value="Login">
		</form>
		<div class="su-btn">
			<a href="find-id">아이디 찾기</a> | <a href="find-pw">비밀번호 찾기</a> | <a
				href="sign-up">회원가입</a>
		</div>


		<fieldset>
			<legend>소셜 로그인</legend>
			<ul>
				<li><a
					href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=91ee64c4cb4fb2b4397fccb575dab52e&redirect_uri=http://localhost:8080/user/kakao"><img
						src="../static/images/kakao_login.png"></a></li>
				<%
				String redirectURI = URLEncoder.encode("http://localhost:8080/user/naver", "UTF-8");
				SecureRandom random = new SecureRandom();
				String state = new BigInteger(130, random).toString();
				String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=L2nqlUqxr9srIFoflYqq";
				apiURL += "&redirect_uri=" + redirectURI;
				apiURL += "&state=" + state;
				session.setAttribute("state", state);
				%>
				<li><a href="<%=apiURL%>"><img src="../static/images/naver_login.png"></a></li>
				<% 
				
				String gapiURL = "https://accounts/google.com/o/oauth2/v2/auth&client_id=310509781431-ianh0bki0s8pg729fnhtmqqujthsjcqt.apps.googleusercontent.com&redirect_url=http://localhost:8080/user/google&response_type=code&scope=profile";
				%>
				<li><a href="https://accounts.google.com/o/oauth2/v2/auth?client_id=310509781431-ianh0bki0s8pg729fnhtmqqujthsjcqt.apps.googleusercontent.com&redirect_uri=http://localhost:8080/user/google&response_type=code&scope=profile">
				<img src="../static/images/google_login.png"></a></li>

			</ul>
		</fieldset>
	</div>
=======
    <div class="login-wrapper">
    	<div class="upper">
    		<div>
        		<h2>Login</h2>
    	    </div>
	        <div>
        		<a href="main"><img class="img-concert" src="/static/images/logo.png"></a>
        	</div>
        </div>
        <form method="post" action="sign-in" id="login-form">
            <input type="text" name="id" placeholder="ID">
            <input type="password" name="password" placeholder="Password">
            <label for="remember-check">
                <input type="checkbox" id="remember-check">아이디 저장하기
            </label>
            <input type="submit" value="Login">
        </form>
        <div class="su-btn">
        	<a href="find-id">아이디 찾기</a> | <a href="find-pw">비밀번호 찾기</a> | <a href="sign-up">회원가입</a>
        </div>
        
        
        <fieldset>
        	<legend>소셜 로그인</legend>
        	<ul>
        		<li><a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=91ee64c4cb4fb2b4397fccb575dab52e&redirect_uri=http://localhost:8080/user/kakao"><img src="/static/images/kakao_login.png"></a></li>
        		<li><a href="naverLogin"><img src="/static/images/naver_login.png"></a></li>
        		<li><a href="googleLogin"><img src="/static/images/google_login.png"></a></li>
        	</ul>
        </fieldset>
    </div>
>>>>>>> ae88f71a5d98cf993b2fdaf0d6538975fc5d4348
</body>
</html>