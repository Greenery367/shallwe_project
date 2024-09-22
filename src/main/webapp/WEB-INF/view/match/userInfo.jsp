<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로필 페이지</title>
<link rel="stylesheet" href="/css/profileInfo.css">
</head>
<body>
	<div class="container">
		<c:choose>
			<c:when test="${user.uploadFileName == null}">
				<img src="/static/images/defaultProfile.jpeg" id="opponent-img">
			</c:when>
			<c:otherwise>
				<img src="/images/${user.uploadFileName}" alt="Profile Picture"
					id="opponent-img">
			</c:otherwise>
		</c:choose>
		<h1>${user.nickname}</h1>
		<p>MBTI : ${mbti.name}</p>
		<p>직업 : ${mbti.nickname}</p>
		<p>특징 : ${mbti.content}</p>
		<button class="add-friend">친구 추가</button>
	</div>

	<script>
        var socket = new WebSocket("ws://localhost:8080/alarm");
        const button = document.querySelector(".add-friend");

        function addFriend() {
            fetch('http://localhost:8080/friends/sendFriend', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    userId: `${principal.userId}`,
                    friendId: `${user.userId}`
                }),
            }).then(response => response.text())
            .then((text) => {
                alert(text);
            });
        }

        button.addEventListener("click", addFriend);
    </script>
</body>
</html>