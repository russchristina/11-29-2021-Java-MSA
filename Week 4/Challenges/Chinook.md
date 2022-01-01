For this week's challenge, you'll be writing SQL queries against the Chinook Database. In order to complete the challenge, you should:

1. Go to the [Chinook repository](https://github.com/lerocha/chinook-database/tree/master/ChinookDatabase/DataSources) and download the script for PostgreSQL. Once you've downloaded it, run the script against your database to create the necessary tables with the proper records.

2. Write SQL queries as instructed (see the section below).

### The Queries

1. Provide a query showing Customers (just their full names, customer ID and country) who are not in the US.

2. Provide a query only showing the Customers from Brazil.

3. Provide a query showing the Invoices of customers who are from Brazil. The resultant table should show the customer's full name, Invoice ID, Date of the invoice and billing country.

4. Provide a query showing only the Employees who are Sales Agents.

5. How many Invoices were there in 2009 and 2011? What are the respective total sales for each of those years? (You'll want to look into the "count" function.)

6. Looking at the InvoiceLine table, provide a query that COUNTs the number of line items for Invoice ID 37.

7. Provide a query that shows the total sales per country. Which country's customers spent the most? (You'll want to look into the "sum" function.)