# Java Service running MongoDB & GraphQL

## Description
A service to that sends workers to inspect websites and monitors for issues.

## Setup
Install [Docker](https://docs.docker.com/install/#supported-platforms) and [Docker Compose](https://docs.docker.com/compose/install/).

```bash
$ git clone https://github.com/Kourchenko/service-sailr
$ cd service-sailr
$ docker-compose up
```

> Docker-Compose is already included with Mac and Windows installs, but not Linux.

> Please note that Windows Home differs from Windows Pro for Docker installations.

## Running the App

```bash
$ docker-compose up -d
```

## Working with the App
<b>Interactactive GraphQL</b> in your web browser `http://localhost:8080/graphiql`.

<b>Postman requests</b> sent to `http://localhost:8080/graphql` to interact with the data.


## Stopping the app

```bash
$ docker-compose down
```

## Data Export
Replace `<date>` with the the current data export date `010520`.sql.
```bash
docker-compose exec mysql mysqldump --no-create-info -u suyc -psuyc suyc --ignore-table=suyc.schema_migerations > <date>.sql
```

## AWS ECR
```bash
$ REPOSITORY_URI=861920639236.dkr.ecr.us-west-2.amazonaws.com/service-sailr
$ aws ecr get-login-password --region us-west-2 | docker login --username AWS --password-stdin $REPOSITORY_URI
$ docker buildx build --platform linux/amd64,linux/arm64 -t $REPOSITORY_URI:latest --push .
```

## Tech Stack
The application utilizies the following technologies and languages:
- Java
- Docker CE
- MongoDB
- [GraphQL-Java](https://www.graphql-java.com/)
- SpringBoot
 ba
