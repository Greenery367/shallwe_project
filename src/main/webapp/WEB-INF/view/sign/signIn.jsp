<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/login.css">
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
    <div class="login-wrapper">
    	<div class="upper">
    		<div>
        		<h2>Login</h2>
    	    </div>
	        <div>
        		<a href="mainPage"><img class="img-concert" src="/images/logo.png"></a>
        	</div>
        </div>
        <form method="post" action="sign/signIn" id="login-form">
            <input type="text" name="username" placeholder="Id">
            <input type="password" name="password" placeholder="Password">
            <label for="remember-check">
                <input type="checkbox" id="remember-check">아이디 저장하기
                 
            </label>
            <input type="submit" value="Login">
        </form>
        <div class="su-btn">
        	<a href="findId">아이디 찾기</a> | <a href="findPw">비밀번호 찾기</a> | <a href="signUp">회원가입</a>
        </div>
        
        
        <fieldset>
        	<legend>소셜 로그인</legend>
        	<ul>
        		<li><a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=61e5a7465f3248de0332e984c98de103&redirect_uri=http://localhost:8080/test/kakaoLogin"><img src="/images/kakao_login.png"></a></li>
        		<li><a href="naverLogin"><img src="/images/naver_login.png"></a></li>
        		<li><a href="googleLogin"><img src="/images/google_login.png"></a></li>
        	</ul>
        </fieldset>
    </div>
</body>
</html>