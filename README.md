# taf-kvitki-2024

This project is an example of Java based test automation framework that provides set of
UI and API tests for the following source: [kvitki.by](https://www.kvitki.by)

## Structure of the project

The project is divided into multiple modules:

- <b>driver</b>: creation of WebDriver instance
- <b>pages</b>: contains PageObjects for interaction with web elements
- <b>steps</b>: contains methods for e2e Login and Registration flow
- <b>utils</b>: factory of test data generation (names, emails, passwords, phone numbers)
- <b>user</b>: factory of registered and the newly created users
- <b>api</b>: contains factories for API requests generation and service for receiving registered user's access token
- <b>UI</b> tests
- <b>API</b> test

## Project specification

- programming language- [Java](https://www.java.com/en/download/help/whatis_java.html)
- build automation tool- [Maven](https://maven.apache.org/)
- web automation framework- [Selenium](https://www.selenium.dev/)
- unit testing framework: [JUnit](https://junit.org/junit5/)
- API test framework: [REST-assured](https://rest-assured.io/)

## Tests composition

<H3> UI tests:</H3>

- Home page rendering
- Login (positive and negative scenarios)
- Registration (positive and negative scenarios)
- Update account (positive and negative scenarios)
- Item search (positive and negative scenarios)

<H3> API tests:</H3>

- Home page rendering
- Login (positive and negative scenarios)
- Registration (positive and negative scenarios)
- Update account (positive and negative scenarios)

## Usage

<b>Preparation</b>: Java 11

<b>To run tests:</b>

- execute Maven-> Lifecycle-> test (for IntelliJ IDEA)
- or run the following command via terminal:

```bash
mvn clean test
```

<b>To manage</b> tests execution scope use already assigned JUnit tags.
<p>Default run configuration includes all tests:  <i>API and UI groups</i> (specified in pom.xml-> maven-surefire-plugin).</p>
<p><b>Available groups</b>: HomePage, Login, Registration, UpdateAccount, Search, API, UI.</p>

<b>To run a specific group:</b>

* specify it via console command e.g.:

```bash
mvn test -Dgroups=API
```

* or update 'groups' path in pom.xml-> 'build' section

<p></p>
<p></p>
<b>Contributor</b>: Viktoryia Pazhydayeva




