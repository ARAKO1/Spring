create table board(
seq number primary key,
title varchar2(30),
writer varchar2(30),
content varchar2(2000),
regDate date default sysdate,
cnt number(3)
);
drop table board;
select*from board;
