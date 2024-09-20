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
					<td>${order.createdAt}</td>
					<td>
						<c:choose>
							<c:when test="${flagList[fn:indexOf(orders, order)]}">
								<span>환불 신청 완료</span>
							</c:when>
							<c:otherwise>
							    <form action="${pageContext.request.contextPath}/my-page/request-refund" method="post">
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

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
    $(document).ready(function() {
        $('form').on('submit', function(event) {
            event.preventDefault(); // 기본 폼 제출 막기
            const form = $(this);
            $.post(form.attr('action'), form.serialize(), function(response) {
                // 환불 요청 성공 시 버튼 비활성화
                form.find('button').prop('disabled', true).text('환불 신청 완료');
            }).fail(function() {
                alert('환불 신청에 실패했습니다.');
            });
        });
    });
</script>

<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
