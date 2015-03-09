# Nower Hybrid Application

Nower is an application that offers the user multiple kind of immediate promos, that is, they last short time or are limited by number of people who can redeem them.

In this repository you will find the client application where a promo can be published giving a position in the globe, a title, description, expiration date and people limit; and in the user application all the promos can be visualized as markers in the map. Also, the user can tap and accept a promo to get a code to redeem it.

The application was developed using [Ionic framework], so it can be deployed for Android and iOS.

### Instructions
In order to get both applications working in your device or in any emulator you should follow these instructions:
 - Install Ionic and Cordova as specified in [this tutorial].
 - Open in a terminal de *nower_client* application
 - Add the desired platform with the command: '*ionic platform add <platform>*', example:
```sh
$ ionic platform add android
```
 - Build and run the application for the platform you added, as follows:
```sh
$ ionic build android
$ ionic run android
```
 - If you don't have a physical device you can emulate with:
```sh
$ ionic emulate android
```
 - Make the same procedure with the *nower_user* application.

After done, both applications would be installed in your device, so you can test Nower, first, publishing a new promo in the *Nower client* application.

###Screenshots
* Publishing a promo.

![alt img](https://raw.githubusercontent.com/ibalejandro/coding_dojos/f49c88e0c8d2447d1ef8072c85c38ff9c0df04bb/challenge_1/screenshots/new_promo.jpg)

* Visualizing the published promo.

![alt img](https://raw.githubusercontent.com/ibalejandro/coding_dojos/03a704e5cdf8c2d0e1c4447456172c2e7e65d667/challenge_1/screenshots/view_promo.jpg)

* Viewing the promo content.

![alt img](https://raw.githubusercontent.com/ibalejandro/coding_dojos/03a704e5cdf8c2d0e1c4447456172c2e7e65d667/challenge_1/screenshots/promo_description.jpg)

* Accepting the promo and getting a code to redeem it.

![alt img](https://raw.githubusercontent.com/ibalejandro/coding_dojos/03a704e5cdf8c2d0e1c4447456172c2e7e65d667/challenge_1/screenshots/promo_code.jpg)

[this tutorial]:http://ionicframework.com/getting-started/
[Ionic framework]:http://ionicframework.com/

