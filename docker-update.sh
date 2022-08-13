#!/bin/bash

docker_user=0x7374657665
app_name=horner-reader
img_tag=${1:-latest}

api=$app_name-api
web=$app_name-web

cd backend
docker build -t "$api:$img_tag" .
docker tag "$api:$img_tag" "$docker_user/$api:$img_tag"
docker push "$docker_user/$api:$img_tag"

cd ../frontend
docker build -t "$web:$img_tag" .
docker tag "$web:$img_tag" "$docker_user/$web:$img_tag"
docker push "$docker_user/$web:$img_tag"