


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
user_id integer references user_credentials(id),
admin_id integer references employee_account(employee_id)

);




create table customer_inventory(

inventory_id serial primary key,
balance numeric not null check (balance >= 0),

);


alter table customer_inventory add column user_id integer references customer_users(user_id);

alter table customer_inventory drop column user_id;


select * from customer_account_users ;
select * from customer_inventory;

create table planets(

planet_id serial primary key,
life_form_id integer references life_forms(life_id),
planet_name varchar not null,
planet_user_id integer references customer_users(user_id),
goldilocks_zone boolean,
water_percent numeric,
average_temp numeric,
atmosphere_composition integer references atmosphere_content(atmosphere_id)

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
chlorine numeric not null

);

alter table atmosphere_content add column user_id integer references customer_users(user_id);
alter table atmosphere_content add column planet_id integer references planets(planet_id)


create table life_forms(

life_id serial primary key,
life_name varchar not null,
population numeric not null check (population > 0),
technology_level numeric not null

);

alter table life_forms add column user_id integer references customer_users(user_id);
alter table life_forms add column planet_id integer references planets(planet_id)
