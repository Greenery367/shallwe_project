<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/css/findFriend.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="search-box">
	<form action="/user/findUser" method="POST">
		<input type="text" placeholder="Search for friends" name="name">
		<input type="hidden" value="0" name="pageNum">
	</form>
	</div>
	<div class="search-result">
		<c:forEach var="user" items="${userList}">
		<div class="user-profile">
            <a href="/chat/profileInfo?id=${user.id}">
            <img src="/images/${user.uploadFileName}" alt="Profile Picture">
            </a>
            <div class="user-info">
                <div class="user-name">
                <a href="/chat/profileInfo?id=${user.id}">${user.nickname}</a>
                </div>
            </div>
        </div>
        </c:forEach>
	</div>
	<div class="page">
		<c:if test="${current} != 1">
		<form action="/user/findUser?name=${name}&page=${current - 1}" method="post">
			<button class="previous">
				이전
			</button>
		</form>
		</c:if>
		<c:forEach var="i" begin="1" end="${pageSize}">
			<ul class="pagination">
				<li><a href="pagePost(${i})"
				<c:if test="${current} == ${i}">class="current-page"</c:if>
				>${i}</a>
				</li>
			</ul>
		<c:if test="${current} != ${pageSize}">
		<form action="/user/findUser?name=${name}&page=${current + 1}" method="post">
			<button class="next">
				다음
			</button>
		</form>
		</c:if>
		</c:forEach>
	</div>
	<script>
		
		function pagePost(page) {
			let form = document.createElement('form');
			form.setAttribute('method','post');
			form.setAttribute('action','/user/findUser');
			
			let pageNum = document.createElement('input');
			pageNum.setAttribute('type','hidden');
			pageNum.setAttribute('name','pageNum');
			pageNum.setAttribute('value',page);
			
			let name = document.createElement('input');
			name.setAttribute('type', 'hidden');
			name.setAttribute('name','name');
			name.setAttribute('value',`${name}`);
			
			form.appendChild(name);
			form.appendChild(pageNum);
			document.body.appendChild(form);
			form.submit();
		}
		
	</script>
</body>
</html>