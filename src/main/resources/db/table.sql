 create database shallwe_db;
 use shallwe_db;

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
id varchar(50) unique key not null,
password varchar(100) not null,
nickname varchar(35) not null unique key,
gender int,
birth_date date not null,
email varchar(50) unique key not null,
phone_number varchar(20) not null,
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
id int primary key,
user_id int,
reason int not null,
reason_detail varchar(200),
createdAt timestamp default now()
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
mbti_id int not null,
created_at timestamp default now(),
FOREIGN KEY (user_id) REFERENCES `user_tb`(user_id),
FOREIGN KEY (mbti_id) references `mbti_tb`(id)
);

-- mbti 질문 테이블
create table mbti_question_tb(
id int primary key auto_increment not null,
question varchar(50),
section varchar(2)
);

-- mbti-궁합 테이블
create table compatibility_tb (
id int primary key auto_increment, -- 주식별자
my_mbti_id int, -- 나의 mbti
well_matched_mbti_id int, -- 잘맞는 mbti
compatibility int, -- 궁합도
foreign key (my_mbti_id) references mbti_tb(id),
foreign key (well_matched_mbti_id) references mbti_tb(id)
);

-- 신고 테이블
create table report_tb(
id int primary key not null auto_increment,
type varchar(55) not null, -- 어디서 신고했는지
type_id int not null, -- 신고한 type의 id
reason varchar(200) not null, -- 이유
sender_id int not null, -- 신고한 사람
reciever_id int not null, -- 신고당한 사람
created_at timestamp default now(), -- 신고 시간
foreign key (sender_id) references user_tb(user_id),
foreign key (reciever_id) references user_tb(user_id)
);

-- 게임 카테고리 테이블
create table game_tb(
id int primary key not null auto_increment,
game_name varchar(20) not null
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
created_at timestamp default now(),
status int default 0,
FOREIGN KEY (author_id) references `user_tb`(user_id)
);

-- 강의-강사 테이블
create table class_instructor_tb(
id int primary key auto_increment not null,
class_id int,
instructor_id int,
foreign key (class_id) references class_tb(id),
foreign key (instructor_id) references user_tb(user_id)
);
-- 강의-수강생 테이블
create table class_student_tb(
id int primary key auto_increment not null,
class_id int,
student_id int,
created_at timestamp,
foreign key (class_id) references class_tb(id),
foreign key (student_id) references user_tb(user_id)
);
-- 강의-리뷰 테이블
create table class_review_tb(
id int primary key auto_increment not null,
class_id int,
author_id int,
comment text,
grade int,
created_at timestamp default now(),
foreign key (class_id) references class_tb(id),
foreign key (author_id) references user_tb(user_id)
);
-- Q&A 테이블 (질문-유저)
create table qna_question_tb(
    id int primary key auto_increment not null,
    title varchar(20) not null,
    user_id int not null,
    writer varchar(30) not null,
    content text not null,
    `status` int default 0, -- 질문／답변　판별용　컬럼　status 0 = 질문 / 1 = 답변
    reply_status int not null default 0,  -- 답변이 달린 질문인지 아닌지 판별용 컬럼 0일시 답변x 1일시 답변o
    created_at timestamp default now(),
    foreign key (user_id) references user_tb(user_id),
foreign key (writer) references user_tb(nickname)
);
-- 자주 묻는 질문 테이블
create table frequently_questions_tb(
id int auto_increment primary key,
title varchar(100) not null,
user_id int not null,
writer varchar(30) not null,
content blob not null,
reply_status int not null default 0,
created_at timestamp default now(),
foreign key (user_id) references user_tb(user_id),
foreign key (writer) references user_tb(nickname)
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
created_at timestamp default current_timestamp,
status int default 0,
foreign key (author_id) references user_tb(user_id)
);
-- 댓글 테이블
create table comment_tb (
id int primary key auto_increment not null,
post_id int not null,
content text not null,
author_id int not null,
created_at timestamp default current_timestamp,
status int not null default 0,
foreign key (author_id) references user_tb(user_id)
);

-- 채팅방 테이블
create table chat_room_tb(
id int primary key auto_increment primary key,
name varchar(50) not null,
head_count int default 0,
created_at timestamp default now()
);

-- 참여한 채팅방 테이블
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
created_at timestamp default now(),
foreign key (user_id_1) references user_tb(user_id),
foreign key (user_id_2) references user_tb(user_id)
);
-- 광고 위치 테이블
create table ad_place_tb(
id int primary key auto_increment not null,
title varchar(20),
image_file_name varchar(200),
price bigint
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
    status int default 0,
    foreign key (place_id) references ad_place_tb(id)
);

-- 친구 테이블
create table friend_tb(
id int primary key auto_increment not null,
user_id int,
friend_id int,
foreign key (user_id) references user_tb(user_id)
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
account_number VARCHAR(20),
foreign key (bank_id) references bank_info_tb(bank_id)
);
-- 캐쉬 사용 내역
CREATE TABLE spend_tb (
id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
user_id INT NOT NULL,
class_id INT NOT NULL,
spend BIGINT NOT NULL,
created_at TIMESTAMP DEFAULT NOW(),
foreign key (user_id) references user_tb(user_id),
foreign key (class_id) references class_tb(id)
);
-- 결제 테이블
create table order_tb(
id int primary key auto_increment,
order_id varchar(200),
user_id int,
name varchar(20),
amount bigint,
created_at timestamp default now(),
platform int, -- 0: 카카오페이, 1: 토스페이
status int default 0,
foreign key (user_id) references user_tb(user_id)
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
status int default 0,
foreign key (order_id) references order_tb(id),
foreign key (user_id) references user_tb(user_id)
);
-- 정산일 tb
create table exchange_schedule_tb(
    id int primary key auto_increment not null,
    year int default 2024,
    month int not null,
    day int not null
);

-- 친구 요청 테이블
create table waiting_friend_tb(
    id int primary key auto_increment,
    user_id int not null,
    friend_id int not null,
    foreign key (user_id) references user_tb(user_id),
    foreign key (friend_id) references user_tb(user_id)
);

-- 서브몰 신청 tb
create table register_submall_tb(
    id int auto_increment primary key,
    user_id int,
    bank_id int,
    created_at timestamp default now(0),
    status int default 0, -- 0: 승인 전, 1: 승인 후
    foreign key (user_id) references user_tb(user_id),
    foreign key (bank_id) references bank_tb(id)
);

-- 서브몰 tb
create table user_submall_tb(
    submall_id varchar(60) primary key,
    user_id int not null,
    type varchar(20) default 'INDIVIDUAL',
    status varchar(20) default 'PARTIALLY_APPROVED',
    account_id int, -- bank_tb id(fk)
    email varchar(100),
    phone_number varchar(100),    
    foreign key (user_id) references user_tb(user_id),
    foreign key (account_id) references bank_tb(id)
);

-- 환전 신청 tb
create table register_exchange_tb(
    id int auto_increment primary key,
    user_id int not null,
    submall_id varchar(50) not null,
    amount bigint not null,
    created_at timestamp default now(),
    status int not null default 0,
    foreign key (user_id) references user_tb(user_id),
    foreign key (submall_id) references user_submall_tb(submall_id)
);

-- 공지 및 뉴스
create table admin_content_tb(
	id int auto_increment primary key,
    title varchar(50),
    sub_title varchar(100),
    content blob,
    url text,
    type int, -- 0: 공지, 1: 뉴스
    created_at timestamp default now()
);


-- 알람 타입 tb
create table alarm_type_tb (
	id int primary key auto_increment,
    type varchar(20)
);

-- 알람 tb
create table alarm_tb (
	id int primary key auto_increment,
	type int not null,
    type_id int,
	user_id int not null,
    opponent_id int,
	content varchar(255),
    created_at timestamp default now(),
    status int default 0,
    foreign key (type) references alarm_type_tb(id)
);