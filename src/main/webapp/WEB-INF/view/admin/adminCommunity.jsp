<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>


<link rel="stylesheet" href="/css/adminCommunity.css">

<%@ include file="/WEB-INF/view/layout/adminHeader.jsp"%>

	<div>
		<p>카테고리 관리</p><br>
		
		<p>카테고리 리스트</p>
		<table class="table">
			<thead>
				<tr>
					<th>카테고리id</th>
					<th>카테고리명</th>
					<th>관리</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="category" items="${categoryList}">
					<tr>
						<td>${category.id}</td>
						<td>${category.gameName}</td>
						<td> <button onclick="openWindow()">카테고리 보기</button> / <form action="/admin/community/delete-category" method="post">
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
			<input type="text" id="gameName" name="gameName" value="사이퍼즈">
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
						<td>게시글보기(댓글관리) / 
						<form action="/admin/community/delete-board" method="post">
								<input type="hidden" id="id" name="id" value="${board.id}">
							<button type="submit">삭제</button>
							</form></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<p>게시글 수정</p>
	<form action="/admin/community/update-board" method="post" class="advertise">
		<div class="form-group">
			<label for="id">수정할 카테고리 id</label> 
			<input type="text" id="id" name="id" value="1">
		</div>
		<div class="form-group">
			<label for="id">수정할 카테고리 id</label> 
			<input type="text" id="id" name="id" value="1">
		</div>
		<div class="form-group">
			<label for="id">수정할 카테고리 id</label> 
			<input type="text" id="id" name="id" value="1">
		</div>
		
		<div class="form-group">
			<label for="title">카테고리명</label> 
			<input type="text" id="title" name="title" value="호호게시판">
		</div>
		<button type="submit">카테고리수정</button>
	</form>
	 <script>
        function openWindow() {
            // 새 창을 열 때의 옵션 설정
            // TODO - 주소수정 필요
            var url = '${pageContext.request.contextPath}/admin/advertise';
            var name = '관리자 - 게시글, 댓글 수정'; // 창의 이름
            var specs = 'width=1000,height=900,left=100,top=100'; // 창의 크기와 위치 설정

            window.open(url, name, specs);
        }
    </script>


<%@ include file="/WEB-INF/view/layout/adminFooter.jsp"%>