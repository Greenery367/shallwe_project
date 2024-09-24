
# < 🕹️셸위 : 게임 친구 매칭 사이트  >
&nbsp; 
&nbsp;
![logo](https://github.com/user-attachments/assets/a457e67b-c80a-4459-a1d3-ad127d33c923)


&nbsp;
### 목차
1. 프로젝트 개요
2. 구성원 및 맡은 역할
3. 서비스 환경
4. 사용 라이브러리 및 외부 API
5. 사이트 맵 (유저, 관리자)
6. 주요 기능
7. ERD 다이어그램
&nbsp; 
## 1️⃣ 프로젝트 개요
* Springboot와 MySQL을 사용한 플랫폼 제작
* 게임 MBTI 테스트 및 친구 매칭 기능이 담긴 게임 유저 매칭 웹사이트 제작
&nbsp; 
## 2️⃣ 구성원 및 맡은 역할
|이름|역할|맡은 역할|
|------|---|---|
|엄송현|팀장| 리더 및 프로젝트 총괄, 게임 유형 테스트 로직, 현금 결제 및 취소, 환전, 서브몰 생성 승인 기능 구현 (관리자)  |
|도준영|팀원| 유저 및 관리자 로그인 및 회원가입, 유저 문의 게시판, 내 강의 생성 및 관리 기능 구현 |
|송원석|팀원| 게임 유형을 활용한 매칭 시스템, 1:1 실시간 채팅, 알림, 친구 추가 및 수락, 채팅 로그 저장을 활용한 신고 기능 구현 |
|이건우|팀원| 관리자 대시보드, 카테고리 및 게시글 관리, 고객 지원 관리, 강의 관리, 광고 및 배너 순환 로직 기능 구현 |
|최이제|팀원| 커뮤니티 게시판, 강의 게시판, 회원 정보 수정, 환전, 환불, 서브몰 신청 기능 구현 (유저) |
## 3️⃣ 서비스 환경 

## 4️⃣ 사용 라이브러리 및 외부 API
### (1) 사용 라이브러리
|유형|구분|서비스 배포 환경|
|------|---|---|
|SW|OS| Windows10 |
||Browser| Chrome 121.0.6167.161 |
||Tool| Spring Tool Suite |
||BackEnd| Java 17 & MySQL 8.0.26 & h2 & redis|
||Version/Issue 관리| GitHub & GitBash |
||Communication| Discord & Notion & Slack|
### (2) 사용 외부 API
|기능|API 명|제공|버전|용도|
|------|------|-----|-----|-----|
|로그인|카카오 로그인|Kakao Developers||카카오 소셜 로그인을 통한 간편 로그인 기능 제작|
||네이버 로그인|Naver Developers||네이버 소셜 로그인을 통한 간편 로그인 기능 제작|
||구글 로그인|Google Cloud|웹버전 9|구글 소셜 로그인을 통한 간편 로그인 기능 제작|
|이메일 인증|Gmail STMP|Gmail||이메일 인증을 통한 보안 및 인증 처리|
|결제|카카오 페이 온라인 결제|Kakao Pay Developers|v1|사용자 결제 및 결제 취소|
||토스 페이먼츠 결제|Toss Payments|2022-11-16|사용자 결제 및 결제 취소|
|지급정산|토스 페이먼츠 지급 대행|Toss Payments||사이트의 현재 지급 가능 금액 확인, 서브몰 신청, 지급 정산|

## 4️⃣ 사이트맵
### (1) 유저
![Screenshot_1](https://github.com/user-attachments/assets/9233e818-a060-4ce2-b658-465ead28a696)

### (2) 관리자
![Screenshot_3](https://github.com/user-attachments/assets/4c779bbf-6f89-4170-8e9a-4de557db04e6)


## 5️⃣ ERD 다이어그램
### (1) 로그인 시퀀스 (직함별로 다른 사이트를 보여주기)
![352962757-64de70dc-b27e-437e-bd74-b1c1ab00955f](https://github.com/user-attachments/assets/e110ed64-c948-4191-8a9d-6e8f626b8cfa)
### (2) 수강신청 시퀀스 (조회) 
![시퀀스 다이어그램 - 수강신청 조회](https://github.com/user-attachments/assets/0a205c41-6601-4adf-b2c5-8ea595a36ada) 
### (3) 수강신청 시퀀스 (신청) 
![시퀀스 다이어그램 - 수강신청](https://github.com/user-attachments/assets/66cf7af1-4c5a-4536-ba82-0e03c6963d69) &nbsp;

## 6️⃣ 주요 기능 및 화면 소개 &nbsp;
![image](https://github.com/user-attachments/assets/c5fbc0f5-5a6a-41f2-bf96-e0d49d4ceb34) &nbsp;


