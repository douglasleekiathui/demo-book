# About this project

#### Instructions to start up project
This project is generated from Spring Initializer  
The latest maven and openjdk versions were used (as of 23 July 2023)  
To run project, do  
1. mvn clean install - to download dependencies
2. mvn - to run spring boot project on port 8080

#### Instructions to test project
To test project  
1. open browser of choice and enter http://locahost:8080 to view test page
2. the topmost section allows you to enter any username. If the username begins with 'd', your user will have the right to delete books
3. the bottommost section shows the server response
4. there are four sections, namely 1) Add book, 2) Update book, 3) Find book, 4) Delete book  
5. when adding or updating books, authors will be added as accordingly
6. when deleting books, authors will NOT be deleted accordingly
 
There is already pre-populated data which can be found in src/main/resouces/data.sql  

#### Out of scope
1. There is not JUnit written for this project
2. There is no sophisticated authentication mechanism or IAM implemented
3. Application exceptions are handled but error messages are not public-friendly
3. There is no data/log persistence/auditing. Database is H2 in-memory
