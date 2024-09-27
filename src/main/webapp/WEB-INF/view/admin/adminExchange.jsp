<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/layout/adminHeader.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="/css/adminRefund.css">
	<div class="main-board">
	<h1>환전 내역 관리</h1>
	<c:if test="${exchangeList != null}">
			<table class="table">
				<tr>
					<th>id</th>
					<th>유저ID</th>
					<th>서브몰ID</th>
					<th>환전금액</th>
					<th>환전신청일자</th>
					<th>처리 상태</th>
				</tr>
			<c:forEach var="exchange" items="${exchangeList}">
				<tr>
					<td>${exchange.id}</td>
					<td>${exchange.userId}</td>
					<td>${exchange.submallId}</td>
					<td>${exchange.amount}</td>
					<td>${exchange.createdAt}</td>
					<td><c:if test="${exchange.status == 0 }"><button class="refund-table" value="${exchange.id}" onclick="decideRefund(this)">환전 미완료</button></c:if>
						<c:if test="${exchange.status != 0 }"><div>환전 처리 완료</div></c:if>
					</td>
				</tr>
			</c:forEach>
			</table>
	</c:if>
	
	</div>
	<script>
	function decideRefund(data) {
    	var a = data.value ; 
    	console.log(a);
        fetch(`http://localhost:8080/admin/exchange/send-request`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ "id": a })
        })
        .catch(error => {
            console.error('요청 처리 중 오류 발생:', error);
        });
    }	
	
	</script>

<%@ include file="/WEB-INF/view/layout/adminFooter.jsp"%>