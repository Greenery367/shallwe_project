<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/login.css">
<meta charset="UTF-8">
<title>아이디 찾기</title>
</head>
<body>
	<div class="login-wrapper">
    	<div class="upper">
    		<div>
        		<h2>아이디 찾기</h2>
    	    </div>
	        <div>
        		<a href="main"><img class="img-concert" src="/images/logo.png"></a>
        	</div>
        </div>
	
		<form method="post" action="/user/find-id" id="login-form">
			<h4>이름 또는 닉네임</h4>
	    	<input type="text" name="username" placeholder="이름 또는 닉네임을 입력하세요">
	    	<h4>이메일</h4>
            <input class ="email-input" id="emailBody"type="text"name="emailBody" placeholder="이메일 입력">
            @
            <input class="email-input" type="text" name="emailDomain" id="emailDomain" placeholder="도메인 입력">
        	<select name="domain" id="domainList">
	          <option value="">직접 입력</option>
	          <option value="naver.com">naver</option>
	          <option value="hanmail.net">daum</option>
	          <option value="gmail.com">google</option>
       		</select>
	    	<input type="submit" value="아이디 찾기">
	    </form>
	    <div class="su-btn">
	     	<a href="find-pw">비밀번호 찾기</a> | <a href="sign-up">회원가입</a>
	    </div>
	</div>
</body>
<script type="text/javascript" src="/js/findId.js"></script>
<script>
    document.getElementById('domainList').addEventListener('change', function() {
        var selectedDomain = this.value;
        document.getElementById('emailDomain').value = selectedDomain;
    });
</script>
</html>