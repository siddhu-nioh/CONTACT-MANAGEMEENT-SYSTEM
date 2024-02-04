create database contactinfo;
use contactinfo;
create table client(
s_no int primary key  ,
name varchar(20), 
organization_name varchar(20),
phone_number int,
mail_id varchar(20),
Designation varchar(20),
adress varchar(20),
task_meetingDate varchar(20),
 task_reminder int default 0
);
create table vendor( 
s_no int primary key  ,
name varchar(20), 
organization_name varchar(20),
phone_number int,mail_id varchar(20),
Designation varchar(20),
adress varchar(20),task_meetingDate varchar(20),
 task_reminder int default 0);
create table partner( 
s_no int primary key  ,
name varchar(20), 
organization_name varchar(20),
phone_number int,mail_id varchar(20),
Designation varchar(20),
adress varchar(20),task_meetingDate varchar(20),
 task_reminder int default 0);
create table user(id int auto_increment primary key,
name varchar(20),
password varchar(20) unique);
insert into user values(1,"admin","root1");
insert into user values(2,"admin","root2");
insert into user values(3,"admin3","root3");
select * from user;
select * from client;
select * from vendor;

insert into client values(1,"sid","root",122561,"sad","ceo","kochi","20-12-2023",0);
insert into vendor values(3,"sid","root",122561,"sad","ceo","kochi","20-12-2023",0);
insert into partner values(1,"sid","root",122561,"sad","ceo","kochi","20-12-2023",0);
insert into client values(4,"sid","root",122561,"sad","ceo","kochi","21-12-2023",0);
insert into vendor values(4,"sid","root",122561,"sad","ceo","kochi","20-12-2023",0);
insert into partner values(2,"sid","root",122561,"sad","ceo","kochi","20-12-2023",0);
alter table vendor
add column meetingDate date ;
 alter table vendor drop column meetingDate;
 alter table vendor
add column task_meetingDate date  ;
alter table client
add column task_meetingDate date  ;
alter table partner
add column task_meetingDate date  ;
alter table vendor
add column task_reminder int default 0  ;
alter table client
add column task_reminder int default 0  ;
alter table partner
add column task_reminder int default 0  ;
drop table vendor;
drop table client;
 drop table partner;
 
 