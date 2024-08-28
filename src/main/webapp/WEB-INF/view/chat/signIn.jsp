<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
body {
	width: 100%;
	height: 100vh;
	display: flex;
	justify-content: center;
	align-items: center;
}

.title {
	height: 300px;
	display: flex;
	align-items: center;
	flex-direction: column;
}

input {
	margin-top: 8px;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="title">
	<form action = "signIn" method="POST">
	닉네임을 입력해주세요<input type="hidden" value="INFP" name="mbti">
	<br>
	<input type="text" name="nickname">
	<br>
	프로필 사진을 등록해주세요
	<br>
	<input type="file" name="file">
	<br>
	<input type="submit" value="채팅방 입장">
	</form>
	</div>
</body>
</html>