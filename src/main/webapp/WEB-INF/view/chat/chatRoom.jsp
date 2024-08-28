<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1 대 1 채팅</title>
</head>
<link rel="stylesheet" href="/css/chatRoom.css">
<body>
<div class="chatroom">
    <p>상대방 이름</p>
    <img src="/images/winter2.jpg"> <!-- 상대방 프로필 사진 -->
    <div id="chatWindow">
        <!-- 여기에 대화 내용이 추가됩니다 -->
    </div>
    <input type="text" class="message">
    <button>전송</button>
</div>
<script type="text/javascript">
	var socket = new WebSocket("ws://192.168.0.131:8080/chat");
	var input = document.querySelector('.message');
	
	input.addEventListener('keyup', (event) => {
		if(event.keyCode === 13 && input.value.trim() !== "") {
			const chatWindow = document.getElementById("chatWindow");
			const myMessage = document.createElement("div");
			myMessage.classList.add("my-message");
			myMessage.innerText = input.value;
			chatWindow.appendChild(myMessage);
			socket.send(`${principal.nickname}` + " : " + input.value);
			input.value = "";
			
			chatWindow.scrollTop = chatWindow.scrollHeight;
		}
	});
	
	socket.onmessage = function(event) {
		const chatWindow = document.getElementById("chatWindow");
        const newMessage = document.createElement("div");
        newMessage.classList.add("chat-message");
        newMessage.innerText = event.data;
        chatWindow.appendChild(newMessage);

        // 새 메시지가 추가된 후 스크롤을 아래로 이동
        chatWindow.scrollTop = chatWindow.scrollHeight;
	}
	
</script>    
</body>
</html>