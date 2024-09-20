<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>


<link rel="stylesheet" href="/css/adminLecture.css">

<%@ include file="/WEB-INF/view/layout/adminHeader.jsp"%>

	<div>
		
		<p>강의 리스트</p>
		<table class="table">
			<thead>
				<tr>
					<th>카테고리명</th>
					<th>작성자이름</th>
					<th>제목</th>
					<th>부제</th>
					<th>내용</th>
					<th>가격</th>
					<th>강의생성시간</th>
					<th>상태</th>
					<th>관리</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="lecture" items="${lectureList}">
					<tr>
						<td>${lecture.categoryId}</td>
						<td>${lecture.authorId}</td>
						<td>${lecture.title}</td>
						<td>${lecture.subtitle}</td>
						<td>${lecture.content}</td>
						<td>${lecture.price}</td>
						<td>${lecture.createdAt}</td>
						<td>${lecture.status}</td>
						<td> 보기 / <form action="/admin/lecture/delete-lecture" method="post">
								<input type="hidden" id="id" name="id" value="${lecture.id}">
							<button type="submit">삭제</button>
							</form></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	
	<p>강의 수정</p>
	<form action="/admin/lecture/update-lecture" method="post" class="advertise">
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
	    


<%@ include file="/WEB-INF/view/layout/adminFooter.jsp"%>