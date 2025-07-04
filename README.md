# Read Me First

The following was discovered as part of building this project:

- The original package name 'com.huerta.kafka.saga-pattern' is invalid and this project uses 'com.huerta.kafka.saga_pattern' instead.

# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

- [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
- [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.5.0/maven-plugin)
- [Create an OCI image](https://docs.spring.io/spring-boot/3.5.0/maven-plugin/build-image.html)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.

## 🧪 Usage

### ✅ Check Formatting

To verify that the code follows the formatting rules:

```bash
mvn spotless:check
```

To automatically formt code:

```bash
mvn spotless:apply
```

### Maven commands

To install without running tests

```bash
mvn clean install -DskipTests
```

To run a specific test with maven surefire

```bash
mvn -Dtest=CreateOrderUseCaseImplTest#test_name test
```

### OUTPUT TO A TXT FILE

To install without running tests

```bash
mvn spotless:apply && mvn clean install > output.txt 2>&1
```

### DOCKER COMMANDS

To run SonarQube

```bash
docker compose -f sonarqube-compose.yml up -d
```
