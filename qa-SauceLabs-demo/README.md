### Objective

Your assignment is to write automated test cases for an online shop to test the core functionality of the site.

### Tasks

-   Compile a list of several testable user flows, cases, or scenarios. 

| Site        | URL                                |
| ----------- | ---------------------------------- |
| Online Shop | https://www.saucedemo.com/ |

Make sure to test scenarios for all provided user accounts.

| User                    | Description                                                             |
| ----------------------- | ----------------------------------------------------------------------- |
| standard_user           | The site should work as expected for this user                          |
| locked_out_user         | User is locked out and should not be able to log in.                    |
| problem_user            | Images are not loading for this user.                                   |
| performance_glitch_user | This user has high loading times. Does the site still work as expected? |

-   Implement automated browser tests for all flows.

## Steps to execute

1. Open terminal
2. Make sure Java (JDK11 or above) and Maven installed in system
3. Download or clone this project from git clone 
2. Go to folder "qa-sauceLabs-demo" using cd command
3. Run command "mvn clean verify"
4. Go to target folder once execution completed
5. Open "cucumber-html-reports" folder
6. Open "overview-features.html" in any browser(chrome recommended)
7. Analyze the report
