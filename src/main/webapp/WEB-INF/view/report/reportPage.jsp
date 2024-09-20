<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="/css/report.css">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="report_form">
	<h1>신고 하기</h1>
	<form action="/report" method="post">
	<input type="hidden" value="${type}" name = "type">
	<input type="hidden" value="${typeId}" name="typeId">
	<div class="sender">신고한 사람 : ${principal.nickname}</div>
	<input type="hidden" value="${principal.userId}" name="senderId">
	<div class="reciever">신고하는 대상 : ${opponent.nickname}</div>
	<input type="hidden" value="${opponent.userId}" name="recieverId">
	<div class="content">신고하실 내용
	<textarea name="reason">
	</textarea>
	<input type="submit" value="신고">
	<button class="close">닫기</button>
	</div>
	</form>
	</div>
</body>
</html>