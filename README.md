# docker-spring-test

After mvn install execute

$> docker build -t springapp .

Which will build an image and tag it with "springapp".

To run image execute:
$> docker run -p 8080:8080 springapp

This will run container, with port forwarding from localhost:8080 to containers 8080.

To get logs from container you need to know the container id. To get it execute:
$> docker ps

CONTAINER ID        IMAGE               COMMAND                CREATED             STATUS              PORTS                    NAMES
e8edde6d22a2        sprinapp            "java -jar /app.jar"   5 minutes ago       Up 5 minutes        0.0.0.0:8080->8080/tcp   youthful_johnson

If you want to get a file with logs you need to navigate to docker daemon directory, and its location depends on OS.
Refer to https://docs.docker.com/config/daemon/
In general it will be:
/var/lib/docker/containers/<container id>/<container id>-json.log

Or you can obtain logs via docker command, which is preferable way to get them:
$> docker logs <container-id>
