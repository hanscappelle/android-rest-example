
## Context

Android REST client example using retrofit for api calls. Demonstrates keeping cookies for session
in singleton Application object as well as using the http response cache.

Realm.io for android is used to cache data on device when network is available so that we can show
something if app is gone offline.

## This example contains

* ListView with custom BaseAdapter using ViewHolder pattern for performance
* Retrofit API endpoint definitions for loading data from web
* using session and http response cache for minimizing network traffic
*


## TODO

* rename project
* check in project git
* add otto event bus for caching data ?
* create screen for push request
* create backend service + instructions + publish

## Resources

* otto event bus page: http://square.github.io/otto/
* retrofit project page: http://square.github.io/retrofit/
* some info on using retrofit: http://corner.squareup.com/2012/07/otto.html
* http response cache: http://developer.android.com/reference/android/net/http/HttpResponseCache.html
* http session cookie persistence: http://stackoverflow.com/questions/18894540/using-retrofit-with-cookie-persistence
* realm.io for android: http://realm.io/news/realm-for-android/
* scaffolding for nodejs: https://github.com/saintedlama/bumm