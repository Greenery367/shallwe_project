<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/view/layout/header.jsp" %>
<div>
    <div>
        <div><b>수정하기</b></div>
        <div>
            <form action="/board/${id}/update" method="POST">
                <div>
                    <input type="text" name="title" value="${post.title}">
                </div>
                <div>
                    <textarea rows="5" name="content" >${post.content}</textarea>
                </div>
                <button type="submit">저장</button>
            </form>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/view/layout/footer.jsp" %>