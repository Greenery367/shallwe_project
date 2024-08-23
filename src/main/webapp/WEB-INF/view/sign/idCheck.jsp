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
	
<script>
	function check(){
		<%
		int result = 1;
		if (result == 1) {
			out.print("사용가능한 아이디입니다");
		%>
		<input type="button" value="아이디 사용하기" onclick="result();">
		<%
		} else if (result == 0) {
		out.print("중복된 아이디입니다");
		} else {
		out.print("알 수 없는 에러.");
		}
		%>
	}
	function result(){
    	opener.document.querySelector(#id).value = checkedId;
    	//6-3. 회원가입창 제어
    	opener.document.querySelector(#id).readOnly=true;
    	window.close();
    }
</script>
</body>
</html>