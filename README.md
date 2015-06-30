# Pollfish Android SDK

![alt tag](https://www.pollfish.com/img/logoHome.png)

Pollfish is new way of monetizing mobile apps that pays up to 20x more than classic ads. 

Pollfish is a survey platform that delivers surveys instead of ads through mobile apps. Integration of Pollfish SDK takes less than 2 minutes and requires only one line of code. No changes are needed in the UI of the app since everything is displayed as an overlay (see screenshots below), while users that respond to surveys, enter into draws and win prizes (happy users)! 

Minimum revenue for each completed survey through an app is $0.30 and users never leave the app. If no survey is available, nothing is shown to the users, as if Pollfish was never integrated in the app. 

Since Pollfish addresses the market research marketplace, Pollfish surveys can be used along with any ad network you are already using in your app without any problem, just to bring extra revenue! 

[Learn More..](http://www.pollfish.com/monetize)

## Prerequisites

*	Android 10+ 

## Quick Quide

* Download Pollfish jar or aar file and add to your project
* Setup Google Play services for your app, as described [here](https://developer.android.com/google/play-services/setup.html).
* Import Pollfish classes
* Add permissions to AndroidManifest.xml
* Call the init function to activate Pollfish
* Set to Release mode and release in Store
* Update your privacy policy

**Note: Be careful to turn the debuggable parameter in AndroidManifest.xml to false when you release your app in Google Play! (or just delete it)**

## Screenshots

![alt tag](https://storage.googleapis.com/pollfish_production/sdk/Android/playful_1.png)
![alt tag](https://storage.googleapis.com/pollfish_production/sdk/Android/playful_2.png)

## Documentation

You can see a step by step guide on how to integrate Pollfish surveys at the official [Documentation page](http://www.pollfish.com/android)

## Try the look and feel of Pollfish surveys on Pollfish Demo App

[Pollfish Android Demo App] (https://play.google.com/store/apps/details?id=com.pollfish.demo)

## Permissions

Pollfish requires only one permission:

```
<uses-permission android:name="android.permission.INTERNET" />
```
