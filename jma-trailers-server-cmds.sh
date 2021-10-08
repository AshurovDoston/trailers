#!/usr/bin/env bash

echo "***removing container***"
docker container rm -f jma-trailers
echo "***removing image***"
docker image rm crasoftinc/trailers
echo "***creating a new container with updated image***"
docker-compose up -d
