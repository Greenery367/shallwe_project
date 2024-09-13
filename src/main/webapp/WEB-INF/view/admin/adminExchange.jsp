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
					<th>유저명</th>
					<th>서브몰id</th>
					<th>환전금액</th>
					<th>환전신청일자</th>
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
	</script>

<%@ include file="/WEB-INF/view/layout/adminFooter.jsp"%>