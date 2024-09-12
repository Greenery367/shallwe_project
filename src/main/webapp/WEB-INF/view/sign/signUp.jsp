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
        		<a href="main"><img class="img-concert" src="/static/images/logo.png"></a>
        	</div>
        </div>
        <form method="post" action="sign-up" id="login-form">
            <h4>아이디</h4>
            <input type="text" id ="id" name="id" placeholder="(6-12자 이내 영문,숫자만 사용가능)" class="id-input">
            <label class="id-check">ID 중복 여부를 확인해 주세요</label>
            <button class="id-btn" type="button">중복 체크</button>
            <h4>비밀번호</h4>
            <input type="password" minlength="6" maxlength="15" name="password" id="password" placeholder="(8-15자 이내 영문/숫자/특수문자 1개이상 필요)" id ="password">
            <label class="pw_show"><input type="checkbox" id="pw-show-checkbox-1">비밀번호 표시</label>
            <h4>비밀번호 확인</h4>
            <input type="password" name="confirmPassword" placeholder="비밀번호 재확인" id ="confirmPassword">
            <label class="pw_show"><input type="checkbox" id="pw-show-checkbox-2">비밀번호 표시</label>
            <br><label class="pw-check" id="pw-check" style="color:red">비밀번호가 다릅니다.</label>
            <h4 class="mar">이름</h4>
            <input type="text" name="username" placeholder="이름을 입력하세요" maxlength="20">
            <h4>닉네임</h4>
            <input type="text" id="nickname" name="nickname" maxlength="30" placeholder="닉네임을 입력하세요(30자 제한)" class="nickname-input">
            <label class="nickname-check">닉네임 중복 여부를 확인해 주세요</label>
            <button class="nickname-btn" type="button" onclick="checkNickname();">중복 체크</button>
            <h4>생년월일</h4>
            <input type="text" pattern="\d*" name="birthDate"  maxlength="8" placeholder="ex)19980307 (-은 제외하고 8자리 숫자로 입력)"
            oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
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
       		<label class="email-check">이메일 인증을 진행해 주세요 </label>
            <button class="email-btn" id="email-btn"type="button" >인증번호 발송</button>
            <input type="number" id="authNum"name="authNum" maxlength="6" placeholder="이메일로 발송된 인증번호를 입력하세요">
            <button type="button" id="email-auth-button" class="email-auth-button">인증하기</button>
       		<h4>연락처</h4>
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