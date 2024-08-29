<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/login.css">
<meta charset="UTF-8">
<title>소셜로그인-회원가입</title>
</head>
<body>
    <div class="login-wrapper">
        <div class="upper">
    		<div>
        		<h2>소셜 회원가입</h2>
    	    </div>
	        <div>
        		<a href="mainPage"><img class="img-concert" src="/images/logo.png"></a>
        	</div>
        </div>
        <form method="post" action="kakaoSignUp" id="login-form">
            <p>이름</p>
            <input type="text" name="username" placeholder="이름을 입력하세요">
            <p>생년월일</p>
            <input type="number" name="birthDate"  maxlength="8" 
            placeholder="ex)19980307(-은 제외하고 8자리 숫자로 입력)">
            <p>이메일</p>
            <input class = "email-input" type="text"name="emailBody" >
            @
            <input class="email-input" type="text" name="emailDomain" id="emailDomain">
        	<select name="domain" id="domainList">
	          <option value="">직접 입력</option>
	          <option value="naver.com">naver</option>
	          <option value="hanmail.net">daum</option>
	          <option value="gmail.com">google</option>
       		</select>
       		<p>연락처</p>
            <input type="number" name="phoneNumber" maxlength="11" placeholder="-제외하고 숫자만 입력">
            <input type="submit" value="회원가입">
            
        	<input type="hidden" id="id" name="id" value="${dto.id}">
        	<input type="hidden" id="nickname" name="nickname" value="${dto.nickname}">
        </form>
    </div>   
</body>
<script type="text/javascript" src="/js/signUp.js"></script>
</html>