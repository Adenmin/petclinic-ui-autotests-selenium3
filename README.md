**Petclinic simple autotests**

To run all tests use:

```concole
gradlew clean test
```
You can specify petclinic main URL:
```concole
gradlew clean test -DpetclinicUrl=http://host:port
```
> default petclinic  URL is http://localhost:8080  

To run tests by specific runner:
```concole
gradlew clean :junit4:test
gradlew clean :junit5:test
gradlew clean :testNG:test
```