	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
	
	
	<link rel="stylesheet" href="/css/adminAdvertise.css">
	
	<%@ include file="/WEB-INF/view/layout/adminHeader.jsp"%>
		
	<nav class="advertise-nav">
		<ul class="advertise-nav">
			<li>
				<button class="list active" onclick="showList()">목록 조회</button>
				<button class="add" onclick="showAdd()">추가</button>
				<button class="update" onclick="showUpdate()">수정</button>
			</li>
		</ul>
	</nav>
				
		
	<div id="advertise-list">
	<br>
	<p>현재 게시중인 광고 미리보기</p>
	<br>	
		<div class="advertise-list-content">
	    <div class="advertise-example-left">
	        <p>좌측</p>
	        <br>
	        <div class="advertise-one-line-box advertise-container">
	            <c:forEach var="advertise" items="${advertiseListOne}">
	                <div class="advertise-box-one" data-id="${advertise.id}">
	                    <div class="advertise-img-one">
		                    <a href="${advertise.link}" target='_blank'>
		                        <img src="/static/images/advertise/${advertise.uploadFileName}" alt="광고사진">
		                    </a>
	                    </div>
	                </div>
	            </c:forEach>
	        </div>
	    </div>
	
	    <div class="advertise-example-center">
	        <p>중앙</p>
	        <br>
	        <div class="advertise-one-line-box advertise-container">
	            <c:forEach var="advertise" items="${advertiseListTwo}">
	                <div class="advertise-box-two" data-id="${advertise.id}">
	                    <div class="advertise-img-two">
	                    	 <a href="${advertise.link}" target='_blank'>
	                        	<img src="/static/images/advertise/${advertise.uploadFileName}" alt="광고사진">
	                        </a>
	                    </div>
	                </div>
	            </c:forEach>
	        </div>
	    </div>
	
	</div>
	
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
						<th>관리</th>
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
							<td><form action="advertise/delete-advertise" method="post">
									<input type="hidden" id="id" name="id" value="${advertise.id}">
									<button type="submit">삭제</button>
								</form></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	
	</div>
	
	<div id="advertise-add">
		<div class="function--box">
		  <h2>광고 추가</h2>
		<form action="/admin/advertise/insert-advertise" method="post" class="advertise" enctype="multipart/form-data">
			<div class="form-group">
				<label for="placeId">위치 : </label>
					<select name="placeId" id="placeId">
							<option value="1">좌측</option>
							<option value="2">중앙</option>
					</select> 
			</div>
			<div class="form-group">
				<label for="title">광고명 : </label> <input type="text" id="title" name="title" value="예나의 화장품 광고">
			</div>
			<div class="form-group">
				<label for="customer">광고주 : </label> <input type="text" id="customer" name="customer" value="최예나">
			</div>
			<div class="form-group">
				<label for="link">링크 : </label> 
				<input type="text" id="link" name="link" value="http://sssssss.sssss" placeholder="연결할 링크를 입력해주세요">
			</div>
			<div class="form-group">
		        <label for="file" class="file-input">광고파일 </label> 
		        <input type="file" id="file" name="file" class="file-input">
		    </div>
			<div class="form-group">
				<label for="startDate">시작시간 : </label>
				<input type="date" id="startDate" name="startDate" 
					max="2030-12-31"
			        min="2024-09-20"
			        value="2024-09-20">
			</div>
			<div class="form-group">
				<label for="endDate">종료시간 : </label>
				<input type="date" id="endDate" name="endDate" 
					max="2030-12-31"
			        min="2024-09-21"
			        value="2024-09-21">
			</div>
			<div class="form-group">
				<label for="status">즉시게시여부 : </label>
				<select name="status" id="status">
							<option value="0">저장만</option>
							<option value="1">즉시게시</option>
					</select> 
			</div>
			<button type="submit">광고추가</button>
		</form>
	</div>
	</div>
	
	<div id="advertise-update">
	<div class="function--box">
		<h2>광고 수정</h2>
		<form action="advertise/update-advertise" method="post" class="advertise">
			<div class="form-group">
				<label for="id">수정할 광고 id :</label> 
				<select name="id" id="id">
				<c:forEach var="advertise" items="${advertiseList}">
							<option value="${advertise.id}">${advertise.id} - ${advertise.title}</option>
				</c:forEach>
					</select> 
			</div>
			<div class="form-group">
				<label for="placeId">위치 : </label>
					<select name="placeId" id="placeId">
							<option value="1">좌측</option>
							<option value="2">중앙</option>
					</select> 
			</div>
			<div class="form-group">
				<label for="title">광고명 :</label> 
				<input type="text" id="title" name="title" value="예나의 치킨 광고">
			</div>
			<div class="form-group">
				<label for="customer">광고주 :</label> 
				<input type="text" id="customer" name="customer" value="최예나">
			</div>
			<div class="form-group">
				<label for="link">링크 :</label> 
				<input type="text" id="link" name="link" value="http://sssssss.sssss">
			</div>
			<div class="form-group">
				<label for="startDate">시작시간 : </label>
				<input type="date" id="startDate" name="startDate" 
					max="2030-12-31"
			        min="2024-09-20"
			        value="2024-09-20">
			</div>
			<div class="form-group">
				<label for="endDate">종료시간 : </label>
				<input type="date" id="endDate" name="endDate" 
					max="2030-12-31"
			        min="2024-09-21"
			        value="2024-09-21">
			</div>
			<div class="form-group">
				<label for="status">즉시게시여부 : </label>
				<select name="status" id="status">
							<option value="0">저장만</option>
							<option value="1">즉시게시</option>
					</select> 
			</div>
			<button type="submit">광고수정</button>
		</form>
		</div>
	
	</div>
	
		
		
	
	
	
	
		<script>
			document.addEventListener('DOMContentLoaded', () => {
				
				// 페이지가 완전히 로드 된 후 스크립트가 실행되도록 지정
				// advertise-container 클래스의 요소를 선택(여러개)해서 advertiseContainers 변수에 저장
			    const advertiseContainers = document.querySelectorAll('.advertise-container');
				
				// forEach 반복 작업 수행 
			    advertiseContainers.forEach(container => {
			    	// advertise-container안의 advertise-box-one, advertise-box-two 클래스의 전환될 광고 요소들을 선택해서
			        const ads = container.querySelectorAll('.advertise-box-one, .advertise-box-two');
			    	// 첫번째 광고를 0으로 설정
			        let currentIndex = 0;
		
			    	
			        function showNextAd() {	
			            // 모든 광고 active 제거 - > 광고 숨김
			            ads.forEach(ad => ad.classList.remove('active'));
		
			            // 다음 광고 인덱스를 계산 - 인덱스가 범위를 초과하지 않도록 지정 
			            currentIndex = (currentIndex + 1) % ads.length;
		
			            // 다음 광고에 active를 추가하여 광고 표시
			            ads[currentIndex].classList.add('active');
			        }
		
			        // 다시 첫번째 광고에 active 클래스를 추가하고 처음부터 광고 표시
			        if (ads.length > 0) {
			            ads[0].classList.add('active');
			        }
		
			        // 2초마다 광고를 변경
			        setInterval(showNextAd, 2000);
			    });
			});
			
			
			document.querySelectorAll('.advertise-nav button').forEach(button => {
			    button.addEventListener('click', function () {
			        document.querySelectorAll('.advertise-nav button').forEach(btn => btn.classList.remove('active'));
			        this.classList.add('active');
			    });
			});
			
			var list = document.getElementById("advertise-list");
			var add = document.getElementById("advertise-add");
			var update = document.getElementById("advertise-update");
			
			function showList(){
				console.log("aaaaaaaa");
				list.style.display="block";
				add.style.display="none";
				update.style.display="none";
			}
			
			function showAdd(){
				console.log("aaaaaaaa");
				list.style.display="none";
				add.style.display="block";
				update.style.display="none";
			}
			
			function showUpdate(){
				console.log("aaaaaaaa");
				list.style.display="none";
				add.style.display="none";
				update.style.display="block";
			}
			showList();
		</script>
	
	
	
		<%@ include file="/WEB-INF/view/layout/adminFooter.jsp"%>