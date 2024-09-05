<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file='/WEB-INF/view/layout/header.jsp'%>



<div>
	<b>문의글 작성하기</b>
</div>
<div>
	<form action="post-FAQ">
		<div>
			<input type="text" name="title" value="문의글 1">
		</div>
		<div>
			<textarea rows="5" name="content"></textarea>
		</div>
		<button type="submit">작성 완료</button>
	</form>
</div>


<%@ include file='/WEB-INF/view/layout/footer.jsp'%>
