# Setup Instructions

## Prerequisites
- Java JDK installed (version 8 or higher)
- Maven installed
- Git installed

### 1. Clone the repository
```bash
git clone https://github.com/ks6201/StudentManagement
````

### 2. Navigate to the project directory

```bash
cd StudentManagement
```

### 3. Build and install the project

```bash
mvn clean install
```

Here’s a clearer way to include the IDE-specific instructions with your existing command:

---

### 4. Run the application

#### Option 1: Using the JAR file

```bash
java -jar Cli/target/Cli-1.0-SNAPSHOT.jar
```

#### Option 2: Using an IDE (IntelliJ or VS Code)

1. Navigate to the source file in of this Cli app in explorer or file tree:

   ```
   Cli/src/main/java/org/assignment/cli/Main.java
   ```
2. Open `Main.java`.
3. Click the **Run** button in your IDE.
   *(Ensure Java is properly installed and your IDE is configured to use the correct JDK.)*