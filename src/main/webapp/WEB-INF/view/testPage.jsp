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
	}
	a{
		text-decoration: none;
		padding: 5px;
	}
	
	.header{
		display: flex;
		align-items: center;
		height:50px;
		justify-content: center;
	}
	.account-menus{
		height:50px;
		width: 100%;
		padding:10px;
		margin-right: 100px;
		margin-top: 50px;
		display: flex;
		justify-content: right;
		align-items: center;
	}
	.banner{
		display: flex;
		align-items: center;
		height:200px;
		justify-content: center;
	}
	.nav-bar{
		display:flex;
		height:80px;
		width:100%;
		justify-content:center;
		align-items:center;
		background: #FD6F22;
	}
	.menus{
		height:80px;
		width:1600px;
		display:flex;
		justify-content: space-around;
		align-items: center;
	}
	.menu{
		color: white;
		text-decoration: none;
	}
	.main-board{
		width: 100%;
		display: flex;
		justify-content: center;
		display-direction: column;
		align-items:center;
		
	}
	.admin-main-1 {
		margin-top: 20px;
		margin-right: 20px;
		width: 180px;
		height: 500px;
		background: #FD6F22;
	}
	.admin-main-2{
		width: 600px;
		height: 190px;
		background-color: yellow;
	}
	.main-page{
		width: 650px;
		height: 700px;
		background: gray;
		margin-top: 20px;
		margin-right: 20px;
	}
	.pro-icon{
		border-radius:70%;
		width: 80px;
		heigth: 80px;
	}
	
	.side-menu{
		width: 200px;
		display: flex;
		display-direction: center;
		justify-content: center;
		align-items: center;
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
					<h1>셸위</h1>
				</div>
	
	<div class="nav-bar">
		<div class="menus">
			<a href="" class="menu">친구 찾기</a>
			<a href="" class="menu">게임 강의</a>
			<a href="" class="menu">커뮤니티</a>
			<a href="" class="menu">뉴스</a>
			<a href="" class="menu">고객 지원</a>
			<a href="" class="menu">콘텐츠</a>
			<a href="" class="menu">회원 정보</a>
		</div>
	</div>
	
	<div class="main-board">
		<div class="admin-main-1"><p>광고란</p></div>
	
		<div class="main-page">
			<div class="admin-main-2"></div>
			<div class>
				<div>
					<h3> 나와 잘 맞는 사람 추천하기</h3>
				</div>
				<button class="recomment"></button>
				<button class="recomment"></button>
				<button class="recomment"></button>
			</div>
			
			<div class>
				<div>
					<h3> 인기 많은 강의 추천하기</h3>
				</div>
				<button class="recomment"></button>
				<button class="recomment"></button>
				<button class="recomment"></button>
			</div>
			
			<div class="post-board">
				<div>
					<h4>공지사항</h4>
					<a href="">1</a>
					<a href="">2</a>
					<a href="">3</a>
					<a href="">4</a>
				</div>
				
				<div>
					<h4>자유게시판</h4>
					<a href="">1번 공지------------</a>
					<a href="">21번 공지------------</a>
					<a href="">31번 공지------------</a>
					<a href="">41번 공지------------</a>
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
	
	
</body>
</html>