# Project 1
> Due January 14, 2022

## Expense Reimbursement System
You are tasked with creating an expense reimbursement system. For a small company/group. This program will allow employees to create reimbursement requests. All managers can view these requests and approve or deny them. When they approve/deny a request, they can optionally leave a message.

### Key features
- Employee
    - An employee can login to see their own reimbursements, past and pending
    - An employee can submit a reimbursement with an amount and a reason
        - Bonus: allow for file upload (Optional)
- Manager
    - A Manager can view all reimbursements past and pending
    - A Manager can approve or deny any reimbursement
    - Managers can view a 'statistics' page. That includes information like what employee spends the most money, mean expenditure cost etc...

### Key Notes
- You do not have to allow for the creation of employees or managers.
    - You can have these already in the database.
- You do not need to have implement security for the application. You can assume that a later security team is responsible for making the application secure.
    - API routes do not need to be protected
    - Passwords do not have to be encrypted

### Technical and testing requirements
- Frontend developed with JS, HTML, CSS
- Backend developed in Javalin
- Backend should be a RESTful web service
    - You may have to a make a non-REST compliant endpoint for login. This is normal.
- AWS Postgres RDS used to persist information
- All DAO methods should be written with Hibernate and have a test
- All Service methods with logic should have a test
    - use mocking when applicable
- There should be logging in the application
- All user stories and acceptance criteria must be written out (optional)

### Some Tips:

- Start early and push often
- Do what you can using the technologies (for the meantime, write your DAO methods in JDBC since you don't know Hibernate)
- Do not spend all of your time in the beginning perfecting your web page's design. Wait until your application meets functional requirements to beautify your webpages.
