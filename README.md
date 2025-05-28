# Manorrock Toyger

## ⚠️ Project Archival Notice

This project is part of the Manorrock Sustainability Initiative. We are seeking new maintainers to take over this project. If no maintainers step forward by December 31, 2025, this repository will be archived and moved to the manorrock-attic organization.

### Project Timeline

- **Through December 31, 2025**: Repository remains active while seeking maintainers
- **After December 31, 2025**: If no maintainers found, project moves to manorrock-attic
- **Until December 31, 2030**: Project remains available read-only in the attic
- **After December 31, 2030**: Project may be removed

### Interested in Maintaining This Project?

If you're interested in becoming a maintainer, please see [this GitHub issue](https://github.com/manorrock/toyger/issues/151) for details on how to express your interest and what's involved. Note that new maintainers will need to migrate the project to a new namespace, as the Manorrock branding will remain with Manorrock.com.

**After December 31, 2025**: If this project moves to the manorrock-attic, GitHub issues will no longer be available. If you become interested in maintaining this project after it's archived, please email info@manorrock.com with the subject "Revival Request: [Project Name]".

### More Information

For more information about the Manorrock Projects Sustainability Initiative, please visit our [blog post](https://www.manorrock.com/blog/2025/04/14/manorrock_sustainability_initiative.html).

---

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

## How do I contribute?

See [Contributing](CONTRIBUTING.md)

## Our code of Conduct

See [Code of Conduct](CODE_OF_CONDUCT.md)

## Important notice

Note if you file issues or answer questions on the issue tracker and/or issue 
pull requests you agree that those contributions will be owned by Manorrock.com
and that Manorrock.com can use those contributions in any manner Manorrock.com
so desires.
