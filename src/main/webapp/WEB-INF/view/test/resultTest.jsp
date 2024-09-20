<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/test.css">
</head>
<body>
	<header>
		<div class="account-menus">
			<div class="menu-container">
				<div class="to-home">
					<img class="logo" alt="로고" src="../static/images/shallwe-icon.png">
					<a href="/test/main"><b>홈</b></a>
				</div>
				<p>|</p>
				<a href="/user/sign-in"><b>로그인</b></a>
				<p>|</p>
				<a href="/user/sign-up"><b>회원가입</b></a>
				</div>
			</div>
			</div>
		</div>
	</header>
	<div class="result-container">
		<div class="result-page">	
		
				<h1>게임 성향 테스트 결과</h1>		
			<div class="my-result">
				<h1 class="title">당신의 성향은...</h1>
				<img class="my-mbti my-icon">
				<h2>${resultMbti.name} : ${resultMbti.nickname}</h2>
				<h3>${resultMbti.content}</h3>
			</div>
			<div class="matching-container">
				<div class="good-matched">
					<h4 class="title">나와 잘 맞는 사람은...</h4>
					<img class="mini well-matched" src="../static/images/icon.png">
					<h5>${goodMatchedMbti.name} : ${goodMatchedMbti.nickname}</h5>
					<p>${goodMatchedMbti.content}</p>
				</div>
			
				<div class="bad-matched">
					<h4 class="title">나와 잘 맞지 않는 사람은...</h4>
					<img class="mini bad-matched" src="../static/images/icon.png">
					<h5>${badMatchedMbti.name} : ${badMatchedMbti.nickname}</h5>
					<p>${badMatchedMbti.content}</p>
				</div>
			</div>
			<div class="button-list">
				<button class='start-btn' onclick="location.href='../user/sign-up'"><b>나와 잘 맞는 친구 만나러 가기→</b></button>
				<button class='start-btn' onclick="location.href='/test/start-test'"><b>테스트 다시 하기→</b></button>
				<button class='share-btn' onclick="location.href='#''"><b>SNS 공유하기→</b></button>
			</div>
		</div>
	</div>
	<footer>
			<div>
				<a class="footer-menu" href="">회사소개</a>
				<a class="footer-menu" href="">제휴안내</a>
				<a class="footer-menu" href="">광고안내</a>
				<a class="footer-menu" href="">이용약관</a>
				<a class="footer-menu" href="">개인정보처리방침</a>
				<a class="footer-menu" href="">청소년보호정책</a>
			</div>
		<p>copyrightⓒ 2024-2024 shallwe. All right reserved.</p>
	</footer>
<script>
	const mbti = document.getElementById('question-text');
	
	let myImg = document.querySelector(".my-result > img");
	let wellImg = document.querySelector(".good-matched > img");
	let badImg = document.querySelector(".bad-matched > img");
	
	myImg.src = "/static/images/icons/${resultMbti.nickname}.png";
	wellImg.src = "/static/images/icons/${goodMatchedMbti.nickname}.png";
	badImg.src = "/static/images/icons/${badMatchedMbti.nickname}.png";
	 

	
	
</script>
</body>
</html>