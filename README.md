<==================STEPS TO RUN =========================>

PREREQUISITES: MySQL, Redis, Node

STEPS to follow to run the application 

  Navigate to src/main/resources/application.properties and change the Database properties like username and password for database

  Create a database in your local system and give the same name as mentioned in src/main/resources/application.properties
  
  Bring up the Redis server (Defaulted to localhost and port 6379)

  Navigate to webapp and run command npm install to download node modules. After downloading the modules run command node index.js to bring up server at port 8000
  
  Run command gradlew bootRun (chmod a+x gradlew && ./gradlew bootRun for Linux machines) at project root level. Tomcat is up and connected to database.

  If you are seeing DDL errors while launching the application then:    

  Drop the database you created

  RUN these two commands in your mysql client

  SET GLOBAL default_storage_engine = 'InnoDB';

  create database <DATABASE_NAME> character set latin1;

  RUN the application again using command gradlew bootRun. (chmod a+x gradlew && ./gradlew bootRun for Linux machines)
  
All the default values have been inserted in database already. You can add them in src/main/java/com/portal/company/postSetup/DatabaseSetup.java
or login using already inserted values in db

Application url to access is http://localhost:8000

Please refer the below google doc for implementation details:
