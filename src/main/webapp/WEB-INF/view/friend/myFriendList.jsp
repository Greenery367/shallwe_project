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
                    <c:choose>
                        <c:when test="${online.uploadFileName == null}">
                            <img src="/static/images/defaultProfile.jpeg">
                        </c:when>
                        <c:otherwise>
                            <img src="/images/${online.uploadFileName}" alt="Profile Picture">
                        </c:otherwise>
                    </c:choose>
                    <span>${online.nickname}</span>
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
document.addEventListener('DOMContentLoaded', function() {
    const friendsDiv = document.querySelectorAll('.user_box');
    const profileBox = document.querySelector('.profile-box');
    const manageFriend = document.querySelector('#manage_friends');
    const addFriend = document.querySelector('#add_friends');
    let isManageMode = false; // 관리 모드 상태를 저장

    addFriend.addEventListener('click', function() {
        location.href = `${pageContext.request.contextPath}/friends/findUser`;
    });

    // 친구 관리 모드에서 삭제 버튼 추가/제거 및 profileBox 비활성화
    manageFriend.addEventListener('click', function() {
        isManageMode = !isManageMode;

        friendsDiv.forEach((friendDiv) => {
            // 관리 모드 활성화 시
            if (isManageMode) {
                console.log("관리 모드 활성화됨");
                // 삭제 버튼이 이미 없으면 추가
                if (!friendDiv.querySelector('.delete-friend-btn')) {
                    const deleteButton = document.createElement('button');
                    deleteButton.classList.add('delete-friend-btn');
                    deleteButton.innerText = '삭제';

                    // 친구 삭제 버튼을 클릭했을 때 이벤트 리스너
                    deleteButton.addEventListener('click', function(event) {
                        event.stopPropagation(); // 클릭 이벤트가 부모 요소로 전달되지 않도록 방지
                        console.log('삭제 버튼 클릭됨'); // 삭제 버튼이 클릭되었을 때 로그 확인

                        // 확인 창 띄우기
                        if (confirm("정말로 삭제하시겠습니까?")) {
                            const userId = friendDiv.querySelector('input[type="hidden"]').value;

                            // "예"를 선택한 경우 서버로 GET 요청
                            fetch("http://localhost:8080/user/deleteFriend?userId=" + userId)
                                .then(response => response.text())
                                .then((text) => {
                                    if (text === 'ok') {
                                        alert('친구 삭제가 완료되었습니다.');
                                        friendDiv.remove(); // 친구 목록에서 제거
                                    } else {
                                        alert('친구 삭제에 실패했습니다.');
                                    }
                                })
                                .catch((error) => {
                                    console.error('Error:', error);
                                    alert('오류가 발생했습니다. 다시 시도해 주세요.');
                                });
                        } else {
                            // "아니오" 선택 시 알림
                            alert('친구 삭제가 취소되었습니다.');
                        }
                    });

                    friendDiv.appendChild(deleteButton);
                }

                // profileBox 클릭 이벤트 비활성화
                friendDiv.classList.add('no-profile-click'); // 클릭 비활성화 시 추가 클래스
            } else {
                console.log("관리 모드 비활성화됨");
                // 관리 모드 비활성화 시 삭제 버튼 제거
                const deleteButton = friendDiv.querySelector('.delete-friend-btn');
                if (deleteButton) {
                    deleteButton.remove();
                }

                // profileBox 클릭 이벤트 활성화
                friendDiv.classList.remove('no-profile-click');
            }
        });
    });

    // 프로필 박스 기본적으로 숨기기
    profileBox.style.display = 'none';

    // 친구 클릭 시 프로필 박스 보이기
    friendsDiv.forEach((friendDiv) => {
        friendDiv.addEventListener('click', function(event) {
            // 관리 모드가 아닐 때만 프로필 박스 표시
            if (!isManageMode && !friendDiv.classList.contains('no-profile-click')) {
                const id = friendDiv.querySelector('input[type="hidden"]').value;
                const x = event.pageX + 1;
                const y = event.pageY;
                profileBox.style.left = x + "px";
                profileBox.style.top = y + "px";

                document.querySelector(".profile-info").firstElementChild
                    .setAttribute("onclick", "window.open('/chat/profileInfo?userId=" + id + "')");
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
            }
        });
    });
});
</script>

<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
