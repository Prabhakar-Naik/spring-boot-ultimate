package com.springboot.ultimate.tutor;

/**
 * @author prabhakar, @Date 24-07-2024
 */
public class Maven {

    public String whatIsApacheMaven(){

        return """
                1. Maven helps Programmers to manage their Projects and all the things
                They need to build their programs.
                2. It is a Transitive Dependency.
                ==> to compile the maven in your project ?
                first way:
                1. Go to Top right of M like maven click that
                2. Go inside the your project
                3. Go inside the lifecycle.
                4. Click the compile two times
                It will start the compilation
                second way using terminal:
                1. Open terminal
                2. ./mvnw clean compile
                It will be finish the compilation
                Important and Danger
                mvnw [options] [<goal(s)>] [<phase(s)>]
                
                maven have three phases
                1. clean : Remove temporary directories and files.
                2. default : Where the most useful goal lives.
                3. site : Where documentation is granted.
                
                I. mvnw clean:
                    1. pre-clean: Hook for before cleaning.
                    2. clean: Does the actual cleaning.
                    3. post-clean: Hook for after cleaning.
                    Experimental example: ./mvnw pre-clean, ./mvnw clean, ./mvnw post-clean
                    it will remove the target folders, each and every class in our project.
                    
                II. mvnw [default] life cycle:
                    1. compile: compile your code into bytecode.
                    2. test : run unit tests.
                    3. package: creates a jar or war files.
                    4. verify: Runs checks and integration tests.
                    
                    you can test the all commands one by one to observe on target dir
                    Experimental example:
                    1. ./mvnw clean    then observe target dir
                    2. ./mvnw compile   ""  to run this command it will compile
                    3. ./mvnw clean     ""  clean the directory of classes
                    4. ./mvnw test      ""  to run this first execute compile then executes test
                    5. ./mvnw clean     ""  clean the directory of classes
                    6. ./mvnw package   ""  to run this first execute compile then executes test and after that package
                    7. ./mvnw clean     ""  clean the directory of classes
                    8. ./mvnw verify    ""  to run this first execute compile then executes test then package finally verify.
                III.Maven project structure
                    To expand your project
                    => you can recognise src/main/java like this
                    => as well for testing src/test/java like this
                    => this is the project structure for maven projects
                    => we can mention that resource dir inside the test directory
                       for static values for testing purpose not in production mode.
                IV.To Run The Application using terminal
                    go throw the command according to your project directory structure
                    1. cd target,
                    2. dir (directories),
                    3. java -jar spring-boot-ultimate-0.0.1-SNAPSHOT.jar (not original jar)
                V.Maven Spring Boot Plugin
                    to run project using terminal in another way is here
                    ==> path must in project directory ./mvnw spring-boot:run
                VI.Spring Framework Vs Boot
                    -> Spring Boot is a Popular framework for building Java Applications.
                    -> A Framework Chunk of code written on top of a languages core library
                       to solve common problems
                            like exposing Rest APIs and connection with databases and so on.
                    -> Actually spring boot is a layer on spring framework,
                       Spring Framework is highly configurable like DI, IOC, Autowiring ect
                VII.Spring App Layers
                    these layers we can divide in three stages like
                    1. presentation
                    2. service
                    3. persistence
                    Persistence layer:
                        ==> persistence layer responsibility is to connect database through entity classes
                        example: persistence layer connect to repositories and dao's for db connection
                                 with the help of entity classes.
                    Service layer: (Implementation Layer)
                        ==> service layer exposed by persistence layer for the actual logic in service
                        example: service layer can build a logic based on persistence layer interfaces
                                 with dev define declarable methods or pre-built from java community.
                    Presentation layer: (Controller layer)
                        ==> presentation layer exposed rest apis for performing actual logic on user requirements.
                        example: presentation layer mean controller can expose our rest apis and perform the functions
                                 with the help of service layer implementation code for user requires.
                VIII.Modularity
                    The spring framework take care of persistence layer
                    like these steps:
                    Spring Data JPA -> Spring JDBC -> Database Driver ex. pgAdmin, oracle, mysql ect.
                IX. Dependency Injection:(Inversion Of Control)
                    It is a design pattern that Spring uses to implement Inversion of Control (IoC).
                    It allows the creation of dependent objects outside of a class and provides those
                    objects to a class in various ways.
                    like we have User class in our project as well in java default class also
                    but we can get the import statements from specified class.
                    when we need the specific one then simply change the target one.
                X. Introducing Beans
                   I implemented on this concept DI and Beans.
                   go through colors directory in project.
                   you can learn DI IOC and Beans practically
                XI. Using @Component we can IOC on Beans
                    we don't require for configuration on bean classes because,
                    I configured @Component to implemented classes.
                    --> but don't mention more than one availability of source
                    if you do you can get the Exception.
                    you can use the @Service annotation also but it sometime not
                    compatible as much as @Component
                    @Service also perform the same thing you can try yourself.
                    
                XII. Using @ComponentScan (Component scanning):
                    @Component scan annotation is looking the related bean classes mentioned
                    in the project directory which is present the SpringBootApplication Class.
                    you can try to access outside the SpringBootApplication directory it will
                    through some exception which is not available or not found ect means
                    could not be found.
                XIII. Using @SpringBootApplication:
                    A @SpringBootApplication is Alias for many annotations they are mentioned below
                    1. @ComponentScan
                    2. @EnableAutoConfiguration
                    3. @SpringBootConfiguration
                    These three annotations are plays major role in spring boot applications.
                    4. @Inherited
                    5. @Documented
                    mainly these 5 annotations
                    remains are not exactly important it will over on policy and target applications
                    6. @Target({ElementType.TYPE})
                    7. @Retention(RetentionPolicy.RUNTIME)
                XIV. Detail About @AutoConfiguration
                    This annotation mainly working on spring boot starters dependencies
                    you want more detail about it go through external libraries and find
                    the starter dependencies and try to find conditional beans in that class.
                    this is enough knowledge about Auto Configurations in spring boot app.
                XV. Know About Common Application Properties
                    you can refer the documentation from spring.io go through below link
                    https://docs.spring.io/spring-boot/appendix/application-properties/index.html#appendix.application-properties.core
                    the core concepts about properties all are mentioned here
                    like change port number you must follow rules & use it in application.
                    ==> server.port=8082
                    if you are using yml extension file you must follow indentation rule like
                    ==> server:
                            port: 8045
                XVI. Environment Variables:
                    you can set the environment variables in spring boot application configurations.
                    like below ways
                     SERVER_PORT=8083
                    or
                     System.setProperty("server.port","8090");
                    and multiple ways there are.
                XVII. Configuration properties
                    we can write CustomContainer for running our application.
                    as well we can declare values in properties and yml files
                    directly through configuration classes
                    another way is we can mention environment variables directly in 
                    spring boot application class.
                XVIII. Database layer:
                    you must clarify about data JPA, JDBC and Driver
                    here the example is
                    1. spring data jpa provide JPA layer for spring boot framework.
                    2. spring jdbc provides CRUD layer for spring boot framework.
                    3. spring drivers internally configured with specified values inside
                       the properties file.    
                    ==> There is no differ b/w JPA repository and CRUD repository
                    some basic functions are perform on their regulations of execution
                    ==> We can also customize the functions.
                    ==> JPA build top of JDBC 
                    ==> The interaction with database take care of by JPA and JDBC can connect
                        via JPA and JPA is very compatible to spring boot framework.
                    ==> There is another layer called hibernate this is also build on top of
                        JDBC and JPA.
                    ==> Hibernate build on top of JPA. the hibernate repositories very lighter
                        because hibernate refers to ORM Object Relational Mapping.
                    ==> ORM interact with java class objects to driver to database as well reverse.
                XIX. Connect to H2 Database:
                    ==> to connect in memory database use h2 database for temporary
                    ==> h2-database configuration In-Memory database
                    ==> you can find the thing about h2 in project go properties file
                        as well dependency management in pom.xml
                XX. Connect to PostgresSql Database:
                    ==> to connect real database I'm using postreSql for local storage.
                    ==> pgAdmin configuration realtime database
                    ==> you can find the thing about postgreSQL in project go properties file
                        as well dependency management in pom.xml
                XXI. Initialize DB Schema
                    ==> we can connect through application.properties file using
                    ==> spring.sql.init.mode=always
                    ==> we must have the data.sql and scheme.sql for initialization
                    or we can achieve this below way.
                    ==> using datasource configuration using jdbc template
                    you can refer DatabaseConfig class in configuration package.
                XXII. What is DAO (Data Access Object)
                    ==> we must have some domains for store data in table like book and author and more,
                    ==> we must have to create and extensionally to create DAO layers on them
                    ==> we should implementing the DAO layer implementation.
                    ==> to test the connection on using jdbc template use in memory db h2
                    ==> i am already configured that just look into it.
                    ==> DAO creation and implementation on test case scenarios.
                XXIII. Create method using Jdbc template In CRUD
                    ==> we integrate the h2 in memory db for testing purposes
                    ==> here to refer the resource folder in test directory in the project
                    ==> go through test directory you can find the book and author
                        creation test methods with help of mockito environment.
                    ==> go through implementation you can learn the great things here.
                XXIV. Let's go with Read method in CRUD 
                    ==> go through test directory you can find the book and author
                        find test methods with help of mockito environment.
                    ==> as well go in author and book dao's functions and row mappers
                        implementation for read operation data.
                XXV. Create Integration test between the functionality
                    ==> to build or implement integration testing on out building functionality
                        we can create some classes like integration test class
                    ==> go through test dir and find the integration test with TestDataUtil class.
                    ==> Then move the functionality write your test cases on different ways.
                XXVI. Let's go with Read many in CRUD
                    ==> to build or implement integration testing, as well low level functionality.
                    ==> go through implementation logic in implTest as well integration test
                        you can find find() method for retrieve the records from db.
                XXVII. Update Integration test functionality
                    ==> to build the functionality on update a record and integrating testing
                    ==> go through implementation in book and author test and integrated test
                        functions on update.
                    ==> find the update methods and analyse it well.
                XXVIII. Delete Integration test implementations
                    ==> to build the functionality about delete integration test.
                    ==> go through implementation in book and authors functionality.
                    ==> delete and deleteOne both function performing same but 
                        procedural query is differ.
                XXIX. Spring Data JPA Setup
                    ==> to implement data jpa in this project it self i made some changes
                    ==> 1. i commented all the code which is developed by me. at the to you can
                        find Jdbc api
                        example: test dir dao.impl completely commented as well i want to reuse the
                        integration part so i copied and created new dir repositories in test dir
                    ==> 2. i completely commented dao and it's implementation in java directory.
                    ==> As well database configuration in configuration directory.
                    
                    
                """;
    }


}
