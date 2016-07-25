# Zaza POC backend

### Running the backend

Run the main methion in the `BootZazaApplication` class.

### Running the frontend

Run `npm install` before first run, and then `npm start`.

If you change the node dependencies in `package.json`, delete the `node_modules` dir or
run `./gradlew frontend:npmInstall`

### Alternatives for running the projects

There is also a gradle task to run the spring server: `./gradlew bootRun`.

## Shipping

This command will generate an optimized bundle and include it in the jar.

```
./gradlew clean assemble
```

You can then launch it with:

```
java -jar backend/build/libs/zaza-0.0.1-SNAPSHOT.jar
```

NB: each application can be assembled with the `assemble` task so you can use `frontend:assemble` or `backend:assemble`.
The backend task depends on the frontend task.

## Docker

The project can create a docker container.

Just run:

```
./gradlew backend:buildDocker
```

And it will create a docker image named `klyver/zaza`.

You can then run it with:

```
docker run -p 8080:8080 klyver/zaza
```

