<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
	</div>
		
	<div class="start-test">
		<progress max="12" class="bar-progress"></progress>
		<h1></h1>
		<h1 id="question-text"></h1>
		<form id="test-form">
			<input class="answerBtn" type="button" value="yes" onclick="submitAnswer('yes')">
			<input class="answerBtn" type="button" value="no" onclick="submitAnswer('no')">
		</form>
	</div>

	<div class="finish-test">
		<h1>모든 질문이 끝났습니다!</h1>
		<h1 id="question-text"></h1>
		<form id="test-form">
			<input class="answerBtn" type="button" onclick="showResult()" value="제출하기">
		</form>
	</div>

	<script type="text/javascript">
		// 질문 리스트 정의
		const questionList = [
			  { id: 1, title: "나는 여러사람들과 같이 하는 게임보다 혼자 할수있는 게임을 좋아한다", section: "SN" },
			  { id: 2, title: "나는 게임에서 만난 사람과 친해지는 것이 힘들다", section: "SN" },
			  { id: 3, title: "나는 게임에서 호흡이 좋았던 사람으로부터 친구 추가가 오면 받기가 부담스럽다", section: "SN" },
			  { id: 4, title: "나는 게임이 잘 안풀릴때 공략을 찾아보는 편이다", section: "QM" },
			  { id: 5, title: "나는 지금 메타에서 성능이 좋은 캐릭터만 키우는 편이다", section: "QM" },
			  { id: 6, title: "나는 내가 키우던 캐릭터가 밸런스 패치로 인해 성능이 안좋아지면 버리는편이다", section: "QM" },
			  { id: 7, title: "나는 게임에서 내가 오더 내리는걸 좋아한다", section: "RE" },
			  { id: 8, title: "나는 팀원들이 각자 개인행동을 하면 화가 난다", section: "RE" },
			  { id: 9, title: "나는 중재를 잘하는 편이다", section: "RE" },
			  { id: 10, title: "나는 게임의 승패유무와 상관없이 내용이 재밌으면 지던 이기던 상관없다", section: "TC" },
			  { id: 11, title: "나는 팀원이 게임을 못하면 화가 난다", section: "TC" },
			  { id: 12, title: "나는 내 티어와 승률에 집착하는 편이다", section: "TC" }
		];

		// 로컬 스토리지에서 questionList 읽어오기
		const storedQuestionList = JSON.parse(localStorage.getItem('questionList')) || questionList;
		
		const progress = document.querySelector('.bar-progress'); 
		const beforeTest = document.querySelector('.intro-container'); // 테스트 시작 div
		const startTest = document.querySelector('.start-test'); // 테스트 div
		const finishTest = document.querySelector('.finish-test'); // 테스트 종료 div
		const testText = document.getElementById('question-text');
		
		let progressValue = 0; // 문제 인덱스
		
		// 대답 배열
		var answerArray = [];

		// 페이지 진입
		function enterPage() {
			startTest.style.display = 'none';		
			finishTest.style.display = 'none';		
		}
		
		// 테스트 시작
		function startTestPage() {
			beforeTest.style.display = 'none';
			finishTest.style.display = 'none';
			startTest.style.display = 'block';
			progress.value = 1;
			testText.innerText = storedQuestionList[progressValue].title;
		}
		
		// 답변 제출 처리
		function submitAnswer(answer) {
			if (progress.value === 12) {
				beforeTest.style.display = 'none';
				startTest.style.display = 'none';
				finishTest.style.display = 'block';
				localStorage.setItem('answerArray', JSON.stringify(answerArray));
				testText.innerText = "모든 질문이 끝났습니다";
				return;
			}
			
			// 진행도 , 문제 진행도 추가
			progress.value++;
			progressValue++;
			
			// 문제 텍스트 변경
			if (progressValue < storedQuestionList.length) {
				testText.innerText = storedQuestionList[progressValue].title;
				answerArray[progressValue - 1] = { answer: answer };
				console.log(answer);
			}
		}
		
		function showResult() {
			var form = document.createElement('form');
			form.setAttribute('method', 'post');
	         form.setAttribute("charset", "UTF-8");
			form.setAttribute('action', '/test/show-result');
			
			var data = document.createElement('input');
			data.setAttribute('type', 'hidden');
			data.setAttribute('name', 'answerArray');
			data.setAttribute('value', JSON.stringify(answerArray));
			form.appendChild(data);
			
			console.log(JSON.stringify(answerArray));
			
			document.body.appendChild(form);
			form.submit();
		}

		enterPage();
	</script>

</body>
</html>
