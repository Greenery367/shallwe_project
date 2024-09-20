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


	<h2 style="text-align: center;">충전 이력</h2>

	<table>
		<thead>
			<tr>
				<th>상품 이름</th>
				<th>충전 금액</th>
				<th>날짜</th>
				<th>캐시 환불</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="order" items="${orders}">
				<tr>
					<td>${order.name}</td>
					<td>${order.amount}</td>
					<p>${flagList.get(1)}</p>
					<td>${order.createdAt}</td>
					<td><c:choose>
							<c:when test="${flagList.get(i) == truetrue}">
								<span>환불 신청 어료</span>
							</c:when>
							<c:otherwise>
								<form action="${pageContext.request.contextPath}/refund/request" method="post">
									<input type="hidden" name="orderId" value="${order.orderId}" /> <input type="hidden" name="userId" value="${order.userId}" />
									<button type="submit">환불 신청</button>
								</form>
							</c:otherwise>
						</c:choose></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</div>











<%@ include file="/WEB-INF/view/layout/footer.jsp"%>