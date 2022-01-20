drop table employee;
create table employee(
emp_id serial primary key,
emp_name varchar not null unique,
emp_username varchar not null unique,
emp_password varchar not null,
emp_ismanager boolean not null
);

insert into employee values (default, 'John Johnson', 'jj', 'jj', 'false');
insert into employee values (2,'Dave Davidson','dd','dd','false');
insert into employee values (3,'Pete Peterson','pp','pp','false');
insert into employee values (4,'Manny McManager','mm','mm','true');


drop table reimb;
create table reimb(
reimb_id serial primary key,
emp_name varchar references employee(emp_name),
reimb_amt numeric not null,
reimb_desc varchar not null,
reimb_status varchar not null,
reimb_note varchar
);

insert into reimb values(default,'John Johnson', 15.00, 'Parking', 'Pending');
insert into reimb values(9,'John Johnson', 100.00,'Car Rental','Pending');
insert into reimb values(3,'John Johnson',50.00,'Business Lunch','Pending');
insert into reimb values(4,'Dave Davidson',250.00,'Plane Ticket','Approved');
insert into reimb values(5,'Dave Davidson',103.24,'Hotel','Approved');
insert into reimb values(6,'Dave Davidson',996.44,'Business Dinner','Denied','We need to talk.');
insert into reimb values(7,'Pete Peterson',80.00,'Monthly Fuel','Approved');
insert into reimb values(8,'Pete Peterson',30.00,'Monthly Parking','Approved');
A
select * from reimb;
select * from employee;

create table app_user(
username varchar,
password varchar
);

