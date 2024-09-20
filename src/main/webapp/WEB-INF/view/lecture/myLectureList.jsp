<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/layout/header.jsp" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/lecture.css">

<div class="container">
    <h1>내 강의 목록</h1>
    <div class="lecture-grid">
        <c:forEach var="lecture" items="${lectureList}">
            <div class="lecture-card">
				<c:choose>
					<c:when test="${empty lecture.uploadFileName}">
						<img src="/static/images/default.png" alt="강사 사진"
							style="width: 150px; height: 150px; border-radius: 50%; object-fit: cover;" />
					</c:when>
					<c:otherwise>
						<img src="/images/${lecture.uploadFileName}" alt="강사 사진"
							style="width: 150px; height: 150px; border-radius: 50%; object-fit: cover;" />
					</c:otherwise>
				</c:choose>
                <div class="lecture-info">
                    <h2>${lecture.title}</h2>
                    <p>${lecture.subtitle}</p>
                    <p>${lecture.currentNum} / ${lecture.limitNum} 수강생</p>
                    <p>${lecture.nickName}</p>
                    <p class="price">${lecture.price}원</p>
                    
                    </a>
                    <a href="${pageContext.request.contextPath}lecture-update/${lecture.id}">
                    <button class="btn">강좌 정보 수정</button>
                    </a>
                    <a href="${pageContext.request.contextPath}lecture-review/${lecture.id}">
                    <button class="btn">강좌 상세 내역 조회</button>
                    </a>
                    
                    <!-- 삭제 버튼 (폼 전송 방식) -->
                    <form id="deleteForm_${lecture.id}" action="${pageContext.request.contextPath}lecture-delete/${lecture.id}" method="POST">
                        <button type="button" class="btn" onclick="removeCheck(${lecture.id})">강좌 삭제</button>
                    </form>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<a class="btn" href="/lecture/create-lecture">강의 생성</a>

<%@ include file="/WEB-INF/view/layout/footer.jsp" %>

<script>
function removeCheck(lectureId) {
    if (confirm("정말 삭제하시겠습니까?")) {  // 확인을 누르면
        document.getElementById("deleteForm_" + lectureId).submit();  // 해당 강좌 삭제 폼을 제출
    } else {
        return false;  // 취소를 누르면 아무런 동작도 하지 않음
    }
}
</script>