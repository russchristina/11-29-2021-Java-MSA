/*
 * SQL stands for "Structured Query Language"; it has a specific syntax and keywords that you must
 * familiarize yourself with. That said, this syntax is simpler than the syntax you see for programming
 * languages.
 * 
 * We use SQL to manage databases (DBs) - create new tables, insert records into tables, delete records, update
 * records, etc.
 * 
 * The most basic SQL commands are geared towards table creation and the insertion and updating of
 * records. Having said that, we are going to first create some "tables". A table is a structure that
 * contains a collection of records (each record represented as a "row" on your table).
 * 
 * The syntax for dropping and creating a table is as follows:
 */

drop table recipe;
create table recipe(
--each column on a table represents a piece of data that belongs to a recipe record
recipe_id numeric,
recipe_name varchar,
recipe_cookTimeInMinutes numeric
);

/*
 * Now that we have a table that is capable of storing recipes, let's insert some records into
 * that table. Here is the syntax for doing so:
 */

insert into recipe values(1, 'Birthday Cake', 80);

insert into recipe values(2, 'Candy Corn', 0), (3, 'Burger', 30), (4, 'Water', -1);

/*
 * Once records exist on the table, you can query the table using a select statement. The "select"
 * keywrd is simpy used for reading from tables. The syntax for reading from a table is as follows.
 * 
 * Note that the "*" means you're selecting all columns on the table. You can be more specific about
 * the columns you're selecting as well.
 */

select * from recipe;

select recipe_name, recipe_cooktimeinminutes from recipe;

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

update recipe set recipe_name = 'Best Burger';

select * from recipe;




