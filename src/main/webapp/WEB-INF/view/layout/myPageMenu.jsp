<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			<li><a href="${pageContext.request.contextPath}/my-page/charge-list?userId=${user.userId}">충전 이력</a></li>
			<li><a href="${pageContext.request.contextPath}/my-page/exchange-list/">환전 관리</a></li>
			<li><a href="${pageContext.request.contextPath}/my-page/spend-list?userId=${user.userId}">사용 이력</a></li>
			<li><a href="${pageContext.request.contextPath}/my-page/refund-list?userId=${user.userId}">환불 이력</a></li>
		</ul>
	</div>