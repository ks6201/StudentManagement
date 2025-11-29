# Project Structure

The **Student Management System** is modular and divided 
into three modules.

## Modules

### 1. Core
- Holds the **business logic** and domain models.
- Declares **interfaces** for services and repositories.
- Contains service implementations that operate on domain models.
- **No infrastructure details** here Core is persistence-agnostic.

### 2. Infra
- Handles **persistence and infrastructure** concerns.
- Implements repository interfaces defined in Core.
- Contains:
  - JPA **entity** classes
  - **Repository** implementations (Hibernate / JPA)
  - **Mappers** to convert between entities and model objects
  - Database configuration (H2 in file mode)
- **Depends on Core** so it can implement Core interfaces.

### 3. CLI
- The application **entry point / frontend** (command-line UI).
- Depends on **both Core and Infra**.
- Uses Core services and the repository implementations provided by Infra.
- Wiring is done so implementations are injectable services operate on interfaces, not concrete classes.
- The CLI module uses a custom-built container library to handle dependency injection in one place, making services and repositories accessible anywhere within the module.

## Why this layout
This arrangement isolates business rules from persistence and from the 
presentation layer. That makes it straightforward to add other frontends or 
application types: to add a GUI or a Spring-based web app, create a new module 
that depends on Core and Infra it can immediately use Core’s services and 
Infra’s repository implementations without changes to existing modules.

## Dependencies (text diagram)
```
CLI  -->  Core  <--  Infra
```
- Core: independent of Infra/CLI  
- Infra: depends on Core  
- CLI: depends on Core and Infra

## Technologies
- ORM: Hibernate  
- Database: H2 (file mode)  
- Build: Maven