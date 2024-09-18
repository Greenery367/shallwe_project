<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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


	<h2 style="text-align: center;">환불 이력</h2>

	<table>
		<thead>
			<tr>
				<th>날짜</th>
				<th>환불 금액</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="history" items="${refunds}">
				<tr>
					<td><fmt:formatDate value="${history.createdAt}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><fmt:formatNumber value="${history.refund}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	</div>











	<%@ include file="/WEB-INF/view/layout/footer.jsp"%>