/*
 * SQL stands for "Structured Query Language"; it has a specific syntax and keywords that you must
 * familiarize yourself with. That said, this syntax is simpler than the syntax you see for programming
 * languages.
 * 
 * We use SQL to manage databases (DBs) - create new tables, insert records into tables, delete records, update
 * records, etc.
 * 
 * SQL as a language is divided into multiple "sublanguages". The sublanguages denote which keywords are used
 * to complete certain types of tasks. The sublanguages are as follows:
 * 
 * DDL -> Data Definition Language (e.g. create table, alter table, drop table, etc.): Geared towards
 * 		  the creation and removal of entities (structures that will hold our data). How am I storing my
 * 		  data?
 * 
 * DML -> Data Manipulation Language (e.g. insert, update, delete, select): Geared towards creating
 * 		  records and modifying those records. What data am I inserting/deleting?
 * 
 * DQL -> Data Query Language (e.g. select): Geared towards reading records
 * 
 * TCL -> Transaction Control Language (e.g. begin transaction, commit, rollback, savepoint): Geared
 * 		  towards finalizing or reverting changes to our database.
 * 
 * DCL -> Data Control Language (e.g. revoke, grant, create user): Geared towards managing access to
 * 		  your DB.
 * 
 * The most basic SQL commands are geared towards table creation and the insertion and updating of
 * records. Having said that, we are going to first create some "tables". A table is a structure that
 * contains a collection of records (each record represented as a "row" on your table).
 * 
 * The syntax for dropping and creating a table is as follows.
 * 
 * PostgreSQL is a database management system (DBMS). In fact, it is considered a relational
 * database management system (RDBMS). A relational database management system allows us to
 * establish relationships between our tables (also called "entities"). These relations allow us
 * to preserve "referential integrity".
 * 
 * In order to create relationships between tables, we use "foreign keys". A foreign key is a column
 * whose value points to an existing record on another table. In fact, a foreign key must point
 * to an existing record on another table.
 * 
 * When we create relationships between tables, we describe them using "multiplicity". Examples of
 * multiplicities include:
 * 
 * Many-To-One/One-To-Many
 * One-To-One (e.g. A person and their social security number)
 * Many-To-Many (often modeled with a bridge/join table)
 */

drop table recipe;
create table recipe(
--each column on a table represents a piece of data that belongs to a recipe record
recipe_id numeric primary key, --primary keys are implicitly unique and not null; they serve as unique identifiers for records
recipe_name varchar,
recipe_cookTimeInMinutes numeric check (recipe_cookTimeInMinutes > 0),
author_id numeric references author(author_id) --this is a foreign key that points to a column on another table
);

/*
 * To be proper, if we wish to add a new column to this table, we should alter it. Alter is a keyword
 * that is used to modify an existing table (among other things). You shouldn't just drop a table
 * to make a small modification to it.
 */

alter table recipe add column "author" varchar;

drop table author;
create table author(
author_id numeric unique not null,
author_name varchar not null, --not null constraints prevent us from inserting null values
author_speciality varchar
);

-- Creating our ingredient table

drop table ingredient;
create table ingredient(
ingredient_id numeric primary key,
ingredient_name varchar,
ingredient_flavor varchar
);

/*
 * A bridge table is used to model a many-to-many relationship. A bridge is simply a table that
 * is composed of foreign keys. Usually a bridge table is named after the two tables/entities you're
 * creating the relationship between.
 */

drop table recipe_ingredient;
create table recipe_ingredient(
	recipe_id numeric references recipe(recipe_id),
	ingredient_id numeric references ingredient(ingredient_id),
	-- This table has a composite primary key which consists of two columns
	constraint recipe_ingredient_pk primary key(recipe_id, ingredient_id)
);

/*
 * Now that we have a table that is capable of storing recipes, let's insert some records into
 * that table. Here is the syntax for doing so:
 */

insert into recipe values(1, 'Birthday Cake', 80, 1);
insert into recipe values(2, 'Candy Corn', 2, 2), (3, 'Burger', 30, 3), (4, 'Water', 1, 2);
insert into recipe values(5, 'Pancakes', 30, 4);
insert into recipe values(6, 'Pizza', 40, 2);
insert into recipe values(7, 'Cereal', 3, null);

insert into author(author_id, author_name, author_speciality) values(1, 'Gordon', 'Steak');
insert into author values(2, 'Bobby', 'Not being beaten very often.');
insert into author values(3, 'Christina', 'Burning food sometimes');
insert into author values(4, 'Grandmas Everywhere', 'Using a pinch of every ingredient');
insert into author values (1, 'Pioneer Woman', 'Themed Special'); --can't insert this because of the unique constraint
insert into author values(null, null, null); --yes, you can do this if you don't have constraints to prevent it

insert into ingredient values(1, 'Salt', 'salty');
insert into ingredient values(2, 'Pepper', 'spicy');
insert into ingredient values(3, 'Clove', 'spicy');
insert into ingredient values(4, 'Celery Salt', 'salty');

--Inserting into our bridge table
insert into recipe_ingredient values(1, 1);
insert into recipe_ingredient values(1, 2);
insert into recipe_ingredient values(5, 1);
insert into recipe_ingredient values(1, 1); --this will not work as their is pk constraint placed on these columns together

select * from recipe_ingredient;

/*
 * Once records exist on the table, you can query the table using a select statement. The "select"
 * keywrd is simpy used for reading from tables. The syntax for reading from a table is as follows.
 * 
 * Note that the "*" means you're selecting all columns on the table. You can be more specific about
 * the columns you're selecting as well.
 */

select * from recipe;

select recipe_name, recipe_id, recipe_cooktimeinminutes from recipe;

/*
 * What if you just want to select specific records? You can use the "where" keyword to filter
 * your records down. Let's imagine that we only want recipes with cook times less than or equal
 * to 30 minutes. Note that you can do compound where clauses (e.g. using the "and" or "or"
 * keywords).
 */

select * from recipe where recipe_name = 'Birthday Cake';

/*
 * You can also order your records by a certain column after retrieval; just use the "order by"
 * keyword to achieve this.
 */

select * from recipe order by recipe_cooktimeinminutes;

select * from recipe order by recipe_cooktimeinminutes desc;

/*
 * You can also search for records that are between two values, etc. To do this, we use the
 * "between" keyword to get records that fall between a certain range.
 */

select * from recipe where recipe_cooktimeinminutes between -1 and 30;

/*
 * How do we update a record? We can use the "update" keyword. Let's make the "Burger" record
 * the "Best Burger" instead.
 * 
 * Please note that when updating or deleting records, we should be using where clauses; if we do
 * not use where clauses, every record on the table will be affected by the query.
 */

update recipe set recipe_name = 'Best Burger'; -- Bad Query
update recipe set recipe_name = 'Best Burger' where recipe_name = 'Burger'; -- Better Query
update recipe set author = 'Bobby' where recipe_name = 'Best Burger';

/*
 * If you wish to remove a record from a table, you can use the "delete" keyword. Let's delete
 * Candy Corn from our recipe table because Candy Corn is gross (in my opinion). The syntax for
 * deleting a record is as follows:
 */

delete from recipe where recipe_name = 'Candy Corn'; -- Again, where clauses are important.

/*
 * Thus far, we've seen:
 * 
 * Creating New Records
 * Reading Records
 * Updating Records
 * Deleting Records
 * 
 * We refer to these actions as basic CRUD - Create, Read, Update, Delete.
 */

select * from recipe;




