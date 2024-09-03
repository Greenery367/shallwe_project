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
    <div class="profile-box">
				<ul>
					<li class="profile-info"><a>프로필 보기</a></li>
					<li class="close"><a>닫기</a></li>
				</ul>
			</div>
    <div id="opponent-info">
    <div id="opponent-pic">
    <img src="" id="opponent-profile"> <!-- 상대방 프로필 사진 -->
    </div>
    <div id="opponent-name"></div>
    </div>
    <div id="chatWindow">
        <!-- 여기에 대화 내용이 추가됩니다 -->
    </div>
    <input type="text" class="message">
</div>
<script type="text/javascript">
	var socket = new WebSocket("ws://192.168.0.131:8080/chat");
	var input = document.querySelector('.message');
	const profileBox = document.querySelector('.profile-box');
	profileBox.style.display = 'none';
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
			
			profileImage.addEventListener('click', function(event) {
		    		const x = event.clientX + 3;
			        const y = event.offsetY + 175;
			        console.log(x);
			        console.log(y);
			        profileBox.style.left = x +"px";
			        profileBox.style.top = y +"px";
			        console.log(`${principal.nickname}`);
			        document.querySelector(".profile-info").firstChild.
			        setAttribute("onclick", "window.open('/chat/profileInfo?name=" + `${principal.nickname}` + "')");
			        profileBox.style.display = 'block'; // profile-box를 클릭한 위치에 보여줍니다.
			        event.stopPropagation(); // 클릭 이벤트 전파 방지
			        document.querySelector(".close").addEventListener("click", function() {
				        profileBox.style.display = 'none'; // 닫기 버튼 클릭 시 profile-box를 숨깁니다.
				    });
				    document.addEventListener('click', function(event) {
				        if (profileBox.style.display === 'block' && !profileBox.contains(event.target) && event.target !== profileImage) {
				            profileBox.style.display = 'none'; // profile-box 외부를 클릭하면 숨깁니다.
				        }
				    });
			   });
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

        // 프로필 이미지에 이벤트 추가
        const profileBox = document.querySelector('.profile-box');
        profileImage.addEventListener('click', function(event) {
    		const x = event.clientX + 3;
	        const y = event.offsetY + 115;
	        console.log(x);
	        console.log(y);
	        profileBox.style.left = x +"px";
	        profileBox.style.top = y +"px";
	        console.log(message.name);
	        document.querySelector(".profile-info").firstChild.
	        setAttribute("onclick", "window.open('/chat/profileInfo?name=" + message.name + "')");
	        profileBox.style.display = 'block'; // profile-box를 클릭한 위치에 보여줍니다.
	        event.stopPropagation(); // 클릭 이벤트 전파 방지
	        document.querySelector(".close").addEventListener("click", function() {
		        profileBox.style.display = 'none'; // 닫기 버튼 클릭 시 profile-box를 숨깁니다.
		    });
		    document.addEventListener('click', function(event) {
		        if (profileBox.style.display === 'block' && !profileBox.contains(event.target) && event.target !== profileImage) {
		            profileBox.style.display = 'none'; // profile-box 외부를 클릭하면 숨깁니다.
		        }
		    });
	    });
        
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