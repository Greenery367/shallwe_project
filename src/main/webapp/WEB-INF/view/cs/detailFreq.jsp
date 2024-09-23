<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/layout/header.jsp" %>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/detailFAQ.css">
<title>게시글 상세 보기</title>
</head>
<body>
    <main class="content-wrapper">
        <!-- 제목 섹션 -->
        <section class="post-header">
            <h1>${FAQ.title}</h1>
        </section>

        <!-- 작성자와 작성시간을 같은 줄에 배치 -->
        <section class="meta-info">
            <span>작성자: ${FAQ.writer}</span>
            <span>작성시간: ${FAQ.createdAt}</span>
        </section>

        <!-- 구분선 -->
        <div class="separator"></div>

        <!-- 본문 내용 -->
        <section class="post-content">
            <pre>${FAQ.content}</pre>
        </section>

        <!-- 버튼 영역 -->
        <div class="btn-area">
            <button type="button" class="btn update-button" style="display: none;">수정</button>
            <button type="button" class="btn delete-button" style="display: none;">삭제</button>
        </div>
    </main>

<%@ include file="/WEB-INF/view/layout/footer.jsp" %>
</body>
</html>
