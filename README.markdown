# Spring boot and react hot starter
This project also sets up spring security and http://projects.spring.io/spring-session/[spring-sessions], which will
automatically store your sessions in Redis, allowing you to scale on multiple servers.


### Running the backend (recommended)

The recommended way to launch the server is to use your favorite java IDE.
The main method of the application is in the `BootReactApplication` class.

### Running the frontend (recommended)

Run `npm install` before first run, and then `npm start`.

If you change the node dependencies in `package.json`, delete the `node_modules` dir or
run `./gradlew frontend:npmInstall`

### Alternatives for running the projects

There is also a gradle task to run the spring server: `./gradlew bootRun`.

The node version used by the gradle build is specified https://github.com/geowarin/boot-react/blob/master/frontend/build.gradle#L11-L12[here].
If you have an equivalent version of npm installed on your system, you can use it to start the frontend.

Go in the `frontend` directory and type `npm install` to install the dependencies.
Then, use `npm start` to start the dev server.

**You will need node 5.0+ and npm 3 to run the dev server and build the project**

If you do not have the required binaries on your machine, you can use `./gradlew frontend:npmInstall` and `./gradlew frontend:start`.
Those two command will download the required node/npm versions automatically and use them to run the node tasks.

### Sessions

Sessions are stored in Redis with spring-sessions.
Spring-sessions allows you to transparently persist the HttpSession on Redis.
This allows to distribute the load on multiple servers if you choose to.

The application relies on a stateless REST api.
When they authenticate, clients will be given a token.
They will save this token in their local storage and send it as an HTTP header (`x-auth-token`).
This allows the retrieval of the session data in Redis.

If you want to use a real redis, you can run the application with the `redis` profile.

If the `redis` profile is not active, your session will be stored in a map.
See: https://github.com/geowarin/boot-react/blob/master/backend/src/main/java/react/config/redis/EmbeddedSessionConfig.java[EmbeddedSessionConfig].
This is great in development but you should avoid it in production.

Summary:
|===
| Profile | description | uses `x-auth-token` header?

| `redis` | Use a real redis connecting on localhost by default. | Yes
| <none> (`!redis`) | Uses a map to store sessions | Yes
|===

### Active profiles

If your run your project with gradle, the system properties won't be passed on to Spring.
See https://github.com/spring-projects/spring-boot/issues/832[this issue] for workarounds.

The simplest way to go is to specify active profiles in your IDE.

http://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-profiles.html[Check out the doc] to learn
more about profiles in Spring Boot.

To run the jar in production mode use the following command:

```
java -jar boot-react-0.0.1-SNAPSHOT.jar --spring.profiles.active=production                                                       16:57:01
```

### Security

The application is configured to work with Spring Security.
It uses an in-memory authentication but you are free use
http://docs.spring.io/spring-security/site/docs/4.0.2.RELEASE/reference/htmlsingle/#jc-authentication[other implementations]
or to http://docs.spring.io/spring-security/site/docs/4.0.2.RELEASE/reference/htmlsingle/#core-services[roll your own].


### Router

In development, we use a dev server that
https://github.com/geowarin/boot-react/blob/master/frontend/server.js#L21-L24[proxies] requests to the index.

In production, we have to use a special https://github.com/geowarin/boot-react/blob/master/backend/src/main/java/react/config/SinglePageAppConfig.java[resource handler]
to redirect all non-asset requests to the index.

### Stylus

In development, the styles are included by webpack, which enables hot reloading.
In production, we use the https://github.com/webpack/extract-text-webpack-plugin[Extract Text Plugin] to extract the css to a separate file.


## Shipping

This command will generate an optimized bundle and include it in the jar.

```
./gradlew clean assemble
```

You can then launch it with:

```
java -jar build/libs/boot-react-0.0.1-SNAPSHOT.jar
```

With spring boot 1.3, you can install the application http://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/html/deployment-install.html#deployment-service[as a linux service]

NB: each application can be assembled with the `assemble` task so you can use `frontend:assemble` or `backend:assemble`.
The backend task depends on the frontend task.

## Docker

The project can create a docker container.

Just run:

```
./gradlew backend:buildDocker
```

And it will create a docker image named `geowarin/boot-react`.

```
> docker images
REPOSITORY                               TAG                 IMAGE ID            CREATED              VIRTUAL SIZE
boot-react/boot-react                    latest              5280d39f660f        About a minute ago   138.9 MB
```

You can then run it with:

```
docker run -p 8080:8080 geowarin/boot-react
```

You can also pass arguments to the application like this:

```
docker run -p 8080:8080 boot-react/boot-react --spring.profiles.active=redis --spring.redis.host=redis
```

## Docker-compose

There is a simple `docker-compose.yml` in the root directory of the project.
Once you have built the application image with `./gradlew backend:buildDocker`, you can run:

```
docker-compose up -d
```

This will run the application together with a redis server.

