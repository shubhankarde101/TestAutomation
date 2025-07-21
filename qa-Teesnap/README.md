### Objective

Your assignment is to write automated regression test cases for Teesnap to test the core functionality of the site.

| Site    | URL      |
|---------| -------- |
| Teesnap |https://teesnapgl.teesnap.net/login|

-   Implement automated browser tests for all flows.

## Few important notes

1. Open terminal
2. Make sure Java (JDK11 or above) and Maven installed in system
3. Download or clone this project from git clone/import the project from Local 
2. Go to folder "qa-Teesnap" using cd command
3. The f/w is created based on page object model design pattern and the oops concepts in Java
3. Run command "mvn clean verify -Dcucumber.filter.tags=@regression  -DparallelCount=3"
4. The above command will invoke parallel testing with the intended browser count provided
5. We can use tag to execute the specific set of test cases, ex: regression tag in the above command to run the regression specific test cases
5. Open the report from the console link(Ex: view your Cucumber Report at: https://reports.cucumber.io/reports/4825e0c5-3f15-46f1-85da-b8cce279c8e2 )
7. Analyze and share the report
8. Version control(Git and Github) and CI/CD(jenkins) will be applicable in the phase2 deployment
