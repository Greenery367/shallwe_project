<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="result-page">			
			<div class="my-result">
				<h1>당신의 성향은...</h1>
				<h2>${resultMbti.name} : ${resultMbti.nickname}</h2>
				<h3>${resultMbti.content}</h3>
			</div>
			
			<div class="good-matched">
				<h4>나와 잘 맞는 사람은...</h4>
				<h5>${goodMatchedMbti.name} : ${goodMatchedMbti.nickname}</h5>
				<p>${goodMatchedMbti.content}</p>
			</div>
			
			<div class="bad-matched">
				<h4>나와 잘 맞지 않는 사람은...</h4>
				<h5>${badMatchedMbti.name} : ${badMatchedMbti.nickname}</h5>
				<p>${badMatchedMbti.content}</p>
			</div>
		</div>

</body>
</html>