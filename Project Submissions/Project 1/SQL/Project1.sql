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
 * Drop/create table to hold status data
 */
drop table request_status;
create table request_status (
	status_id integer primary key,
	status_name varchar
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
	status integer references request_status(status_id),
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
 * Insert status data: pending, approved, denied
 */
insert into request_status values(1, 'Pending');
insert into request_status values(2, 'Approved');
insert into request_status values(3, 'Denied');

select * from request_status;

/*
 * Insert request data for testing
 */
insert into requests values(default, 'Misty', 200, 'Travel', 1);
insert into requests values(default, 'Misty', 200, 'Travel', 2, 'Have fun!');
insert into requests values(default, 'Brock', 200, 'Travel', 2);
insert into requests values(default, 'Brock', 500, 'Travel', 3);


select * from requests;
select * from requests where status = 1;
select employee_name, amount from requests where status = 2 order by amount desc limit 1;
select amount from requests;
select count(1) from requests;

update requests set status = 3, note = 'Too much travel. Need to word more!' where request_id = 5;
