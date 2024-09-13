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
	<%@ include file="/WEB-INF/view/layout/myPageMenu.jsp"%>


	<h2 style="text-align: center;">사용 이력</h2>

	<table>
		<thead>
			<tr>	
				<th>강의 제목</th>
				<th>강의자 닉네임</th>
				<th>강의 가격</th>
				<th>지불 금액</th>
				<th>날짜</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="history" items="${spends}">
				<tr>
					<td>${history.classTitle}</td>
					<td>${history.teacherNickname}</td>
					<td><fmt:formatNumber value="${history.classPrice}" /></td>
					<td><fmt:formatNumber value="${history.spend}" /></td>
					<td><fmt:formatDate value="${history.createdAt}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	</div>











	<%@ include file="/WEB-INF/view/layout/footer.jsp"%>