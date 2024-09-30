
# < 🕹️셸위 : 게임 친구 매칭 사이트  >
### Springboot와 MyBatis를 사용한 게임 친구 매칭 사이트
&nbsp;
&nbsp; 
![logo](https://github.com/user-attachments/assets/a457e67b-c80a-4459-a1d3-ad127d33c923)

* 유튜브 시연 영상 : https://www.youtube.com/watch?v=XGrV_bXjmmg
* 노션 : https://coffee-pike-9e2.notion.site/df2cb01b726b41cdb25713ee339bf99c?pvs=4



&nbsp;
### 목차
1. 프로젝트 개요
2. 구성원 및 맡은 역할
3. 서비스 환경
4. 사용 라이브러리 및 외부 API
5. 사이트 맵 (유저, 관리자)
6. 주요 기능
7. ERD 다이어그램
&nbsp; &nbsp;&nbsp;
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
||BackEnd| Java 17 & MySQL 8.0.26 & h2 & redis & MyBatis|
||Version/Issue 관리| GitHub & GitBash |
||Communication| Discord & Notion & Slack|

## 4️⃣ 사용 라이브러리 및 외부 API
### (1) 사용 라이브러리
|라이브러리 명|버전|용도|
|------|---|---|
|javax servlet api|4.0.1| 커스텀 라이브러리 구현체 사용 |
|jakarta servlet jsp jstl|3.0.0| 커스텀 라이브러리 구현체 사용을 위한 인터페이스|
|jackson |2.15.0-rc1 | ObjectMapper API를 통한 JSON 객체 활용|
|Lombok|1.18.34|어노테이션을 활용한 간단한 메서드 사용 및 편의성 증가|
|MySQL Connecter Java|MySQL Connector Java 8.0.21 | Java와 MySQL을 연결한 효율적인 데이터베이스 사용 |
|Chart.js|4.4.4| 차트를 사용한 효과적인 데이터 시각화 및 대시보드 제작|
|spring security crypto|4.4.4| Springboot 기반의 간편한 인증 및 보안 처리|
|spring-boot-starter-websocket|3.0.3| 전이중 통신 및 양방향 통신을 위한 연결 지향 통신 제공|

### (2) 사용 외부 API
|기능|API 명|제공|용도|
|------|------|-----|-----|
|로그인|카카오 로그인|Kakao Developers카카오 소셜 로그인을 통한 간편 로그인 기능 제작|
||네이버 로그인|Naver Developers|네이버 소셜 로그인을 통한 간편 로그인 기능 제작|
||구글 로그인|Google Cloud|구글 소셜 로그인을 통한 간편 로그인 기능 제작|
|이메일 인증|Gmail STMP|Gmail|이메일 인증을 통한 보안 및 인증 처리|
|결제|카카오 페이 온라인 결제|Kakao Pay Developers|사용자 결제 및 결제 취소|
||토스 페이먼츠 결제|Toss Payments|사용자 결제 및 결제 취소|
|지급정산|토스 페이먼츠 지급 대행|Toss Payments|사이트의 현재 지급 가능 금액 확인, 서브몰 신청, 지급 정산|

## 4️⃣ 사이트맵
### (1) 유저
![Screenshot_1](https://github.com/user-attachments/assets/9233e818-a060-4ce2-b658-465ead28a696)

### (2) 관리자
![Screenshot_3](https://github.com/user-attachments/assets/4c779bbf-6f89-4170-8e9a-4de557db04e6)


## 5️⃣ ERD 다이어그램
![ERD다이어그램](https://github.com/user-attachments/assets/f6214843-2830-4cdd-b50e-afede442ad4e)



## 6️⃣ 주요 기능 및 화면 소개 &nbsp;
### 1. 유저
#### (1) 로그인 및 회원가입 (일반/네이버/카카오/구글)
![유저 로그인](https://github.com/user-attachments/assets/0e0a9707-402b-417c-8b81-7eff1cdf66ce)

#### (2) ID, PW 찾기
![비밀번호 찾기](https://github.com/user-attachments/assets/ea98fd78-821c-4cf6-bfbc-e00b3cff16ec)

#### (3) 메인 화면
![Screenshot_4](https://github.com/user-attachments/assets/16a8adef-d087-4d15-8031-e468b14ad0fd) &nbsp;

#### (4) 게임 MBTI를 알아보는 테스트 화면
![테스트 결과](https://github.com/user-attachments/assets/a0fc8895-8752-42c4-be62-ac2b7ece403d)

#### (5) MBTI 유형에 따른 친구 매칭 &nbsp;
![image](https://github.com/user-attachments/assets/4746cbc4-7872-41fa-9332-92326000965c)

#### (6) 1:1 실시간 채팅
![image](https://github.com/user-attachments/assets/10207d42-b9e0-4c48-ad27-13ef53a1d59d)

#### (7) 친구 추가 및 수락
![image](https://github.com/user-attachments/assets/cf4fbaf3-b7cb-4599-9822-f88131e0f58f)

#### (8) 게임 강의 게시판  &nbsp;
![Screenshot_9](https://github.com/user-attachments/assets/6dcb7ace-afd9-471a-ad15-d8e13e1178de) &nbsp;

#### (9) 게임 강의 수강 및 포인트 결제 &nbsp;
![강의 디테일](https://github.com/user-attachments/assets/5861d2c2-8391-4b8c-8e3d-61c76ddc9bec)  &nbsp;

#### (10) 캐쉬 충전 (카카오페이/토스페이먼츠) &nbsp;
![Screenshot_1](https://github.com/user-attachments/assets/95ea3182-5409-41c1-9901-17997b414c25) &nbsp;

#### (11) 커뮤니티 게시판
![커뮤니티](https://github.com/user-attachments/assets/24b2dd6e-0ae6-405d-bc87-6f9e64e4c63a)

#### (12) 뉴스 및 공지 게시판
![뉴스](https://github.com/user-attachments/assets/d24e8b7f-fae3-4eff-94d8-e6dfc289c255)

#### (13) Q&A 게시판
![고객지원](https://github.com/user-attachments/assets/b0c18546-bcdf-4e8d-ac0b-5a81e69d70a4)

#### (14) 회원정보 수정
![회원정보 수정](https://github.com/user-attachments/assets/fdcc4d92-f7aa-46fb-9897-9ae9a13d315a)

#### (11) 캐쉬 충전 및 사용 내역
![캐쉬 사용 이력](https://github.com/user-attachments/assets/e9a36e69-17de-4c7a-95d1-d7b1e62deaa7)

#### (11) 서브몰 생성 신청 및 환전 내역
![환전 신청 내역](https://github.com/user-attachments/assets/673ade57-8c71-4a15-b7b4-553556e2aecf)

#### (12) 내 강좌 관리 및 강좌 생성
![강좌개설](https://github.com/user-attachments/assets/222f2679-3288-4f2a-bce2-3ffc74d5b4a5)
![강의 목록](https://github.com/user-attachments/assets/a10471eb-8928-4a72-9b9b-c2747bd42518)



### 2. 관리자
#### (1) 로그인 
![관리자 로그인](https://github.com/user-attachments/assets/2976a4a9-bcfc-4421-9346-78f2e43450ba)

#### (2) 대쉬보드 
![Screenshot_3](https://github.com/user-attachments/assets/552c8a7e-de72-400c-95fb-c0da16c20788)


#### (3) 강의 관리
![강의 관리](https://github.com/user-attachments/assets/66c97cc6-bcd3-4852-bc3a-97ccdbd42a6d)

#### (4) 서브몰 신청 및 내역 관리 
![관리자 서브몰 관리](https://github.com/user-attachments/assets/19c31685-13ad-48c5-a271-ff3e504a6263)

#### (5) 환전 신청 내역 조회 및 승인 
![관리자-환전 내역 관리](https://github.com/user-attachments/assets/1473ee47-33af-464c-b49c-7ba2de9b3e17)

#### (6) 현금 결제 환불 관리
![관리자 환불 관리](https://github.com/user-attachments/assets/6b06ffca-0161-4b85-8c87-e8d342745d06)

#### (7) 고객 지원 관리
![고객지원](https://github.com/user-attachments/assets/78625440-f0bd-4f8d-93b3-a861b7d12fd0)

#### (8) 실시간 광고 배너 관리
![광고목록](https://github.com/user-attachments/assets/e36a6c12-5011-4e97-8952-82cebdf824ba)

#### (9) 게시글 카테고리/게시글.댓글 관리
![카테고리 관리](https://github.com/user-attachments/assets/92cb1668-427d-4eb7-8a52-ff5618cb7949)
