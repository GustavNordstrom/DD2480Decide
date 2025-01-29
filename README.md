# DD2480Decide

## **📌 Project Overview**
This project implements **DECIDE()**, a function that determines whether an interceptor should be launched based on radar tracking data. The decision process involves evaluating **15 Launch Interceptor Conditions (LICs)**, combining them using logical operators, and generating a **Final Unlocking Vector (FUV)**, which ultimately decides whether to launch.

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

## **🔹 Features**
- Evaluates **15 Launch Interceptor Conditions (LICs)** to determine interceptor launch.
- Uses **CMV (Condition Met Vector)**, **PUM (Preliminary Unlocking Matrix)**, and **FUV (Final Unlocking Vector)** for decision-making.
- Supports **JSON-based input** for radar tracking data.
- Includes **unit tests** to ensure correctness.

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
- **Biming Wen**: Design modules and interfaces. Set up project structure and environment. Employ Continuous Integration scripts. Implement modules of FUV, PUM, inputoutput and their unit tests.
- **Xu Zuo**: Implemented the majority of LICs and corresponding unit tests, set up initial tests and implemented tests for decide(), add new features to /common to handle input data, fix parts of FUV and PUM modules for test.
- **Gustav Nordström**:
Implemented parts of CMV module including LIC and corresponding unit tests and CalculationUtils to reduce code duplication.
- **Gustav Wallin** :
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
