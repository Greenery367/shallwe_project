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
			<c:forEach var="order" items="${orders}" varStatus="status">
				<tr>
					<td>${order.name}</td>
					<td>${order.amount}</td>
					<td>${order.createdAt}</td>
					<td>
						<c:choose>
							<c:when test="${flagList[status.index] == true}">
								<span>환불 신청 완료</span>
							</c:when>
							<c:otherwise>
							    <form action="${pageContext.request.contextPath}/my-page/request-refund" method="post" onsubmit="return disableBtn(this, ${status.index});">
							        <input type="hidden" name="orderId" value="${order.id}" />
							        <input type="hidden" name="userId" value="${userId}" />
							        <button type="submit">환불 신청</button>
							    </form>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<script>
	// 폼이 제출될 때 해당 폼의 버튼을 비활성화하고 텍스트를 변경하는 함수
	function disableBtn(form, index) {
		// index에 따라 고유한 버튼을 선택하여 비활성화
		const button = document.getElementById(`refundBtn${index}`);
		button.disabled = true;
		button.innerText = '환불 신청 완료';
		return true; // 폼 제출 허용
	}
</script>

<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
