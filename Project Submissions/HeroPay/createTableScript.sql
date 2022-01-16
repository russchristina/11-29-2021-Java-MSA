

select * from employee_account;
drop table employee_account;

create table employee_account(
id serial primary key,
first_name varchar,
last_name varchar,
role integer references employee_role(id)
);


select * from employee_role;
drop table employee_role;

create table employee_role(
id serial primary key,
role_name varchar unique not null
);


select * from login_info;
drop table login_info;

create table login_info(
id serial primary key,
username varchar unique not null,
password varchar not null,
employee_id integer references employee_account(id)
);


select * from pending_request;
drop table pending_request;

create table pending_request(
id serial primary key,
employee_id integer references employee_account(id),
type integer references request_type(id),
request_message varchar,
amount numeric not null,
date_submission date not null,
status boolean default false
);

select * from request_type;
drop table request_type;

create table request_type(
id serial primary key,
type varchar unique not null
);


select * from completed_request;
drop table completed_request;

create table completed_request(
completed_request_id integer references pending_request(pending_request_id),
employee_id integer references employee_account(id),
manager_id integer references employee_account(id),
status boolean,
response varchar,
date_resolved date not null
);
truncate completed_request;

alter table completed_request 
drop column employee_id;

create sequence pending_request_id_seq start 1;
create sequence completed_request_unique_id_seq start 1;

alter table completed_request add column unique_id serial primary key;
alter table completed_request rename column id to completed_request_id;
alter table pending_request rename column id to pending_request_id;
alter table login_info rename column id to login_info_id;

alter table pending_request add column file_upload_check boolean;

update pending_request set file_upload_check = false;

