-- drop database shallwe_db;
-- create database shallwe_db;
-- use shallwe_db;

-- 관리자 테이블
create table admin_tb(
    id int primary key auto_increment not null,
    admin_name varchar(20) not null,
    admin_id varchar(20) unique key not null,
    password varchar(20) not null,
    email varchar(50) ,
    phone_number varchar(20) unique key,
    status int not null default 0 -- 0 회원 가입 유지, 1 탈퇴
);

-- 사용자 테이블
create table user_tb(
user_id int primary key auto_increment not null,
username varchar(20) not null,
id varchar(20) unique key not null,
password varchar(100) not null,
nickname varchar(30) not null unique key,
birth_date date not null,
email varchar(50) unique key not null,
phone_number varchar(20) unique key not null,
origin_file_name varchar(200) default '1',
upload_file_name varchar(200),
lecture_cash bigint not null default 0,
current_cash bigint not null default 0,
created_at timestamp default now(),
sign_up_status int not null default 0, -- 0 회원 가입 유지, 1 탈퇴
sign_in_connection int not null default 0, -- on/off
online_status int not null default 0 -- 0 오프라인 , 1 온라인
);

-- 탈퇴 테이블
create table quit_user_tb(
<<<<<<< HEAD
    id int primary key,
    user_id int,
    reason int not null,
    reason_detail varchar(200),
<<<<<<< HEAD
    created_at timeStamp default now()
=======
    created_at timestamp default now()
>>>>>>> dev1
=======
id int primary key,
user_id int,
reason int not null,
reason_detail varchar(200),
createdAt timestamp default now()
>>>>>>> dev1
);

-- mbti 테이블
create table mbti_tb(
id int primary key auto_increment not null,
name varchar(10) not null, -- mbti 명
nickname varchar(20) not null, -- 직업
content text not null, -- mbti 설명
icon_url text
);

-- mbti-user 테이블
create table mbti_user_tb(
id int primary key auto_increment not null,
user_id int not null,
mbti_id int not null
);

-- mbti 질문 테이블
create table mbti_question_tb(
id int primary key auto_increment not null,
question varchar(50),
section varchar(2)
);

-- mbti 유저 답변 테이블
create table mbti_user_answer_tb(
id int primary key auto_increment not null,
user_id int,
mbti_id int,
created_at timestamp default now()
);

-- mbti-궁합 테이블
create table compatibility_tb (
<<<<<<< HEAD
    id int primary key auto_increment, -- 주식별자
    my_mbti_id int, -- 나의 mbti
    well_matched_mbti_id int, -- 잘맞는 mbti
    compatibility int -- 궁합도
=======
id int primary key auto_increment, -- 주식별자
my_mbti_id int, -- 나의 mbti
well_matched_mbti_id int, -- 잘맞는 mbti
compatibility int -- 궁합도
>>>>>>> dev1
);

-- 신고 테이블
create table report_tb(
id int primary key not null auto_increment,
type int not null, -- 어디서 신고했는지
reason varchar(200) not null, -- 이유
sender_id int not null, -- 신고한 사람
reciever_id int not null, -- 신고당한 사람
created_at timestamp default now() -- 신고 시간
);

-- 게임 카테고리 테이블
create table game_tb(
id int primary key not null auto_increment,
game_name varchar(20) not null
);

-- 신고 타입 테이블
create table report_type_tb(
id int primary key not null auto_increment,
type varchar(20) -- 어디서 신고했는지(유저, 게시글, 댓글, 강의)
);

-- 강의 테이블
create table class_tb(
id int primary key auto_increment not null,
category_id int not null,
author_id int not null,
title varchar(20) not null,
subtitle varchar(30) not null,
content blob not null,
limit_num int,
current_num int,
price bigint,
total_num int,
created_at timestamp default now()
);

-- 강의-강사 테이블
create table class_instructor_tb(
id int primary key auto_increment not null,
class_id int,
instructor_id int
);

-- 강의-수강생 테이블
create table class_student_tb(
id int primary key auto_increment not null,
class_id int,
student_id int,
created_at timestamp
);

-- 강의-리뷰 테이블
create table class_review_tb(
id int primary key auto_increment not null,
class_id int,
author_id int,
comment text,
grade int,
created_at timestamp default now()
);

-- 공지사항 테이블
create table notice_tb(
id int primary key auto_increment not null,
title varchar(20) not null,
content blob not null,
author_id int not null,
created_at timestamp default now()
);

-- 이벤트 테이블
create table event_tb(
id int primary key auto_increment not null,
title varchar(20) not null,
content blob not null,
author_id int not null,
created_at timestamp default now()
);

-- Q&A 테이블 (질문-유저)
create table qna_question_tb(
    id int primary key auto_increment not null,
    title varchar(20) not null,
    user_id varchar(30) not null,
    writer varchar(30) not null,
    content text not null,
    status int default 0, -- 질문／답변　판별용　컬럼　status 0 = 질문 / 1 = 답변
    reply_status int not null default 0,  -- 답변이 달린 질문인지 아닌지 판별용 컬럼 0일시 답변x 1일시 답변o
    created_at timestamp default now()
);

-- Q&A 테이블 (답변-관리자)
create table qna_answer_tb(
id int primary key auto_increment not null,
content blob not null,
author_id int not null,
created_at timestamp default now()
);

-- 자주 묻는 질문 테이블
create table frequently_questions_tb(
id int auto_increment primary key,
title varchar(100) not null,
user_id varchar(30) not null,
writer varchar(30) not null,
content blob not null,
reply_status int not null default 0,
created_at timestamp default now()
);

-- 커뮤니티 게시판 테이블
create table board_tb (
id int primary key auto_increment not null,
category_id int not null,
title varchar(20) not null,
content blob not null,
author_id int not null,
view_num int default 0,
good int default 0,
report_status int default 0,
created_at timestamp default current_timestamp,
status int default 0
);

-- 댓글 테이블
create table comment_tb (
id int primary key auto_increment not null,
post_id int not null,
content text not null,
author_id int not null,
created_at timestamp default current_timestamp,
status int not null default 0
);

-- 참여한 채팅방 테이블
create table chat_room_tb(
id int primary key auto_increment,
name varchar(50) not null,
head_count int default 0,
created_at timestamp default now()
);

-- 채팅방 테이블
create table chat_room_join_tb(
id int primary key auto_increment not null,
user_id int not null,
room_id int not null,
created_at timestamp default now(),
foreign key (room_id) references chat_room_tb(id),
foreign key (user_id) references user_tb(user_id)
);

-- 채팅방 히스토리 테이블
create table chat_room_history_tb(
id int primary key auto_increment not null,
user_id_1 int,
user_id_2 int,
created_at timestamp default now()
);

-- 광고 테이블
create table advertise_tb(
    id int primary key auto_increment not null,
    place_id int not null,
    title varchar(20),
    customer varchar(20),
    link text,
    origin_file_name varchar(200),
    upload_file_name varchar(200),
    created_at timestamp default now(),
    start_date timestamp,
    end_date timestamp,
    status int default 0
);

-- 광고 위치 테이블
create table ad_place_tb(
id int primary key auto_increment not null,
title varchar(20),
image_file_name varchar(200),
price bigint
);

-- 업적 테이블
create table quest_tb(
id int primary key auto_increment not null,
title varchar(50) not null,
type_id int, -- ex: 게시글 0, 댓글 1
achievement_num int, -- ex: 달성 조건 = 게시글 30개 (achievement=30)
title_id int
);

-- 유저-업적 테이블
create table user_quest_tb(
id int primary key auto_increment not null,
user_id int not null,
quest_id int not null,
current_achievement int
);

-- 칭호 테이블
create table title_tb(
id int primary key auto_increment not null,
title varchar(20),
content text
);

-- 유저-칭호 테이블
create table user_title_tb(
id int primary key auto_increment not null,
title_id int,
user_id int
);

-- 친구 테이블
create table friend_tb(
id int primary key auto_increment not null,
user_id int,
friend_id int
);

-- 명성치 테이블
create table fame_tb(
id int primary key auto_increment not null,
sender_id int,
reciever_id int
);

-- 은행 고유 정보
CREATE TABLE bank_info_tb (
bank_id VARCHAR(10) PRIMARY KEY NOT NULL, -- bank_id를 VARCHAR로 정의
bank_name VARCHAR(50) NOT NULL
);

-- 사용자-계좌 정보
CREATE TABLE bank_tb (
id INT PRIMARY KEY AUTO_INCREMENT NOT NULL, -- 단일 기본 키 id 사용
user_id INT NOT NULL,
bank_id VARCHAR(10) NOT NULL,
account_number VARCHAR(20)
);

-- 캐쉬 사용 내역
CREATE TABLE spend_tb (
id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
user_id INT NOT NULL,
class_id INT NOT NULL,
spend BIGINT NOT NULL,
created_at TIMESTAMP DEFAULT NOW()
);

-- 주문 테이블
create table order_tb(
id int primary key auto_increment,
order_id varchar(200),
user_id int,
name varchar(20),
amount bigint,
created_at timestamp default now(),
platform int, -- 0: 카카오페이, 1: 토스페이
status int default 0
);

-- 결제 상세 내역
create table order_detail_tb(
id int primary key auto_increment,
order_id varchar(100),
cid varchar(100),
tid varchar(100),
partner_order_id varchar(100),
partner_user_id varchar(100),
item_name varchar(50),
total_amount bigint,
tax_free_amount bigint
);

-- 환불 테이블
create table user_refund_tb(
id int primary key auto_increment,
order_id int,
user_id int,
created_at timestamp default now(),
status int default 0
);

-- 정산일 tb
create table exchange_schedule_tb(
    id int primary key auto_increment not null,
    year int default 2024,
    month int not null,
    day int not null
);


