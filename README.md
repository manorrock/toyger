# Manorrock Toyger

[![build](https://github.com/manorrock/toyger/actions/workflows/build.yml/badge.svg)](https://github.com/manorrock/toyger/actions/workflows/build.yml)

This project delivers you with a Docker registry and an admin UI.

## Registry protected by BASIC authentication / authorization

To get started use the following command line:

```shell
  docker run --name registry --rm -it -p 5001:5000 manorrock/toyger
```

This will start Manorrock Toyger and expose it on port 5001. Note that by
default no user has access to the registry so you will need to add a user so you
can access the registry.

Assuming you have the registry up and running using the command line from above
you can add a user to the registry by executing the command line below. Make 
sure to replace &lt;username> and &lt;password> with your own values.

```
  docker exec -it registry htpasswd -Bb /mnt/registry/passwd <username> <password>
```

The next step is to log into the registry so you can pull or push images. As you
exposed it on port 5001 the following command line will log you in:

```shell
  docker login localhost:5001
```

## Admin to manage authentication / authorization

_This is the admin web application to manage Manorrock Toyger_

To get started use the following command line replacing $PWD with the directory
that contains the root directory of the registry container.

```shell
  docker run --name admin --rm -it -p 8080:8080 -v $PWD:/mnt manorrock/toyger-admin
```

Then browse to `http://localhost:8080` to manage your Manorrock Toyger registry.

## Building the Docker images locally

First you will have to build the Java bits. Use the following command line:

```bash
  mvn clean install
```

To build the Docker image for the admin go to the `admin` directory. And the use
the command below:

```bash
  docker build -t manorrock/toyger-admin:latest -f Dockerfile .
```

To build the Docker image for the registry go to the `registry` folder. And then
execute the command line below:

```bash
  docker build -t manorrock/toyger:latest -f Dockerfile .
```

## How do I contribute?

See [Contributing](CONTRIBUTING.md)

## Our code of Conduct

See [Code of Conduct](CODE_OF_CONDUCT.md)

## Important notice

Note if you file issues or answer questions on the issue tracker and/or issue 
pull requests you agree that those contributions will be owned by Manorrock.com
and that Manorrock.com can use those contributions in any manner Manorrock.com
so desires.
