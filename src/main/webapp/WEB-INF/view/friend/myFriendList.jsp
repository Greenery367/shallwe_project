<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/layout/header.jsp"%>
<link rel="stylesheet" href="/static/css/myFriendList.css">

<!-- 메인 컨테이너: 서브헤더와 메인 콘텐츠를 감싸는 div -->
<div class="main-container">
    <!-- 서브헤더 -->
    <div class="sub-header">
        <jsp:include page="/WEB-INF/view/layout/friendHeader.jsp"/>
    </div>

    <!-- 메인 콘텐츠 -->
    <div class="main-content">
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
        <!-- 클릭 시 프로필 보기 창 -->
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
            const x = event.pageX + 1;
            const y = event.pageY;
            profileBox.style.left = x + "px";
            profileBox.style.top = y + "px";

            document.querySelector(".profile-info").firstElementChild
                .setAttribute("onclick", "window.open('/chat/profileInfo?id=" + id + "')");
            document.querySelector(".chat").firstElementChild
                .setAttribute("onclick", "window.open('/chat/friendChat?id=" + id + "')");

            profileBox.style.display = 'block';
            event.stopPropagation();

            document.querySelector(".close").addEventListener("click", function() {
                profileBox.style.display = 'none';
            });

            document.addEventListener('click', function(event) {
                if (profileBox.style.display === 'block' && !profileBox.contains(event.target) && !friendDiv.contains(event.target)) {
                    profileBox.style.display = 'none';
                }
            }, { once: true });
        });
    });
</script>

<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
