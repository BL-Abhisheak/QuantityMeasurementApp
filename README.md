# 📏 Quantity Measurement App

![Java](https://img.shields.io/badge/Java-21-orange) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.0-brightgreen) ![Spring Data JPA](https://img.shields.io/badge/Spring%20Data-JPA-blue) ![Hibernate](https://img.shields.io/badge/Hibernate-ORM-lightgrey) ![H2](https://img.shields.io/badge/DB-H2-blue) ![JUnit5](https://img.shields.io/badge/Tests-JUnit5%20%2B%20Mockito-green)

A Spring Boot REST API for performing unit comparison, conversion, and arithmetic operations across multiple measurement categories, built incrementally through 17 use cases — starting from a single `Feet` equality check and evolving into a layered, generic, database-backed REST service.

---

## 📖 Overview

The Quantity Measurement App lets you compare, convert, and add quantities across four measurement categories:

- 📏 **Length** — Feet, Inches, Yards, Centimeters
- ⚖️ **Weight** — Kilogram, Gram, Pound
- 🧪 **Volume** — Litre, Millilitre, Gallon
- 🌡️ **Temperature** — Celsius, Fahrenheit *(equality/conversion supported; arithmetic intentionally restricted — see [Design Notes](#-design-notes))*

Every operation is persisted to an H2 in-memory database, with history and count endpoints for querying past operations.

The core design goal of this project is a **single generic engine** (`Quantity<U extends IMeasurable>`) that handles all four categories without duplicated logic — new units and categories are added by implementing an interface, not by copy-pasting classes.

---

## 🚀 Features

- ✅ Compare Quantities
- ✅ Add Quantities
- ✅ Unit Conversion
- ✅ Operation History (by operation type / measurement type / errors)
- ✅ Operation Count
- ✅ H2 In-Memory Database
- ✅ REST APIs (Spring MVC)
- ✅ Spring Data JPA + Hibernate ORM
- ✅ Global Exception Handling
- ✅ DTO-Based, Layered Architecture
- ✅ Generic type-safe unit engine (`Quantity<U>`)
- ✅ JUnit 5 + Mockito Unit/Controller Testing

---

## 🏗 Project Architecture

```
                   Client
                      │
                      ▼
        QuantityMeasurementController
                      │
                      ▼
        QuantityMeasurementServiceImpl
                      │
                      ▼
        QuantityMeasurementRepository
                      │
                      ▼
              H2 Database (JPA)
```

Underneath the service layer sits the core domain engine, independent of Spring/HTTP/persistence:

```
        Quantity<U extends IMeasurable>
                      │
        ┌─────────────┼─────────────┬──────────────┐
        ▼             ▼             ▼              ▼
   LengthUnit     WeightUnit    VolumeUnit   TemperatureUnit
```

---

## 📂 Project Structure

```
com.quantity
│
├── Quantity.java                       # Generic core engine (equals, convertTo, add, subtract, divide)
├── QuantityMeasurementApp.java         # Spring Boot entry point
│
├── controller
│   └── QuantityMeasurementController.java
│
├── service
│   ├── IQuantityMeasurementService.java
│   └── QuantityMeasurementServiceImpl.java
│
├── repository
│   └── QuantityMeasurementRepository.java
│
├── dto
│   ├── QuantityDTO.java
│   ├── QuantityInputDTO.java
│   └── QuantityMeasurementDTO.java
│
├── model
│   ├── QuantityMeasurementEntity.java
│   └── OperationType.java
│
├── unit
│   ├── IMeasurable.java                # Contract every unit enum implements
│   ├── LengthUnit.java
│   ├── WeightUnit.java
│   ├── VolumeUnit.java
│   └── TemperatureUnit.java
│
├── exception
│   └── GlobalExceptionHandler.java
│
├── config
│   └── SecurityConfig.java
│
└── util
    ├── DBConnection.java
    └── DatabaseInitializer.java
```

---

## 📐 Supported Measurement Types

| Category | Units |
|---|---|
| Length | FEET, INCHES, YARDS, CENTIMETERS |
| Weight | KILOGRAM, GRAM, POUND |
| Volume | LITRE, MILLILITRE, GALLON |
| Temperature | CELSIUS, FAHRENHEIT |

---

## 📡 REST APIs

### Compare Quantities
`POST /api/v1/quantities/compare`

```json
{
  "thisQuantityDTO": { "value": 1, "unit": "FEET", "type": "LENGTH" },
  "thatQuantityDTO": { "value": 12, "unit": "INCHES", "type": "LENGTH" }
}
```

### Convert Quantity
`POST /api/v1/quantities/convert`

### Add Quantities
`POST /api/v1/quantities/add`

### Get Operation History (by operation)
`GET /api/v1/quantities/history/operation/{operation}`

### Get Operation History (by measurement type)
`GET /api/v1/quantities/history/type/{measurementType}`

### Get Error History
`GET /api/v1/quantities/history/errored`

### Get Operation Count
`GET /api/v1/quantities/count/{operation}`

---

## 💾 Database

**H2 Console:** `http://localhost:8080/h2-console`

| Property | Value |
|---|---|
| JDBC URL | `jdbc:h2:mem:quantitymeasurementdb` |
| Username | `sa` |
| Password | *(blank)* |

Schema is auto-managed via `spring.jpa.hibernate.ddl-auto=update`.

---

## 🧪 Testing

- JUnit 5 + Mockito for service-layer tests
- MockMvc for controller-layer tests (`compare`, `add`, `history`, `count`)

Run all tests:

```bash
mvn test
```

---

## 🎯 Concepts Covered

**Core Java**
Classes & Objects · Encapsulation · Inheritance · Polymorphism · Abstraction · Generics · Enums implementing interfaces · Functional interfaces (`DoubleBinaryOperator`) · Exception handling

**Design Patterns**
Strategy (arithmetic operations as enum + lambda) · Repository · DTO · Layered Architecture · Dependency Injection

**Spring Boot**
REST APIs · Spring MVC · Dependency Injection · Bean Validation (`@Valid`) · `@ControllerAdvice` · Spring Security (permissive dev config) · Spring Data JPA · Hibernate ORM

**Testing**
JUnit 5 · Mockito · MockMvc · Assertions

---

## 🛠 Design Notes

A few deliberate design decisions worth knowing before extending this project:

- **Temperature has selective arithmetic support.** Celsius/Fahrenheit are interval scales, not ratio scales — adding two temperatures isn't physically meaningful the way adding two lengths is. `IMeasurable` exposes `supportsArithmetic()` and `validateOperationSupport()` for units to opt out of arithmetic; `TemperatureUnit` overrides both.
- **`Quantity<U extends IMeasurable>` is the single source of truth** for equality, conversion, addition, subtraction, and division across all four categories — new categories are added by implementing `IMeasurable`, not by writing a new `Quantity`-like class.
- **`QuantityLength` and `QuantityWeight`** are earlier, pre-generics versions of the engine, kept in the codebase for reference but no longer used anywhere.

---

## ▶️ Running the Project

```bash
git clone <your-repo-url>
cd quantitymeasurementapp
mvn clean install
mvn spring-boot:run
```

Or run `QuantityMeasurementApp.java` directly from your IDE.

---

## 🚀 Future Enhancements

- [ ] Wire the REST layer's compare/convert/add operations through the core `Quantity<U>` engine end-to-end
- [ ] Enforce temperature arithmetic restriction inside `Quantity.add()`/`subtract()`/`divide()`
- [ ] JWT Authentication + Spring Security hardening
- [ ] PostgreSQL / MySQL support for production
- [ ] Docker Compose setup
- [ ] Swagger / OpenAPI documentation

---

## 👨‍💻 Author

**Abhisheak**
Java Backend Developer — Spring Boot · Kafka · Redis · Hibernate

