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
                        <button class="refund-table" value="${submallInfoDto.submallInfo.id}" onclick="requestMakeNewSubmall(this)">생성 미완료</button>
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
					<th>서브몰 id</th>
					<th>유저 id</th>
					<th>지급 타입</th>
					<th>지급 상태</th>
					<th>은행 코드</th>
					<th>이메일</th>
					<th>전화번호</th>
				</tr>
			<c:forEach var="submall" items="${submallList}">
				<tr>
					<td>${submall.submallId}</td>
					<td>${submall.userId}</td>
					<td>${submall.type}</td>
					<td>${submall.status}</td>
					<td>${submall.accountId}</td>
					<td>${submall.email}</td>
					<td>${submall.phoneNumber}</td>
				</tr>
			</c:forEach>
			</table>
	</div>
	<script>
    function requestMakeNewSubmall(data) {
    	var a = data.value ; 
    	console.log(a);
        fetch(`http://localhost:8080/admin/submall/add`, {
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