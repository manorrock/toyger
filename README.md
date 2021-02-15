# Manorrock Toyger - a Docker registry

This project delivers you with a Docker registry.

## Registry protected by BASIC authentication / authorization

_This configuration is the default configuration_

To get started use the following command lines in an __EMPTY__ directory:

```shell
  curl -o config.yml https://raw.githubusercontent.com/manorrock/toyger/master/config/config_basic.yml 
  touch passwd
  mkdir data
  docker run --name registry --rm -it -p 5000:5000 -v $PWD:/mnt manorrock/toyger
```

_Note you will need to configure your local Docker daemon to allow interacting
with the insecure registry at localhost:5000_

Then in another terminal window you first have to setup a user that can log into
the registry. The command line below creates the user `toyger` with password 
`r0cks`.

```shell
  docker exec -it registry htpasswd -Bb /mnt/passwd toyger r0cks 
```

Now login into the registry using the user you just created by issuing the 
following command line:

```shell
  docker login localhost:5000
```

And then you can interact with the registry:

```shell
  docker pull hello-world
  docker tag hello-world localhost:5000/hello-world
  docker push localhost:5000/hello-world
  docker run localhost:5000/hello-world
```

## Registry protected by Token authentication / authorization

This configuration is under development.