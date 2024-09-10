<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/layout/adminHeader.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="/css/adminRefund.css">
	<div class="main-board">
	<h1>환불 내역 관리</h1>
	<c:if test="${refundList != null}">
			<table class="main-table">
				<tr>
					<th>id</th>
					<th>주문번호</th>
					<th>유저명</th>
					<th>환불사유</th>
					<th>환불신청일자</th>
					<th>처리 상태</th>
				</tr>
			<c:forEach var="refund" items="${refundList}">
				<tr>
					<td>${refund.id}</td>
					<td>${refund.orderId}</td>
					<td>${refund.userId}</td>
					<td>${refund.reason}</td>
					<td>${refund.createdAt}</td>
					<td><c:if test="${refund.status == 0 }"><button class="refund-table" value="${refund}" onclick="decideRefund(this)">환불 미완료</button></c:if>
						<c:if test="${refund.status != 0 }"><div>환불 처리 완료</div></c:if>
					</td>
				</tr>
			</c:forEach>
			</table>
	</c:if>
	
	</div>
	<script>
		function decideRefund(button){
			var refundData = button.value;
			 fetch(`http://localhost:8080/admin/refund/send-request`, {
		            method: "POST",
		            headers: {
		                "Content-Type": "application/json",
		                // "charset"은 필요하지 않음. 브라우저가 자동으로 처리
		            },
		            body: JSON.stringify({ id : button.getAttribute('id'),
		            						orderId : button.getAttribute('orderId'),
		            						userId : button.getAttribute('userId'),
		            						reason : button.getAttribute('reason'),
		            						createdAt : button.getAttribute('createdAt'),
		            						status : button.getAttribute('status')
		            }) // 요청 본문에 데이터를 포함
		        })
		        .then(response => response.json()) // 응답을 JSON 형식으로 변환
		        .catch(error => {
		            // 오류를 콘솔에 출력
		            console.error('요청 처리 중 오류 발생:', error);
		        });
		}
	</script>

<%@ include file="/WEB-INF/view/layout/adminFooter.jsp"%>