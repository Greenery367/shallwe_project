<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/login.css">
<meta charset="UTF-8">
<title>쉘위 계정 설정</title>
</head>
<body>
	<div class="login-wrapper">
		<div class="upper">
			<div>
				<h2>쉘위 계정 설정</h2>
			</div>
			<div>
				<a href="main"><img class="img-concert" src="../static/images/logo.png"></a>
			</div>
		</div>
		<h5>
			안전한 사이트 관리를 위해 아래 정보를 입력해 주세요.
			</h5>
				<form method="post" action="social-sign-up" id="login-form">
					<p>닉네임</p>
            		<input type="text" id="nickname" name="nickname" maxlength="30" placeholder="닉네임을 입력하세요(30자 제한)" class="nickname-input">
            		<label class="nickname-check">닉네임 중복 여부를 확인해 주세요</label>
		            <button class="nickname-btn" type="button" onclick="checkNickname();">중복 체크</button>
					<p>생년월일</p>
					<input type="number" name="birthDate" maxlength="8"
						placeholder="ex)19980307(-은 제외하고 8자리 숫자로 입력)">
					<p>보조 이메일</p>
					<input class="email-input" type="text" name="emailBody"> @
					<input class="email-input" type="text" name="emailDomain"
						id="emailDomain"> <select name="domain" id="domainList">
						<option value="">직접 입력</option>
						<option value="naver.com">naver</option>
						<option value="hanmail.net">daum</option>
						<option value="gmail.com">google</option>
					</select>
					<p>연락처</p>
					<input type="number" name="phoneNumber" maxlength="11"
						placeholder="-제외하고 숫자만 입력"> <input type="submit"
						value="회원가입"> 
						<input type="hidden" id="id" name="id" value="${dto.id}"> 
						<input type="hidden" id="username"name="username" value="${dto.username}">
				</form>
	</div>
</body>
<script type="text/javascript" src="/js/socialSignUp.js"></script>
</html>