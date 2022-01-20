
-- catering company reimbursement 

drop table employee_login cascade;
create table employee_login(
	employee_login_id serial primary key,
	employee_login_name varchar,
	employee_login_password varchar
);

drop table manager_login;
create table manager_login(
	manager_login_id serial primary key,
	manager_login_name varchar,
	manager_login_password varchar	
);

drop table reimbursements_Ben cascade;
create table reimbursements_Ben(
	reimbursed_invoice_num serial primary key, -- serial makes the id climb automatically
	reimbursed_employee varchar,
	reimbursed_past_pending varchar,
    reimbursed_approval varchar,
	reimbursed_amount numeric,
	reimbursed_reason varchar,
	reimbursed_manager_reason varchar
);

drop table reimbursements_Alli cascade;
create table reimbursements_Alli(
	reimbursed_invoice_num serial primary key, -- serial makes the id climb automatically
	reimbursed_employee varchar,
	reimbursed_past_pending varchar,
    reimbursed_approval varchar,
	reimbursed_amount numeric,
	reimbursed_reason varchar,
	reimbursed_manager_reason varchar
);

drop table reimbursements_Sam cascade;
create table reimbursements_Sam(
	reimbursed_invoice_num serial primary key, -- serial makes the id climb automatically
	reimbursed_employee varchar,
	reimbursed_past_pending varchar,
    reimbursed_approval varchar,
	reimbursed_amount numeric,
	reimbursed_reason varchar,
	reimbursed_manager_reason varchar
);



insert into employee_login values(1, 'Ben Employee', 'benemployee');
insert into employee_login values(2, 'Alli Employee', 'alliemployee');
insert into employee_login values(3, 'Sam Employee', 'samemployee');
insert into employee_login values(4, 'Jerry Admin', 'jerryadmin');

insert into manager_login values(1, 'Jerry Admin', 'jerryadmin');

insert into reimbursements_Ben values(333,'Ben Employee','pending','not approved', 4.69, 'Birthday candles for event.','awaiting reason');
insert into reimbursements_Ben values(336, 'Ben Employee', 'complete', 'approved', 3.71, 'Pens for dinner guests at event.', 'Forgot to get them. Thanks!');
insert into reimbursements_Ben values(337, 'Ben Employee', 'complete', 'approved', 5.24, 'Lighters for the tables at event', 'Forgot to get them. Thanks!');
insert into reimbursements_Ben values(342, 'Ben Employee', 'pending', 'not approved', 14.09, 'Copies of menu at copiers', 'awaiting reason');
insert into reimbursements_Ben values(339, 'Ben Employee', 'complete', 'not approved', 3.12, 'Bus ticket to work', 'Sorry, transportation is not paid for.');

insert into reimbursements_Alli values(334, 'Alli Employee', 'complete', 'approved', 10.25, 'Work lunch meeting.', 'Work meeting, thanks!');
insert into reimbursements_Alli values(338, 'Alli Employee', 'pending', 'not approved', 13.54, 'Napkins for birthday event', 'awaiting reason');
insert into reimbursements_Alli values(341, 'Alli Employee', 'complete', 'not approved', 53.02, 'Phone bill', 'Phone bills are not covered.');
insert into reimbursements_Alli values(343, 'Alli Employee', 'complete', 'approved', 17.08, 'Plastic cups for event', 'Event planner forgot them. Thanks!');

insert into reimbursements_Sam values(340, 'Sam Employee', 'complete', 'approved', 30.98, 'Two bottles of wine for guests', 'Event planner forgot them. Thanks!');
insert into reimbursements_Sam values(335, 'Sam Employee', 'complete', 'not approved', 20.53, 'Tie for wedding event.', 'Outfits are not compensated.');


drop table stats_amount cascade;
create table stats_amount
as
  select reimbursed_employee, reimbursed_amount 
  from reimbursements_Ben rb
  union all
  select reimbursed_employee, reimbursed_amount
  from reimbursements_Alli ra
  union all
  select reimbursed_employee, reimbursed_amount
  from reimbursements_Sam rs

