## To run locally
use -DconfDir=conf -DconfDirEnv=conf/uat15

## To run in k8s

* kubectl create namespace uat15
* kubectl create namespace uat16
* kube/app-ns.sh uat15
* kube/app-ns.sh uat16
* add IP from "kubectl get ing -A" column ADDRESS to /etc/hosts
* check links from index.html

## test case

* change some properties - modify prop file[s], delete and create configmap or "kubectl edit configmap config-dev1 -n uat15"
* check that properties are updated - "kubectl exec -it app-dev1-pod -n uat15 -- cat /etc/config/<property file>"
* check app uses old properties - http://uat15.bnp.com/dev1/actuator/env
* restart dev1 app - "kubectl exec -it app-dev1-pod -n uat15 -- kill 1"
* check app uses new properties - http://uat15.bnp.com/dev1/actuator/env