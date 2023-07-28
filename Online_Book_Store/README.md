# online_book_store
A REST API service which allows users to manage books in their store.

This is an MVP application and more feature can be built on top of this.

**Development frameworks:**

* Java 11
* Springboot
* Gradle
* MongoDB
* Mockito
* Jacoco

**How to run the API:**

* **Set up the local mongo db and collection in your system.**

Install "mongodb compass GUI".

Use the connection string as "mongodb://localhost:27017" to configure a new connection.

Add a new database with name "book-store".

If need be, please import pre-created "books.json" collection to your DB, which is placed in the project directory in following path- src/main/resources/static/books.json

Or you can just use the 'addBook' endpoint to add new books.

* **Using IDE (ex: IntelliJ)**

Import the project to your IDE. Right-click on the project and click on 'Build module online-book-store'.

Once the project is successfully built, right-click on 'OnlineBookStoreApplication.java' file. And click on "Run OnlineBookStoreApplication.main()".

This will start the REST API to run on localhost with port 8080.

Now you can use the Postman or any other REST API testing tool to hit the endpoints.

**Features of the application:**

* The application is a book store management REST API developed using Java 11 and Spring boot 2.7.7
* Gradle has been used as the build framework and MongoDB as NoSQL database for its schemaless properties.
* The REST API has been documented using OPEN-API and can be viewed at below url when the app is running http://localhost:8080/swagger-ui/index.html
* Mockito has been used as testing framework.
* Pagination has been implemented to cater large volume of data.
* Jacoco test coverage verification is also implemented with minimum coverage threshold as 85% and can be viewed in the below path once the test cases with coverage are run build/reports/jacoco/test/html/index.html
* A pre-generated test coverage report is also available to view under the path - src/main/resources/static/index.html
* Lombok java library has been used to reduce the boilerplate code.
* MongoDB indexing.
* 'stylecheck' plugin has been implemented to perform quality checks on the project's java source files and generate reports from these checks. This can be run in Gradle tab under 'Tasks/other' and generated reports can be checked under "build/reports/checkstyle"
* Actuator endpoints have been enabled for app monitoring.
* Env profiling has been done for specific environments to ease the release cycle and production readiness.
* mockMvc has been used for integration testing of the application.
