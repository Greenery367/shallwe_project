<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
<head>
   <link href="https://fonts.googleapis.com/css2?family=Lato&display=swap" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<style>
body {
	margin: 0;
	font-family: Arial, sans-serif;
}

.container {
	display: flex;
	flex-direction: column;
	height: 100vh;
}

.header {
	background-color: #333;
	color: #fff;
	padding: 10px;
	text-align: center;
	flex-shrink: 0;
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
	float: right;
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

.modal-alarm{
	position:absolute;
	display:none;
	justify-content: center;
	top:0;
	left:0;
	width:100%;
	height:100%;
	background-color: rgba(0,0,0,0.4);
}

.modal-qna{
	position:absolute;
	display:none;
	justify-content: center;
	top:0;
	left:0;
	width:100%;
	height:100%;
	background-color: rgba(0,0,0,0.4);
}

.modal-report{
	position:absolute;
	display:none;
	justify-content: center;
	top:0;
	left:0;
	width:100%;
	height:100%;
	background-color: rgba(0,0,0,0.4);
}

.modal_body{
			display:flex;
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


</style>
</head>
<body>
	<div class="container">
		<div class="header">
			<h1>관리자 페이지</h1>
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
					<li><a href="advertisement">광고 / 배너</a></li>
					<li><a href="category">카테고리 / 게시판</a></li>
					<li><a href="cash">캐쉬</a></li>
					<li><a href="support">고객 지원</a></li>
					<li><a href="notice">뉴스 / 공지</a></li>
				</ul>
			</div>
			<div class="content">
				<h2>대시 보드</h2>
				<p>통계</p>
				<div class="main-canvas">
					<div style="position: relative; height:25vh; width:30vw">
						<canvas id="myChart"></canvas>
					</div>
					<div style="position: relative; height:25vh; width:30vw">
						<canvas id="myChart2"></canvas>
					</div>
					<div style="position: relative; height:50vh; width:40vw">
						<canvas id="myChart3"></canvas>
					</div>
				</div>
				
				
				<script>
					const ctx1 = document.getElementById('myChart').getContext('2d');
					const ctx2 = document.getElementById('myChart2').getContext('2d');
					const ctx3 = document.getElementById('myChart3').getContext('2d');

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
					
					new Chart(ctx3, {
						type: 'doughnut',
						data: {
							labels: ['1', '2', '3', '4', '5', '6'],
							datasets: [{
								label: '유형별 인원수',
								data: [152, 201, 30, 51, 166, 250],
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
							}]
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
		        	<div class="modal_body">
		            	<h2>알림</h2>
		            	<p>알림내용 </p>
		           	<button class="close-btn-alarm">닫기</button>
		        	</div>
		    	</div>
		    	
		    	<div class="modal-qna">
		        	<div class="modal_body">
		            	<h2>Q&A</h2>
		            	<p>Q&A내용 </p>
		           	<button class="close-btn-qna">닫기</button>
		        	</div>
		    	</div>
		    	
		    	<div class="modal-report">
		        	<div class="modal_body">
		            	<h2>신고</h2>
		            	<p>신고내용 </p>
		           	<button class="close-btn-report">닫기</button>
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
		</div>
	</div>
</body>
</html>