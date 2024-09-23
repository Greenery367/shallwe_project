<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>


<link rel="stylesheet" href="/css/adminCommunity.css">

<%@ include file="/WEB-INF/view/layout/adminHeader.jsp"%>

	<div>
		<p>QnA</p><br>
		
		<p>QnA 리스트</p>
		<table class="table">
			<thead>
				<tr>
					<th>No</th>
					<th>글 제목</th>
					<th>작성자</th>
					<th>작성 시간</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="post" varStatus="status" items="${postList}">
					<tr>
						<td>${status.count}</td>
						<td>${post.title}</td>
						<td>${post.writer}</td>
						<td>${post.createdAt}</td>
						<input id="FAQid" name="id" type="hidden" value="${post.id}">
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	
	 <script>
	 function openWindow(boardId) {
		    // 데이터 전달을 위한 URL 생성
		    var url = '${pageContext.request.contextPath}/admin/community/detail?id=' + encodeURIComponent(boardId);
		    var name = '관리자 - 게시글, 댓글 수정';
		    var specs = 'width=1000,height=900,left=100,top=100';

		    window.open(url, name, specs);
		}
    </script>
    <script>
	    function opentab(categoryId) {
	        // 데이터 전달을 위한 URL 생성 TODO - 수정예정
	        var url = '${pageContext.request.contextPath}/admin/community/detail?id=' + encodeURIComponent(categoryId);
	
	        // 새 탭에서 열기
	        window.open(url, '_blank');
	    }
	</script>
    


<%@ include file="/WEB-INF/view/layout/adminFooter.jsp"%>