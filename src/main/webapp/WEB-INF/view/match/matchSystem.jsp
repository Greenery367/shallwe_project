<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/matchPage.css">
</head>
<body>
	<div class="container">
    <!-- 왼쪽: 내 정보 -->
    <div class="left">
        <div class="profile-pic"></div>
        <h3>내 이름</h3>
        <p>MBTI: INFP</p>
        <div class="mbti-description">
            INFP는 이상적이고, 창의적이며, 깊이 있는 생각을 하는 성격 유형입니다.
        </div>
    </div>

    <!-- 중간: 매칭 시작 버튼 -->
    <div class="middle">
        <button onclick="startMatching()">매칭 시작</button>
        <div id="matchingTime"></div>
    </div>

    <!-- 오른쪽: 나와 잘 맞는 상대방 정보 -->
    <div class="right">
        <div class="profile-pic"></div>
        <h3 id = "opponent">상대방</h3>
        <p id="opponent-mbti">MBTI: ENFJ</p>
        <div class="mbti-description" id="mbti-detail">
            ENFJ는 타인을 돕고, 리더십을 발휘하며, 대인 관계에서 능숙한 성격 유형입니다.
        </div>
    </div>
</div>
 <script>
        function startMatching() {
            const startTime = new Date().getTime();
            var socket = new WebSocket("ws://192.168.0.131:8080/match");
            document.getElementById('matchingTime').textContent = '매칭 중...';

            setInterval(function() {
                const now = new Date().getTime();
                const elapsed = Math.floor((now - startTime) / 1000);
                document.getElementById('matchingTime').textContent = '매칭 시간: ' + elapsed + '초';
            }, 1000);
            socket.send(`${principal}`);
            
            socket.onmessage = function(event) {
            	console.log(event.data);
            }
        }
        
        
    </script>
</body>
</html>