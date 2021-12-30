/*
 * Normalization is the process of reducing redundancy on tables/across an entire schema. There
 * are various levels of normalization. The levels that you are required to know according to the
 * curriculum:
 * 
 * 0NF: No normalization whatsoever
 * 1NF: There should be a unique identifer for each record and all data should be atomic (we should
 * 	be storing the smallest amount of data in each column)
 * 2NF You get 2NF for free if you don't have a composite key. If you have a composite key and a
 * 	column that is not a part of your key depends on only a portion of your key (partial dependency),
 * you are not in 2NF
 * 3NF: There can be no transitive dependencies on your table (there should not a column C which
 * 	depends on a column B which depends on a column A)
 */

drop table person_0nf;
create table person_0nf(
person_name varchar unique,
person_social_security_number integer,
person_age integer,
person_birthdate date
);

create table person_1nf(
person_id serial unique not null,
person_social_security_number integer unique not null,
person_first_name varchar,
person_last_name varchar,
person_birthdate integer,
person_age integer,
constraint person_pk primary key(person_id, person_social_security_number)
);

drop table person_2nf;
create table person_2nf(
person_social_security_number integer primary key,
person_first_name varchar,
person_last_name varchar,
person_birthdate date, --Note that we cannot escape 2NF if we have the age and birthdate on this table
person_age integer
);

drop table person_3nf;
create table person_3nf(
person_social_security_number integer primary key,
person_first_name varchar,
person_last_name varchar,
person_birthdate date
);

-- Lookup table example
create table account_status(
account_status_id serial primary key,
status_name varchar
);

create table bank_account(
account_id serial primary key,
account_status integer references account_status(account_status_id)
);

select * from account_status;


