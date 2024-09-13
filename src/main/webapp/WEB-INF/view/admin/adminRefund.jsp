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
					<th>플랫폼</th>
					<th>처리 상태</th>
					<th>자세히 보기</th>
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
					<td><button value="${refund}" onclick="refundDetail(this)">자세히 보기</button></td>
				</tr>
			</c:forEach>
			</table>
	</c:if>
	
	</div>
	<script>
		function decideRefund(button){
			console.log(button.value);
			var refundData = button.value;
			 fetch(`http://localhost:8080/admin/refund/send-request`, {
		            method: "POST",
		            headers: {
		                "Content-Type": "application/json",
		                // "charset"은 필요하지 않음. 브라우저가 자동으로 처리
		            },
		            body: JSON.stringify({refundInfo : button.value}) // 요청 본문에 데이터를 포함
		        })
		        .then(response => {
		            if (!response.ok) {
		                return response.json().then(err => {
		                    console.error('Error response from server:', err);
		                    throw new Error(err.message || 'Network response was not ok');
		                });
		            }
		            return response.json(); // Parse the response JSON
		        })
		        .then(data => {
		            console.log('Refund request successful:', data); // Handle the success response
		            alert('Refund request sent successfully!');
		        })
		        .catch(error => {
		            console.error('Error processing request:', error); // Log the error to the console
		            alert('An error occurred while sending the refund request.');
		        });
		}
	</script>

<%@ include file="/WEB-INF/view/layout/adminFooter.jsp"%>