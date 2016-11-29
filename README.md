# jersey-docker
Build docker image: 
```
mvn clean package docker:build
```

Run docker image:
```
mvn docker:run
```

Run and watch for changes in mvn target:
```
mvn package docker:watch
```

