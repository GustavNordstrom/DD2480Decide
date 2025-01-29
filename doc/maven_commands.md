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

### For linux/macOS:
```shell
For mvn exec:java -Dexec.args="src/test/resources/test_input1.json"
```

### For windows:
```shell
 mvn exec:java -D"exec.args"="src\test\resources\test_input1.json"
 ```