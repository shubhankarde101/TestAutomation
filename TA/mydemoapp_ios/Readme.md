## Single code base framework to test iOS 'deardoc_patientapp' using appium - testNG tests

## Prequisites:

- Appium server installed on the machine. In case not, install it by running command `npm install -g appium@next`

- Real device to execute tests.(IOS Version: 16.2)

- Install Xcode (Version 14.2, 14C18 )

- Install Appium Inspector(App Version: 2022.11.1 )

- Desired Capabilities to set up Real Device in Appium Inspector
 {
  "appium:deviceName": "Ankit Jain's iPhone",
  "platformName": "iOS",
  "appium:plateformVersion": "16.2",
  "appium:noReset": true,
  "appium:automationName": "XCUITest",
  "appium:udid": "00008030-001C10411487802E"
  }

- Install Maven in your machine. Maven is a build tool (can be downloaded from [here](https://maven.apache.org/download.cgi)). pom.xml file is present in base directory which has all the required dependencies and code to invoke testng.xml file when executed from command line.

## Framework with testng tests setup and execution

### Running sample test

1. Connect your device to your machine 

*Note: start appium server on your machine if not included programatically*


###### Run below commands to execute iOS testng test:

$ cd deardoc_patientapp_ios/
$ mvn clean test


### Reporting

Extent report is being used for reporting purpose, 'index.html' will be generated after test execution in the main project directory. Refer sample report attached below
![img.png](img.png)
![img_1.png](img_1.png)





