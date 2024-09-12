<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/view/layout/header.jsp" %>



<div>
	<b>문의글 작성하기</b>
</div>
<div>
	<form action="post-FAQ" method="post">
		<div>
			<input type="text" name="title" value="문의글 1">
		</div>
		<div>
			<textarea rows="5" name="content"></textarea>
		</div>
		<button type="submit">작성 완료</button>
	</form>
</div>
 <script src="https://code.jquery.com/jquery-3.5.1.min.js" crossorigin="anonymous"></script>
 <script type="text/javascript" src="/lib/smarteditor2-2.9.2/workspace/js/service/HuskyEZCreator.js"></script>
<%@ include file="/WEB-INF/view/layout/footer.jsp" %>