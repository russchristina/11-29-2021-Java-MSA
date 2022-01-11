
drop view employee_details_and_login;
select * from employee_details_and_login;

create view employee_details_and_login as
select ea.id as "employee_id", username , "password" ,first_name, last_name, role_name
from login_info li 
full join employee_account ea 
on li.employee_id = ea.id 
full join employee_role er 
on ea."role" = er.id;


drop view manager_details;
select * from manager_details;

create view manager_details as
select ea.id as "employee_id", first_name, last_name
from login_info li
full join employee_account ea 
on li.employee_id = ea.id 
full join employee_role er 
on ea."role" = er.id
where ea."role" = 4;


select * from knight_details;
drop view knight_details;

create view knight_details as
select ea.id as "employee_id", first_name, last_name
from login_info li
full join employee_account ea 
on li.employee_id = ea.id 
full join employee_role er 
on ea."role" = er.id
where ea."role" = 1;


select * from cleric_details;
drop view cleric_details;

create view cleric_details as
select ea.id as "employee_id", first_name, last_name
from login_info li
full join employee_account ea 
on li.employee_id = ea.id 
full join employee_role er 
on ea."role" = er.id
where ea."role" = 2;

create view request_with_role_id;
select pr.id, pr.employee_id, er.id as role_id, pr."type" , pr.request_message , pr.date_submission , pr.status 
from pending_request pr 
full join employee_role er 
on pr.employee_id = er.id ;

select * from employee_role er ;

select * from mage_details;
drop view mage_details;

create view mage_details as
select ea.id as "employee_id", first_name, last_name
from login_info li
full join employee_account ea 
on li.employee_id = ea.id 
full join employee_role er 
on ea."role" = er.id
where ea."role" = 3;

create view quick_sum as
select employee_id , sum (amount) 
from pending_request pr 
where status = true
group by employee_id
order by sum (amount) asc;

select * from quick_sum;

