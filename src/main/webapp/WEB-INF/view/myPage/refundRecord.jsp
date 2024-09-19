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
				<th>상품 명</th>
				<th>환불 금액</th>
				<th>날짜</th>
				<th>승인</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="refund" items="${refunds}">
				<tr>
					<td>${refund.name}</td>
					<td><fmt:formatNumber value="${refund.amount}" /></td>
					<td><fmt:formatDate value="${refund.createdAt}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>
						<c:choose>
					        <c:when test="${refund.status == 0}">
					            처리중
					        </c:when>
					        <c:when test="${refund.status == 1}">
					            환불 완료
					        </c:when>
					    </c:choose>
					</td> 
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	</div>











	<%@ include file="/WEB-INF/view/layout/footer.jsp"%>