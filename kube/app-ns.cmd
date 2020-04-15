kubectl delete configmap --all -n %1
kubectl delete pod --all -n %1
kubectl delete svc --all -n %1

kubectl create configmap config-dev1  -n %1 ^
    --from-file=conf\application.properties ^
    --from-file=conf\application-dev1.properties ^
    --from-file=conf\%1\application-spec.properties

kubectl create configmap config-dev2  -n %1 ^
    --from-file=conf\application.properties ^
    --from-file=conf\%1\application-dev2.properties ^
    --from-file=conf\%1\application-spec.properties

kubectl apply -n %1 -f kube\app2.yaml
