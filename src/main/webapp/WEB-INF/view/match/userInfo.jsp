<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<button onclick="" class="add-friend">친구 추가</button>
	<script>
	var socket = new WebSocket("ws://192.168.0.131:8080/alarm");
	const button = document.querySelector(".add-friend");
		function addFriend() {
			fetch('http:192.168.0.131:8080/user/sendFriend', {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json',
				},
				body: JSON.stringify({
					userId: `${principal.id}`,
					friendId: `${user.id}`
				}),
			}).then(response => if(response.ok) {
				alert("친구 요청을 보냈습니다.");
				button.disabled = true;
				// message 에 알람 내용을 담아서 알람 소켓에 전송
				var message = JSON.stringify({
					sendUserId: `${principal.id}`,
					receiveUserId: `${user.id}`,
					uploadFileName: `${principal.uploadFileName}`,
					nickname: `${principal.nickname}`,
					content: `${principal.nickname}` + "님이 친구 추가요청을 보냈습니다."
				});
				socket.send(message);
			} else {
				alert("친구 요청 실패");
			});
		}
	
	</script>
</body>
</html>