# Manorrock Toyger - a Docker registry

This project delivers you with a Docker registry.

## Registry with no authentication / authorization

This configuration is not protected by any authentication / authorization and as such is __NOT SECURE__.

To get started use the following command lines in an __EMPTY__ directory:

```shell
  curl -o config.yml https://raw.githubusercontent.com/manorrock/toyger/master/config/config_open.yml 
  mkdir data
  docker run --rm -it -p 5000:5000 -v $PWD:/mnt manorrock/toyger
```

_Note you will need to configure your local Docker daemon to allow interacting with the insecure registry at localhost:5000_

Then in another terminal window you can interact with the registry, e.g:

```shell
  docker pull hello-world
  docker tag hello-world localhost:5000/hello-world
  docker push localhost:5000/hello-world
  docker run localhost:5000/hello-world
```

## Registry protected by BASIC authentication / authorization

This configuration is the default configuration.

## Registry protected by Token authentication / authorization
