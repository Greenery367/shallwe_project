create database db_shallwe;

-- 직함 테이블
create table role_tb(
	id int primary key auto_increment not null,
    name varchar(20) unique key not null
);

-- 사용자 테이블
create table user_tb(
	user_id int primary key auto_increment not null,
    role_id int not null, -- 직함 id
    username varchar(20) not null,
    id varchar(20) unique key not null,
    password varchar(20) not null,
    nickname varchar(20) not null unique key, 
    birth_date date not null,
    email varchar(50) unique key not null,
    origin_file_name varchar(200) not null,
    upload_file_name varchar(200) not null,
    current_cash bigint not null default 200, -- 캐시, 가입 시 200점
    challenge_point bigint not null default 200, -- 내기 포인트, 가입시 200점 
    status int not null -- 0 회원 가입 유지, 1 탈퇴
);

-- mbti 테이블
create table mbti_tb(
	id int primary key auto_increment not null,
    name varchar(10) not null,  -- mbti 명
    nickname varchar(20) not null, -- 직업
    content text not null -- mbti 설명
);

-- mbti-user 테이블
create table mbti_user_tb(
	id int primary key auto_increment not null,
    user_id int not null,
    mbti_id int not null
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

-- 장르 테이블
create table genre_tb(
	id int auto_increment primary key,
    genre_name varchar(20) not null primary key
);

-- 게임 카테고리 테이블
create table game_tb(
	id int primary key not null auto_increment,
    game_name varchar(20) not null,
    genre_id int not null
);

-- 신고 타입 테이블
create table report_type_tb(
	id int primary key not null auto_increment,
    type varchar(20) -- 어디서 신고했는지(유저, 게시글, 댓글, 강의)
);

-- 캐시 사용 타입 테이블
create table cash_type_tb(
	id int primary key auto_increment not null,
    type varchar(10) not null
);

-- 사용자-캐시 히스토리 테이블
create table user_cash_history(
	id int primary key auto_increment not null,
    user_id int primary key auto_increment not null,
    charge_amount bigint,
    spend_amount bigint,
    created_at Timestamp default now()
);





