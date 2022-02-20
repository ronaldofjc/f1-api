#!/bin/bash
# Script para execução em ambiente LOCAL
# Parametros possiveis:
# -b : true ou false (ex: sh podman_runlocal.sh -b false). default = true
# O parâmetro -b indica se é necessário compilar a aplicação ou não

CONTAINER_NAME="api-f1"

while getopts p:b: flag
do
    case "${flag}" in
        b) build=${OPTARG};;
    esac
done

if [[ -z "$build" || "$build" != "false" ]]
then
    build=true
fi

echo "Build $build"

if [ "$build" == "true" ]
then
    echo "---- Iniciando compilação do projeto ---- "
    mvn clean install
fi

result=$?
if [ $result -ne 0 ] ; then
  echo Could not perform mvn clean install, exit code [$result]; exit $result
fi

echo "---- Parando o container podman ----"
podman stop $CONTAINER_NAME

echo "---- Deletando a imagem podman ----"
podman rmi $CONTAINER_NAME

echo "---- Criando o container podman ----"
podman build -t api-f1 .
podman run -i --name api-f1 --rm -p 8090:8090 api-f1