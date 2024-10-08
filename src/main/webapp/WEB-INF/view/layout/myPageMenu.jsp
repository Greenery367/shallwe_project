<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 내 정보 관리 메뉴 -->
	<div style="width: 20%; padding: 10px; border-right: 1px solid #ccc;">
		<h3>내 정보 관리</h3>
		<ul style="list-style-type: none; padding-left: 0;">
			<li><a href="/my-page/">개인정보</a></li>
			<li><a href="#">비밀번호 변경</a></li>
			<li><a href="${pageContext.request.contextPath}/lecture/my-lecture?userId=${user.userId}">내 강의 관리</a></li>
			<li><a href="#">회원탈퇴</a></li>
		</ul>

		<h3>캐시 및 포인트 관리</h3>
		<ul style="list-style-type: none; padding-left: 0;">
			<li><a href="${pageContext.request.contextPath}/my-page/charge-list">캐시 충전 이력</a></li>
			<li><a href="${pageContext.request.contextPath}/my-page/spend-list">캐시 사용 이력</a></li>
			<li><a href="${pageContext.request.contextPath}/my-page/refund-list">캐시 환불 이력</a></li>
			<li><a href="${pageContext.request.contextPath}/my-page/exchange-list/">포인트 환전 관리</a></li>
		</ul>
	</div>