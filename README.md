# TestLogin

# Selenium WebDriver Login Tests
This project is a Java-based automated testing suite for the login functionality of the SauceDemo website. It uses Selenium WebDriver for browser automation and JUnit 5 for test execution. The project is designed to run tests in parallel on different browsers (Chrome and Edge) and includes parameterized tests to validate various login scenarios. The project also utilizes Log4j for logging and Hamcrest for assertions.

# Project Structure
TestLogin.java: Contains the main test cases for validating the login functionality. 
Helper.java: A utility class that provides helper methods for interacting with web elements and logging actions.
junit-platform.properties: Configuration for JUnit 5.
log4j.properties: Configuration for Log4j logging.
pom.xml: Maven project configuration file, including dependencies and build configuration.

# Features
Browser Support: The tests can be run on Chrome and Edge browsers. The browser can be specified using the -Dbrowser system property (e.g., -Dbrowser=chrome).
Parameterized Tests: The tests use JUnit 5's @ParameterizedTest and @MethodSource to run the same test with different inputs.
Logging: Log4j is used to log significant actions and messages during test execution.
Assertions: Hamcrest is used to provide readable and flexible assertions.
Parallel Execution: Tests are configured to run in parallel for faster execution.

# Cloning a repository
Open Git Bash.
Change the current working directory to the location where you want the cloned directory.
Type "https://github.com/Nea1in/TestLogin-main.git".

# Running the Tests
mvn clean install
mvn -Dtest=TestLogin -Dbrowser=chrome test  or  mvn -Dtest=TestLogin -Dbrowser=edge test

# Tests description
UC-1 Test Login form with empty credentials:
Type any credentials into "Username" and "Password" fields.
Clear the inputs.
Hit the "Login" button.
Check the error messages: "Username is required".

UC-2 Test Login form with credentials by passing Username:
Type any credentials in username.
Enter password.
Clear the "Password" input.
Hit the "Login" button.
Check the error messages: "Password is required".

UC-3 Test Login form with credentials by passing Username & Password:
Type credentials in username which are under Accepted username are sections.
Enter password as secret sauce.
Click on Login and validate the title “Swag Labs” in the dashboard.