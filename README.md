# Finance Tracker API (Backend)

A RESTful backend service built with Spring Boot and Java to manage financial transactions.

## Tech Stack
* Java 17+
* Spring Boot 3
* Spring Data JPA
* MySQL / H2 Database

## Development Log

<details>
<summary>[Sep 8, 2026] Phase 1: Core Architecture & Data Transfer Security</summary>

* Bootstrapped Spring Boot 3 backend with Maven, establishing a strict 3-tier enterprise architecture (Controller, Service, Repository).
* Configured local embedded H2 database persistence via Spring Data JPA and Hibernate for rapid local development.
* Refactored core financial data types to `BigDecimal` to prevent IEEE 754 floating-point precision loss.
* Implemented the Data Transfer Object (DTO) pattern using immutable Java 17 records to isolate raw database entities from the presentation layer.
* Engineered the Service layer to act as a secure translation firewall, utilizing Java Streams to efficiently map domain entities to TransactionResponseDTOs.

</details>

<details>
<summary>[Sep 26, 2026] Phase 1.5: 2.Global Exception Handling</summary>

* Standardized Error Contracts:
  created `ErrorResponseDTO.java` class to return a structured and consistent JSON response (timestamp, status, message, field-errors) upon API failures
* Global Exception Handler:
  created `GlobalExceptionHandler.java` class to intercept all exceptions
* Validation Handling:
  configured method for `MethodArgumentNotValidException` to intercept full-object DTO validation errors (`@Valid` on POST/PUT)
  configured method for `ConstraintViolationException` to intercept validation errors on primitive fields (`@Validated` on PATCH)
* ResourceNotFoundException:
  created custom `ResourceNotFoundException.java` to deal with missing DB records
* Service Layer Refactoring:
  removed redundant manual validations and replaced `IllegalArgumentException` with `ResourceNotFoundException`

</details>

<details>
<summary>[Sep 28, 2026] Phase 1.5: 3. Database Relational Mapping</summary>

* **User Entity Foundation:** 
    Created `User.java` to act as the primary owner of all financial data, laying the groundwork for multi-tenant architecture and authentication.
* **Bidirectional JPA Relationships:** 
    * Implemented `@ManyToOne(fetch = FetchType.LAZY)` on the `Transaction` entity to optimize server memory by preventing automatic, heavy `JOIN` queries.
  * Implemented `@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)` on the `User` entity to manage the lifecycle of transactions.
* **Data Integrity Enforcement:** 
    Secured the database schema using `@JoinColumn(name = "user_id", nullable = false)`, enforcing strict referential integrity at the PostgreSQL level to mathematically prevent orphaned transactions or "ghost data."
  * **Service Layer Atomicity:** 
    Applied `@Transactional` to `TransactionService` to enforce the Atomicity ACID property. This guarantees that all service methods execute as a single, indivisible database transaction, automatically rolling back to prevent partial data corruption in the event of an application fault.

</details>