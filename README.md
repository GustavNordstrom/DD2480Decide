# DD2480Decide

## **📌 Project Overview**
This project implements **DECIDE()**, a function that determines whether an interceptor should be launched based on radar tracking data. The decision process involves evaluating **15 Launch Interceptor Conditions (LICs)**, combining them using logical operators, and generating a **Final Unlocking Vector (FUV)**, which ultimately decides whether to launch. Includes **unit tests** to ensure correctness.

### **🔍 Program Structure**
- **Entry Point**: The program starts in `App.java`, containing the `decide` function, which takes an input file and outputs the launch decision with the help of the following modules.
- **Modules**:
   - **common**: Contains utility classes and common data structures used across the project.
  - **CMV**: Evaluates the 15 LIC's and generates a Conditions Met Vector (CMV).
   - **PUM**: Creates the Preliminary Unlocking Matrix (PUM) using the Logical Connector Matrix (LCM) and CMV.
  - **FUV**: Generates the Final Unlocking Vector (FUV) using the Preliminary Unlocking Vector (PUV) and PUM.
  - **inputoutput**: Handles input and output operations, including processing input file (JSON) into data structures and formatting output data.

## **📂 Project Structure**
```
├── src/
│   ├── main/java/com/dd2480/
│   │   ├── CMV/
│   │   ├── common/
│   │   ├── example/
│   │   ├── FUV/
│   │   ├── inputoutput/
│   │   ├── PUM/
│   │   ├── App.java
│   │   ├── input1.json
│   ├── test/java/com/dd2480/
│   │   ├── CMV/
│   │   ├── common/
│   │   ├── example/
│   │   ├── FUV/
│   │   ├── PUM/
│   │   ├── AppTest.java
│   ├── target/
│   ├── .gitignore
│   ├── pom.xml
│   ├── README.md
```

## **🏃 How to run the program**

### Requirements
Project tested on: 
- Java JDK 18
- Apache Maven 3.9.9

### How to run

1. Compile with ```mvn compile```
2. Run the program 
   - Linux/MacOS ```mvn exec:java -Dexec.args="path/to/input.json"```
   
   Example:
   ```mvn exec:java -Dexec.args="src/test/resources/test_input1.json"```
   - Windows ```mvn exec:java -D"exec.args"="path\to\input.json"```
   
   Example:
    ```mvn exec:java -D"exec.args"="src\test\resources\test_input1.json"```

### How to run tests

- Use ```mvn test```

## **📌 Contributions**
### **🔹 Team Members**
- Biming Wen @WenBiming
- Xu Zuo @floralsea
- Gustav Nordström @GustavNordstrom
- Gustav Wallin @Muppsz
- David Persson @Eb3nezzer

### **🔹 Contribution Statements**
- **Biming Wen**: 
  - Design modules and interfaces, including packages CMV, common FUV, inputoutput, PUM.
  - Set up project structure and environment. Employ Continuous Integration scripts .github/workflows/maven.yml. 
  - Implement specific modules of 
    - CMV: Interfaces Condition, ConditionContext, CondtionManager. Classes CMV, ConditionContextImpl, ConditionManagerImpl  
    - FUV: Interfaces FUVManager. Classes FUV, FUVManagerImpl.
    - PUM: Interfaces PUMManager, classes PUM, PUMManagerImpl
    - inputoutput: Interfaces InputHandler, OutputHandler. Classes InputHandlerImpl, OutputHandlerImpl, OutputFormatter
  - unit tests of modules above
- **Xu Zuo**:
  - Implemented the 1st to the 12th LICs and corresponding unit tests, implemented the logic for _evaluate()_ in each condition.
  - Added new features to /common (_LCMDeserializer_, _PointCollectionDeserializer_, _PUVDeserializer_) as helper to handle input data (parsing JSON format input).
  - Set up initial test skeleton and implemented tests for _decide()_ in App.java, implemented and debugged the initial test for _decide()_ in AppTest.java.
  - Fixed parts of FUV and PUM modules for test.
  - Provide comments in the code modules I was responsible for.
- **Gustav Nordström**:
   Implemented parts of CMV module including:
   - The 13th LIC and corresponding unit tests.
   - Refactored CMV module to remove duplicate code used for calculations by setting up common utility calculationUtils class.
- **Gustav Wallin** : Also implemented parts of CMV module with LIC and unit test along with revising unit tests for already finished LIC's. Added to calculationUtils along with refactoring that followed that. Worked on input handling of main function and decide function.
- **David Persson**:

### **🔹 How to Contribute**
1. Clone the repo:
   ```sh
   git clone https://github.com/your-repo.git
   ```
2. Create a feature branch:
   ```sh
   git checkout -b feature-branch
   ```
3. Commit and push:
   ```sh
   git commit -m "Implemented ConditionX"
   git push origin feature-branch
   ```
4. Open a pull request.

## **📜 License**
This project is licensed under the **MIT License**.

### **🎯 Summary**
✔ Implements **decide()** function for interceptor launch decision.  
✔ Uses **LIC conditions**, **LCM**, **CMV**, **PUM**, **FUV** for decision-making.  
✔ Supports **unit testing** and **JSON-based input**.  

🚀 **Now, Run the Code and Protect the System!** 🚀

### Essence 

Regarding essence, our team has filled some parts of the checklist but not all of them. Generally, we believe we have established  principles and a foundation for a way of working with key practices and tools and they have been integrated in our way of working, for example communications, git workflow, program modules etc of which many aspects have improved during the project. At the same time, we are probably not at the highest stages as a team either as, at least for some members, not all tools are used fluently. Also not everyone is equally contributing to inspection and adaptation of the work. 
