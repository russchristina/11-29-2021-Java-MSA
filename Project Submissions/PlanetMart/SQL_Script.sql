


create table user_credentials(

id serial primary key,
username varchar unique not null,
password varchar not null,
first_name varchar not null,
last_name varchar not null

);



create table customer_account(

customer_account_id serial primary key,
primary_user_id integer references user_credentials(id)


);

alter table customer_account drop column name_of_user;


create table customer_users(

user_id serial primary key,
user_name varchar not null,
inventory_id integer references customer_inventory(inventory_id),
customer_account_id integer references customer_account

);

create table employee_account(

employee_id serial primary key,
user_id integer references user_credentials(id) on delete cascade,
admin_id integer references employee_account(employee_id)

);


drop table employee_account;

create table customer_inventory(

inventory_id serial primary key,
balance numeric not null check (balance >= 0)

);


alter table customer_inventory add column user_id integer references customer_users(user_id);

alter table customer_inventory drop column user_id;


select * from customer_account_users ;
select * from customer_inventory;

create table planets(

planet_id serial primary key,
planet_name varchar not null,
planet_user_id integer references customer_users(user_id) on delete cascade,
goldilocks_zone boolean,
water_percent numeric,
average_temp numeric

);




create table atmosphere_content(

atmosphere_id serial primary key,
water numeric not null,
oxygen numeric not null,
hydrogen numeric not null,
nitrogen numeric not null,
argon numeric not null,
helium numeric not null,
carbon_dioxide numeric not null,
methane numeric not null,
chlorine numeric not null,
unknown numeric not null,
planet_id integer

);

alter table atmosphere_content add column user_id integer references customer_users(user_id);
alter table atmosphere_content add column planet_id integer references planets(planet_id);
alter table atmosphere_content add column unknown numeric not null;

create table life_forms(

life_id serial primary key,
life_name varchar not null,
population numeric not null check (population > 0),
technology_level numeric not null,
planet_id integer

);

alter table life_forms add column user_id integer references customer_users(user_id);
alter table life_forms add column planet_id integer references planets(planet_id)


alter table customer_account add column user_credential_id integer references user_credentials(id);
alter table customer_account add column primary_user_id integer references customer_users(user_id);



insert into user_credentials values(default, 'user1', 'pass1', 'John', 'Doe'), (default, 'user2', 'pass2', 'Johno', 'Doeo'), (default, 'user3', 'pass3', 'Mohn', 'Noe'), (default, 'user4', 'pass4', 'Lee', 'dEe');

insert into employee_account values(default, 3, 1), (default, 4, 1);
insert into customer_account values(default, 1), (default, 2);


insert into customer_users values(default, 'John', 1, 1), (default, 'Johno', 2, 2); 

insert into customer_inventory values(default, 10000), (default, 41232), (default, 12314), (default, 12355);

insert into customer_users values(default, 'Maria', 3, 1), (default, 'Ruben', 4, 2);


alter table customer_users add constraint inventoryKey foreign key (inventory_id) references customer_inventory(inventory_id);




truncate customer_users cascade;

alter table customer_users drop column inventory_id;
alter table customer_users drop column customer_account_id;
alter table customer_users add column inventory_id integer references customer_inventory(inventory_id) on delete cascade;
alter table customer_users add column customer_account_id integer references customer_account(customer_account_id) on delete cascade;

alter table customer_account drop column user_credential_id;
alter table customer_account drop column primary_user_id;

alter table customer_account add column user_credential_id integer references user_credentials(id) on delete cascade;
alter table customer_account add column primary_user_id integer;


alter table planets drop column planet+

insert into user_credentials values(default, 'ADMIN', 'ADMIN', 'ADMIN', 'ADMIN');

insert into employee_account values(default, 28, null);
