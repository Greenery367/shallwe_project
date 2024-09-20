<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게임 친구 매칭 사이트: 셸위?</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css"/>
<link rel="stylesheet" href="/static/css/header.css"/>
<link rel="stylesheet" href="/static/css/FriendHeader.css"/>
<script src="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.js"></script>
</head>
<body>
<div class="alarm-box">
    <div class="alarm-header">
        <span class="alarm-all active" onclick="selectAlarmType(this)">전체</span>
        <span class="alarm-friend" onclick="selectAlarmType(this)">친구</span>
        <span class="alarm-post" onclick="selectAlarmType(this)">게시글</span>
        <span class="alarm-chat" onclick="selectAlarmType(this)">채팅</span>
        <span class="alarm-lecture" onclick="selectAlarmType(this)">강의</span>
    </div>
    <div class="alarm-content"></div>
</div>
<div class="main">
	<header>
		<div class="header">
			<div class="account-menus">
				<img class="mini-logo" alt="로고" src="/static/images/shallwe-icon.png">
				<img src="/static/images/alarm.png" class="alarm-bell">
				<a href="/user/main"><b>홈</b></a>
				<p>|</p>
				<c:choose>
						<c:when test="${user == null }">
							<a href="/user/sign-in"><b>로그인</b></a>
							<p>|</p>
							<a href="/user/sign-up"><b>회원가입</b></a>
							<p>|</p>
						</c:when>
						<c:otherwise>
							<a href="/user/sign-in"><b>로그아웃</b></a>
							<p>|</p>
						</c:otherwise>
					</c:choose>
					
					<div class="charge-cash">
						<a href="/cash/charge"><b>캐시충전</b></a> <img
							src="/static/images/cash.png">
					</div>
			</div>
		</div>
	</header>
	<hr>
	<div class="banner">
		<img class="logo" alt="로고" src="/static/images/logo.png">
	</div>
	<div class="nav-bar">
		<div class="menus">
			<div class="menu-container">
				<a href="#" class="menu">친구 찾기</a>
				<div class="drop-down-menus-game">
					<ul>
						<div class="game-menu-box">
							<c:forEach var="category" items="${categoryList}">
								<li>
									<div onclick="match(${principal.mbti})" class="game--category--menu">${category.gameName}</div>
								</li>
							</c:forEach>
							<li>
								<div onclick="location.href=http://192.168.0.131:8080/chat/match" class="game--category--menu">기타게임</div>
							</li>
						</div>
					</ul>
				</div>
			</div>
			<div class="menu-container">
				<a href="" class="menu">게임 강의</a>
				<ul class="drop-down-menus-game">
					<div class="game-menu-box">
						<c:forEach var="category" items="${categoryList}">
							<li>
								<div onclick="location.href='${pageContext.request.contextPath}/lecture/category/${category.id}'" class="game--category--menu">${category.gameName}</div>
							</li>
						</c:forEach>
						<li>
							<div onclick="location.href='${pageContext.request.contextPath}/admin/dashboard'" class="game--category--menu">기타게임</div>
						</li>
					</div>
				</ul>
			</div>
			<div class="menu-container">
				<a href="" class="menu">커뮤니티</a>	
				<ul class="drop-down-menus-game">
					<div class="game-menu-box">
						<li>
							<div onclick="location.href='${pageContext.request.contextPath}/admin/dashboard'" class="game--category--menu">자유게시판</div>
						</li>
						<c:forEach var="category" items="${categoryList}">
							<li>
								<div onclick="location.href='${pageContext.request.contextPath}/community/category/${category.id}'" class="game--category--menu">${category.gameName}</div>
							</li>
						</c:forEach>
						<li>
							<div onclick="location.href='${pageContext.request.contextPath}/admin/dashboard'" class="game--category--menu">기타게임</div>
						</li>
					</div>
				</ul>
			</div>
			<div class="menu-container">
				<a href="" class="menu">친구</a>
				<div class="drop-down-menus">
					<ul>
						<li><a href='${pageContext.request.contextPath}/friends/all'><h4>나의 친구</h4></a></li>
						<li><a href='${pageContext.request.contextPath}/friends/findUser'><h4>친구 추가</h4></a></li>
						<li><a href='${pageContext.request.contextPath}/friends/wait'>대기중인 초대</h4></a></li>
					</ul>
				</div>
			</div>
			<p>|</p>
			<div class="menu-container">
				<a href="/notice/news-list" class="menu">뉴스</a>
				<ul class="drop-down-menus">
					<li onclick="location.href='/notice/notice-list'"><h4>공지사항</h4></li>
					<li onclick="location.href='/notice/news-list'"><h4>게임 뉴스</h4></li>
				</ul>
			</div>

			<div class="menu-container">
				<a href="/cs/main" class="menu">고객 지원</a>
				<ul class="drop-down-menus">
					<li><h4>Q&A</h4></li>
				</ul>
			</div>
			<div class="menu-container">
				<a href="" class="menu">콘텐츠</a>
				<div class="drop-down-menus">
					<ul>
						<li><a href='${pageContext.request.contextPath}/test/start-test'><h4>게임 성향 테스트</h4></a></li>
					</ul>
				</div>
			</div>
			<div class="menu-container">
				<a href="${pageContext.request.contextPath}/my-page/" class="menu">회원 정보</a>
			</div>	
		</div>
	</div>
</div>
<script>
	const alarmBox = document.querySelector(".alarm-box");
	const alarmBell = document.querySelector(".alarm-bell");
	const alarmContent = document.querySelector(".alarm-content");
	const alarmAll = document.querySelector(".alarm-all");
	const alarmFriend = document.querySelector(".alarm-friend");
	const alarmPost = document.querySelector(".alarm-post");
	const alarmChat = document.querySelector(".alarm-chat");
	const alarmLecture = document.querySelector(".alarm-lecture");
	
	alarmBox.style.display = 'none';
	
	// 알림 정보를 서버에서 가져와서 알림 창에 표시
    fetch("http://192.168.0.131:8080/user/alarm")
    .then((response) => response.text())
    .then((text) => {
        if (text !== null) {
            const alarmList  = JSON.parse(text);
            console.log(alarmList);
            if(alarmList.length > 0) {
            	alarmList.forEach(function(alarms) {
        			const alarm = document.createElement("div");
                    const opponentPic = document.createElement("img");
                    const opponentName = document.createElement("span");
                    const content = document.createElement("span");

                    alarm.classList.add("alarm-element");
                    opponentPic.classList.add("alarm-pic");
                    opponentName.classList.add("alarm-name");
                    content.classList.add("alarm-message");

                    opponentPic.src = "/images/" + alarms.uploadFileName;
                    opponentName.innerText = alarms.nickname;
                    content.innerText = alarms.content;

                    alarm.appendChild(opponentPic);
                    alarm.appendChild(opponentName);
                    alarm.appendChild(content);
                    if(alarms.typeId !== undefined) {
                    
                    	alarm.addEventListener('click', function (event) {
                    		location.href=`${pageContext.request.contextPath}/user/move?type=` +  alarms.type
                    				+ '&userId=' + alarms.userId
                    });
                    } else {
                    	alarm.addEventListener('click', function (event) {
                    		location.href=`${pageContext.request.contextPath}/user/move?type=` +  alarms.type
                    				+ '&userId=0'
                    });
                    }
                    alarmContent.appendChild(alarm);
                    // 새로운 알림이 있을 경우 알림 아이콘 변경
                    if (alarms.status === 0) {
                        alarmBell.src = "/static/images/alarm2.png";
                    }
            	});
            } else {
                    const noAlarmMessage = document.createElement("div");
                    noAlarmMessage.classList.add("no-alarm-message");
                    noAlarmMessage.innerText = "알림이 없습니다.";
                    alarmContent.appendChild(noAlarmMessage);
                }
        } 
    });

    // 알림 벨 클릭 시 알림 창이 벨 아래에 나타남
    alarmBell.addEventListener('click', function(event) {
        if (alarmBox.style.display === 'none') {
            const bellPosition = alarmBell.getBoundingClientRect();
            alarmBox.style.left = bellPosition.left + "px";
            alarmBox.style.top = bellPosition.bottom + 10 + "px"; // 벨 아래로 약간의 간격
            alarmBox.style.display = 'block';
            alarmBell.src = "/static/images/alarm.png";
        } else {
            alarmBox.style.display = 'none';
        }
    });
	
	function match(id) {
		if (${principal.mbti == 0}) {
			alert("먼저 mbti 검사를 하셔야합니다!");
		} else {
			location.href = "http://192.168.0.131:8080/chat/match?type=" + id;
		}
	}
</script>
	
	function selectAlarmType(selected) {
	    // 기존의 active 클래스 제거
	    document.querySelectorAll('.alarm-header span').forEach(function(item) {
	        item.classList.remove('active');
	    });

	    // 선택된 항목에 active 클래스 추가
	    selected.classList.add('active');
	}

</script>
</body>
</html>
