
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/layout/header.jsp"%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/lecture.css">
<div class="container">
	<h1>${categoryName} 강의목록</h1>
	<div class="lecture-grid">
		<c:forEach var="lecture" items="${lectureList}">
			<div class="lecture-card">
					<c:choose>
						<c:when test="${empty lecture.uploadFileName}">
							<img src="/static/images/default.png" alt="강사 사진"
								style="width: 150px; height: 150px; border-radius: 50%; object-fit: cover; margin-left: 70px; "  />
						</c:when>
						<c:otherwise>
							<img src="/images/${lecture.uploadFileName}" alt="강사 사진"
								style="width: 150px; height: 150px; border-radius: 50%; object-fit: cover; margin-left: 70px; " />
						</c:otherwise>
					</c:choose>
				<div class="lecture-info">
					<h2>${lecture.title}</h2>
					<p>${lecture.subtitle}</p>
					<p>${lecture.currentNum}/${lecture.limitNum} 수강생</p>
					<p>${lecture.nickName}</p>
					<p class="price">${lecture.price}원</p>

					<a href="${pageContext.request.contextPath}/lecture/lectureDetail/${lecture.id}">
						<button class="btn">상세보기</button>
					</a>
				</div>
			</div>
		</c:forEach>
	</div>
</div>

<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
