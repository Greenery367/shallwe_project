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
					<th>환불 신청 일자</th>
					<th>플랫폼</th>
					<th>처리 상태</th>
				</tr>
			<c:forEach var="refundDto" items="${refundList}">
				<tr>
					<td>${refundDto.id}</td>
					<td>${refundDto.orderId}</td>
					<td>${refundDto.userId}</td>
					<td>${refundDto.createdAt}</td>
					<td><c:if test="${refundDto.platform == 1 }">카카오페이</c:if>
						<c:if test="${refundDto.platform == 2 }">토스페이먼츠</c:if>
					</td>
					<td><c:if test="${refundDto.status == 0 }">
						<button class="refund-table" onclick="decideRefund(${refundDto.platform}, ${refundDto.id}, ${refundDto.orderId}, ${refundDto.userId},)">환불 미완료</button></c:if>
						<c:if test="${refundDto.status != 0 }"><div>환불 처리 완료</div></c:if>
					</td>
					</tr>
			</c:forEach>
			</table>
	</c:if>
	
	</div>
	<script>
		function decideRefund(platform, id){
			console.log(platform, id);
			
			// 카카오페이 결제 - 환불 처리
			if(platform == 1){
				fetch(`http://localhost:8080/admin/refund/send-request/kakao`, {
		            method: "POST",
		            headers: {
		                "Content-Type": "application/json",
		                // "charset"은 필요하지 않음. 브라우저가 자동으로 처리
		            },
		            body: JSON.stringify({"id" : id}) // 요청 본문에 데이터를 포함
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
			
			
			// 토스 페이 환불 처리
			if(platform == 2){
				fetch(`http://localhost:8080/admin/refund/send-request/toss`, {
		            method: "POST",
		            headers: {
		                "Content-Type": "application/json",
		                // "charset"은 필요하지 않음. 브라우저가 자동으로 처리
		            },
		            body: JSON.stringify({"id" : id}) // 요청 본문에 데이터를 포함
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
			 
		}
		
		
	</script>

<%@ include file="/WEB-INF/view/layout/adminFooter.jsp"%>