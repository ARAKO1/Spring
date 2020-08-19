create table users(
id varchar2(20) primary key,
password varchar2(20),
name varchar2(20),
role varchar2(20)
);

select * from users;
insert into users(id, password, name, role) values('admin','1234','관리자','admin');
insert into users(id, password, name, role) values('test','test1234','사용자','user');