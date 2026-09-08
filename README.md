# Finance Tracker API (Backend)

A RESTful backend service built with Spring Boot and Java to manage financial transactions.

## Tech Stack
* Java 17+
* Spring Boot 3
* Spring Data JPA
* MySQL / H2 Database

## Development Log
**[Sep 8, 2026] Phase 1: Core Architecture & Data Transfer Security**
* Bootstrapped Spring Boot 3 backend with Maven, establishing a strict 3-tier enterprise architecture (Controller, Service, Repository).
* Configured local embedded H2 database persistence via Spring Data JPA and Hibernate for rapid local development.
* Refactored core financial data types to `BigDecimal` to prevent IEEE 754 floating-point precision loss.
* Implemented the Data Transfer Object (DTO) pattern using immutable Java 17 `record`s to isolate raw database entities from the presentation layer.
* Engineered the Service layer to act as a secure translation firewall, utilizing Java Streams to efficiently map domain entities to `TransactionResponseDTO`s.