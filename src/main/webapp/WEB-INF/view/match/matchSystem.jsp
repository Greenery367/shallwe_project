<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/matchPage.css">
</head>
<body>
	<div class="page-wrapper">
		<div class="container">
			<!-- 왼쪽: 내 정보 -->
			<div class="left">
				<div class="profile-pic">
					<img src="/images/uploads/${principal.uploadFileName}" alt="내 프로필 사진">
				</div>
				<h3>${principal.nickname}</h3>
				<p>MBTI: ${mbti.name}</p>
				<p>직업: ${mbti.nickname}</p>
				<div class="mbti-description">${mbti.content}</div>
			</div>

			<!-- 중간: 매칭 시작 버튼 -->
			<div class="middle">
				<button onclick="startMatching()">매칭 시작</button>
				<div id="matchingTime"></div>
			</div>

			<!-- 오른쪽: 나와 잘 맞는 상대방 정보 -->
			<div class="right">
				<div class="profile-pic" id="opponent-profile"></div>
				<h3 id="opponent">상대방</h3>
				<p id="opponent-mbti">MBTI: ${compatibilityList[0].name}</p>
				<p id="opponent-job">직업: ${compatibilityList[0].nickname}</p>
				<p id="opponent-compatibility">잘 맞는 정도: ${compatibilityList[0].compatibility}%</p>
				<div class="mbti-description" id="mbti-detail">${compatibilityList[0].content}</div>
				<p id="opponent-id" style="display:none;">${compatibilityList[0].wellMatchedMbtiId}</p>
			</div>

		</div>
		<!-- 색상별 MBTI 리스트 -->
		<div class="compatibility-list">
			<c:forEach var="compatibility" items="${compatibilityList}">
				<button class="compatibility-button" id="compatibility-${compatibility.wellMatchedMbtiId}">${compatibility.name}</button>
			</c:forEach>
		</div>
	</div>
	<script>
	 var socket = new WebSocket("ws://192.168.0.131:8080/match");
	 document.addEventListener("DOMContentLoaded", function() {
	 const compatibilityList = JSON.parse(`${compatibilityJson}`);
     const profile = document.getElementById("opponent-profile"); 
     const name = document.getElementById("opponent");
     const mbti = document.getElementById("opponent-mbti");
     const job = document.getElementById("opponent-job");
     const compatibility = document.getElementById("opponent-compatibility");
     const detail = document.getElementById("mbti-detail");
     const buttons = document.querySelectorAll(".compatibility-button");
     // 첫 번째 버튼을 기본으로 선택
     buttons[0].classList.add("current-button");

     buttons.forEach((button, index) => {
         button.addEventListener("click", () => {
             // 이전 선택 해제
             document.querySelector(".current-button").classList.remove("current-button");
             // 현재 선택
             button.classList.add("current-button");
             // 선택된 MBTI 정보 업데이트
             const selected = compatibilityList[index];
             mbti.textContent = "MBTI: " + selected.name;
             job.textContent = "직업: " + selected.nickname;
             compatibility.textContent = "잘 맞는 정도: " + selected.compatibility + "%";
             detail.textContent = selected.content;
			 id.textContent = selected.wellMatchedMbtiId;
         });
     });
 });
 	
        function startMatching() {
        	const id = document.getElementById("opponent-id");
            const startTime = new Date().getTime();
            const profile = document.getElementById("opponent-profile"); 
            const name = document.getElementById("opponent");
            document.getElementById('matchingTime').textContent = '매칭 중...';
            console.log(id.textContent);
			socket.send(id.textContent);
            let interval = setInterval(function() {
                const now = new Date().getTime();
                const elapsed = Math.floor((now - startTime) / 1000);
                document.getElementById('matchingTime').textContent = '매칭 시간: ' + elapsed + '초';
            }, 1000);
            
            socket.onmessage = function(event) {
            	const principal = JSON.parse(event.data);
            	const profileImage = document.createElement("img");
                profileImage.src = "/images/uploads/" + principal.uploadFileName;
                profileImage.alt = "Profile Image";
                profileImage.classList.add("profile-image");
                profile.appendChild(profileImage);
            	name.innerText = principal.nickname;
            	console.log(event.data);
            	clearInterval(interval);
            }
        }
        
    </script>
</body>
</html>