# API Documentation

## Core Module

### Repositories

These are for accessing data. Infra module implements them.

* **`ICourseRepository`** — works with courses.
* **`IStudentRepository`** — works with students.
* **`IEnrollmentRepository`** — works with student enrollments.

---

### Services

These are for business logic.

* **`ICourseService`** — handles course-related tasks.
* **`IEnrollmentService`** — handles enrollment tasks.
* **`IStudentService`** — handles student-related tasks.

---

## CLI Module

### Container

* **`IContainer`** — used for dependency injection (provides objects when needed).

---

### Console

* **`IConsole`** — handles printing on screen and clearing the console.

---

### Menu

* **`IMenu`** — ensures all menus have a `getMenu()` method for smooth initialization.
* **`MenuNode` (abstract class)** — base class for different menu nodes.

    * **`ListingMenuNode`** — shows a list of menu options.
    * **`TerminalMenuNode`** — final menu item that runs actions using Core services.