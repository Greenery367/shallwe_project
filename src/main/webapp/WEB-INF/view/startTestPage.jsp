<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div class="personal-test">
	
		<div class="intro-container content-container">
			<h1>나의 게임 성향을 알아보는 MBTI 테스트</h1>
			<button onclick="startTest">테스트 시작</button>
		</div>
	
		<div>
			<h2>
				<span class="question"></span>
				<span class="answer"></span>
			</h2>
			<div class="answer-container">
				<button class="answer" type="button" data-answer="a"></button>
				<button class="answer" type="button" data-answer="b"></button>
			</div>
		</div> 
		
		<div class="result-container content-container">
			<h2 class="result-text"></h2>
			<button class="restart" type="button" data-action="restart">다시 하기</button>
		</div>
	</div>
	
	<script>
	 	// 시험 시작
	 	class PersonalTest{
	 		constructor(target){
	 			this.container=document.querySelector*target
	 		}
	 	}
	 </script>
	 

</body>
</html>