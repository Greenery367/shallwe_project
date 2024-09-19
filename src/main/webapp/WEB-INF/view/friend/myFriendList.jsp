<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/layout/header.jsp"%>
<%@ include file="/WEB-INF/view/layout/friendHeader.jsp"%>
<link rel="stylesheet" href="/static/css/myFriendList.css">

<div class="main" style="display: flex;">
    <div class="content">
        <div class="friends_count">
            <span>나의 친구 ${count}</span>
            <button id="manage_friends" class="manage_fiends_btn">
                <span>친구 목록 관리</span>
            </button>
            <button id="add_friends" class="add_friends_btn">
                <span>친구 추가</span>
            </button>
        </div>
        <div class="search_friend">
            <input type="text" placeholder="이름으로 친구 검색">
        </div>
        <!-- 클릭시 프로필 보기 창 -->
        <div class="profile-box">
            <ul>
                <li class="profile-info"><a>프로필 보기</a></li>
                <li class="chat"><a>1:1 채팅</a></li>
                <li class="close"><a>닫기</a></li>
            </ul>
        </div>
        <div id="state_online" class="state_block">온라인</div>
        <c:forEach var="online" items="${onlineList}">
            <div class="user_box">
                <input type="hidden" value="${online.userId}"> 
                <a class="select_overlay" href="/chat/profileInfo?id=${online.userId}"> 
                    <c:choose>
                        <c:when test="${online.uploadFileName == null}">
                            <img src="/static/images/defaultProfile.jpeg">
                        </c:when>
                        <c:otherwise>
                            <img src="/images/${online.uploadFileName}" alt="Profile Picture">
                        </c:otherwise>
                    </c:choose>
                </a> 
                <a class="select_overlay" href="/chat/profileInfo?id=${online.userId}"> 
                    <span>${online.nickname}</span>
                </a>
            </div>
        </c:forEach>
        <div id="state_offline" class="state_block">오프라인</div>
        <c:forEach var="offline" items="${offlineList}">
            <div class="user_box">
                <input type="hidden" value="${offline.userId}">
                <c:choose>
                    <c:when test="${offline.uploadFileName == null}">
                        <img src="/static/images/defaultProfile.jpeg">
                    </c:when>
                    <c:otherwise>
                        <img src="/images/${offline.uploadFileName}" alt="Profile Picture">
                    </c:otherwise>
                </c:choose>
                <span>${offline.nickname}</span>
            </div>
        </c:forEach>
    </div>
</div>

<script>
const friendsDiv = document.querySelectorAll('.user_box');
const profileBox = document.querySelector('.profile-box');
profileBox.style.display = 'none';
friendsDiv.forEach((friendDiv) => {
    friendDiv.addEventListener('click', function(event) {
        const id = friendDiv.querySelector('input[type="hidden"]').value;
        const x = event.clientX + 3;
        const y = event.clientY + 115;
        console.log(profileBox);
        profileBox.style.left = x + "px";
        profileBox.style.top = y + "px";

        document.querySelector(".profile-info").firstElementChild
            .setAttribute("onclick", "window.open('/chat/profileInfo?id=" + id + "')");
        document.querySelector(".chat").firstElementChild
            .setAttribute("onclick", "window.open('/chat/friendChat?id=" + id + "')");

        profileBox.style.display = 'block'; // profile-box를 클릭한 위치에 보여줍니다.
        event.stopPropagation(); // 클릭 이벤트 전파 방지

        document.querySelector(".close").addEventListener("click", function() {
            profileBox.style.display = 'none'; // 닫기 버튼 클릭 시 profile-box를 숨깁니다.
        });

        document.addEventListener('click', function(event) {
            if (profileBox.style.display === 'block' && !profileBox.contains(event.target) && !friendDiv.contains(event.target)) {
                profileBox.style.display = 'none'; // profile-box 외부를 클릭하면 숨깁니다.
            }
        }, { once: true }); // 이벤트가 한 번만 실행되도록 설정합니다.
    });
});
</script>
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
