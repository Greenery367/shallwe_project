<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
        <h3>nickname</h3>
        <p>MBTI: mbti</p>
        <div class="mbti-description">
            mbtidetail
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
        <p id="opponent-mbti">MBTI: INTP</p>
        <div class="mbti-description" id="mbti-detail">
            DD
        </div>
    </div>
</div>
 <script>
        function startMatching() {
            const startTime = new Date().getTime();
            var socket = new WebSocket("ws://192.168.0.131:8080/match");
            document.getElementById('matchingTime').textContent = '매칭 중...';

            let interval = setInterval(function() {
                const now = new Date().getTime();
                const elapsed = Math.floor((now - startTime) / 1000);
                document.getElementById('matchingTime').textContent = '매칭 시간: ' + elapsed + '초';
            }, 1000);
            
            socket.onmessage = function(event) {
            	console.log(event.data);
            	clearInterval(interval);
            }
        }
        
    </script>
</body>
</html>