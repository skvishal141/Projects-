# HON_TaskManagerBackEnd_Participant

A beginner-friendly Spring Boot project that combines **Spring Data JPA**,
**MySQL**, and **Spring Security**. Several methods have been left
incomplete on purpose, marked with `To-Do Item` comments. Complete every
To-Do to get a fully working, secured Task Manager REST API.

See **TaskManager_TODO_List.pdf** for the full list of To-Do items to complete.

## Setup

1. Install MySQL and run the script at `src/main/resources/TaskManagerDb.sql`
   to create the `taskmanagerdb` database, its tables, and seed data.
2. Update `src/main/resources/application.properties` if your MySQL
   username/password differ from `root` / `root`.
3. Import the project into your IDE (Eclipse/STS/IntelliJ) as a Maven project.
4. Complete every `To-Do Item` referenced in the PDF.
5. Run `Application.java` (or `mvn spring-boot:run`) and test the endpoints
   with Postman, using HTTP Basic auth with username `admin` and password
   `password123` (seeded for you), or register a new user via `/user/register`.

## Endpoints (once completed)

| Method | URL                  | Auth required | Description              |
|--------|----------------------|----------------|---------------------------|
| POST   | /user/register       | No             | Register a new user       |
| POST   | /task/save            | Yes            | Add a new task             |
| GET    | /task/list            | Yes            | List all tasks             |
| PUT    | /task/complete/{id}   | Yes            | Mark a task as completed   |
| DELETE | /task/delete/{id}     | Yes            | Delete a task              |
