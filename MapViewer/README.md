## Objective

This assignment is to write automated test cases for 'Arcgis' website.

## Tasks
1. Open site "https://www.arcgis.com/apps/mapviewer/index.html"
2. Clicking on the search icon
3. Searching a country name(Ex: Nepal)
4. Clicking on the search button
5. Validating the search result is correct as per the expectation
6. Clicking on zoom In 
7. Clicking on zoom out
8. Validating the search result after zoom functionality


## Best practises adhered 
- Completeness: All the features are automated as per task requirement
- Correctness: Functionality acts in sensible, thought-out ways
- Maintainability: code is written in a clean, maintainable way
- Data driven: Code is made in data driven approach by TestNG data provider annotation(further can be enhanced through json file/excel sheet which is out of scope in this framework)


## Steps to execute

1. Open terminal
2. Make sure Java (JDK11 or above) and Maven installed in system
3. Go to folder "MapViewer" using cd command
4. Run command "mvn clean test"
5. Open 'test_execution.html' extent report in the target folder on any browser
6. Analyze the report
7. Framework has provision to implement the selenium GRID by turning on/off the Grid value in 'config.properties' file under src/main/resources folder
8. Parallel execution is supported and can be configured through 'testng.xml' file under main project folder
9. Proper page object model design pattern adhered for better code optimization and maintainability

