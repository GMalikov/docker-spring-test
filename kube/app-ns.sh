# params 1 - namespace

kubectl delete configmap --all -n $1
kubectl delete pod --all -n $1
kubectl delete svc --all -n $1

kubectl create configmap config-app1  -n $1 \
    --from-file=conf/application.properties \
    --from-file=conf/application-app1.properties \
    --from-file=conf/$1/application-spec.properties

kubectl create configmap config-app2  -n $1 \
    --from-file=conf/application.properties \
    --from-file=conf/$1/

kubectl apply -f kube/pod.yaml -n $1
kubectl apply -f kube/svc.yaml -n $1

cat <<EOF > kube/kustomization.yaml
resources:
- ing.yaml
patchesJson6902:
- target:
    group: extensions
    version: v1beta1
    kind: Ingress
    name: bright-ingress
  path: ingress/$1/patch-host.yaml
EOF

kubectl apply -k kube -n $1

#kubectl get pod,svc -n $1
