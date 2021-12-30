-- Let's say that we wanted to find ALL of the information concerning the Birthday Cake
-- recipe. We could fine the recipe on the recipe table, but it has a foreign key that points
-- to a related record on the author table. This means that we would have to write another
-- query to get all of the information concerning the Birthday Cake recipe. Like so:
select * from recipe where recipe_name = 'Birthday Cake';
select * from author where author_id = 1;

-- Fortunately, we don't have to write 2 queries in order to find this information. We can use
-- "joins" instead. A join allows us to combine the data of two tables (temporarily). This would
-- allow us to review information about a recipe and its author all at once.

-- There are several types of joins: inner, outer, left, right, cross, self.

-- inner join: joins two tables, returning records if there are no null values in the 
-- column used in the join predicate (e.g. recipe.author_id = author.author_id).

select * from recipe;

select * from recipe inner join author on recipe.author_id = author.author_id;

-- outer join (can be written as "full outer join" or "full join" in PostgreSQL): joins two
-- tables, though it is less restrictive than an inner join. An outer join will guarantee that
-- every record from both tables is represented, even if this means that nulls must be substituted
-- for values that do not exist.

select * from recipe full outer join author on recipe.author_id = author.author_id;

-- left join: joins two tables, guarantees that every record from the left table is represented,
-- even if nulls must be substituted for nonexistent values to do this.

select * from recipe left join author on recipe.author_id = author.author_id;

-- right join: joins two tables, guarantees that every record from the right table is represented,
-- even if nulls must be substituted for nonexistent values to do this.

select * from recipe right join author on recipe.author_id = author.author_id;

-- cross join: joins two tables, returning the cartesian product of the records on the tables;
-- this means that every possible combination of records on table A with records on table B will
-- be returned.

select * from recipe cross join author;

-- self join: entails joining the same table to itself; using aliases is a must here. An alias
-- is just an alternate name for a table. Aliases are useful when you just want to reference
-- a table using a shorter name.

-- Note that all of our previous joins were "equi" joins; we used the "=" sign in those joins.
-- This join is going to be a "theta" join; actually, we'll theta join and equi join here.

-- Fun fact: The "<>" operator means "not equal to".

select r1.recipe_name, r2.recipe_name, r1.recipe_cooktimeinminutes 
from recipe r1 inner join recipe r2 on r1.recipe_id <> r2.recipe_id 
and r1.recipe_cooktimeinminutes = r2.recipe_cooktimeinminutes;

-- Multiple joins:

select * from recipe_ingredient;

select * from recipe_ingredient ri inner join recipe r on ri.recipe_id = r.recipe_id 
inner join author a on a.author_id = r.author_id inner join ingredient i on i.ingredient_id = ri.ingredient_id;
