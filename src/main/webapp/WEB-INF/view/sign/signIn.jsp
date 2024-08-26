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
        <h2>Login</h2>
        <form method="post" action="sign/signIn" id="login-form">
            <input type="text" name="username" placeholder="Id">
            <input type="password" name="password" placeholder="Password">
            <label for="remember-check">
                <input type="checkbox" id="remember-check">아이디 저장하기
                 
            </label>
            <input type="submit" value="Login">
        </form>
        <div class="su-btn">
        	<a href="signUp">회원가입</a>
        </div>
    </div>
</body>
</html>