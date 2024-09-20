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
					<th>QnA </th>
					<th>카테고리명</th>
					<th>관리</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="category" items="${categoryList}">
					<tr>
						<td>${category.id}</td>
						<td>${category.gameName}</td>
						<td> <button onclick="opentab(${category.id})">카테고리 보기</button> / <form action="/admin/community/delete-category" method="post">
								<input type="hidden" id="id" name="id" value="${category.id}">
							<button type="submit">삭제</button>
							</form></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<p>카테고리 생성</p>
	<form action="/admin/community/insert-category" method="post" class="advertise">
		<div class="form-group">
			<label for="gameName">카테고리명</label>
			<input type="text" id="gameName" name="gameName" value="사이퍼즈" placeholder="게임이름을 입력해주세요">
		</div>
		<button type="submit">카테고리추가</button>
	</form>
	
	<p>카테고리 수정</p>
	<form action="/admin/community/update-category" method="post" class="advertise">
		<div class="form-group">
			<label for="id">수정할 카테고리 id</label> 
			<input type="text" id="id" name="id" value="1">
		</div>
		<div class="form-group">
			<label for="gameName">카테고리명</label> 
			<input type="text" id="gameName" name="gameName" value="사이퍼즈">
		</div>
		<button type="submit">카테고리수정</button>
	</form>
	
	
	<br>
	<br>
	<p>------------------------------------------------------------------------------------------</p>
	<br>
	<br>
	<div>
		<p>게시판 / 댓글 관리</p><br>
		
		<p>게시글 리스트</p>
		<table class="table">
			<thead>
				<tr>
					<th>게시글 id</th>
					<th>카테고리명(id)[수정예정]</th>
					<th>제목</th>
					<th>작성자(id)[수정예정]</th>
					<th>조회수</th>
					<th>좋아요</th>
					<th>게시글 작성시간</th>
					<th>관리</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="board" items="${boardList}">
					<tr>
						<td>${board.id}</td>
						<td>${board.categoryId}</td>
						<td>${board.title}</td>
						<td>${board.authorId}</td>
						<td>${board.viewNum}</td>
						<td>${board.good}</td>
						<td>${board.createdAt}</td>
						<td><button onclick="openWindow(${board.id})">게시글 보기 (댓글 관리)</button> / 
						<form action="/admin/community/detail/delete-board" method="post">
								<input type="hidden" id="id" name="id" value="${board.id}">
							<button type="submit">삭제</button>
							</form></td>
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