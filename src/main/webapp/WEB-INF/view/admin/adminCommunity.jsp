<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>


<link rel="stylesheet" href="/css/adminAdvertise.css">

<%@ include file="/WEB-INF/view/layout/adminHeader.jsp"%>

	<div>
		<p>카테고리 관리</p><br>
		
		<p>카테고리 리스트</p>
		<table class="table">
			<thead>
				<tr>
					<th>카테고리id</th>
					<th>카테고리명</th>
					<th>게시글수</th>
					<th>관리</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="category" items="${categoryList}">
					<tr>
						<td>${category.id}</td>
						<td>${category.title}</td>
						<td>${category.numberOfBoard}</td>
						<td>보기 / 삭제</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<p>카테고리 생성</p>
	<form action="/admin/community/insert-category" method="post" class="advertise">
		<div class="form-group">
			<label for="title">카테고리명</label> 
			<input type="text" id="title" name="title" value="ㄱㄱ게시판">
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
			<label for="title">카테고리명</label> 
			<input type="text" id="title" name="title" value="호호게시판">
		</div>
		<button type="submit">카테고리수정</button>
	</form>
	
	<p>카테고리 삭제</p>
	<form action="/admin/community/delete-category" method="post" class="advertise">
		<div class="form-group">
			<label for="id">카테고리id</label> 
			<input type="text" id="id" name="id" value="1">
		</div>
		<button type="submit">카테고리삭제</button>
	</form>
	


<%@ include file="/WEB-INF/view/layout/adminFooter.jsp"%>