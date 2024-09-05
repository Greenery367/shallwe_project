<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div class="search-box">
	<form action="">
		<input type="text" placeholder="Search for friends" name="name">
	</form>
	</div>
	<div class="search-result">
		<c:forEach var="user" items="${userList}">
		<div class="user-profile">
            <img src="${user.profilePictureUrl}" alt="Profile Picture">
            <div class="user-info">
                <div class="user-name">${user.name}</div>
                <div class="user-description">${user.description}</div>
            </div>
        </div>
        </c:forEach>
	</div>
</body>
</html>