
/*
 * Drop/create table to hold customer personal data and attach each username to an account
 */

drop table users;
create table users (
	user_name varchar primary key,
	first_name varchar,
	last_name varchar,
	pass_word varchar,
	date_of_birth varchar,
	email varchar,
	address varchar,
	account_id integer references accounts(account_id) on delete cascade
);

/*
 * Drop/create table to hold account data
 */

drop table accounts;
create table accounts (
	account_id serial primary key,
	account_balance numeric
);

/*
 *  Drop/create table to hold employee user data
 */

drop table employees;
create table employees (
	employee_name varchar primary key,
	employee_password varchar,
	aministrator boolean
);

insert into employees values('John', 'pass123', true);
insert into employees values('Alan', 'pass123', false);

select * from employees;

insert into accounts values(default, 300.25);
insert into accounts values(default, 0.00);

insert into users values('JackJoe', 'Jack', 'Johnson', 'pass123', '08/01/1990', 'email@email.com', '123 Road Rd.', 1);
insert into users values('Jack', 'Jack', 'Johnson', 'pass123', '03MAR90', 'email@email.com', '123 Road Rd.', 1);
insert into users values('joe', 'Joe', 'Jo', 'pass123', 'January 5, 1990', 'email@email.com', '123 Road Rd.', 3);

select * from users;
select * from accounts;
select * from accounts where account_id = 2;

delete from accounts where account_id > 7 or account_id = 0;

delete from accounts where account_id = 2;

select * from accounts order by account_id desc limit 1;

delete from employees;

select * from users where user_name = 'joe';


