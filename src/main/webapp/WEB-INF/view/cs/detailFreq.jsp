<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/view/layout/header.jsp" %>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	    <main class="content-wrapper">
        <section class="form-title">
            <h1> 게시글 상세 보기 </h1>
            
        </section>

        <section>
            <table>
                <tr>
                    <th>제목</th>
                    <td>
                        <input type="text" readonly id="title" value="${FAQ.title}">
                    </td>
                </tr>
                <tr>
                    <th>작성자</th>
                    <td>
                        <input type="text" readonly id="writer" value="${FAQ.writer}">
                    </td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td>
                        <pre id="content" class="content" >${FAQ.content}</pre>                        
                    </td>
                </tr>
                <tr>
                    <th>작성시간</th>
                    <td>
                        <input type="text" readonly id="writer" value="${FAQ.createdAt}">
                    </td>
                </tr>
            </table>

            <div class="btn-area">
                <button type="button" class="btn update-button" style=display:none;>수정</button>
                &nbsp;
                <button type="button" class="btn delete-button" style=display:none;>삭제</button>
            </div>
        </section>
      </main>
<%@ include file="/WEB-INF/view/layout/footer.jsp" %>