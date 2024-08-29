<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>새 게시글 작성</title>
</head>
<body>
	<h1>새 게시글 작성</h1>

	<form action="${pageContext.request.contextPath}/community/createBoard" method="post">
		<table>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" required /></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="content" rows="10" cols="50" required></textarea></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="int" name="author" required /></td> <!-- 여긴 세션값 받아야함 -->
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="작성하기" /> <input type="reset" value="초기화" /></td>
			</tr>
		</table>
	</form>
	<a href="${pageContext.request.contextPath}/community/category/${board.categoryId}?currentPage=${currentPage}">목록으로 돌아가기</a>
</body>
</html>
