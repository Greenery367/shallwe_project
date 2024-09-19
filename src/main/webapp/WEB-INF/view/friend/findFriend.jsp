<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/layout/header.jsp" %>	
<%@ include file="/WEB-INF/view/layout/friendHeader.jsp" %>	
<link rel="stylesheet" href="/css/findFriend.css">
	<div class="content">
	<div class="search-box">
	<form action="/friends/findUser" method="POST">
		<input type="text" placeholder="Search for friends" name="name">
		<input type="hidden" value="1" name="pageNum">
	</form>
	</div>
	<div class="search-result">
		<c:forEach var="user" items="${userList}">
		<div class="user-profile">
            <a href="/chat/profileInfo?id=${user.userId}">
            <c:choose>
            <c:when test="${user.uploadFileName == null}">
            <img src="/static/images/defaultProfile.jpeg">
            </c:when>
            <c:otherwise>
            <img src="/images/${user.uploadFileName}" alt="Profile Picture">
            </c:otherwise>
            </c:choose>
            </a>
            <div class="user-info">
                <div class="user-name">
                <a href="/chat/profileInfo?id=${user.userId}">${user.nickname}</a>
                </div>
            </div>
        </div>
        </c:forEach>
	</div>
	<div class="page">
		<c:if test="${current} != 1">
		<form action="/friends/findUser?name=${name}&page=${current - 1}" method="post">
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
		<form action="/friends/findUser?name=${name}&page=${current + 1}" method="post">
			<button class="next">
				다음
			</button>
		</form>
		</c:if>
		</c:forEach>
	</div>
	</div>
	<script>
		
		function pagePost(page) {
			let form = document.createElement('form');
			form.setAttribute('method','post');
			form.setAttribute('action','/friends/findUser');
			
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