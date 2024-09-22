<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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

    h1 {
        text-align: left;
        color: #000000;
    }

    table {
        width: 100%;
        margin-bottom: 20px;
        border-collapse: collapse;
    }

    th {
        text-align: left;
        padding: 12px;
        background-color: #FD6F22; /* 주황색 */
        color: white;
    }

    td {
        padding: 12px;
        border: 1px solid #ddd;
    }

    input[type="text"],
    textarea {
        width: 100%;
        padding: 10px;
        border: 1px solid #ddd;
        border-radius: 4px;
        margin-bottom: 10px;
        resize: none;
        font-size: 14px;
    }

    input[type="submit"],
    input[type="reset"] {
        padding: 10px 20px;
        margin: 10px 5px;
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

    input[type="submit"]:hover,
    input[type="reset"]:hover {
        background-color: #45A049; /* 하늘색 */
    }

    a {
        text-decoration: none;
        color: #FD6F22;
    }

    a:hover {
        text-decoration: underline;
    }
</style>

<div class="container">
    <h1>게시글 수정</h1>

    <form action="${pageContext.request.contextPath}/community/update/${board.id}" method="post">
        <input type="hidden" name="authorId" value="${board.authorId}" />
        <input type="hidden" name="categoryId" value="${board.categoryId}" />
        <table>
            <tr>
                <th>작성자</th>
                <td><p>${board.nickName}</p></td>
            </tr>
            <tr>
                <th>제목</th>
                <td><input type="text" name="title" value="${board.title}" required /></td>
            </tr>
            <tr>
                <th>내용</th>
                <td><textarea name="content" rows="10" required>${board.content}</textarea></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="수정하기" />
                    <input type="reset" value="초기화" />
                </td>
            </tr>
        </table>
    </form>
    <a href="${pageContext.request.contextPath}/community/category/${board.categoryId}?currentPage=${currentPage}">목록으로 돌아가기</a>
</div>

<%@ include file="/WEB-INF/view/layout/footer.jsp" %>
