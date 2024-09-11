<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/WEB-INF/view/layout/header.jsp"%>
<style>
table {
	width: 50%;
	margin: 20px auto;
	border-collapse: collapse;
}

table, th, td {
	border: 1px solid black;
}

th, td {
	padding: 8px;
	text-align: center;
}

th {
	background-color: #f2f2f2;
}
</style>
<div style="display: flex;">
	<!-- 내 정보 관리 메뉴 -->
	<div style="width: 20%; padding: 10px; border-right: 1px solid #ccc;">
		<h3>내 정보 관리</h3>
		<ul style="list-style-type: none; padding-left: 0;">
			<li><a href="${pageContext.request.contextPath}/my-page/${user.userId}">개인정보</a></li>
			<li><a href="#">비밀번호 변경</a></li>
			<li><a href="#">강의관리</a></li>
			<li><a href="#">회원탈퇴</a></li>
		</ul>

		<h3>캐시 이력</h3>
		<ul style="list-style-type: none; padding-left: 0;">
			<li><a href="${pageContext.request.contextPath}/my-page/charge-list?userId=${user.userId}">충전 이력</a></li>
			<li><a href="${pageContext.request.contextPath}/my-page/exchange-list?userId=${user.userId}">환전 이력</a></li>
			<li><a href="${pageContext.request.contextPath}/my-page/spend-list?userId=${user.userId}">사용 이력</a></li>
			<li><a href="${pageContext.request.contextPath}/my-page/refund-list?userId=${user.userId}">환불 이력</a></li>
		</ul>
	</div>


	<h2 style="text-align: center;">환전 이력</h2>

	<table>
		<thead>
			<tr>
				<th>날짜</th>
				<th>환전 금액</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="history" items="${exchanges}">
				<tr>
					<td><fmt:formatDate value="${history.createdAt}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><fmt:formatNumber value="${history.exchange}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	</div>











	<%@ include file="/WEB-INF/view/layout/footer.jsp"%>