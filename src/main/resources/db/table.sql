create database shallwe_db;

-- 직함 테이블
create table role_tb(
	id int primary key auto_increment not null,
    name varchar(20) unique key not null
);

-- 사용자 테이블
create table user_tb(
	id int primary key auto_increment not null,
    role_id int,
    username varchar(20) not null,
    user_id varchar(20) unique key not null,
    password varchar(20) not null,
    nickname varchar(20) not null unique key, 
    birth_date date not null,
    email varchar(50) unique key,
    origin_file_name varchar(200),
    upload_file_name varchar(200),
    cash bigint not null default 200,
    challenge_point bigint not null default 200,
    status int not null default 0
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
    genre_name varchar(20)
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
create table user_cash_history_tb(
	id int primary key auto_increment not null,
    user_id int not null,
    charge_amount bigint,
    spend_amount bigint,
    created_at Timestamp default now()
);

-- 강의 테이블
create table class_tb(
	id int primary key auto_increment not null,
    title varchar(20) not null,
    content text not null,
    limit_num int,
    current_num int,
    price bigint,
    total_num int,
    status int,
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
    num int
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
    content text not null,
    author_id int not null,
    created_at timestamp default now()
);

-- 이벤트 테이블
create table event_tb(
	id int primary key auto_increment not null,
    title varchar(20) not null,
    content text not null,
    author_id int not null,
    created_at timestamp default now()
);

-- Q&A 테이블 (질문-유저)
create table qna_question_tb(
	id int primary key auto_increment not null,
    title varchar(20) not null,
    content text not null,
    author_id int not null,
    answer_id int not null,
    created_at timestamp default now()
);

-- Q&A 테이블 (답변-관리자)
create table qna_answer_tb(
	id int primary key auto_increment not null,
    content text not null,
    author_id int not null,
    created_at timestamp default now()
);


-- 커뮤니티 게시판 테이블
create table board_tb (
	id int primary key auto_increment not null,
    category_id int not null,
    title varchar(20) not null,
    content text not null,
    author int not null,
    view_num int default 0,
    good int default 0,
    created_at timestamp default current_timestamp
);
-- 댓글 테이블
create table comment_tb (
	id int primary key auto_increment not null,
    post_id int not null,
    content text not null,
    author int not null,
    create_at timestamp default current_timestamp
);

-- 채팅방 테이블
create table chat_room_tb(
	id int primary key auto_increment not null,
    user_id_1 int not null,
    user_id_2 int not null,
    created_at timestamp default now()
);

-- 광고 테이블
create table advertise_tb(
	id int primary key auto_increment not null,
    place_id int not null,
    title varchar(20),
    customer_id int,
    link text,
    origin_file_name varchar(200),
    upload_file_name varchar(200),
    created_at timestamp default now(),
    start_date timestamp,
    end_date timestamp,
    status int
);

-- 광고 위치 테이블
create table ad_place_tb(
	id int primary key auto_increment not null,
    title varchar(20),
    image_name varchar(200)
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