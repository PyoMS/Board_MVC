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


create table tbl_reply(
    rid int auto_increment comment '일련번호'  primary key,
    bid int not null comment '게시물 일련번호',
    content text        null comment '댓글내용',
    reg_id  varchar(45) not null comment '작성자',
    reg_dt  timestamp   not null comment '작성일',
    edit_dt timestamp   not null comment '수정일',
    constraint tbl_reply_tbl_board_bid_fk
    foreign key (bid) references tbl_board (bid)
);