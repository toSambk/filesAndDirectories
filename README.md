# filesAndDirectories

`filesAndDirectories` is a small Java web application for scanning local folders and presenting their contents in a browser.

The app lets a user:

- add a directory by path
- store scanned directory metadata in an H2 database
- see a summary for each tracked root directory
- open a modal with the immediate child files and folders for a selected root

## What The Application Does

When a directory is submitted on the main page, the application:

1. Validates that the submitted path points to a directory.
2. Scans the immediate children of that directory.
3. Stores the directory tree metadata with JPA/Hibernate.
4. Calculates summary information such as nested directory count, file count, and total file size.
5. Exposes file details through a small REST endpoint used by the frontend modal.

This is not a full desktop file manager. It is a server-rendered Spring MVC app focused on directory inspection and summary reporting.

## Tech Stack

- Java 8 target
- Spring MVC
- Spring Data JPA
- Hibernate ORM
- Hibernate Validator
- H2 database
- JSP + JSTL
- Maven
- JUnit 4

## Project Structure

- `src/main/java/filesdirectories/controllers`: MVC and REST controllers
- `src/main/java/filesdirectories/service`: directory scanning and view-model conversion
- `src/main/java/filesdirectories/entities`: JPA entities for directories and files
- `src/main/java/filesdirectories/repo`: Spring Data repositories
- `src/main/web`: JSP pages, JavaScript, CSS, and `web.xml`
- `src/test/java`: unit and integration-style tests

## Main Endpoints

- `GET /`: renders the main page with tracked directories
- `POST /addToList`: adds a new root directory to the tracked list
- `GET /api/files/find?rootDirId={id}`: returns child entries for the selected root directory

## Build And Run

### Requirements

- JDK 17 or newer to build
- Maven 3.9+

The project compiles for Java 8 bytecode, but building with a modern JDK is fine.

### Run Tests

```bash
mvn test
```

### Build The Web Archive

```bash
mvn clean package
```

The build produces a WAR file that can be deployed to a servlet container such as Tomcat.

## Notes

- The production persistence unit uses a file-based H2 database under `target/`.
- The test persistence unit uses an in-memory H2 database.
- The UI text is currently in Russian, while this README describes the project in English.

## Recent Maintenance

This repository was updated to:

- align Spring, Spring Data, and Hibernate versions to a compatible `javax`-based stack
- configure UTF-8 explicitly for sources and resources
- remove an unused Spring Data REST dependency
- configure WAR packaging for the existing web application layout
