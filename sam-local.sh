#!/bin/sh

./gradlew clean assemble
docker build . -t micronaut-lambda-graal
docker run --rm --entrypoint cat micronaut-lambda-graal  /home/application/app.zip > build/app.zip

docker-compose up -d

sam local start-api -t sam-graal.yaml -p 8080 --docker-network micronaut-lambda-graal_default --env-vars sam-local-env.json
