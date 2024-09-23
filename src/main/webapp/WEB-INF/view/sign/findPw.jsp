<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/login.css">
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
</head>
<body>
	<div class="login-wrapper">
    	<div class="upper">
    		<div>
        		<h2>비밀번호 찾기</h2>
    	    </div>
	        <div>
        		<a href="main"><img class="img-concert"
					src="../static/images/logo.png"></a>
        	</div>
        </div>
	
		<form method="post" action="find-pw" id="login-form">
			<h4>아이디</h4>
	    	<input type="text" name="id" placeholder="비밀번호를 찾을 ID를 입력하세요">
	    	<h4>이메일</h4>
            <input class ="email-input" id="emailBody"type="text"name="emailBody" >
            @
            <input class="email-input" type="text" name="emailDomain" id="emailDomain">
        	<select name="domain" id="domainList">
	          <option value="">직접 입력</option>
	          <option value="naver.com">naver</option>
	          <option value="hanmail.net">daum</option>
	          <option value="gmail.com">google</option>
       		</select>
	    	<input type="submit" value="비밀번호 찾기">
	    </form>
	    <div class="su-btn">
	     	<a href="find-id">아이디 찾기</a> | <a href="sign-in">로그인</a> | <a href="sign-up">회원가입</a>
	    </div>
	</div>
</body>
<script type="text/javascript" src="/js/findPw.js"></script>
<script>
    document.getElementById('domainList').addEventListener('change', function() {
        var selectedDomain = this.value;
        document.getElementById('emailDomain').value = selectedDomain;
    });
</script>
</html>