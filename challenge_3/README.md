##### Challenge 3
---
# Hexagonal/Clean architecture
## Overview
The back-end of the application was implemented using clean architecture,
so we took the core (entities, use cases, logic) and moved it out into a gem 
(*nower_core*).
That gem is used from the rails application (*nower_server*), specificly from
each controller. A repository adapter is injected in the initializer located at
 **config/initializers/nower_core.rb**, so that the core can work with it.

The *nower_user* project was slightly modified because we moved out the promo
code generation to the back-end as a new use case.

The *nower_client* project received slight modifications too.

## Try it yourself
##### In our host
Run *nower_client* or *nower_user* application using Ionic.
##### In your local or another host.
You can build the gem from the *nower_core* project using:
```sh
$ gem build nower_core.gemspec
```
A **nower_core-0.0.1.gem** file will be generated, then you can install it in
your machine using:
```sh
$ gem install nower_core-0.0.1.gem
```
After that, execute a `bundle install` and `rake db:migrate` (if needed) in the
 rails application and start it.

Modify in both *nower_user* and *nower_client* applications in the file
**www/js/services.js** all URL to your localhost or desired address.

Execute *client* or *user* application.

