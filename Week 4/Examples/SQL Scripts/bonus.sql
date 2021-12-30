/*
 * Views: We can use views to abstract queries away from ourselves. Note that when you use a view,
 * the query it represents is run under the hood. This is useful when you have a long/complex query
 * that you do not want to write multiple times.
 */

drop view longQuery;
create view longQuery as 
select ri.recipe_id, ri.ingredient_id, i.ingredient_name, i.ingredient_flavor, 
r.recipe_name, r.recipe_cooktimeinminutes, r.author_id 
from recipe_ingredient ri 
inner join ingredient i on ri.ingredient_id = i.ingredient_id
inner join recipe r on ri.recipe_id = r.recipe_id;

select * from longQuery;

/*
 * Indexes: Are used to improve performance when looking up records on a table. They allow us
 * to locate records faster. Primary keys are automatically indexed. That said, you can create
 * your own indexes on columns.
 */

-- If I create an index on the ingredient_name column, it becomes faster to look up ingredients
-- by name. That is because this syntax creates a B-Tree index. This means that the records
-- can be stored in order by the ingredient_name column's values.
create unique index custom_index on ingredient(ingredient_name);

drop index custom_index;

/*
 * We can also create other types of indexes (that are not B-Tree indexes). You can even use
 * expressions when creating your index. 
 */

create index on ingredient(lower(ingredient_name));

-- An example of using a scalar function in SQL.
select ingredient_id, concat(ingredient_name, ' is an ingredient') from ingredient;

/*
 * Set Operations: Used to combine the results of queries. Many dialects of SQL support
 * the following set operations. As a general note, the number of columns and data types
 * MUST match up in the different result sets when using set operations.
 * 
 * union: combines the results of queries, omitting duplicates
 * union all: combines the results of queries, keeping the duplicates
 * intersect : any records that are present in both result sets are returned
 * except (called "minus" in some dialects): returns any records that are present in
 * 	the first result set but not in the second.
 */

--Union
select recipe_name from recipe union 
select ingredient_name from ingredient;

--Union All
select recipe_name from recipe union all select ingredient_name from ingredient;

--Intersect
select * from recipe intersect select * from recipe;
select * from ingredient intersect select * from author;

--Except
select * from recipe except select * from recipe;
select * from ingredient except select * from author;

-- Properties of a Transaction: Our properties of a transaction describe what must be true about
-- a SQL transaction. SQL transactions are:
/*
 * Atomicity: "All or nothing"; all of the data will either be persisted or it won't
 * Consistency: All concurrent transactions must abide by the same DB constraints
 * Isolation: Assuming transactions are concurrent, the results of one transaction should not affect another transaction
 * Durability: All data that is committed is persisted
 */

/*
 * Honing in on Isolation: There are 4 isolation levels in SQL:
 * 
 * Read Uncomitted: BAD PRACTICE; allows for uncommitted data to be read during concurrent
 * 	transactions. Allows for "dirty reads".
 * 
 * Read Committed: prevents concurrent transactions from accessing data that is not yet
 * commited. Prevents "dirty reads" - reading uncommitted data.
 * 
 * Repeatable Reads: prevents concurrent transactions from modifying records that are being
 * used (e.g. selected, etc.). Prevents dirty reads and "non-repeatable reads".
 * 
 * Serializable: prevents concurrent transactions from modifying the number of records on a table
 * while its records are being accessed. Prevents "phantom reads" (and all of the other reads).
 */

-- Example of changing your isolation level; please never go below read committed. Note that
-- higher you go, the slower your transactions will be as higher levels place more read and
-- write locks on tables and records.
set transaction isolation level read committed;