<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://fonts.googleapis.com/css2?family=Lato&display=swap" rel="stylesheet">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<style>
body {
	margin: 0;
	font-family: "Jua", sans-serif;
	font-weight: 400;
	font-style: normal;
}

p {
	margin: 0;
	padding: 0;
}

.container {
	display: flex;
	flex-direction: column;
	height: 100vh;
}

.header {
	display: flex;
	justify-content: space-between;
	background-color: #333;
	align-items: flex-end;
	color: #fff;
	padding: 10px;
	text-align: center;
	flex-shrink: 0;
	height: 120px;
}

.admin-title {
	display: flex;
	justify-content: center;
	align-items: center;
	height: 120px;
}

.main-content {
	display: flex;
	flex: 1;
}

.sidebar {
	background-color: #f4f4f4;
	width: 200px;
	padding: 15px;
	flex-shrink: 0;
}

.content {
	background-color: #fff;
	flex: 1;
	padding: 15px;
	height: 100vh;
	overflow-y: auto;
}

.header a {
	color: #fff;
	text-decoration: none;
	margin: 0 15px;
}

.header a:hover {
	text-decoration: underline;
}

.header-menu {
	display: flex;
	flex-direction: row;
}

.btn-open-modal-alarm, .btn-open-modal-qna, .btn-open-modal-report {
	display: flex;
	flex-direction: column;
	align-items: flex-end;
	margin: 2px;
	font-family: "Jua", sans-serif;
	font-weight: 400;
	font-style: normal;
}

.header img {
	float: left;
	width: 300px;
	height: 120px;
}

.main-canvas {
	display: flex;
}

ul {
	display: flex;
	flex-direction: column;
	height: 100vh;
	padding: 0;
}

li {
	list-style: none;
	padding: 10px;
	background-color: #dddddd;
	text-align: center;
	align-content: center;
	border-bottom: 1px solid #000;
	border-top: 1px solid #000;
}

li a {
	text-decoration: none;
}

h2 {
	text-align: center;
}

h1 {
	width: 250px;
	font-family: "Jua", sans-serif;
	font-weight: 400;
	font-style: normal;
}

.modal-alarm, .modal-qna, .modal-report {
	position: absolute;
	display: none;
	justify-content: center;
	top: 0;
	left: 0;
	bottom: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.4);
}

.close-btn-alarm, .close-btn-qna, .close-btn-report {
	font-family: "Jua", sans-serif;
	font-weight: 400;
	font-style: normal;
}

.modal-body {
	flex-direction: column;
	position: absolute;
	top: 50%;
	width: 500px;
	height: 700px;
	padding: 40px;
	text-align: center;
	background-color: rgb(255, 255, 255);
	border-radius: 10px;
	box-shadow: 0 2px 3px 0 rgba(34, 36, 38, 0.15);
	transform: translateY(-50%);
}

.modal_body-content {
	height: 100%;
}

.footer {
	background-color: orange;
	height: 6rem;
	left: 0;
	right: 0;
	bottom: 0;
	align-content: center;
}

.footer p {
	text-align: center;
}

.statistics-user-box {
	display: flex;
	justify-content: space-around;
}

.number-of-user-box {
	background-color: #FFB700;
	border-radius: 1em;
	padding: 10px;
	margin: 3px;
	width: 15%;
}

.main-canvas {
	display: flex;
	width: 100%;
}

.table {
	border: 1px solid;
}
</style>
</head>
<body>
	<div class="container">
		<div class="header">
			<div>
				<img src="../images/logo.png" alt="로고">
			</div>
			<div class="admin-title">
				<h1>관리자 페이지</h1>
			</div>
			<div class="header-menu">
				<button class="btn-open-modal-alarm">알림</button>
				<button class="btn-open-modal-qna">Q&A</button>
				<button class="btn-open-modal-report">신고내역</button>
			</div>
		</div>
		<div class="main-content">
			<div class="sidebar">
				<h2>관리 목록</h2>
				<ul>
					<li><a href="dashboard">대시보드</a></li>
					<li><a href="user">이용자 관리</a></li>
					<li><a href="cash">캐쉬</a></li>
					<li><a href="support">고객 지원</a></li>
					<li><a href="advertisement">광고 / 배너</a></li>
					<li><a href="category">카테고리 / 게시판</a></li>
					<li><a href="notice">뉴스 / 공지</a></li>
				</ul>
			</div>
			<div class="content">
				<div>
					<p>광고 리스트</p>
					<table class="table">
						<thead>
							<tr>
								<th>id</th>
								<th>위치</th>
								<th>광고명</th>
								<th>광고주</th>
								<th>파일링크</th>
								<th>시작시간</th>
								<th>종료시간</th>
								<th>상태</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="advertise" items="${advertiseList}">
								<tr>
									<td>${advertise.id}</td>
									<td>${advertise.placeId}</td>
									<td>${advertise.title}</td>
									<td>${advertise.customer}</td>
									<td>${advertise.link}</td>
									<td>${advertise.startDate}</td>
									<td>${advertise.endDate}</td>
									<td>${advertise.status}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

				</div>
				<div>광고 추가</div>
				<form action="advertisement/insertAdvertise" method="post">
					<div class="form-group">
						<label for="place_Id">위치 :</label> 
						<input type="number" id="place_Id" name="placeId" value="1">
					</div>
					<div class="form-group">
						<label for="title">광고명:</label> 
						<input type="text" id="title" name="title" value="예나의 화장품 광고">
					</div>
					<div class="form-group">
						<label for="customer">광고주:</label> 
						<input type="text" id="customer" name="customer" value="최예나">
					</div>
					<button type="submit">광고추가</button>
				</form>



				<div class="modal-alarm">
					<div class="modal-body">
						<div class="modal_body-content">
							<h2>알림</h2>
							<p>알림내용</p>
							<button class="close-btn-alarm">닫기</button>
						</div>
					</div>
				</div>

				<div class="modal-qna">
					<div class="modal-body">
						<h2>Q&A</h2>
						<p>Q&A내용</p>
						<button class="close-btn-qna">닫기</button>
					</div>
				</div>

				<div class="modal-report">
					<div class="modal-body">
						<h2>신고</h2>
						<p>신고내용</p>
						<button class="close-btn-report">닫기</button>
					</div>
				</div>
			</div>
			<script>
				        const modalAlarm = document.querySelector('.modal-alarm');
				        const modalQnA = document.querySelector('.modal-qna');
				        const modalReport = document.querySelector('.modal-report');
				        
				        const btnOpenModalAlarm=document.querySelector('.btn-open-modal-alarm');
				        const btnOpenModalQnA=document.querySelector('.btn-open-modal-qna');
				        const btnOpenModalReport=document.querySelector('.btn-open-modal-report');
				        
				        const btnCloseModalAlarm = document.querySelector('.close-btn-alarm');
				        const btnCloseModalQnA = document.querySelector('.close-btn-qna');
				        const btnCloseModalReport = document.querySelector('.close-btn-report');
				        
				        
				        btnOpenModalAlarm.addEventListener("click", ()=>{
				        	modalAlarm.style.display="flex";
				        });
				        
				        btnOpenModalQnA.addEventListener("click", ()=>{
				        	modalQnA.style.display="flex";
				        });
				        
				        btnOpenModalReport.addEventListener("click", ()=>{
				        	modalReport.style.display="flex";
				        });
				        
				        
				        btnCloseModalAlarm.addEventListener("click", () => {
				        	modalAlarm.style.display = "none";
				        });
				        
				        btnCloseModalQnA.addEventListener("click", () => {
				        	modalQnA.style.display = "none";
				        });
				        
				        btnCloseModalReport.addEventListener("click", () => {
				        	modalReport.style.display = "none";
				        });

				        window.addEventListener("click", (event) => {
				            if (event.target === modalAlarm) {
				            	modalAlarm.style.display = "none";
				            }
				        });
				        window.addEventListener("click", (event) => {
				            if (event.target === modalQnA) {
				            	modalQnA.style.display = "none";
				            }
				        });
				        window.addEventListener("click", (event) => {
				            if (event.target === modalReport) {
				            	modalReport.style.display = "none";
				            }
				        });
			    </script>

		</div>

		<footer>
			<div class="footer">
				<p>footer</p>
			</div>
		</footer>
	</div>
</body>
</html>