<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
</head>
<body> 
	<h1>게시글 수정</h1>
	<form action="${pageContext.request.contextPath}/community/update/${board.id}" method="post">
		<input type="hidden" name="author" value="${board.author}" required/>
		<table>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" value="${board.title}" required /></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" name="author" value="${board.author}" readonly /></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="content" rows="10" cols="50" required>${board.content}</textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="수정하기" />
					<input type="reset" value="초기화" />
				</td>
			</tr>
		</table>
	</form>
	<a href="${pageContext.request.contextPath}/community/category/${board.categoryId}">목록으로 돌아가기</a>
</body>
</html>
