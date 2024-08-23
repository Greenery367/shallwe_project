<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
	body{
	width:100%;
	padding:0;
	margin:0;
	background-color: #3D4450;
	}
	a{
		text-decoration: none;
		padding: 5px;
		color: WHITE;
	}
	footer{
		width: 100%;
		height: 100px;
		color: white;
		text-align: center;
		background-color: #1D1E23;
		padding:50px 0;
	}
	
	.header{
		display: flex;
		align-items: center;
		height:50px;
		justify-content: center;
	}
	.account-menus{
		color: white;
		height:50px;
		width: 100%;
		padding:10px;
		margin-right: 300px;
		margin-top: 50px;
		display: flex;
		justify-content: right;
		align-items: center;
	}
	.banner{
		display: flex;
		align-items: center;
		height:250px;
		justify-content: center;
	}
	.logo{
		width: 400px;
		heigth: 150px;
	}
	.nav-bar{
		display:flex;
		height:80px;
		width:100%;
		justify-content:center;
		align-items:center;
		background: #FD6F22;
		box-shadow: 0px 8px 6px -6px #666;
	}
	.menus{
		height:80px;
		width:80%;
		display:flex;
		justify-content: space-around;
		align-items: center;
	}
	.menu{
		color: white;
		text-decoration: none;
		font-size: 20px;
	}
	.main-board{
		width: 100%;
		display: flex;
		justify-content: center;
	}
	.admin-main-1 {
		margin-top: 20px;
		margin-right: 20px;
		width: 180px;
		height: 350px;
		background: #FD6F22;
	}
	.admin-main-2{
		width: 750px;
		height: 250px;
		background-color: yellow;
		margin: 2%;
	}
	.recommend{
		width: 750px;
		height: 250px;
		background-color: yellow;
		margin: 2%;
		display: flex;
		flex-direction: column; 
		align-items: center;
	}
	.main-page{
		width: 900px;
		height: 1200px;
		background: gray;
		margin-top: 20px;
		margin-right: 20px;
		display: flex;
		flex-direction: column; 
		align-items: center;
	}
	.btn-circle{
		width: 100px;
		height: 100px;
		border-radius: 70%;
		background-color: red;
	}
	.pro-icon{
		border-radius:70%;
		width: 80px;
		heigth: 80px;
	}
	.post-board{
		display:flex;
		width: 750px
		justify-content: center;
	}
	.post-board-small{
		border: 1px solid black;
		padding: 10px;
		margin: 10px;
	 	border-radius: 5px;
		width: 340px;
		height: 200px;
	}
	.side-menu{
		width: 200px;
		display: flex;
		display-direction: center;
		justify-content: center;
		margin-top: 20px;
	}
	.footer-menu{
		text-decoration: none;
		color: white;
	}
</style>
<body>

<div class="main">
	<header>
		<div class="header">
			<div class="account-menus">
					<a href="">홈</a>
					<a href="">로그인</a>
					<a href="">회원가입</a>
				</div>
			</div>
		</div>
	</header>
				<div class="banner">
					<img class="logo" alt="로고" src="../images/logo.png">
				</div>
	
	<div class="nav-bar">
		<div class="menus">
			<a href="" class="menu">친구 찾기</a>
			<a href="" class="menu">게임 강의</a>
			<a href="" class="menu">커뮤니티</a>
			<p>|</p>
			<a href="" class="menu">뉴스</a>
			<a href="" class="menu">고객 지원</a>
			<a href="" class="menu">콘텐츠</a>
			<a href="" class="menu">회원 정보</a>
		</div>
	</div>
	
	<div class="main-board">
		<div class="admin-main-1"><img class="logo" alt="로고" src="../images/banner1.png"><p>광고란</p></div>
	
		<div class="main-page">
			<div class="admin-main-2"></div>
			<div class="recommend">
				<div>
					<h3> 나와 잘 맞는 사람 추천하기</h3>
				</div>
				<div class="btn">
					<button class="btn-circle"></button>
					<button class="btn-circle"></button>
					<button class="btn-circle"></button>
					<button class="btn-circle"></button>
					<button class="btn-circle"></button>
				</div>
			</div>
			
			<div class="recommend">
				<div>
					<h3> 인기 많은 강의 추천하기</h3>
				</div>
				<div class="btn">
					<button class="btn-circle"></button>
					<button class="btn-circle"></button>
					<button class="btn-circle"></button>
					<button class="btn-circle"></button>
					<button class="btn-circle"></button>
				</div>
		</div>
			<div class="post-board">
				<div class="post-board-small">
					<h4>공지사항</h4>
					<a href="">1</a>
					<a href="">2</a>
					<a href="">3</a>
					<a href="">4</a>
				</div>
				
				<div class="post-board-small">
					<h4>자유게시판</h4>
					<div>
						<a href="">1번 공지------------</a>
					</div>
					<div>
						<a href="">1번 공지------------</a>
					</div>
					<div>
						<a href="">1번 공지------------</a>
					</div>
					<div>
						<a href="">1번 공지------------</a>
					</div>
				</div>
			</div>
	</div>
	<div class="side-menu">
			<div class="my-profile">
				<img class="pro-icon" src="https://picsum.photos/id/237/200/300">
				<div><h4>칭호+</h4><h4>내 이름</h4></div>
				<h3>MBTI</h3>
				<h3>명성치: </h3>
				<a>마이 페이지</a>
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
</body>
</html>