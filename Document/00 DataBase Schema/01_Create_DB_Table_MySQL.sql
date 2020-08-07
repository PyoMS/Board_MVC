CREATE TABLE tbl_board(
  bid      int auto_increment comment '일련번호' primary key,
  cate_cd  varchar(20)   not null comment '게시글 카테고리',
  title    varchar(200)  not null comment '제목',
  content  text          not null comment '게시글',
  tag      varchar(1000) null comment '태그',
  view_cnt int default 0 not null comment '카운트',
  reg_id   varchar(45)   not null comment '작성자',
  reg_dt   date          not null comment '작성일',
  edit_dt  date          not null comment '수정일'
);
