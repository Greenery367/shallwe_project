<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1 대 1 채팅</title>
</head>
<body>
<div class="chatroom">
    <p>상대방 이름</p>
    <img src="/images/winter2.jpg"> <!-- 상대방 프로필 사진 -->
    <br>
    <br>
    <br>
    <input type="text" class="message">
    <button>전송</button>
</div>
<script type="text/javascript">
	var socket = new WebSocket("ws://localhost:8080/chatSocket");
	
	socket.onmessage = function(event) {
		console.log("message recived : " + event.data);
	}
	
	document.querySelector('button').addEventListener('click', () => {
		
		var message = document.querySelector('.message');
		socket.send(message.value);
		message.value = "";
	})
	
</script>    
</body>
</html>