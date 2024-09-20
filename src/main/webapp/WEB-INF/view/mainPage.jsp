<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ include file="/WEB-INF/view/layout/header.jsp" %>	
	<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>


		<div class="main-board">
			<div class="banner-container-vertical">
				<div class="advertise-example-left">
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
			</div>
		<div class="main-page">
			<div class="banner-conatiner-whole-box">
				<div class="banner-container">
					<div class="banners">
						<img class="admin-main-2" alt="로고" src="../static/images/banner2.jpg">
					</div>
					<div class="banners">
						<img class="admin-main-2" alt="로고" src="../static/images/banner3.png">
					</div>
					<div class="banners">
						<img class="admin-main-2" alt="로고" src="../static/images/banner4.jpg">
					</div>
					<div class="banners">
						<img class="admin-main-2" alt="로고" src="../static/images/banner5.png">
					</div>
					<div class="banners">
						<img class="admin-main-2" alt="로고" src="../static/images/banner6.jpg">
					</div>
				</div>
			</div>
			<div class="recommend">
				<div class="recomment-user-bar">
					<h2> 나와 잘 맞는 사람 추천하기</h2>
					<button class="recomment-button"><b>나와 같은 타입</b></button>
					<button class="recomment-button"><b>나와 잘 맞는 타입</b></button>
					<button class="detail-button"><b>자세히 보러 가기 →</b></button>
				</div>
				<hr/>
				<div class="recommended-user-list">
					<div class="recommended-user">
						<button class="btn-circle"><img src="../static/images/anonymus.png"></button>
						<div>춘식이</div>
					</div>
					<div class="recommended-user">
						<button class="btn-circle"><img src="../static/images/anonymus.png"></button>
						<div>춘식이</div>
					</div>
					<div class="recommended-user">
						<button class="btn-circle"><img src="../static/images/anonymus.png"></button>
						<div>춘식이</div>
					</div>
					<div class="recommended-user">
						<button class="btn-circle"><img src="../static/images/anonymus.png"></button>
						<div>춘식이</div>
					</div>
					<div class="recommended-user">
						<button class="btn-circle"><img src="../static/images/anonymus.png"></button>
						<div>춘식이</div>
					</div>
					<div class="recommended-user">
						<button class="btn-circle"><img src="../static/images/anonymus.png"></button>
						<div>춘식이</div>
					</div>
				</div>
			</div>
			
			
			<div class="recommend">
				<div class="recomment-user-bar">
					<h2> 지금 인기많은 강사 추천하기</h2>
					<button class="recomment-button"><b>나와 같은 타입</b></button>
					<button class="recomment-button"><b>나와 잘 맞는 타입</b></button>
					<button class="detail-button" onclick="location.href='/lecture/category/2'"><b>자세히 보러 가기 →</b></button>
				</div>
				<hr/>
				<div class="recommended-user-list">
					<div class="recommended-user">
						<button class="btn-circle"><img src="../static/images/anonymus.png"></button>
						<div>춘식이</div>
					</div>
					<div class="recommended-user">
						<button class="btn-circle"><img src="../static/images/anonymus.png"></button>
						<div>춘식이</div>
					</div>
					<div class="recommended-user">
						<button class="btn-circle"><img src="../static/images/anonymus.png"></button>
						<div>춘식이</div>
					</div>
					<div class="recommended-user">
						<button class="btn-circle"><img src="../static/images/anonymus.png"></button>
						<div>춘식이</div>
					</div>
					<div class="recommended-user">
						<button class="btn-circle"><img src="../static/images/anonymus.png"></button>
						<div>춘식이</div>
					</div>
					<div class="recommended-user">
						<button class="btn-circle"><img src="../static/images/anonymus.png"></button>
						<div>춘식이</div>
					</div>
				</div>
		</div>
			<div class="post-board-container">
				<div class="post-board">
					<div class="board-title">
						<h2>공지사항</h2>
						<button class="detail-button" onclick = "location.href='/notice/notice-list'"><b>더보기</b></button>
					</div>
					<hr/>
					<div class="post-board-small">
						<c:forEach items="${noticeList}" var="notice">
							<div class="link-box">
								<a href="/notice/notice-detail/${notice.id}" class="link-text">${notice.title}</a>
							</div>
						</c:forEach>
						</div>
				</div>
				
				<div class="post-board">
					<div class="board-title">
						<h2>자유 게시판</h2>
						<button class="detail-button" onclick = "location.href='/notice/news-list'"><b>더보기</b></button>
					</div>
					<hr/>
					<div class="post-board-small">
						<c:forEach items="${newsList}" var="news">
							<div class="link-box">
								<a href="/notice/news-detail/${news.id}" class="link-text">${news.title}</a>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
	</div>
	<div class="side-menu">
		<div class="my-profile">	
			<div class="pro-title">
				<h2><b>나의 프로필</b></h2>
			</div>
				
					
					<c:choose>
						<c:when test="${user != null }">
							<img class="pro-icon" src="${user.upload_file_name}">
							<div class="user-info-list">
							<div class="user-name"><h4 class="user-figurative">댓글장인</h4>&nbsp;<h4>${user.nickname}</h4>&nbsp;<h4>님</h4></div>
							<p class="user-mbti"><b>SQRC :장군</b></p>
							<p>현재 캐쉬: ${user.currentCash}</p>
						<div class="user-button-list">
						<button class="user-button" onclick="location.href='/my-page/${user.userId}'"><b>마이페이지</b></button>
						<button class="user-button"><b>로그아웃</b></button>
						</div>	
						</c:when>
						<c:otherwise>
							<img class="pro-icon" src="../static/images/image.png">
							<div class="user-info-list">
							<div class="user-name">&nbsp;<h4>익명님</h4></div>
							<div class="user-button-list">
							<button class="user-button" onclick="location.href='/user/sign-in'""><b>로그인</b></button>
							<button class="user-button" onclick="location.href='/user/sign-up'"><b>회원가입</b></button>
							</div>	
						</c:otherwise>
					</c:choose>
					</div>
							
			</div>
		<div class="friend-list">
			<div class="friend-list-container">
				<h2><b>접속 중인 친구</b></h2>
				<div class="friend-box-container">
					<div class="friend-container">
						<img class="friend-icon" src="../static/images/아기춘식.jpg">
						<h6 class="friend-name">친구 이름</h6>
					</div>
					<div class="friend-container">
						<img class="friend-icon" src="../static/images/아기춘식.jpg">
						<h6 class="friend-name">친구 이름</h6>
					</div>
					<div class="friend-container">
						<img class="friend-icon" src="../static/images/아기춘식.jpg">
						<h6 class="friend-name">친구 이름</h6>
					</div>
					<div class="friend-container">
						<img class="friend-icon" src="../static/images/아기춘식.jpg">
						<h6 class="friend-name">친구 이름</h6>
					</div>
					<div class="friend-container">
						<img class="friend-icon" src="../static/images/아기춘식.jpg">
						<h6 class="friend-name">친구 이름</h6>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
	<div class="floating-menu-up">
		<a href="#">
			<img src="../static/images/floating-menu/floating-menu-up.png" alt="위로" onclick="up()">
		</a>
	</div>
<script>
/**
 * 
 */let currentBanner = 0;
 const banners = document.querySelectorAll(`.banners`);
 const bannerCount = banners.length;
 
 function showBanner(n) {
	 banners.forEach(banners => banners.style.display = 'none');
	 banners[n].style.display = 'block';
 }
 
 function nextBanner(){
	currentBanner = (currentBanner + 1)% bannerCount ;
	showBanner(currentBanner);
 }
 
 function prevBanner(){
	currentBanner = (currentBanner - 1 + bannerCount) %	 bannerCount;
	showBanner(currentBanner);
 }
 
 document.addEventListener('DOMContentLoaded',()=>{
	showBanner(currentBanner);
	setInterval(nextBanner, 2000);
 })
 
 
</script>
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
<%@ include file="/WEB-INF/view/layout/footer.jsp" %>	
	