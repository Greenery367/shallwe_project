<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>


<link rel="stylesheet" href="/css/adminAdvertise.css">

<%@ include file="/WEB-INF/view/layout/adminHeader.jsp"%>
			
			<div class="content">
				<div class="advertise-list-content">
				
				<p>현재 게시중인 게시글</p>
					<div class="advertise-one-line-box" id="advertise-container">
                        <c:forEach var="advertise" items="${advertiseListNow}">
                            <div class="advertise-box" data-id="${advertise.id}">
                                <div class="advertise-img">
                                    <img src="../static/images/advertise/yenaAdvertise1.jpg" alt="광고사진">
                                </div>
                                <div class="advertise-content">
                                    <div>id : ${advertise.id}</div>
                                    <div>제목 : ${advertise.title}</div>
                                    <div>광고주 : ${advertise.customer}</div>
                                    <div>시작시간 : ${advertise.startDate}</div>
                                    <div>종료시간 : ${advertise.endDate}</div>
                                </div>
                            </div>
                        </c:forEach>
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
				<form action="/admin/advertise/insert-advertise" method="post" class="advertise" enctype="multipart/form-data">
					<div class="form-group">
						<label for="placeId">위치 :</label> 
						<input type="number" id="placeId" name="placeId" value="1">
					</div>
					<div class="form-group">
						<label for="title">광고명 :</label> 
						<input type="text" id="title" name="title" value="예나의 화장품 광고">
					</div>
					<div class="form-group">
						<label for="customer">광고주 :</label> 
						<input type="text" id="customer" name="customer" value="최예나">
					</div>
					<div class="form-group">
						<label for="link" class="link-label">링크(파일) :</label>
						<input type="file" id="link" name="link" class="link-input">
					</div>
					<div class="form-group">
						<label for="startDate">시작시간 :</label> 
						<input type="text" id="startDate" name="startDate" value="2024-09-01">
					</div>
					<div class="form-group">
						<label for="endDate">종료시간 :</label> 
						<input type="text" id="endDate" name="endDate" value="2024-09-06">
					</div>
					<div class="form-group">
						<label for="status">현재상태 :</label> 
						<input type="text" id="status" name="status" value="0">
					</div>
					<button type="submit">광고추가</button>
				</form>
				
				<div>광고 수정</div>
				<form action="advertise/update-advertise" method="post" class="advertise">
					<div class="form-group">
						<label for="id">수정할 광고 id :</label> 
						<input type="number" id="id" name="id" value="1">
					</div>
					<div class="form-group">
						<label for="placeId">위치 :</label> 
						<input type="number" id="placeId" name="placeId" value="2">
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
						<input type="text" id="link" name="link" value="rrrrrrr">
					</div>
					<div class="form-group">
						<label for="startDate">시작시간 :</label> 
						<input type="text" id="startDate" name="startDate" value="2024-09-01">
					</div>
					<div class="form-group">
						<label for="endDate">종료시간 :</label> 
						<input type="text" id="endDate" name="endDate" value="2024-09-06">
					</div>
					<div class="form-group">
						<label for="status">현재상태 :</label> 
						<input type="text" id="status" name="status" value="0">
					</div>
					<button type="submit">광고수정</button>
				</form>
				
				<div>광고 삭제</div>
				<form action="advertise/delete-advertise" method="post" class="advertise">
					<div class="form-group">
						<label for="id">삭제할 광고 id :</label> 
						<input type="number" id="id" name="id" value="1">
					</div>
					<button type="submit">광고삭제</button>
				</form>


			
                <script>
					 // 파일 선택 시 파일 이름을 표시하도록 하는 코드
					  $(".link-input").on("change", function() {
					    // 선택된 파일의 전체 경로를 콘솔에 출력합니다.
					    console.log($(this).val());
					    
					    // 파일 경로에서 파일 이름만 추출합니다.
					    let link = $(this).val().split("\\").pop();
					    
					    // 해당 파일 이름을 사용하여 .link-label 요소의 내용과 스타일을 업데이트합니다.
					    $(this).siblings(".link-label").addClass("selected").html(link);
					  });
				</script>
				
				<script>
				    document.addEventListener('DOMContentLoaded', () => {
				        // 광고 요소를 선택
				        const advertiseContainers = document.querySelectorAll('#advertise-container .advertise-box');
				        let currentIndex = 0;
				
				        // 광고를 변경하는 함수
				        function showNextAd() {
				            // 현재 광고를 숨김
				            advertiseContainers[currentIndex].style.display = 'none';
				
				            // 다음 광고로 이동
				            currentIndex = (currentIndex + 1) % advertiseContainers.length;
				
				            // 다음 광고를 표시
				            advertiseContainers[currentIndex].style.display = 'flex';
				        }
				
				        // 모든 광고를 숨김
				        advertiseContainers.forEach(ad => ad.style.display = 'none');
				
				        // 첫 번째 광고를 표시
				        advertiseContainers[currentIndex].style.display = 'flex';
				
				        // 5초마다 광고를 변경
				        setInterval(showNextAd, 5000);
				    });
				</script>



<%@ include file="/WEB-INF/view/layout/adminFooter.jsp"%>
