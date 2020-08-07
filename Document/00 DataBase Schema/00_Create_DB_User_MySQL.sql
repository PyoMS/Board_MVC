--database 생성
CREATE DATABASE 'mess';
--사용자 추가
CREATE USER 'mess'@'%' IDENTIFIED BY 'mess';
--접근권한 부여
GRAND ALL PRIVILEGES ON MESS.* TO 'mess'@'%';
--설정 반영
FLUSH PRIVILEGES;
