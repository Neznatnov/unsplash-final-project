# Test automation project for the [unsplash.com](https://unsplash.com/) website
Unsplash is a platform powered by an amazing community that has gifted hundreds of thousands of their own photos to fuel creativity around the world.
## Table of contents


- [Stack of technologies](#computer-stack-of-technologies)
- [List of UI tests](#list-of-ui-tests)
- [List of API tests](#list-of-api-tests)
- [Running tests on a local machine](#running-automated-tests-on-a-local-machine)
- [Running tests in Jenkins](#running-tests-in-jenkins)
- [Test results report in Allure Report](#test-results-report-in-allure-report)
- [Integration with Allure TestOps](#integration-with-allure-testops)
- [Integration with Jira](#integration-with-jira)
- [Telegram notifications using a bot](#telegram-notifications-using-a-bot)
- [Example of test execution in Selenoid](#example-of-test-execution-in-selenoid)


## :computer: Stack of technologies
<div style="text-align: center;">
<a href="https://jetbrains.com/idea/"><img src="readme/icons/Idea.svg" width="50" height="50"  alt="IntelliJ IDEA"/></a>
<a href="https://java.com/"><img src="readme/icons/Java.svg" width="50" height="50"  alt="Java"/></a>
<a href="https://junit.org/junit5/"><img src="readme/icons/JUnit5.svg" width="50" height="50"  alt="JUnit 5"/></a>
<a href="https://selenide.org/"><img src="readme/icons/Selenide.svg" width="50" height="50"  alt="Selenide"/></a>
<a href="https://rest-assured.io/"><img src="readme/icons/RestAssured.svg" width="50" height="50" alt="RestAssured"/></a>
<a href="https://gradle.org/"><img src="readme/icons/Gradle.svg" width="50" height="50"  alt="Gradle"/></a>
<a href="https://github.com/"><img src="readme/icons/GitHub.svg" width="50" height="50"  alt="Github"/></a>
<a href="https://jenkins.io/"><img src="readme/icons/Jenkins.svg" width="50" height="50"  alt="Jenkins"/></a>
<a href="https://github.com/allure-framework/allure2"><img src="readme/icons/Allure.svg" width="50" height="50"  alt="Allure Report"/></a>
<a href="https://aerokube.com/selenoid/"><img src="readme/icons/Selenoid.svg" width="50" height="50"  alt="Selenoid"/></a>
<a href="https://qameta.io/"><img src="readme/icons/Allure_TO.svg" width="50" height="50"  alt="Allure Testops"/></a>  
<a href="https://www.atlassian.com/software/jira"><img src="readme/icons/Jira.svg" width="50" height="50"  alt="Jira"/></a>  
</div>
In this project, the automated tests are written in <code>Java</code> using the <code>Selenide</code> framework. <code>Gradle</code> is used to build the project. <code>JUnit 5</code> is used as the unit testing framework. <code>Rest-assured</code> library was used for API tests. The tests are run from <code>Jenkins</code>. After the run is completed, a notification is sent using a bot on Telegram. <code>Selenoid</code> is used to run browsers in Docker containers. Integration with <code>Allure TestOps</code> and <code>Jira</code> is set up for test reporting and test result analytics.

## List of UI Tests
- [x] Authorization with email and password
- [x] Verification of displayed sub-items for each menu item on the main page (parametrized test)
#### Integrations page
- [x] Check the search of integrations by name
- [x] Verification of the message when no results match the search query
#### Pricing calculator for enterprise teams
- [x] Check the number of users and prices displayed by default
- [x] Check prices for the maximum number of users
- [x] Check the error message when the number of users is less than minimum
- [x] Check the error message when the number of users exceeds the maximum

## List of API tests
- [x] Successful board creation
- [x] Check if a board cannot be created without specifying its name
- [x] Successful deletion of the board
- [x] Successful creation of a list in the board
- [x] Successful creation of a card in the list
- [x] Card update: moving the card to another list

## Running automated tests on a local machine
To run tests locally on your machine, add the local.properties file to the src/test/resources/config folder and fill in the following properties:

```properties
clean
${TASK}
--info
-Dbrowser=${BROWSER}
-DbrowserVersion=${BROWSER_VERSION}
-DbrowserSize=${BROWSER_SIZE}
-DbaseUrl=${BASE_URL}
-Denv=${env}
```
>- <code>BROWSER</code> - browser in which the tests will be run (Chrome is set by default)
>- <code>BROWSER_SIZE</code> - size of the browser window (1920x1080 is set by default)
>- <code>BASE_URL</code> - the base URL of the web application under test
>- <code>TRELLO_USER_EMAIL</code> - test account login
>- <code>TRELLO_USER_PASSWORD</code> - test account password
>- <code>API_BASE_URL</code> - the base URL of the API

Then use the following command:
```
gradle clean TASK -Denv=local
```
<code>TASK</code> - ui_test / api_test / test

Or you can specify parameters directly in the command to run:
```
gradle clean TASK
-Dbrowser=BROWSER 
-DbrowserSize=BROWSER_SIZE
-DbaseUrl=BASE_URL
-DtrelloUserEmail=TRELLO_USER_EMAIL
-DtrelloUserPassword=TRELLO_USER_PASSWORD
-DapiBaseUrl=API_BASE_URLgit
-DtrelloApiKey=TRELLO_API_KEY
-DtrelloApiToken=TRELLO_API_TOKEN
```
## Running tests in Jenkins
To run tests in Jenkins, follow these steps:
1. Click on the [provided link](https://jenkins.autotests.cloud/job/19_neznatnov_final_unsplash_unit24/)
2. Click the **Build with Parameters** link
3. Modify the parameters as needed
4. Click the **Build** button, and build will be started

<div style="text-align: center;">
  <img src="media/screens/jenkins_1.png" alt="Jenkins" width="900">
</div>

Once the build is complete, viewing the Allure report becomes available.
<div style="text-align: left;">
  <img src="media/screens/jenkins_2.png" alt="Jenkins" width="400">
</div>

## Test results report in [Allure Report](https://jenkins.autotests.cloud/job/C19-CatherineMoko-final_project/allure/)
The report of the test execution includes:
- Test steps
- Screenshot of the page on the last test step
- Page Source
- Browser console logs
- Video of the test run
<div style="text-align: center;">
  <img src="media/screens/allure_report_1.png" alt="allure report" width="900">
</div>

<div style="text-align: center;">
  <img src="media/screens/allure_report_2.png" alt="allure report" width="900">
</div>

## Integration with [Allure TestOps](https://allure.autotests.cloud/project/3544/dashboards)
<div style="text-align: center;">
  <img src="media/screens/allure_testops_1.png" alt="allure report" width="900">
</div>

<div style="text-align: center;">
  <img src="media/screens/allure_testops_2.png" alt="allure report" width="900">
</div>

## Integration with [Jira](https://jira.autotests.cloud/browse/HOMEWORK-804)
<div style="text-align: center;">
  <img src="media/screens/jira.png" alt="allure report" width="900">
</div>

## Telegram notifications using a bot
After passing all the tests, an automatic report is sent to the Telegram messenger.
<div style="text-align: center;">
  <img src="media/screens/telegram.png" alt="allure report" width="500">
</div>

## Example of test execution in Selenoid
<div style="text-align: center;">
  <img title="Selenoid Video" src="media/screens/video.gif" alt="Test execution in Selenoid">
</div>