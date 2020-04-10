1. kubectl create configmap conf-uat15 from application.properties + uat15/application-spec.properties
2. add configmap conf-uat15 to deploy yaml with selector env=uat15
3. expose configmap as volume dir