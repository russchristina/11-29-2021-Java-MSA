# Simple Business API

> Leveraging Java 8, create an application that simulates simple transactions for a themed (work-appropriate) business of your choice. 

## Requirements:

### Overview of Required Technologies:

* Java 8
* Maven
* JUnit
* Mockito
* Log4J/Logback
* PostgreSQL
* JDBC

### Project Format and Metrics:

* Build the application using Java 8.
* The project should be a Maven project.
* All interaction with the user should be done through the console using the Scanner class.
* All information should be persisted using a SQL database; we're specifically using PostgreSQL.
* You must use AWS RDS to host your DB.
* You should use the DAO design pattern with JDBC for data connectivity.
* You must write at least 30 JUnit tests to ensure application quality.
* Logging should be accomplished using a logging framework such as Log4J or Logback.
* All transactions should be logged.

### Functional Requirements:

* Customers of the business should be able to register with a username and password.
* Customers should be able to have multiple accounts with the business (e.g. having two separate accounts with different credentials should be possible).
* Customers should be able to add a secondary user to their accounts for their service (e.g. add a child user to a movie streaming service, add another user who can purchase items using their account).
* Once the account is open, customers should be able to add funds, remove funds, and transfer funds between different accounts.
* All basic validation should be done, such as accounting for trying to input negative amounts, overdrawing from account funds, etc.
* Employees of the business should be able to view all of their customers' information. This includes:
   * Account information (e.g. how many users have access to the account)
   * Account balances (e.g. remaining funds for the account)
   * Personal information (e.g. users' addresses, date of birth, etc.)
* Employees should be able to cancel accounts.
* There should also be special employees who are Administrators who can view and edit all accounts.
This includes:
   * Canceling accounts
   * Modifying account balances and users' personal information

