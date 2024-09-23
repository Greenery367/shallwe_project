<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게임 친구 매칭 사이트: 셸위?</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css" />
<link rel="stylesheet" href="/static/css/header.css" />
<link rel="stylesheet" href="/static/css/FriendHeader.css" />
<script src="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.js"></script>
</head>
<body>
	<div class="alarm-box">
		<div class="alarm-header">
			<input type="checkbox" id="select-all" style="display: none;">
			<span class="alarm-all active" onclick="selectAlarmType(this)">전체</span>
			<span class="alarm-friend" onclick="selectAlarmType(this)">친구</span>
			<span class="alarm-post" onclick="selectAlarmType(this)">게시글</span> <span
				class="alarm-chat" onclick="selectAlarmType(this)">채팅</span> <span
				class="alarm-lecture" onclick="selectAlarmType(this)">강의</span> <span
				class="alarm-delete" onclick="toggleDeleteMode()">삭제하기</span>
		</div>
		<!-- 삭제 모드가 활성화되면 보여줄 요소 -->
		<div class="delete-header" style="display: none;">
			<input type="checkbox" id="selectAll" class="round-checkbox"
				onclick="toggleSelectAll()"> <label for="selectAll">전체선택</label>
			<button class="delete-alarms-btn" onclick="deleteSelectedAlarms()">삭제하기</button>
			<button class="cancel-btn" onclick="cancelDeleteMode()">취소</button>
		</div>
		<div class="alarm-content"></div>
	</div>
	<div class="main">
		<header>
			<div class="header">
				<div class="account-menus">
					<img class="mini-logo" alt="로고"
						src="/static/images/shallwe-icon.png"> <img
						src="/static/images/alarm.png" class="alarm-bell"> <a
						href="/user/main"><b>홈</b></a>
					<p>|</p>
					<c:choose>
						<c:when test="${user == null }">
							<a href="${pageContext.request.contextPath}//user/sign-in"><b>로그인</b></a>
							<p>|</p>
							<a href="${pageContext.request.contextPath}//user/sign-up"><b>회원가입</b></a>
							<p>|</p>
						</c:when>
						<c:otherwise>
							<a href="${pageContext.request.contextPath}//user/sign-in"><b>로그아웃</b></a>
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
										<div onclick="match(${principal.mbti})"
											class="game--category--menu">${category.gameName}</div>
									</li>
								</c:forEach>
								<li>
									<div
										onclick="location.href=http://${pageContext.request.contextPath}/chat/match"
										class="game--category--menu">기타게임</div>
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
									<div
										onclick="location.href='${pageContext.request.contextPath}/lecture/category/${category.id}'"
										class="game--category--menu">${category.gameName}</div>
								</li>
							</c:forEach>
							<li>
								<div
									onclick="location.href='${pageContext.request.contextPath}/admin/dashboard'"
									class="game--category--menu">기타게임</div>
							</li>
						</div>
					</ul>
				</div>
				<div class="menu-container">
					<a href="" class="menu">커뮤니티</a>
					<ul class="drop-down-menus-game">
						<div class="game-menu-box">
							<li>
								<div
									onclick="location.href='${pageContext.request.contextPath}/admin/dashboard'"
									class="game--category--menu">자유게시판</div>
							</li>
							<c:forEach var="category" items="${categoryList}">
								<li>
									<div
										onclick="location.href='${pageContext.request.contextPath}/community/category/${category.id}'"
										class="game--category--menu">${category.gameName}</div>
								</li>
							</c:forEach>
							<li>
								<div
									onclick="location.href='${pageContext.request.contextPath}/admin/dashboard'"
									class="game--category--menu">기타게임</div>
							</li>
						</div>
					</ul>
				</div>
				<div class="menu-container">
					<a href="" class="menu">친구</a>
					<div class="drop-down-menus">
						<ul>
							<li><a href='${pageContext.request.contextPath}/friends/all'><h4>나의
										친구</h4></a></li>
							<li><a
								href='${pageContext.request.contextPath}/friends/findUser'><h4>친구
										추가</h4></a></li>
							<li><a
								href='${pageContext.request.contextPath}/friends/wait'>대기중인
									초대
									</h4>
							</a></li>
						</ul>
					</div>
				</div>
				<p>|</p>
				<div class="menu-container">
					<a href="/notice/news-list" class="menu">뉴스</a>
					<ul class="drop-down-menus">
						<li onclick="location.href='/notice/notice-list'"><h4>공지사항</h4></li>
						<li onclick="location.href='/notice/news-list'"><h4>게임
								뉴스</h4></li>
					</ul>
				</div>

				<div class="menu-container">
					<a href="/cs/main" class="menu">고객 지원</a>
					<ul class="drop-down-menus">
						<li><h4>Q&A</h4></li>
					</ul>
				</div>
				<div class="menu-container">
					<a href='${pageContext.request.contextPath}/test/start-test' class="menu">콘텐츠</a>
					<div class="drop-down-menus">
						<ul>
							<li><a
								href='${pageContext.request.contextPath}/test/start-test'><h4>게임
										성향 테스트</h4></a></li>
						</ul>
					</div>
				</div>
				<div class="menu-container">
					<a href="${pageContext.request.contextPath}/my-page/" class="menu">회원
						정보</a>
				</div>
			</div>
		</div>
	</div>
	<script>
	var socket = new WebSocket("ws://localhost:8080/alarm");
    const alarmBox = document.querySelector(".alarm-box");
    const alarmBell = document.querySelector(".alarm-bell");
    const alarmContent = document.querySelector(".alarm-content");
	let alarmList;
    const alarmHeader = document.querySelector(".alarm-header"); // 기존 알람 헤더
    let originalHeaderHTML = alarmHeader.innerHTML; // 원래의 헤더 내용을 저장

    let deleteMode = false;
    let selectedAlarms = [];

    alarmBox.style.display = 'none';

    // 알림 정보를 서버에서 가져와서 알림 창에 표시
    fetch("http://localhost:8080/user/alarm")
        .then((response) => response.text())
        .then((text) => {
            if (text !== null) {
                alarmList = JSON.parse(text);
                console.log(alarmList);
                if (alarmList.length > 0) {
                    alarmList.forEach(function (alarms) {
                        const alarm = document.createElement("div");
                        const opponentPic = document.createElement("img");
                        const opponentName = document.createElement("span");
                        const content = document.createElement("span");
                        const checkbox = document.createElement("input");
                        const hiddenId = document.createElement("input");

                        alarm.classList.add("alarm-element");
                        checkbox.type = "checkbox";
                        checkbox.style.display = "none"; // 처음엔 숨김
                        hiddenId.type = "hidden";
                        hiddenId.value = alarms.id; // 알람 ID 저장

                        opponentPic.classList.add("alarm-pic");
                        content.classList.add("alarm-message");

                        if (alarms.uploadFileName === null) {
                            opponentPic.src = "/static/images/defaultProfile.jpeg";
                        } else {
                            opponentPic.src = "/images/" + alarms.uploadFileName;
                        }

                        opponentName.innerText = alarms.nickname;
                        content.innerText = alarms.content;

                        alarm.appendChild(checkbox); // 체크박스 추가
                        alarm.appendChild(hiddenId); // 숨겨진 ID 추가
                        alarm.appendChild(opponentPic);
                        alarm.appendChild(content);

                     	// 체크박스 클릭 시 이벤트 전파 중지
                        checkbox.addEventListener('click', function(event) {
                            event.stopPropagation(); // 이벤트 전파 중지하여 체크박스 클릭 시 div의 이벤트가 발생하지 않도록 함
                        
                            if (checkbox.checked) {
                                selectedAlarms.push(alarms.id); // 체크된 경우 선택된 알람 ID 추가
                            } else {
                                selectedAlarms = selectedAlarms.filter(id => id !== alarms.id); // 체크 해제 시 ID 제거
                            }
                            
                        });
                     
                        
                        // 클릭 시 체크박스 선택
                        alarm.addEventListener('click', function () {
                            if (deleteMode) {
                                checkbox.checked = !checkbox.checked;
                                if (checkbox.checked) {
                                    selectedAlarms.push(alarms.id); // 선택된 알람 ID 추가
                                } else {
                                    selectedAlarms = selectedAlarms.filter(id => id !== alarms.id); // 선택 해제 시 ID 제거
                                }
                            } else {
                                // 원래의 클릭 이벤트 동작
                                if (alarms.userId !== undefined) { 
                                    window.open(`${pageContext.request.contextPath}/user/move?type=` + alarms.type + '&userId=' + alarms.userId);
                                } else {
                                    location.href = `${pageContext.request.contextPath}/user/move?type=` + alarms.type + '&userId=0';
                                }
                            }
                        });

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

 // 알림 벨 클릭 시 알림 창이 벨 왼쪽 하단에 나타나도록 수정
    alarmBell.addEventListener('click', function(event) {
        if (alarmBox.style.display === 'none') {
            const bellPosition = alarmBell.getBoundingClientRect();
            alarmBox.style.left = (bellPosition.left - 310) + "px"; // 왼쪽으로 310px 이동
            alarmBox.style.top = (bellPosition.bottom + 10) + "px"; // 벨 아래로 약간의 간격
            alarmBox.style.display = 'block';
            alarmBell.src = "/static/images/alarm.png";

            // 서버에 읽은 상태 업데이트
            fetch("http://localhost:8080/user/status", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(alarmList) // alarmList 자체를 JSON으로 변환
            })
            .then((response) => response.text())
            .then((text) => {
                console.log(text);
            });
        } else {
            alarmBox.style.display = 'none';
        }
    });

    // 삭제 모드로 전환
    function toggleDeleteMode() {
        deleteMode = !deleteMode;
        const checkboxes = document.querySelectorAll(".alarm-element input[type='checkbox']");

        if (deleteMode) {
            // 삭제 모드 활성화 시 delete-header 표시
            alarmHeader.innerHTML = `
                <span>
                    <input type="checkbox" id="select-all" onchange="toggleSelectAll(this)">
                    <label for="select-all">전체 선택</label>
                </span>
                <button onclick="deleteSelectedAlarms()">삭제하기</button>
                <button onclick="toggleDeleteMode()">취소</button>
            `;
            checkboxes.forEach(checkbox => checkbox.style.display = "block"); // 체크박스 표시
        } else {
            // 삭제 모드 비활성화 시 원래의 헤더로 복구
            alarmHeader.innerHTML = originalHeaderHTML;
            checkboxes.forEach(checkbox => checkbox.style.display = "none"); // 체크박스 숨김
        }
    }

    // 전체 선택 기능
    function toggleSelectAll(selectAllCheckbox) {
        const checkboxes = document.querySelectorAll(".alarm-element input[type='checkbox']");
        selectedAlarms = []; // 초기화
        if (selectAllCheckbox.checked) {
            checkboxes.forEach(checkbox => {
                checkbox.checked = true;
                const id = checkbox.nextElementSibling.value; // hidden input에 저장된 ID 가져오기
                selectedAlarms.push(parseInt(id));
            });
        } else {
            checkboxes.forEach(checkbox => checkbox.checked = false);
            selectedAlarms = [];
        }
    }

    // 선택된 알람 삭제
    function deleteSelectedAlarms() {
    if (selectedAlarms.length === 0) {
        alert("삭제할 알림을 선택하세요.");
        return;
    }

    fetch("http://localhost:8080/user/deleteAlarm", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(selectedAlarms) // 선택된 알람 ID를 서버로 전송
    })
    .then((response) => response.text())
    .then((text) => {
        console.log(text);
        if (text === 'ok') {
            // 삭제 성공 후 화면에서 선택된 알람 요소 제거
            selectedAlarms.forEach(id => {
                const alarmElement = document.querySelector(`input[value='${id}']`).closest('.alarm-element');
                if (alarmElement) {
                    alarmElement.remove(); // 해당 알람 DOM 요소 제거
                }
            });
            // 선택된 알람 리스트 초기화
            selectedAlarms = [];
            alert('선택된 알람이 삭제되었습니다.');
        } else {
            alert('알람 삭제에 실패했습니다.');
        }
    })
    .catch((error) => {
        console.error('Error:', error);
    });
}

    function match(id) {
        if (${principal.mbti == 0}) {
            alert("먼저 mbti 검사를 하셔야합니다!");
        } else {
            location.href = "http://localhost:8080/chat/match?type=" + id;
        }
    }

    function selectAlarmType(selected) {
        // 기존의 active 클래스 제거
        document.querySelectorAll('.alarm-header span').forEach(function (item) {
            item.classList.remove('active');
        });

        // 선택된 항목에 active 클래스 추가
        selected.classList.add('active');
    }

</script>
</body>
</html>