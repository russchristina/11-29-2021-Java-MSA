/* Recall that TCL is geared towards finalizing and reverting changes to your DB.
 * 
 * Technically speaking, whenever you insert records into a DB, you should be working within a
 * "transaction". A transaction is a measure of work on your DB; in essence, you can think of
 * it as a collection of SQL statements the result of which you intend to either finalize together
 * or revert all at once. This property of transactions is referred to as "atomicity" - all or nothing.
 * Either all changes within a transaction are persisted or none of them are.
 */

select * from ingredient;

-- Explicitly begin a transaction:

begin transaction;

insert into ingredient values(9, 'Beef', 'savory');

savepoint before_my_shenanigans; --savepoints can be used to rollback to moments in time before you did work on your DB you regret

insert into ingredient values(10, 'Tofu', 'sweet');

rollback to before_my_shenanigans; -- this reverts changes made during a transaction

commit; -- this finalizes changes made during a transaction


