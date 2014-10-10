
## Context

Android REST client example using retrofit for api calls. Demonstrates keeping cookies for session
in singleton Application object as well as using the http response cache.

Realm.io for android is used to cache data on device when network is available so that we can show
something if app is gone offline.

## This example contains

* ListView with custom BaseAdapter using ViewHolder pattern for performance
* Retrofit API endpoint definitions for loading data from web
* http cookie based session and http response cache for minimizing network traffic


## TODO

* create fragment for creating new items from app
* implement detail fragment
* add otto event bus for caching data ?

## Backend

You can either consume the endpoints on the example backend I created an published on heroku at
http://still-peak-5323.herokuapp.com/

Or you can follow the below instructions to create your own backend with NodeJS and MongoDB.

### Prerequisites

Install NodeJs & NPM from http://nodejs.org and MongoDB from http://mongodb.org. Once these are
installed you can use NPM to install the bumm project with the below command

    npm install -g bumm

### Project scaffolding

And that is enough to start creating a project. Check bumm project website for more details at
https://github.com/saintedlama/bumm

    bumm app test
    cd test
    npm install
    bumm scaffold item title description checked:boolean
    node app.js

### Tips

#### server address

If you want to host that project on any other server you'll have to update the app.js file, find the
below lines at the bottom of the file

    // Start server if not invoked by require('./app')
    if (require.main === module) {
        http.createServer(app).listen(config.port, config.address, function() {
            console.log("Express server listening on %s:%d in %s mode", config.address, config.port, app.settings.env);
        });
    } else {

and remove the config.address argument from the listen method. That way you can run it on any server
(or match the property in the config)

    // Start server if not invoked by require('./app')
    if (require.main === module) {
        http.createServer(app).listen(config.port, function() {
            console.log("Express server listening on port %d in %s mode", config.port, app.settings.env);
        });
    } else {

#### json output

By default bumm will create views and use these to render content. If you want json only output you
can edit the /routes/items.js file to have something like this

    app.get('/items/json', function(req, res) {
        Item.find({}, function(err, items) {
            res.send(items);
        });
    });

This will return json formatted items when perform a GET request at /items/json URL.

#### Update database config

Just check the /config/defaults.js (or whatever environment you're running at) to update the
mongodb url

#### Publish on heroku

You need to have the heroku toolbelt installed for these commands to work

    heroku create
    heroku addons:add mongohq:sandbox

configure mongohq url, use heroku config to get the url printed first

    heroku config

and update the /config/defaults.js (or any other env you're configured for) to match that url

Next create a Procfile with content

    web: node app.js

and push all this code towards your heroku app and you should get it running. If not check logs with

    heroku logs

optional rename of your project

    heroku apps:rename NEWNAME

## Resources

* otto event bus page: http://square.github.io/otto/
* retrofit project page: http://square.github.io/retrofit/
* some info on using retrofit: http://corner.squareup.com/2012/07/otto.html
* http response cache: http://developer.android.com/reference/android/net/http/HttpResponseCache.html
* http session cookie persistence: http://stackoverflow.com/questions/18894540/using-retrofit-with-cookie-persistence
* realm.io for android: http://realm.io/news/realm-for-android/
* scaffolding for nodejs: https://github.com/saintedlama/bumm