### Objective

Your assignment is to write automated test cases for an online shop to test the core functionality of the site.

### Tasks

-   Compile a list of several testable user flows, cases, or scenarios. This doesn't have to cover every possible real-life case; please focus on what you think is most fundamental (e.g., sign in).

| Site        | URL                                |
| ----------- | ---------------------------------- |
| Online Shop | https://qa-challenge.codesubmit.io |

Make sure to test scenarios for all provided user accounts.

| User                    | Description                                                             |
| ----------------------- | ----------------------------------------------------------------------- |
| standard_user           | The site should work as expected for this user                          |
| locked_out_user         | User is locked out and should not be able to log in.                    |
| problem_user            | Images are not loading for this user.                                   |
| performance_glitch_user | This user has high loading times. Does the site still work as expected? |

-   Implement automated browser tests for all flows. Use any testing technology you'd like – Cypress, Selenium, or any other you think would work well

### Evaluation Criteria

-   **Automation & QA** best practices
-   Show us your work through your commit history
-   We're looking for you to produce working code, with enough room to demonstrate how to structure components in a small program
-   Completeness: did you complete the features?
-   Correctness: does the functionality act in sensible, thought-out ways?
-   Maintainability: is it written in a clean, maintainable way?

### CodeSubmit

Please organize, design, test and document your code as if it were going into production - then push your changes to the master branch. After you have pushed your code, you may submit the assignment on the assignment page.

**Have fun building!** 🚀

The Solar Insure Team

## Steps to execute

1. Open terminal
2. Make sure Java (JDK11 or above) and Maven installed in system
3. Download or clone this project from git clone 
2. Go to folder "qa-automation-eznors" using cd command
3. Run command "mvn clean verify"
4. Go to target folder once execution completed
5. Open "cucumber-html-reports" folder
6. Open "overview-features.html" in any browser(chrome recommended)
7. Analyze the report
