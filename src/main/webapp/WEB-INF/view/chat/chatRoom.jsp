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
    <p>그린 톡방</p>
    <img src="/images/fukuoka2.gif" style="height: 300px"> <!-- 상대방 프로필 사진 -->
    <div id="chatWindow">
        <!-- 여기에 대화 내용이 추가됩니다 -->
    </div>
    <input type="text" class="message">
</div>
<script type="text/javascript">
	var socket = new WebSocket("ws://192.168.0.131:8080/chat");
	var input = document.querySelector('.message');
	// var imoticon = document.querySelector('.imoticon');
	
	input.addEventListener('keyup', (event) => {
		if(event.keyCode === 13 && input.value.trim() !== "") {
			const chatWindow = document.getElementById("chatWindow");
			const myMessage = document.createElement("div");
	        const profileImage = document.createElement("img");
	        const textContent = document.createElement("span");
	        const messageContent = document.createElement("div");
			myMessage.classList.add("chat-message");
			messageContent.classList.add("my-message");
	        profileImage.src = "/images/uploads/" + `${principal.uploadFileName}`;
	        profileImage.alt = "/images/fukuoka4.gif";
	        profileImage.classList.add("myProfile-image");
	        textContent.innerText = input.value;
			messageContent.appendChild(textContent);
			myMessage.appendChild(messageContent);
			myMessage.appendChild(profileImage);
			chatWindow.appendChild(myMessage);
			socket.send(input.value);
			input.value = "";
			
			chatWindow.scrollTop = chatWindow.scrollHeight;
		}
	});
	
	socket.onmessage = function(event) {
		const chatWindow = document.getElementById("chatWindow");
        const newMessage = document.createElement("div");
        const messageContent = document.createElement("div");
        const message = JSON.parse(event.data);

        newMessage.classList.add("chat-message");
        messageContent.classList.add("message-content");
	
    	// 프로필 이미지를 생성
        const profileImage = document.createElement("img");
        profileImage.src = "/images/uploads/" + message.uploadFileName;
        profileImage.alt = "Profile Image";
        profileImage.classList.add("profile-image");

        // 프로필 이미지를 newMessage div에 추가
        newMessage.appendChild(profileImage);

        // 텍스트 메시지를 생성
        const textContent = document.createElement("span");
        textContent.innerText = message.name + " : " + message.message;

        // 텍스트 메시지를 messageContent div에 추가
        messageContent.appendChild(textContent);

        // 바깥쪽 newMessage div에 안쪽 messageContent div를 추가
        newMessage.appendChild(messageContent);

        // chatWindow에 새 메시지를 추가
        chatWindow.appendChild(newMessage);

        // 새 메시지가 추가된 후 스크롤을 아래로 이동
        chatWindow.scrollTop = chatWindow.scrollHeight;
	}
	
</script>    
</body>
</html>