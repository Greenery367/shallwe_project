
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
### (1) 프로젝트 주제 및 목적
* Springboot와 MySQL을 사용한 플랫폼 제작
* 게임 MBTI 테스트 및 친구 매칭 기능이 담긴 게임 유저 매칭 웹사이트 제작

### (2) 프로젝트 핵심 기능
* 게임 MBTI 테스트
* MBTI간 궁합도에 기반한 1:1 매칭, 실시간 1:1 채팅
* 강의 수강을 통한 교류 및 수익 창출
* 광고 배너 실시간 관리를 통한 광고 수익 창출
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
|유형|구분|서비스 배포 환경|
|------|---|---|
|SW|OS| Windows10 |
||Browser| Chrome 121.0.6167.161 |
||Tool| Spring Tool Suite |
||BackEnd| Java 17 & MySQL 8.0.26 & h2 & redis|
||Version/Issue 관리| GitHub & GitBash |
||Communication| Discord & Notion & Slack|

## 4️⃣ 사용 라이브러리 및 외부 API
### (1) 사용 라이브러리
|라이브러리 명|버전|용도|
|------|---|---|
|javax servlet api|4.0.1| 커스텀 라이브러리 구현체 사용 |
|jakarta servlet jsp jstl|3.0.0| 커스텀 라이브러리 구현체 사용을 위한 인터페이스|
|jackson |2.15.0-rc1 | ObjectMapper API를 통한 JSON 객체 활용|
|Lombok||어노테이션을 활용한 간단한 메서드 사용 및 편의성 증가|
|MySQL Connecter Java|MySQL Connector Java 8.0.21 | Java와 MySQL을 연결한 효율적인 데이터베이스 사용 |
|MyBatis|3.0.3| 간편한 DB 사용 및 동적 쿼리 작성|
|Chart.js|4.4.4| 차트를 사용한 효과적인 데이터 시각화 및 대시보드 제작|
|spring security crypto|4.4.4| Springboot 기반의 간편한 인증 및 보안 처리|
|spring-boot-starter-websocket|3.0.3| 전이중 통신 및 양방향 통신을 위한 연결 지향 통신 제공|

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
![352962757-64de70dc-b27e-437e-bd74-b1c1ab00955f](https://github.com/user-attachments/assets/e110ed64-c948-4191-8a9d-6e8f626b8cfa)


## 6️⃣ 주요 기능 및 화면 소개 &nbsp;
### (1) 로그인 및 회원가입 (일반/네이버/카카오/구글)
![image](https://github.com/user-attachments/assets/c5fbc0f5-5a6a-41f2-bf96-e0d49d4ceb34) &nbsp;

### (2) ID, PW 찾기

### (3) 메인 화면
![Screenshot_4](https://github.com/user-attachments/assets/16a8adef-d087-4d15-8031-e468b14ad0fd) &nbsp;

### (4) 게임 MBTI를 알아보는 테스트 화면
![테스트](https://github.com/user-attachments/assets/9e3eb46b-5d02-4442-b386-79643669a400)
![테스트 중](https://github.com/user-attachments/assets/d1525899-8577-4aa6-8fc2-8ef78b9eaf6c)
![테스트 결과](https://github.com/user-attachments/assets/a0fc8895-8752-42c4-be62-ac2b7ece403d)


### (5) MBTI 유형에 따른 친구 매칭 &nbsp;
![Screenshot_6](https://github.com/user-attachments/assets/075ab236-c1f2-42f7-8ac8-ef4eb2335952) &nbsp;
![image](https://github.com/user-attachments/assets/bef82f85-0965-4d3b-87f1-7d666289110b)&nbsp;
![image](https://github.com/user-attachments/assets/9eaab0b2-7ddd-4ffe-bafe-33e4c24b5bb5)&nbsp;

### (6) 1:1 실시간 채팅
![image](https://github.com/user-attachments/assets/10207d42-b9e0-4c48-ad27-13ef53a1d59d)

### (7) 친구 추가 및 수락
![image](https://github.com/user-attachments/assets/f3a530b3-a9b7-4e62-954d-2f13ca384569)
![image](https://github.com/user-attachments/assets/81ea32f5-0f7e-494f-b333-165d43f39666)

### (8) 게임 강의 게시판  &nbsp;
![Screenshot_9](https://github.com/user-attachments/assets/6dcb7ace-afd9-471a-ad15-d8e13e1178de) &nbsp;

### (9) 게임 강의 수강 및 포인트 결제 &nbsp;
![강의 디테일](https://github.com/user-attachments/assets/5861d2c2-8391-4b8c-8e3d-61c76ddc9bec)  &nbsp;
![강의 결제](https://github.com/user-attachments/assets/bb6a0937-ecf5-4177-b12e-ae295281a460)  &nbsp;


### (10) 캐쉬 충전 (카카오페이/토스페이먼츠) &nbsp;
![Screenshot_1](https://github.com/user-attachments/assets/95ea3182-5409-41c1-9901-17997b414c25) &nbsp;
![Screenshot_2](https://github.com/user-attachments/assets/4d4922be-df0d-4a38-a910-ea61aaaddcb1) &nbsp;
![Screenshot_3](https://github.com/user-attachments/assets/a7ad4c00-4673-4483-84ac-abfe97af2acf) &nbsp;


### (11) 커뮤니티 게시판

### (12) 뉴스 및 공지 게시판
![뉴스](https://github.com/user-attachments/assets/d24e8b7f-fae3-4eff-94d8-e6dfc289c255)
![뉴스 디테일](https://github.com/user-attachments/assets/e6e6a465-d2e0-4250-b551-52c5bf0586ae)
![공지](https://github.com/user-attachments/assets/fbcde347-7c85-43e7-94b9-02fabf6d94c2)
![공지 디테일](https://github.com/user-attachments/assets/4176b02a-db1d-44ef-90ae-eb119dfea147)


### (13) Q&A 게시판

### (14) 회원정보 수정

### (11) 캐쉬 충전 및 사용 내역

### (11) 서브몰 생성 신청 및 환전 내역

