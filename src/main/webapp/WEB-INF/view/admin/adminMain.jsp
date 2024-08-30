<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

p{
	margin: 0;
	padding: 0;
}

.container {
	display: flex;
	flex-direction: column;
	height: 100vh;
}

.header {
	display:flex;
	justify-content: space-between;
	background-color: #333;
	align-items:flex-end;
	color: #fff;
	padding: 10px;
	text-align: center;
	flex-shrink: 0;
	height: 120px;
}

.admin-title{
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
	height:100vh;
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

.header-menu{
	display: flex;
	flex-direction: row;
}

.btn-open-modal-alarm,
.btn-open-modal-qna,
.btn-open-modal-report{
	display: flex;
	flex-direction: column;
	align-items: flex-end;
	margin: 2px;
	font-family: "Jua", sans-serif;
	font-weight: 400;
	font-style: normal;
}


.header img{
	float: left;
	width: 300px;
	height: 120px;
}

.main-canvas{
	display: flex;
	
}

ul{
	display: flex;
	flex-direction: column;
	height: 100vh;
	padding: 0;
}

li{
	list-style: none;
	padding: 10px;
	background-color: #dddddd;
	text-align: center;
	align-content: center;
	border-bottom: 1px solid #000;
	border-top: 1px solid #000;
}

li a{
	text-decoration: none; 
}


h2{
	text-align: center;
}

h1{
	width: 250px;
	font-family: "Jua", sans-serif;
	font-weight: 400;
	font-style: normal;
}

.modal-alarm,
.modal-qna,
.modal-report{
	position:absolute;
	display:none;
	justify-content: center;
	top:0;
	left:0;
	bottom: 0;
	width:100%;
	height:100%;
	background-color: rgba(0,0,0,0.4);
}

.close-btn-alarm,
.close-btn-qna,
.close-btn-report{
	font-family: "Jua", sans-serif;
	font-weight: 400;
	font-style: normal;
}

.modal-body{
			flex-direction: column;
            position:absolute;
            top:50%;  
            width:500px;  
            height:700px;  
            padding:40px;  
            text-align: center;
            background-color: rgb(255,255,255); 
            border-radius:10px;  
            box-shadow:0 2px 3px 0 rgba(34,36,38,0.15);  
            transform:translateY(-50%);  
        }
.modal_body-content{
	height: 100%;
}
        
        
.footer{
	background-color: orange;	
	height: 6rem;
	left: 0;
    right: 0;
    bottom: 0;
	align-content: center;
}

.footer p{
	text-align: center;
}

.statistics-user-box {
	display: flex;
	justify-content:space-around;
}

.number-of-user-box {
	background-color: #FFB700;
	border-radius: 1em;
	padding:10px;
	margin: 3px;
	width: 15%;
}

.main-canvas{
	display: flex;
	width: 100%;
	
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
				<h2>대시 보드</h2>
				<p>유저</p>
				<div class="statistics-user-box">
					<div class="number-of-user-box">
						<div class="number-of-user-content">
							<h1>${numberOfUser}</h1>
							<p>회원수</p>
						</div>
						<div class="number-of-user-info-box">
							<a href="dashboard/user" class="btn-info">자세히 보기</a>
						</div>
					</div>

					<div class="number-of-user-box">
						<div class="number-of-user-content">
							<h1>200</h1>
							<p>매칭수</p>
						</div>
						<div class="number-of-user-info-box">
							<a href="dashboard/matching" class="btn-info">자세히 보기</a>
						</div>
					</div>
					<div class="number-of-user-box">
						<div class="number-of-user-content">
							<h1>200 / 10</h1>
							<p>회원가입/탈퇴수</p>
						</div>
						<div class="number-of-user-info-box">
							<a href="dashboard/userRegistrations" class="btn-info">자세히 보기</a>
						</div>
					</div>
					<div class="number-of-user-box">
						<div class="number-of-user-content">
							<h1>5123</h1>
							<p>방문수</p>
						</div>
						<div class="number-of-user-info-box">
							<a href="dashboard/visitor" class="btn-info">자세히 보기</a>
						</div>
					</div>
				</div>

				<p>캐쉬</p>
				<div class="statistics-user-box">
					<div class="number-of-user-box">
						<div class="number-of-user-content">
							<h1>${cashUseRate}%</h1>
							<p>캐쉬 사용률</p>
						</div>
						<div class="number-of-user-info-box">
							<a href="dashboard/cashUtilization" class="btn-info">자세히 보기</a>
						</div>
					</div>

					<div class="number-of-user-box">
						<div class="number-of-user-content">
							<h1>${numberOfChargeCash}</h1>
							<p>캐쉬충전액</p>
						</div>
						<div class="number-of-user-info-box">
							<a href="dashboard/charge" class="btn-info">자세히 보기</a>
						</div>
					</div>
					<div class="number-of-user-box">
						<div class="number-of-user-content">
							<h1>500000</h1>
							<p>캐쉬환전액</p>
						</div>
						<div class="number-of-user-info-box">
							<a href="dashboard/exchange" class="btn-info">자세히 보기</a>
						</div>
					</div>
					<div class="number-of-user-box">
						<div class="number-of-user-content">
							<h1>200000</h1>
							<p>캐쉬 환불액</p>
						</div>
						<div class="number-of-user-info-box">
							<a href="dashboard/refund" class="btn-info">자세히 보기</a>
						</div>
					</div>
				</div>
				
				<p>광고 및 게시판</p>
				<div class="statistics-user-box">
					<div class="number-of-user-box">
						<div class="number-of-user-content">
							<h1>400000</h1>
							<p>광고수익</p>
						</div>
						<div class="number-of-user-info-box">
							<a href="dashboard/cashUtilization" class="btn-info">자세히 보기</a>
						</div>
					</div>

					<div class="number-of-user-box">
						<div class="number-of-user-content">
							<h1>142</h1>
							<p>게시글 작성수</p>
						</div>
						<div class="number-of-user-info-box">
							<a href="dashboard/charge" class="btn-info">자세히 보기</a>
						</div>
					</div>
					<div class="number-of-user-box">
						<div class="number-of-user-content">
							<h1>430</h1>
							<p>댓글 작성수</p>
						</div>
						<div class="number-of-user-info-box">
							<a href="dashboard/exchange" class="btn-info">자세히 보기</a>
						</div>
					</div>
				</div>


				<div class="main-canvas">
					<div style="position: relative; height: 35vh; width:50%">
						<canvas id="myChart"></canvas>
					</div>
					<div style="position: relative; height: 35vh; width: 50%">
						<canvas id="myChart2"></canvas>
					</div>
				</div>




				<script>
					const ctx1 = document.getElementById('myChart').getContext('2d');
					const ctx2 = document.getElementById('myChart2').getContext('2d');

					new Chart(ctx1, {
						type: 'line',
						data: {
							labels: ['2024-08-18', '2024-08-19', '2024-08-20', '2024-08-21', '2024-08-22', '2024-08-23'],
							datasets: [
							{
								label: '캐쉬 충전량',
								data: [152, 201, 30, 51, 166, 250],
								borderWidth: 1
							},
							{
								label: '캐쉬 사용량',
								data: [52, 121, 120, 21, 106, 150],
								borderWidth: 1
							},
							]
						},
						options: {
							scales: {
								y: {
									beginAtZero: true
								}
							}
						}
					});

					new Chart(ctx2, {
						type: 'bar',
						data: {
							labels: ['2024-08-18', '2024-08-19', '2024-08-20', '2024-08-21', '2024-08-22', '2024-08-23'],
							datasets: [
								{
								label: '회원 가입수',
								data: [152, 201, 30, 131, 166, 250],
						        backgroundColor: [
						                'rgba(255, 99, 132, 0.2)',
						                'rgba(54, 162, 235, 0.2)',
						                'rgba(255, 206, 86, 0.2)',
						                'rgba(75, 192, 192, 0.2)',
						                'rgba(153, 102, 255, 0.2)',
						                'rgba(255, 159, 64, 0.2)'
						            ],
						            borderColor: [
						                'rgba(255, 99, 132, 1)',
						                'rgba(54, 162, 235, 1)',
						                'rgba(255, 206, 86, 1)',
						                'rgba(75, 192, 192, 1)',
						                'rgba(153, 102, 255, 1)',
						                'rgba(255, 159, 64, 1)'
						            ],
						        borderWidth: 1
							},
							 {
						        label: '회원 탈퇴수',
						        data: [75, 182, 250, 31, 201, 35],
						        backgroundColor: [
						                'rgba(255, 99, 132, 0.2)',
						                'rgba(54, 162, 235, 0.2)',
						                'rgba(255, 206, 86, 0.2)',
						                'rgba(75, 192, 192, 0.2)',
						                'rgba(153, 102, 255, 0.2)',
						                'rgba(255, 159, 64, 0.2)'
						            ],
						            borderColor: [
						                'rgba(255, 99, 132, 1)',
						                'rgba(54, 162, 235, 1)',
						                'rgba(255, 206, 86, 1)',
						                'rgba(75, 192, 192, 1)',
						                'rgba(153, 102, 255, 1)',
						                'rgba(255, 159, 64, 1)'
						            ],
						        borderWidth: 1
						      }
							]
						},
						options: {
							scales: {
								y: {
									beginAtZero: true
								}
							}
						}
					});
					
				</script>
				
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