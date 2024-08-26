<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	String id = request.getParameter("id");
	%>
	<fieldset>
		ID : <input type="text" id="checkedId" name="id" value="<%=id%>"> 
		<input type="submit" value="중복 확인" onclick="check();">
	</fieldset>
	

</body>
</html>