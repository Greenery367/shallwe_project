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
        <form method="post" action="sign-up" id="login-form">
            <p>아이디</p>
            <input type="text" id ="id" name="id" placeholder="ID를 입력하세요." class="id-input">
            <label class="id-check">ID 중복 여부를 확인해 주세요</label>
            <button class="id-btn" type="button">중복 체크</button>
            <p>비밀번호</p>
            <input type="password" name="password" placeholder="영문/숫자가 모두 포함된 6글자 이상 15글자 이내로 입력하시오">
            <p>비밀번호 확인</p>
            <input type="password" name="confirmPassword" placeholder="비밀번호 재확인">
            <p>이름</p>
            <input type="text" name="username" placeholder="이름을 입력하세요">
            <p>닉네임</p>
            <input type="text" id="nickname" name="nickname" maxlength="30" placeholder="닉네임을 입력하세요(20자 제한)" class="nickname-input">
            <label class="nickname-check">닉네임 중복 여부를 확인해 주세요</label>
            <button class="nickname-btn" type="button" onclick="checkNickname();">중복 체크</button>
            <p>생년월일</p>
            <input type="number" name="birthDate" maxlength="8" placeholder="ex)19980307 (-은 제외하고 8자리 숫자로 입력)">
            <p>이메일</p>
            <input class = "email-input" id="emailBody"type="text"name="emailBody" >
            @
            <input class="email-input" type="text" name="emailDomain" id="emailDomain">
        	<select name="domain" id="domainList">
	          <option value="">직접 입력</option>
	          <option value="naver.com">naver</option>
	          <option value="hanmail.net">daum</option>
	          <option value="gmail.com">google</option>
       		</select>
       		<label class="email-check">이메일 인증을 진행해 주세요 </label>
            <button class="email-btn" id="email-btn"type="button" onclick="sendEmailVerification();">인증번호 발송</button>
            <input type="number" id="authNum"name="authNum" maxlength="6" placeholder="이메일로 발송된 인증번호를 입력하세요">
            <button type="button" onclick="verifyEmailAuthCode();" id="email-auth-button" class="email-auth-button">인증하기</button>
       		<p>연락처</p>
            <input type="number" name="phoneNumber" maxlength="11" placeholder="-제외하고 숫자만 입력">
            <input type="submit" value="회원가입">
        </form>
    </div>   
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript" src="/js/signUp.js"></script>
<script>
    document.getElementById('domainList').addEventListener('change', function() {
        var selectedDomain = this.value;
        document.getElementById('emailDomain').value = selectedDomain;
    });
</script>
</html>