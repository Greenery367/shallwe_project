<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/WEB-INF/view/layout/header.jsp" %>

<style>
    body {
        font-family: 'Arial', sans-serif;
        margin: 0;
        padding: 0;
        background-color: #f9f9f9;
        color: #333;
    }

    .container {
        width: 80%;
        margin: 0 auto;
        padding: 20px;
        background-color: white;
        box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.1);
        border-radius: 8px;
    }

    h1, h2, h3 {
        text-align: left;
        color: #000000; 
    }

    table {
        width: 100%; /* 테이블을 전체 너비로 변경 */
        margin-bottom: 20px;
        border-collapse: collapse;
        box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
    }

    table th, table td {
        padding: 12px;
        text-align: left;
        border: 1px solid #ddd;
        word-wrap: break-word; /* 단어 줄 바꿈 */
    }

    table th {
        background-color: #FD6F22; /* 주황색 */
    	color: white;
    	width: 10%; /* 원하는 너비로 조정 (예: 15%) */
    }

    button, a.button {
        display: inline-block;
        padding: 10px 20px;
        margin: 10px 0;
        font-size: 14px;
        text-align: center;
        text-decoration: none;
        color: white;
        background-color: #FD6F22; /* 주황색 */
        border-radius: 5px;
        border: none;
        cursor: pointer;
        transition: background-color 0.3s;
    }

    button:hover, a.button:hover {
        background-color: #45A049; /* 하늘색 */
    }

    .no-comments {
        text-align: center;
        color: #666;
        font-style: italic;
    }

    .comments-section {
        margin-top: 20px;
        width: 100%; /* 전체 너비로 변경 */
    }

    .comment-form {
        margin-top: 20px; /* 댓글 작성 폼의 위 여백 추가 */
        clear: both; /* 댓글 목록 아래에서 clear */
        padding: 10px; /* 패딩 추가 */
        border: 1px solid #ddd; /* 테두리 추가 */
        border-radius: 5px; /* 모서리 둥글게 */
        background-color: #f9f9f9; /* 배경색 추가 */
    }
</style>

<div class="container">
    <h1>게시글 상세보기</h1>

    <c:if test="${not empty board}">
        <table>
            <tr>
                <th>작성자</th>
                <td>${board.nickName}</td>
            </tr>
            <tr>
                <th>제목</th>
                <td>${board.title}</td>
            </tr>
            <tr>
                <th>작성일</th>
                <td><fmt:formatDate value="${board.createdAt}" type="DATE" pattern="yyyy-MM-dd" /></td>
            </tr>
            <tr>
                <th>조회수</th>
                <td>${board.viewNum}</td>
            </tr>
            <tr>
                <th>추천</th>
                <td>${board.good}</td>
            </tr>
            <tr>
                <th>내용</th>
                <td>${board.content}</td>
            </tr>
        </table>

        <c:if test="${user.userId == board.authorId}">
            <div class="buttons">
                <button type="button" onclick="window.location.href='${pageContext.request.contextPath}/community/update-board/${board.id}';">수정</button>
                <form action="${pageContext.request.contextPath}/community/delete-board/${board.id}" method="post" style="display: inline;">
                    <input type="hidden" name="categoryId" value="${board.categoryId}" />
                    <input type="hidden" name="authorId" value="${board.authorId}" />
                    <button type="submit">삭제</button>
                </form>
            </div>
        </c:if>
    </c:if>

    <div class="comments-section">
        <h2>댓글</h2>
        <c:if test="${not empty comments}">
            <c:forEach var="comment" items="${comments}">
                <div style="border: 1px solid #ddd; padding: 10px; margin-bottom: 10px; border-radius: 5px;">
                    <strong>${comment.nickName}</strong>
                    <p id="commentContent-${comment.id}">${comment.content}</p>
                    <small><fmt:formatDate value="${comment.createdAt}" type="DATE" pattern="yyyy-MM-dd HH:mm:ss" /></small>

                    <c:if test="${user.userId == comment.authorId}">
                        <button type="button" onclick="toggleEditComment(${comment.id});">수정</button>
                        <form action="${pageContext.request.contextPath}/community/delete-comment/${comment.id}" method="post" style="display: inline;">
                            <input type="hidden" name="postId" value="${comment.postId}"/>
                            <button type="submit">삭제</button>
                        </form>
                        <div id="editComment-${comment.id}" style="display: none;">
                            <form action="${pageContext.request.contextPath}/community/update-comment" method="post">
                                <input type="hidden" name="commentId" value="${comment.id}" />
                                <input type="hidden" name="postId" value="${comment.postId}" />
                                <textarea name="content" rows="4">${comment.content}</textarea>
                                <button type="submit">저장</button>
                            </form>
                        </div>
                    </c:if>
                </div>
            </c:forEach>
        </c:if>
        <c:if test="${empty comments}">
            <p class="no-comments">댓글이 없습니다.</p>
        </c:if>
    </div>

    <div class="comment-form">
    <h3>댓글 작성</h3>
    <c:if test="${empty user}"> <!-- 로그인하지 않은 경우 -->
        <p>댓글 작성을 위해서는 <strong>로그인</strong>이 필요합니다.</p>
        <a href="${pageContext.request.contextPath}/user/sign-in" class="button">로그인하기</a>
    </c:if>
    <c:if test="${not empty user}"> <!-- 로그인한 경우 -->
        <form action="${pageContext.request.contextPath}/community/createComment" method="post">
            <input type="hidden" name="postId" value="${board.id}" />
            <div>
                <textarea name="content" rows="4" placeholder="댓글을 입력하세요." required></textarea>
            </div>
            <button type="submit">댓글 작성</button>
        </form>
    </c:if>
</div>

    <a href="${pageContext.request.contextPath}/community/category/${board.categoryId}?currentPage=${currentPage}" class="button">목록으로 돌아가기</a>
</div>

<script>
    function toggleEditComment(commentId) {
        var editSection = document.getElementById('editComment-' + commentId);
        editSection.style.display = (editSection.style.display === 'none' || editSection.style.display === '') ? 'block' : 'none';
    }
</script>

<%@ include file="/WEB-INF/view/layout/footer.jsp" %>
