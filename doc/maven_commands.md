# compile the project
```mvn compile```

# run tests
```
mvn test
```

# run a single test method in a test class
```shell
mvn -Dtest=ClassName#methodName test
```

# run all test methods in a test class
```shell
mvn -Dtest=ClassName test
```

# run the program
```shell
mvn exec:java
```