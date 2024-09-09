<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<div style="display: flex;">
	<!-- 내 정보 관리 메뉴 -->
	<div style="width: 20%; padding: 10px; border-right: 1px solid #ccc;">
		<h3>내 정보 관리</h3>
		<ul style="list-style-type: none; padding-left: 0;">
			<li><a href="/my-page/${userId}">개인정보</a></li>
			<li><a href="#">비밀번호 변경</a></li>
			<li><a href="#">강의관리</a></li>
			<li><a href="#">회원탈퇴</a></li>
		</ul>

		<h3>캐시 이력</h3>
		<ul style="list-style-type: none; padding-left: 0;">
			<li><a href="#">충전 이력</a></li>
			<li><a href="#">환전 이력</a></li>
			<li><a href="#">사용 이력</a></li>
		</ul>
	</div>

	<!-- 개인정보 수정 영역 -->
	<div style="width: 80%; padding: 10px;">
		<h3>개인정보</h3>

		<!-- 프로필 사진 -->
		<div style="display: flex; align-items: center; margin-bottom: 20px;">
			<div>
				<c:choose>
					<c:when test="${empty user.uploadFileName}">
						<img src="/static/images/default.png" alt="프로필 사진" style="width: 150px; height: 150px; border-radius: 50%; object-fit: cover;" />
					</c:when>
					<c:otherwise>
						<img src="/images/${user.uploadFileName}" alt="프로필 사진" style="width: 150px; height: 150px; border-radius: 50%; object-fit: cover;" />
					</c:otherwise>
				</c:choose>
			</div>
			<div style="margin-left: 20px;">
				<!-- 프로필 사진 변경 폼 -->
				<form action="/my-page/update-profile" method="post" enctype="multipart/form-data">
					<input type="file" name="file" accept="image/*" />
					<button type="submit" style="margin-left: 10px;">사진 변경</button>
				</form>
			</div>
		</div>

		<!-- 개인정보 항목들 -->
		
			<div style="margin-bottom: 10px;">
				<label>ID:</label> <strong>${user.id}</strong>
			</div>
			<!-- 이름 수정 폼 -->
		<form action="/my-page/update-username" method="post" onsubmit="return confirmUpdate()">
			<div style="margin-bottom: 10px;">
				<label>이름:</label> 
				<input type="text" name="username" placeholder="${user.username}" />
				<button type="submit" style="margin-left: 10px;">개명</button>
			</div>
		</form>
			<div style="margin-bottom: 10px;">
				<label>닉네임:</label> <input type="text" name="nickname" placeholder="${user.nickname}" />
				<button type="submit" name="action" value="updateNickname" style="margin-left: 10px;">변경</button>
			</div>
			<div style="margin-bottom: 10px;">
				<label>핸드폰:</label> <input type="text" name="phoneNumber" placeholder="${user.phoneNumber}" />
				<button type="submit" name="action" value="updatePhoneNumber" style="margin-left: 10px;">변경</button>
			</div>
			<div style="margin-bottom: 10px;">
				<label>이메일:</label> <input type="email" name="email" placeholder="${user.email}" />
				<button type="submit" name="action" value="updateEmail" style="margin-left: 10px;">변경</button>
			</div>
			<c:choose>
				<c:when test="${empty user.userAccount}">
					<div style="margin-bottom: 10px;">
						<label>계좌:</label> <input type="text" name="userAccount" placeholder="계좌 정보가 없습니다." />
						<button type="submit" name="action" value="createAccount" style="margin-left: 10px;">생성</button>
					</div>
				</c:when>
				<c:otherwise>
					<div style="margin-bottom: 10px;">
						<label>계좌:</label> <input type="text" name="userAccount" placeholder="${user.userAccount}" />
						<button type="submit" name="action" value="updateAccount" style="margin-left: 10px;">변경</button>
					</div>
				</c:otherwise>
			</c:choose>
			<div style="margin-bottom: 10px;">
				<label>MBTI:</label> <input type="text" name="mbti" placeholder="받아올겨~" />
				<button type="submit" name="action" value="updateMBTI" style="margin-left: 10px;">재검사</button>
			</div>
		

		<!-- 캐시 및 포인트 관리 -->
		<h3>캐시 및 포인트</h3>
		<div style="margin-bottom: 10px;">
			<strong>현재 캐시:</strong> ${user.currentCash}점
			<button style="margin-left: 10px;">충전</button>
			<button style="margin-left: 10px;">환전</button>
			<button style="margin-left: 10px;">환불</button>
		</div>
		<div style="margin-bottom: 10px;">
			<strong>내기 포인트:</strong> ${user.challengePoint}점
		</div>
	</div>
</div>

<script>
        function confirmUpdate() {
            return confirm("정말 수정 하시겠습니까?");
        }
</script>

<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
