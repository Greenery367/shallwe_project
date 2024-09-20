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

    .form-container {
        width: 50%;
        margin: 20px auto;
        text-align: center;
    }

    .error-message {
        color: red;
        text-align: center;
    }
</style>

<div style="display: flex; justify-content: space-between;">

    <%@ include file="/WEB-INF/view/layout/myPageMenu.jsp"%>

    <div style="flex-grow: 1; text-align: center;">

        <!-- 환전 신청 폼 -->
        <div class="form-container">
            <h2>포인트 환전 신청</h2>
            <form action="registerExchange" method="post">
                <label for="amount">포인트 환전 금액:</label>
                <input type="number" id="amount" name="amount" min="0" required />
                <button type="submit">포인트 환전 신청</button>
	            <p><strong>현재 포인트:</strong> ${user.lectureCash}점</p>
            </form>
            

            <!-- 오류 메시지 표시 -->
            <c:if test="${not empty error}">
                <div class="error-message">${error}</div>
            </c:if>
        </div>

        <h2>포인트 환전 이력</h2>
        <table>
            <thead>
                <tr>
                    <th>날짜</th>
                    <th>포인트 환전 금액</th>
                    <th>승인</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="exchange" items="${exchangeList}">
                    <tr>
                        <td><fmt:formatDate value="${exchange.createdAt}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                        <td><fmt:formatNumber value="${exchange.amount}" /></td>
                        <td>
                            <c:choose>
                                <c:when test="${exchange.status == 0}">처리중</c:when>
                                <c:when test="${exchange.status == 1}">환전 완료</c:when>
                                <c:otherwise>알 수 없음</c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
