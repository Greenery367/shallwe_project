<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채팅방 목록</title>
<link rel="stylesheet" type="text/css" href="/css/styles.css">
</head>
<body>
    <div class="container">
        <h1>대화 목록</h1>
        
        <div class="chat-list">
            <ul id="chatRoomList">
                <!-- 생성된 채팅방 목록이 여기에 추가됩니다 -->
            </ul>
        </div>
        
        <div class="create-room">
            <input type="text" id="roomNameInput" placeholder="채팅방 이름 입력">
            <button id="createRoomButton">채팅방 생성</button>
        </div>
    </div>
    
    <script type="text/javascript">
        // 채팅방 목록
        const chatRooms = [];

        // 채팅방 생성 버튼 클릭 이벤트
        document.getElementById('createRoomButton').addEventListener('click', function() {
            const roomName = document.getElementById('roomNameInput').value.trim();
            if (roomName) {
                // 방 목록에 추가
                chatRooms.push(roomName);
                updateChatRoomList();
                document.getElementById('roomNameInput').value = ''; // 입력 필드 초기화
            }
        });

        // 채팅방 목록 업데이트 함수
        function updateChatRoomList() {
            const list = document.getElementById('chatRoomList');
            list.innerHTML = ''; // 기존 목록 초기화
            
            chatRooms.forEach(function(roomName, index) {
                const li = document.createElement('li');
                li.textContent = roomName;
                li.addEventListener('dblclick', function() {
                    // 채팅방 참여 (예: 채팅방 페이지로 이동)
                    window.location.href = `list?roomname=1`;
                });
                list.appendChild(li);
            });
        }
    </script>
</body>
</html>