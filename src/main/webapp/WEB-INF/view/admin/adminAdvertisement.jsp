	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
	
	
	<link rel="stylesheet" href="/css/adminAdvertise.css">
	
	<%@ include file="/WEB-INF/view/layout/adminHeader.jsp"%>
				
		<p>현재 게시중인 게시글</p>
		
		
	<div class="advertise-list-content">
	    <div class="advertise-example-left">
	        <p>좌측</p>
	        <div class="advertise-one-line-box advertise-container">
	            <c:forEach var="advertise" items="${advertiseListOne}">
	                <div class="advertise-box-one" data-id="${advertise.id}">
	                    <div class="advertise-img-one">
	                        <img src="/static/images/advertise/${advertise.uploadFileName}" alt="광고사진">
	                    </div>
	                </div>
	            </c:forEach>
	        </div>
	    </div>
	
	    <div class="advertise-example-center">
	        <p>중앙</p>
	        <div class="advertise-one-line-box advertise-container">
	            <c:forEach var="advertise" items="${advertiseListTwo}">
	                <div class="advertise-box-two" data-id="${advertise.id}">
	                    <div class="advertise-img-two">
	                        <img src="/static/images/advertise/${advertise.uploadFileName}" alt="광고사진">
	                    </div>
	                </div>
	            </c:forEach>
	        </div>
	    </div>
	
	    <div class="advertise-example-right">
	        <p>우측</p>
	        <div class="advertise-one-line-box advertise-container">
	            <c:forEach var="advertise" items="${advertiseListThree}">
	                <div class="advertise-box-three" data-id="${advertise.id}">
	                    <div class="advertise-img-three">
	                        <img src="/static/images/advertise/${advertise.uploadFileName}" alt="광고사진">
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
	
		<div>광고 추가</div>
		<form action="/admin/advertise/insert-advertise" method="post" class="advertise" enctype="multipart/form-data">
			<div class="form-group">
				<label for="placeId">위치 :</label> <input type="number" id="placeId" name="placeId" value="1">
			</div>
			<div class="form-group">
				<label for="title">광고명 :</label> <input type="text" id="title" name="title" value="예나의 화장품 광고">
			</div>
			<div class="form-group">
				<label for="customer">광고주 :</label> <input type="text" id="customer" name="customer" value="최예나">
			</div>
			<div class="form-group">
				<label for="link">링크 :</label> 
				<input type="text" id="link" name="link" value="http://sssssss.sssss">
			</div>
			<div class="form-group">
		        <label for="file" class="file-input">광고파일</label> 
		        <input type="file" id="file" name="file" class="file-input">
		    </div>
			<div class="form-group">
				<label for="startDate">시작시간 :</label> <input type="text" id="startDate" name="startDate" value="2024-09-01">
			</div>
			<div class="form-group">
				<label for="endDate">종료시간 :</label> <input type="text" id="endDate" name="endDate" value="2024-09-06">
			</div>
			<div class="form-group">
				<label for="status">현재상태 :</label> <input type="text" id="status" name="status" value="0">
			</div>
			<button type="submit">광고추가</button>
		</form>
	
		<div>광고 수정</div>
		<form action="advertise/update-advertise" method="post" class="advertise">
			<div class="form-group">
				<label for="id">수정할 광고 id :</label> <input type="number" id="id" name="id" value="1">
			</div>
			<div class="form-group">
				<label for="placeId">위치 :</label> <input type="number" id="placeId" name="placeId" value="2">
			</div>
			<div class="form-group">
				<label for="title">광고명 :</label> <input type="text" id="title" name="title" value="예나의 치킨 광고">
			</div>
			<div class="form-group">
				<label for="customer">광고주 :</label> <input type="text" id="customer" name="customer" value="최예나">
			</div>
			<div class="form-group">
				<label for="link">링크 :</label> 
				<input type="text" id="link" name="link" value="http://sssssss.sssss">
			</div>
			<div class="form-group">
				<label for="startDate">시작시간 :</label> <input type="text" id="startDate" name="startDate" value="2024-09-01">
			</div>
			<div class="form-group">
				<label for="endDate">종료시간 :</label> <input type="text" id="endDate" name="endDate" value="2024-09-06">
			</div>
			<div class="form-group">
				<label for="status">현재상태 :</label> <input type="text" id="status" name="status" value="0">
			</div>
			<button type="submit">광고수정</button>
		</form>
	
		
	
	
	
	
		<script>
			document.addEventListener('DOMContentLoaded', () => {
			    // 모든 광고 박스 컨테이너를 선택
			    const advertiseContainers = document.querySelectorAll('.advertise-container');
		
			    advertiseContainers.forEach(container => {
			        const ads = container.querySelectorAll('.advertise-box-one, .advertise-box-two, .advertise-box-three');
			        let currentIndex = 0;
		
			        function showNextAd() {
			            // 현재 활성 광고를 숨김
			            ads.forEach(ad => ad.classList.remove('active'));
		
			            // 다음 광고로 이동
			            currentIndex = (currentIndex + 1) % ads.length;
		
			            // 다음 광고를 표시
			            ads[currentIndex].classList.add('active');
			        }
		
			        // 첫 번째 광고를 표시
			        if (ads.length > 0) {
			            ads[0].classList.add('active');
			        }
		
			        // 2초마다 광고를 변경
			        setInterval(showNextAd, 2000);
			    });
			});
		</script>
	
	
	
		<%@ include file="/WEB-INF/view/layout/adminFooter.jsp"%>