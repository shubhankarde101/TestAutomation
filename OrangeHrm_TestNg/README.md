### Objective

This assignment is to write automated test cases for 'Orange HRM' website.

### Tasks
1.	Automate the Login flow.
2.	After logging in, mouse hover to PIM and click
3.	In the PIM page, click on “Add Employee”.
4.	Add 3-4 employees using Data Driven Testing from a csv sheet.
5.	After Adding go to the “Employee List page”
6.	Scroll and look for the employees added and verify with name.
7.	Print name verified once found
8.	Logout from the dashboard.



### Best practises adhered 
- Completeness: All the features are automated as per task requirement
- Correctness: Functionality acts in sensible, thought-out ways
- Maintainability: code is written in a clean, maintainable way
- Data driven: Code is made in data driven approach by fetching the employee data from excel sheet('TestData.xlsx' under src/test/resources/Excel)



## Steps to execute

1. Open terminal
2. Make sure Java (JDK11 or above) and Maven installed in system
3. Go to folder "OrangeHrm_TestNg" using cd command
4. you can add/delete/modify as many number of employee data from excel sheet 'TestData.xlsx' maintained under src/test/resources/Excel
5. Run command "mvn clean test"
6. Open 'index.html' extent report in the main project folder on any browser
7. Analyze the report(Refer info logs and screenshots for detailed analysis)
