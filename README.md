
# < 🕹️셸위 : 게임 친구 매칭 사이트  >
&nbsp; 
&nbsp;
![logo](https://github.com/user-attachments/assets/a457e67b-c80a-4459-a1d3-ad127d33c923)


&nbsp;
### 목차
1. 프로젝트 개요
2. 구성원 및 맡은 역할
3. 서비스 환경
4. 사용 라이브러리 및 프로토콜
5. 시퀀스 다이어그램
6. 주요 기능
7. ERD 다이어그램
&nbsp; 
## 1️⃣ 프로젝트 개요
* JSP,JSTL을 사용한 학사정보관리 웹사이트 제작
* 프로젝트 수행을 통한 협업 및 일정 관리 툴 사용법 학습
&nbsp; 
## 2️⃣ 구성원 및 맡은 역할
|이름|역할|맡은 역할|
|------|---|---|
|배병호🐸|팀장| -리더 및 프로젝트 총괄, DB 설계 및 샘플 데이터 제작, 휴학신청 관련 기능 및 교수 강의 관련 기능 구현  |
|김민경🐱|팀원| 학사일정, 공지, 단과대학, 강의 관련 CRUD 기능 구현 |
|김성후🐻|팀원| HTML,CSS를 사용한 UI 설계, 세션과 쿠키를 사용한 로그인, 회원가입, 마이페이지 조회 기능 구현 |
|엄송현🐹|팀원| 보고서, PPT, ReadMe 작성, 학생/교수/교직원 조회, 등록, 검색 기능, 등록금 고지서 발송 및 수강신청 기간 설정 기능 구현 |
|최이제🐺|팀원| Git Bash를 사용한 버전 관리, 등록금 내역 및 납부 고지서 조회 기능, 강의 시간표 조회 및 수강신청-예비 수강신청 기능 구현 |
## 3️⃣ 서비스 환경 및 사용 라이브러리
|유형|구분|서비스 배포 환경|
|------|---|---|
|SW|OS| Windows10 |
||Browser| Chrome 121.0.6167.161 |
||Tool| Spring Tool Suite |
||BackEnd| Java 17 7 MySQL 8.0.26 |
||Version/Issue 관리| GitHub & GitBash |
||Communication| Discord & Notion|
### (2) 사용 라이브러리
|라이브러리명|버전명|용도|
|------|---|---|
|HikariCP|HikariCP 5.1.0| Connection Pool을 통한 효율적인 DB 연결 관리 |
|jakarta.servlet.jsp.jstl|Jakarta.servlet.jsp.jstl.api 3.0.0| 커스텀 라이브러리 구현체 사용을 위한 인터페이스 |
|jakarta.servlet.jsp.jstl|jakarta.servlet.jsp.jstl 3.0.0| 커스텀 라이브러리 구현체 사용 |
|Lombok|Lombok| 간편한 생성자 및 메서드 사용 |
|MySQL Connector Java|MySQL Connector Java 8.0.21| MySQL 테이블, DAO&DTO, 검색 기능 및 페이징 기능 구현 |
|SLF4J|SLF4J API 2.0.0| 다양한 로깅 프레임워크에 대한 추상화 및 연결 |커스텀 라이브러리 구현체|
|SLF4J|SLF4J simple 2.0.0| SLF4J 인터페이스를 로깅 구현체와 연결 |커스텀 라이브러리 구현체 사용을 위한 인터페이스|

## 4️⃣ 사이트맵
### (1) 공통 기능
![Sitemap Example (5)](https://github.com/user-attachments/assets/ee100bab-b1d3-42dd-aea2-fd6dffe1291c)

### (2) 학생 기능
![Sitemap Example](https://github.com/user-attachments/assets/25a5cc2a-b4d2-4d7f-ba09-0bd06e2d6f76)

### (3) 교직원 기능
![Sitemap Example (1)](https://github.com/user-attachments/assets/c54f478e-654a-4b04-bea0-0075a486a850)

### (4) 교수 기능
![Sitemap Example (2)](https://github.com/user-attachments/assets/67040582-2b99-43c6-97f3-2a09708628be)


## 5️⃣ 시퀀스 다이어그램
### (1) 로그인 시퀀스 (직함별로 다른 사이트를 보여주기)
![352962757-64de70dc-b27e-437e-bd74-b1c1ab00955f](https://github.com/user-attachments/assets/e110ed64-c948-4191-8a9d-6e8f626b8cfa)
### (2) 수강신청 시퀀스 (조회) 
![시퀀스 다이어그램 - 수강신청 조회](https://github.com/user-attachments/assets/0a205c41-6601-4adf-b2c5-8ea595a36ada) 
### (3) 수강신청 시퀀스 (신청) 
![시퀀스 다이어그램 - 수강신청](https://github.com/user-attachments/assets/66cf7af1-4c5a-4536-ba82-0e03c6963d69) &nbsp;
## 6️⃣ ERD 다이어그램 &nbsp;
![image](https://github.com/user-attachments/assets/c5fbc0f5-5a6a-41f2-bf96-e0d49d4ceb34) &nbsp;
## 7️⃣ 화면 및 기능 설명 
(1) 공통 - 로그인 &nbsp;
![로그인](https://github.com/user-attachments/assets/bf23b481-f332-4d10-a428-905937ec026c) &nbsp;
(1) 공통 - 메인 화면 &nbsp;
![메인 화면](https://github.com/user-attachments/assets/d38c4d53-ff37-4873-8585-d330cacfa2a4) &nbsp;
(2) 학생 - 수강 신청 &nbsp;
![수강신청조회](https://github.com/user-attachments/assets/869f4344-1d31-4d52-9814-7b4a413f17c4) &nbsp;
(2) 학생 - 휴학 신청  &nbsp;
![휴학 내역 조회](https://github.com/user-attachments/assets/7254cc85-4e67-4bbc-9b7c-f83ebf46305b) &nbsp;
(3) 교직원 - 학생/교수 목록 조회 및 검색  &nbsp;
![교수 명단 조회](https://github.com/user-attachments/assets/8f76c05c-d5e3-492c-9776-86c1d3d5696a)
(4) 교수 - 강의 목록 조회  &nbsp;
![전체 강의 조회](https://github.com/user-attachments/assets/7727d170-d178-4302-8899-02a619d8062b)

