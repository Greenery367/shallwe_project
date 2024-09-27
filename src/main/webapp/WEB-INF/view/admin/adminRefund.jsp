<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/layout/adminHeader.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="/css/adminRefund.css">
	<div class="main-board">
	<h1>환불 내역 관리</h1>
			<table class="table">
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
						<button class="refund-table" onclick="decideRefund(${refundDto.platform}, ${refundDto.id})">환불 미완료</button></c:if>
						<c:if test="${refundDto.status == 1 }"><div>환불 처리 완료</div></c:if>
					</td>
					</tr>
			</c:forEach>
			</table>
	</div>
<script>
    function decideRefund(platform, id) {
        console.log(platform, id);
        
        const url = platform === 1 
            ? `http://localhost:8080/admin/refund/send-request/kakao` 
            : `http://localhost:8080/admin/refund/send-request/toss`;

        fetch(url, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({"id": id}),
        })
        .then(data => {
            console.log('환불 요청 성공:', data);
            alert('환불 요청이 성공적으로 처리되었습니다.');
            // 여기에서 UI 업데이트 (예: 버튼 상태 변경)
        })
    }
</script>


<%@ include file="/WEB-INF/view/layout/adminFooter.jsp"%>