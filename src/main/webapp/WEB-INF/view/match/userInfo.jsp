<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<img src="/static/images/${user.uploadFileName}">
	<h1>${user.nickname}</h1>
	<p>${user.mbti}</p>
	<button class="add-friend">친구 추가</button>
	<script>
	var socket = new WebSocket("ws://192.168.0.131:8080/alarm");
	const button = document.querySelector(".add-friend");
		function addFriend() {
			fetch('http://192.168.0.131:8080/friends/sendFriend', {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json',
				},
				body: JSON.stringify({
					userId: `${principal.userId}`,
					friendId: `${user.userId}`
				}),
			}).then(response => response.text())
			.then((text) => {
				alert(text);
			});
		}
			button.addEventListener("click", addFriend);
	
	</script>
</body>
</html>