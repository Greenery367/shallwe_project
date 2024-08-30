<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
</style>
</head>
<body>

	<div class="personal-test">
		<div class="intro-container">
			<h1>나의 게임 성향을 알아보는 MBTI 테스트</h1>
			<button class='start-btn' onclick="startTestPage()">테스트 시작</button>
		</div>
		
		<div class="start-test">
			<progress max="12" value="0" class="bar-progress"></progress>
			<h1>${question.question}</h1>
			<input class="answerBtn" id="0" type="submit" value="yes"></button>
			<input class="answerBtn" type="submit" value="no"></button>
		</div>
		
		<div class="result-page">			
			<div class="my-result">
				<h1>당신의 성향은...</h1>
				<h2>SQRC : 장군</h2>
				<h3>팀원들을 조율하고 직접 오더를 내리는것을 좋아한다.</h3>
				<h3>남들과 똑같은 방식으로 이기는것보다 자신의 팀만의 색깔로 이기는것이 중요함!!</h3>
			</div>
			
			<div class="good-matched">
				<h4>나와 잘 맞는 사람은...</h4>
				<h5>상인</h5>
				<p>계산적이며 게임의 정보를 공부하는것을 좋아함 하지만 승부에 연연하지는 않는 지식을 탐구하는자!!</p>
			</div>
			
			<div class="bad-matched">
				<h4>나와 잘 맞지 않는 사람은...</h4>
				<h5>마왕</h5>
				<p>이기기위해 모든 최선을 다하며 팀원들을 승리의 길로 이끈다!!</p>
			</div>
		</div>
	</div>
	
	
	<!---------------------------------------------------- -->
	
	<script type="text/javascript">
		const progress=document.querySelector('.bar-progress'); 
		const beforeTest = document.querySelector('.intro-container'); // 테스트 시작 div
		const startTest = document.querySelector('.start-test'); // 테스트 div
		const finishTest = document.querySelector('.result-page'); // 테스트 결과 div
		const startBtn = document.querySelector('.start-btn'); // 테스트 시작 버튼
		
		function enterPage(){
			startTest.style.display='none';
			finishTest.style.display='none';
			progress.value+=1;
		}
		
		function startTestPage(){
			beforeTest.style.display='none';
			startTest.style.display='block';
			surveyProgress=1;
			progress.value+=1;
		}
		
		function resultPage(){
			beforeTest.style.display='none';
			startTest.style.display='none';
			finishTest.style.display='block';
		}
		
		
		
		
		enterPage();
	</script>
	
</body>
</html>
