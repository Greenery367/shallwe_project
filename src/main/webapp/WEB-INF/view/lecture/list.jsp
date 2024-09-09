<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/view/layout/header.jsp" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/lecture.css">
    <div class="container">
        <h1>${categoryId} 강의 목록</h1>
        <div class="lecture-grid">
            <c:forEach var="lecture" items="${lectureList}">
                <div class="lecture-card">
                    <img src="${pageContext.request.contextPath}/static/images/lalLecture.png" alt="${lecture.title}">
                    <div class="lecture-info">
                        <h2>${lecture.title}</h2>
                        <p>${lecture.subtitle}</p>
                        <p>${lecture.currentNum} / ${lecture.limitNum} 수강생</p>
                        <p>${lecture.nickName}</p>
                        <p class="price">${lecture.price}원</p>
                        
                        <a href="${pageContext.request.contextPath}/lecture/lectureDetail/${lecture.id}">
                        <button class="btn">상세보기</button>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    
<%@ include file="/WEB-INF/view/layout/footer.jsp" %>