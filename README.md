# docker-spring-test

## To run as docker container

After mvn install execute

$> docker build -t docker-spring-test .

Which will build an image and tag it with "docker-spring-test".

To run image execute:
$> docker run -p 8080:8080 docker-spring-test

This will run container, with port forwarding from localhost:8080 to containers 8080.

To get logs from container you need to know the container id. To get it execute:
$> docker ps

|CONTAINER ID |IMAGE| COMMAND| CREATED| STATUS| PORTS| NAMES|

|e8edde6d22a2 | docker-spring-test| "java -jar /app.jar"| 5 minutes ago | Up 5 minutes| 0.0.0.0:8080->8080/tcp| youthful_johnson|

$> docker logs $container-id


## To run in k8s

You need to deploy app ans service to get access to it.
cd to kube folder.

$> kubectl apply -f app-node-port.yaml
$> kubectl apply -f app-deployment.yaml

Application is available on port 31700. THis is being set in nodePort in app-node-port.yaml
To access application first get your minikube ip:
 $>minikube ip
 192.168.64.2
 
 then open $your-minikube-ip:192.168.64.2/greeting in browser.
 
 To check deployed pods:
 $> kubectl get pods
 
 To check deployments:
 $> kubectl get deployments
 
 To delete pod/deployment:
 $> kubectl delete -f path-to-yaml-file.yaml
 