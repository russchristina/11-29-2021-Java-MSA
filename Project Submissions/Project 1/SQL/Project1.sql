/* 
 * Drop/create table to hold employee data
 */
drop table employees;
create table employees (
	employee_name varchar primary key,
	employee_password varchar,
	isManager boolean
);

/*
 * Drop/create table to hold request data
 */
drop table requests;
create table requests (
	request_id serial primary key,
	employee_name varchar references employees(employee_name),
	amount numeric,
	reason varchar,
	status varchar,
	note varchar
);

/*
 * Insert employee data: 1 employee, 2 managers
 */
insert into employees values('Brock', 'pass123', false);
insert into employees values('Misty', 'pass123', true);
insert into employees values('May', 'pass123', true);

select * from employees;

/*
 * Insert request data for testing
 */
insert into requests values(default, 'Misty', 200.00, 'Travel', 'Approved', 'Have fun!');
insert into requests values(default, 'Misty', 100.00, 'Travel', 'Pending');
insert into requests values(default, 'Brock', 300.00, 'Travel', 'Approved');
insert into requests values(default, 'Brock', 500.00, 'Travel', 'Denied');
insert into requests values(default, 'Brock', 400.00, 'Travel', 'Pending');


select * from requests;
select * from requests where status = 'Pending';
select employee_name, amount from requests where status = 'Approved' order by amount desc limit 1;
select amount from requests;
select count(1) from requests;
select * from requests where employee_name = 'Brock';

update requests set status = 'Denied', note = 'Too much travel. Need to work more!' where request_id = 5;
