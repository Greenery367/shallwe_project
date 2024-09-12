<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/view/layout/header.jsp" %>
<link rel="stylesheet" href="/static/css/cs.css">
<div class="qna-container">
	<nav class="qna-nav">
		<ul>
			<li><a href="main">자주 묻는 질문</a></li>
<<<<<<< HEAD
			<li><a href="FAQ">FAQ</a></li>  <!-- active 적용 -->
=======
			<li><a href="FAQ">FAQ</a></li>
>>>>>>> ae88f71a5d98cf993b2fdaf0d6538975fc5d4348
			<li><a href="#">1:1 문의</a></li>
			<li><a href="#">공지사항</a></li>
		</ul>
	</nav>

	<table class="qna-table">
		<thead>
			<tr>
<<<<<<< HEAD
				<th></th>
=======
>>>>>>> ae88f71a5d98cf993b2fdaf0d6538975fc5d4348
				<th>No</th>
				<th>글 제목</th>
				<th>작성자</th>
				<th>작성 시간</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="post" varStatus="status" items="${postList}">
				<tr>
<<<<<<< HEAD
					<td>&#9742;</td>
=======
>>>>>>> ae88f71a5d98cf993b2fdaf0d6538975fc5d4348
					<td>${status.count}</td>
					<td>${post.title}</td>
					<td>${post.writer}</td>
					<td>${post.createdAt}</td>
<<<<<<< HEAD
				<input id="FAQid" name="id" type="hidden" value="${post.id}">
=======
>>>>>>> ae88f71a5d98cf993b2fdaf0d6538975fc5d4348
				</tr>
			</c:forEach>
		</tbody>
	</table>
<<<<<<< HEAD
=======

>>>>>>> ae88f71a5d98cf993b2fdaf0d6538975fc5d4348
	<div class="pagination">
		<ul>
			<li class="page-item <c:if test='${curruntPage == 1}'>disabled</c:if>">
				<a class="page-link" href="?page=1&size=${size}">First</a>
			</li>
			<li class="page-item <c:if test='${curruntPage == 1}'>disabled</c:if>">
				<a class="page-link" href="?page=${curruntPage-1}&size=${size}">Previous</a>
			</li>
			<c:set var="beginPage" value="${curruntPage - 2}" />
			<c:set var="endPage" value="${curruntPage + 2}" />
			<c:if test="${beginPage < 1}">
				<c:set var="beginPage" value="1" />
			</c:if>
			<c:if test="${endPage > totalPages}">
				<c:set var="endPage" value="${totalPages}" />
			</c:if>
			<c:forEach begin="${beginPage}" end="${endPage}" var="page">
				<li class="page-item <c:if test='${page == curruntPage}'>active</c:if>">
					<a class="page-link" href="?page=${page}&size=${size}">${page}</a>
				</li>
			</c:forEach>
			<li class="page-item <c:if test='${curruntPage == totalPages}'>disabled</c:if>">
				<a class="page-link" href="?page=${curruntPage+1}&size=${size}">Next</a>
			</li>
			<li class="page-item <c:if test='${curruntPage == totalPages}'>disabled</c:if>">
				<a class="page-link" href="?page=${totalPages}&size=${size}">Last</a>
			</li>
		</ul>
	</div>
	
	<form class ="wrbtn" action="post-FAQ">
		<button class="qna-write-btn" type="submit">문의글 작성</button>
	</form>
</div>

<%@ include file="/WEB-INF/view/layout/footer.jsp" %>
<<<<<<< HEAD
<script>
document.addEventListener('DOMContentLoaded', function () {
    // 테이블의 각 행에 클릭 이벤트를 추가
    const rows = document.querySelectorAll('.qna-table tbody tr');
    
    rows.forEach(function(row) {
        row.addEventListener('click', function() {
            const writer = this.querySelector('td:nth-child(3)').innerText; // 작성자
            const id = this.querySelector('input[name="id"]').value; // 히든 필드의 id 값 가져오기

            // detail 컨트롤러로 이동 (GET 요청)
            window.location.href = `/cs/detailFreq?writer=${writer}&id=${id}`;
        });
    });
});

</script>
=======
>>>>>>> ae88f71a5d98cf993b2fdaf0d6538975fc5d4348
