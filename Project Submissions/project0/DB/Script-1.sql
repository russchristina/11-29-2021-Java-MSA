create table users(first_name varchar, last_name varchar, username varchar, passwords varchar);
drop table users;
insert into users values('atharva','bhagwat','atharvab','atharvab',true);
insert into users values('john','snow','jsnow','jsnow');

create table employee(first_name varchar, last_name varchar, username varchar, passwords varchar);
insert into employee values('john','snow','jsnow','jsnow');

drop table customer (account_id int , username varchar, passwords varchar);
alter table customer add primary key (account_id);
alter table customer add unique (username);

create table customer(first_name varchar, last_name varchar, username varchar, passwords varchar, bank varchar);