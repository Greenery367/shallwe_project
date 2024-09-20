<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
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
				<li class="report"><a>신고</a></li>
				<li class="close"><a>닫기</a></li>
			</ul>
		</div>
		<div id="opponent-info">
			<div id="opponent-pic">
				 <c:choose>
                        <c:when test="${opponent.uploadFileName == null}">
                            <img src="/static/images/defaultProfile.jpeg">
                        </c:when>
                        <c:otherwise>
                            <img src="/images/${opponent.uploadFileName}" alt="Profile Picture">
                        </c:otherwise>
                   </c:choose>
			</div>
			<div id="opponent-name">${opponent.nickname}</div>
		</div>
		<div id="chatWindow">
			<!-- 여기에 대화 내용이 추가됩니다 -->
		</div>
		<input type="text" class="message">

	</div>
	<script>
	console.log(`${principal.nickname}`);
	var socket = new WebSocket("ws://192.168.0.131:8080/friend");
	var input = document.querySelector('.message');
	const profileBox = document.querySelector('.profile-box');
	profileBox.style.display = 'none';
	const opponent = document.querySelector('#opponent-pic');
	opponent.addEventListener('click', function(event) {
		const x = event.pageX;
        const y = event.pageY;
        document.querySelector(".report").firstChild.
        style.display = 'block'; // 상대방은 신고기능이 뜨게 만듬
        profileBox.style.left = x +"px";
        profileBox.style.top = y +"px";
        document.querySelector(".profile-info").firstChild.
        setAttribute("onclick", "window.open('/chat/profileInfo?id=" + `${principal.userId}` + "')");
        document.querySelector(".profile-info").firstChild.
        setAttribute("onclick", "window.open('/chat/profileInfo?id=" + `${opponent.userId}` + "')");
        document.querySelector(".report").firstChild.
        setAttribute("onclick", "window.open(`/report?roomId=${roomId}&opponentId=${opponent.userId}&type=chat`,'신고 페이지','width=500,height=600');");
        document.querySelector(".close").addEventListener("click", function() {
	        profileBox.style.display = 'none'; // 닫기 버튼 클릭 시 profile-box를 숨깁니다.
	    });
        profileBox.style.display = 'block'; // profile-box를 클릭한 위치에 보여줍니다.
        event.stopPropagation(); // 클릭 이벤트 전파 방지
        document.querySelector(".close").addEventListener("click", function() {
	        profileBox.style.display = 'none'; // 닫기 버튼 클릭 시 profile-box를 숨깁니다.
	    });
	    document.addEventListener('click', function(event) {
	        if (profileBox.style.display === 'block' && !profileBox.contains(event.target) && event.target !== opponent) {
	            profileBox.style.display = 'none'; // profile-box 외부를 클릭하면 숨깁니다.
	        }
	    });
	});
	        
	input.addEventListener('keyup', (event) => {
		if(event.keyCode === 13 && input.value.trim() !== "") {
			const chatWindow = document.getElementById("chatWindow");
			const myMessage = document.createElement("div");
	        const profileImage = document.createElement("img");
	        const textContent = document.createElement("span");
	        const messageContent = document.createElement("div");
			myMessage.classList.add("chat-message");
			messageContent.classList.add("my-message");
			if(`${principal.uploadFileName}` === '') {
				profileImage.src = "/static/images/defaultProfile.jpeg";
			} else {
	        profileImage.src = "/images/" + `${principal.uploadFileName}`;
			}
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
		    		const x = event.pageX;
			        const y = event.pageY;
			        profileBox.style.left = x +"px";
			        profileBox.style.top = y +"px";
			        document.querySelector(".profile-info").firstChild.
			        setAttribute("onclick", "window.open('/chat/profileInfo?id=" + `${principal.id}` + "')");
			        document.querySelector(".report").firstChild.
			        style.display = 'none'; // 나에게는 신고기능이 안뜨도록 방어
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
        const myMessage = document.createElement("div");
        const profileImage = document.createElement("img");
        const textContent = document.createElement("span");
        
        if(message.name === `${principal.nickname}`) {
        	
        	myMessage.classList.add("chat-message");
			messageContent.classList.add("my-message");
			if(`${principal.uploadFileName}` === '') {
				profileImage.src = "/static/images/defaultProfile.jpeg";
			} else {
	        profileImage.src = "/images/" + `${principal.uploadFileName}`;
			}
	        profileImage.alt = "/images/fukuoka4.gif";
	        profileImage.classList.add("myProfile-image");
	        textContent.innerText = message.message;
			messageContent.appendChild(textContent);
			myMessage.appendChild(messageContent);
			myMessage.appendChild(profileImage);
			chatWindow.appendChild(myMessage);
			chatWindow.scrollTop = chatWindow.scrollHeight;
			
			profileImage.addEventListener('click', function(event) {
		    		const x = event.pageX;
			        const y = event.pageY;
			        profileBox.style.left = x +"px";
			        profileBox.style.top = y +"px";
			        document.querySelector(".profile-info").firstChild.
			        setAttribute("onclick", "window.open('/chat/profileInfo?id=" + `${principal.id}` + "')");
			        document.querySelector(".report").firstChild.
			        style.display = 'none'; // 나에게는 신고기능이 안뜨도록 방어
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
        	
        } else {
        	newMessage.classList.add("chat-message");
            messageContent.classList.add("message-content");
        
    	// 프로필 이미지를 생성
    	console.log(message.uploadFileName);
    	if(message.uploadFileName === null) {
    		profileImage.src = 	"/static/images/defaultProfile.jpeg";
    	} else {
        	profileImage.src = "/images/" + message.uploadFileName;
    	}
        profileImage.alt = "Profile Image";
        profileImage.classList.add("profile-image");

        // 프로필 이미지에 이벤트 추가
        const profileBox = document.querySelector('.profile-box');
        profileImage.addEventListener('click', function(event) {
        	const x = event.pageX;
	        const y = event.pageY;
	        document.querySelector(".report").firstChild.
	        style.display = 'block'; // 상대방은 신고 기능이 뜨게 만듬
	        profileBox.style.left = x +"px";
	        profileBox.style.top = y +"px";
	        console.log(message.name);
	        setAttribute("onclick", "window.open('/chat/profileInfo?id=" + `${principal.id}` + "')");
	        document.querySelector(".profile-info").firstChild.
	        setAttribute("onclick", "window.open('/chat/profileInfo?id=" + message.id + "')");
	        document.querySelector(".report").firstChild.
	        setAttribute("onclick", "window.open(`/report?roomId=${chat}&opponentId=${opponent.userId}&type=chat`,'신고 페이지','width=500,height=600');");
	        document.querySelector(".close").addEventListener("click", function() {
		        profileBox.style.display = 'none'; // 닫기 버튼 클릭 시 profile-box를 숨깁니다.
		    });
	        profileBox.style.display = 'block'; // profile-box를 클릭한 위치에 보여줍니다.
	        event.stopPropagation(); // 클릭 이벤트 전파 방지
		    document.addEventListener('click', function(event) {
		        if (profileBox.style.display === 'block' && !profileBox.contains(event.target) && event.target !== profileImage) {
		            profileBox.style.display = 'none'; // profile-box 외부를 클릭하면 숨깁니다.
		        }
		    });
	    });
        
        // 프로필 이미지를 newMessage div에 추가
        newMessage.appendChild(profileImage);
		
        // 텍스트 메시지를 생성
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
	}
</script>
</body>
</html>