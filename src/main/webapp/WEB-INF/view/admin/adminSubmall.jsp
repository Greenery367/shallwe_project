<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/layout/adminHeader.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="/css/adminRefund.css">
	<div class="main-board">
	<h1>서브몰 신청 목록 관리</h1>
			<table class="main-table">
				<tr>
					<th>id</th>
					<th>유저id</th>
					<th>은행</th>
					<th>계좌명</th>
					<th>서브몰 신청 일자</th>
					<th>처리 상태</th>
				</tr>
			 <c:forEach var="submallInfoDto" items="${submallInfoList}">
            <tr>
                <td>${submallInfoDto.submallInfo.id}</td>
                <td>${submallInfoDto.user.nickname}</td>
                <td>${submallInfoDto.bankInfo.bankId}</td>
                <td>${submallInfoDto.bankInfo.accountNumber}</td>
                <td>${submallInfoDto.submallInfo.createdAt}</td>
                <td>
                    <c:if test="${submallInfoDto.submallInfo.status == 0}">
                        <button class="refund-table" value="${submallInfoDto}" onclick="requestMakeNewSubmall(this)">생성 미완료</button>
                    </c:if>
                    <c:if test="${submallInfoDto.submallInfo.status != 0}">
                        <div>생성 처리 완료</div>
                    </c:if>
                </td>
				</tr>
			</c:forEach>
			</table>
	
	<h1>서브몰 목록 관리</h1>
			<table class="main-table">
				<tr>
					<th>id</th>
					<th>유저명</th>
					<th>은행</th>
					<th>계좌명</th>
					<th>이메일</th>
					<th>전화번호</th>
				</tr>
			<c:forEach var="refund" items="${refundList}">
				<tr>
					<td>${refund.id}</td>
					<td>${refund.orderId}</td>
					<td>${refund.userId}</td>
					<td>${refund.reason}</td>
					<td>${refund.createdAt}</td>
					<td><c:if test="${refund.status == 0 }"><button class="refund-table" value="${refund}" >환불 미완료</button></c:if>
						<c:if test="${refund.status != 0 }"><div>환불 처리 완료</div></c:if>
					</td>
				</tr>
			</c:forEach>
			</table>
	</div>
	<script>
    function requestMakeNewSubmall(data) {
    	var a = data.value.get[1] ; 
    	console.log(a);
        fetch(`http://localhost:8080/admin/exchange/add-submall`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ "submallInfo": a })
        })
        .catch(error => {
            console.error('요청 처리 중 오류 발생:', error);
        });
    }
	</script>

<%@ include file="/WEB-INF/view/layout/adminFooter.jsp"%>