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
		            	<input type="hidden" id="id" value="${FAQ.id}">
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
                        <input type="text" readonly id="createdAt" value="${FAQ.createdAt}">
                    </td>
                </tr>
            </table>
            <%int status = Integer.parseInt(request.getParameter("status")); %>
            <c:if test="${status == 0}">
            <div class="btn-area">
                <button type="button" class="update-button" >수정</button>
                &nbsp;
                <button type="button" class="delete-button" >삭제</button>
            </div>
            </c:if>
        </section>
      </main>
<%@ include file="/WEB-INF/view/layout/footer.jsp" %>
<script type="text/javascript">
    document.addEventListener('DOMContentLoaded', function() {
        // 수정 버튼 클릭 이벤트
        const updateButton = document.querySelector('.update-button');
        
        updateButton.addEventListener('click', function() {
            // 입력 필드의 값을 가져옴
            const id = document.getElementById('id').value;

            // 컨트롤러로 GET 요청을 보냄
            window.location.href = `/cs/update?id=${id}`;
        });
    });
    
    document.addEventListener('DOMContentLoaded', function() {
        // 삭제 버튼 클릭 이벤트
        const deleteButton = document.querySelector('.delete-button');
        
        updateButton.addEventListener('click', function() {
            // 입력 필드의 값을 가져옴
            const id = document.getElementById('id').value;

            // 컨트롤러로 GET 요청을 보냄
            window.location.href = `/cs/delete?id=${id}`;
        });
    });
    
</script>