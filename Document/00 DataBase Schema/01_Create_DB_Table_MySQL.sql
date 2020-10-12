CREATE TABLE tbl_board(
  bid      int auto_increment comment '일련번호' primary key,
  cate_cd  varchar(20)   comment '게시글 카테고리',
  title    varchar(200)  not null comment '제목',
  content  text          not null comment '게시글',
  tag      varchar(1000) null comment '태그',
  view_cnt int default 0 not null comment '카운트',
  reg_id   varchar(45)   not null comment '작성자',
  reg_dt   timestamp          not null comment '작성일',
  edit_dt  timestamp          not null comment '수정일'
);

-- 제약조건에 cascade 추가 2020.09.23
create table tbl_reply(
    rid int auto_increment comment '일련번호'  primary key,
    bid int not null comment '게시물 일련번호',
    content text        null comment '댓글내용',
    reg_id  varchar(45) not null comment '작성자',
    reg_dt  timestamp   not null comment '작성일',
    edit_dt timestamp   not null comment '수정일',
    constraint tbl_reply_tbl_board_bid_fk
    foreign key (bid) references tbl_board (bid) on delete cascade
);

ALTER table tbl_reply add constraint tbl_reply_tbl_board_bid_fk foreign key(bid) references tbl_board(bid) on delete cascade;

ALTER TABLE tbl_reply DROP CONSTRAINT tbl_reply_tbl_board_bid_fk;


-- menu 테이블 추가.
create table tbl_menu (   
    mid int auto_increment primary key comment '메뉴번호',   
    code     varchar(10) not null comment '코드',   
    codename varchar(50)   null comment '코드명', 
    sort_num int null           comment '정렬번호',   
    comment  varchar(1000) null comment '코멘트',   
    reg_id   varchar(20)   null comment '등록ID',   
    reg_dt   timestamp     null comment '등록일자',   
    constraint tbl_menu_code_uindex unique (code) 
);
-- code 값은 '유니크'한 값을 가져야함. constraint 설정.

-- 회원정보 2020.10.12
create table tbl_user ( 
    uid varchar(20) not null primary key comment '유저ID', 
    name varchar(100) not null comment '이름', 
    pwd varchar(100) not null comment '비밀번호', 
    email varchar(100) null comment '이메일', 
    grade varchar(30) null comment '등급', 
    reg_dt timestamp null comment '등록날짜' 
);

