# Campus Lost & Found

**Author:** Carlos Gonzalez in collaboration with professor Mooath Alrajab

**Date:** 2026-11-15

**Course:** CSC 311 Advanced Programming, Fall 2026

**Professor:** Mooath Alrajab


Note: This project was made with assistance from Claude AI

A Spring Boot app where students report items found on campus. Visitors must register and sign in
before they can see the Lost & Found page. Sign-in uses Spring Security, the pages use Thymeleaf,
and data is stored in an H2 database. Passwords are stored as BCrypt hashes, never as plain text.

## Requirements

- Java 21 or newer
- Maven (included with IntelliJ IDEA)

## How to run

**In IntelliJ IDEA:** open the project folder, wait for Maven to finish loading, then run
`LostfoundApplication`.

**Or from a terminal** in the project folder:

```bash
mvn spring-boot:run
```

Then open http://localhost:8080 in your browser.

## How to use it

1. Opening http://localhost:8080 while signed out takes you to the sign-in page.
2. Click **Register** and create an account. The username needs at least 3 characters and the
   password at least 6, and the username can't already be taken.
3. Sign in with your new account to reach the Lost & Found page.
4. Report a found item with a description and location. It appears in the list.
5. Click **Sign out** to return to the sign-in page.

## Viewing the database

Open http://localhost:8080/h2-console and connect with:

- **JDBC URL:** `jdbc:h2:file:./data/lostfound-db`
- **User name:** `sa`
- **Password:** leave it empty

Run `SELECT * FROM APP_USERS` to see the registered users. Each password is a BCrypt hash that
starts with `$2a$`.
