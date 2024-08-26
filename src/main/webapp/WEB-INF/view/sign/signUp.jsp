<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/login.css">
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
    <div class="login-wrapper">
        <div class="upper">
    		<div>
        		<h2>회원 가입</h2>
    	    </div>
	        <div>
        		<a href="mainPage"><img class="img-concert" src="/images/logo.png"></a>
        	</div>
        </div>
        <form method="post" action="test/signUp" id="login-form">
            <p>아이디</p>
            <input type="text" id ="id" name="id" placeholder="ID를 입력하세요.">
            <label class="id-check">ID 중복 여부를 확인해 주세요</label>
            <button class="id-btn" onclick="checkid();">중복 체크</button>
            <p>비밀번호</p>
            <input type="password" name="password" placeholder="사용하실 비밀번호를 입력하세요">
            <p>비밀번호 확인</p>
            <input type="password" name="confirmPassword" placeholder="비밀번호 재확인">
            <p>이름</p>
            <input type="text" name="username" placeholder="이름을 입력하세요">
            <p>닉네임</p>
            <input type="text" name="nickname" placeholder="닉네임을 입력하세요">
            <p>생년월일</p>
            <input type="text" name="birthDate" placeholder="ex)19980307 (-은 제외하고 8자리 숫자로 입력)">
            <p>이메일</p>
            <input class = "email-input" type="text"name="emailBody" placeholder="">
            @
            <input class="email-input" type="text" name="emailDomain" id="emailDomain">
        	<select name="domain" id="domainList">
	          <option value="">직접 입력</option>
	          <option value="naver.com">naver</option>
	          <option value="hanmail.net">daum</option>
	          <option value="gmail.com">google</option>
       		</select>
       		<p>연락처</p>
            <input type="text" name="tel" placeholder="-제외하고 숫자만 입력">
            <input type="submit" value="회원가입">
        </form>
    </div>   
</body>
<script type="text/javascript" src="/js/signUp.js"></script>
</html>