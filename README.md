# Pollfish Android SDK

![alt tag](https://www.pollfish.com/img/logoHome.png)

Pollfish Android SDK allows the integration of Pollfish surveys into Android mobile apps. 

Pollfish is a mobile monetization platform delivering surveys instead of ads through mobile apps. Developers get paid per completed surveys through their apps.

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

## Steps Analytically (Documentation)

You can see a step by step guide on how to integrate Pollfish surveys at the official [Documentation page](http://www.pollfish.com/android)

## Try the look and feel of Pollfish surveys on Pollfish Demo App

[Pollfish Android Demo App] (https://play.google.com/store/apps/details?id=com.pollfish.demo)

## Permissions

Pollfish requires only one permission:

```
<uses-permission android:name="android.permission.INTERNET" />
```
