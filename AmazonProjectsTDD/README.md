## Objective

This assignment is to write automated test cases for 'Amazon' website.

## Tasks
1. Open Amazon India.
2. Search for headphones using the search bar
3. Filter the search result by selecting 'wireless' as the connectivity type
4. Click on the 5th item from the search results
5. Verify the product details page is displayed correctly
6. Capture and log the product name, price and ratings
7. Click on add to cart and ensure the item is added successfully
8. Navigate to the cart and proceed to checkout
9. Verify the correct item added to the cart before checkout



## Best practises adhered 
- Completeness: All the features are automated as per task requirement
- Correctness: Functionality acts in sensible, thought-out ways
- Maintainability: code is written in a clean, maintainable way
- Data driven: Code is made in data driven approach by TestNG data provider annotation(further can be enhanced through json file/excel sheet which is out of scope in this framework)


## Steps to execute

1. Open terminal
2. Make sure Java (JDK11 or above) and Maven installed in system
3. Go to folder "AmazonProjectsTDD" using cd command
4. Run command "mvn clean test"
5. Open 'test_execution.html' extent report in the target folder on any browser
6. Analyze the report
7. Framework has provision to implement the selenium GRID by turning on/off the Grid value in 'config.properties' file under src/main/resources folder
8. Parallel execution is supported and can be configured through 'testng.xml' file under main project folder
9. Proper page object model design pattern adhered for better code optimization and maintainability
10. All the images are disabled in the application and script will execute in chrome incognito mode for faster and reliable execution

## Note
The Amazon production site is very unstable and every when and then it will ask for "CAPTHA" to authenticate human/manual interaction, as a result script might get failed. 
Only way is to Re-trigger the script and analyze the application behavior which can be handled in future scope 
