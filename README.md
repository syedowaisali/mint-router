# Mint Router ![](https://img.shields.io/bintray/v/syedowaisali/maven/mint-router.svg)   [![GitHub issues](https://img.shields.io/github/issues/syedowaisali/mint-router.svg)](https://github.com/syedowaisali/mint-router/issues)   [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)



- Router library for providing elegant way to define "Router" layer in VIPER architecture.
- This defines a consistent way to navigate between activities and avoid calling startActivity anywhere.
- This also implies by RouteProtocol to fulfil any pre-requisite an Activity might need.
- Reduces boiler plate code by creating intents and calling startActivity internally.


## Release
Available Version:  1.0.0 on [jcenter](https://bintray.com/syedowaisali/maven/mint-router/1.0.0) 


## Library Source
[Jump to library source.](https://github.com/syedowaisali/mint-router/tree/master/mint-router/src/main/java/net/crystalapps/mint/router/library)

## Getting Started
### Prerequisites

Minimum API Level is 21. 

### Adding the library


In your app level gradle **(4.0+)**, add:
```
    implementation 'net.crystalapps:mint-router:1.0.0'
```
for gradle versions **below 4.0** use:
```
    compile 'net.crystalapps:mint-router:1.0.0'
```
## Using in your project

- For simple route to any Activity you can call:
```
    Router.route( this, DetailsActivity.class);
```    
- For simple route with flag to finish current activity use:
```    
    Router.route( this, DetailsActivity.class, true);
```    
    
- To customize intent which the RouteEngine is using, you can provide your own Routeable instance by implementing [RouteProtocol](https://github.com/syedowaisali/mint-router/blob/master/mint-router/src/main/java/net/crystalapps/mint/router/library/protocols/RouteProtocol.java)

- You can also add start delay, which will specify how long the activity will wait before starting, by overriding getLaunchDelay in custom Route class.

```   
public class DetailsRouter implements RouteProtocol {


    private int delay;

    public LoginRouter(int delay) {
        this.delay = delay;
    }

    @Override
    public int getLaunchDelay() {
        return delay;
    }

    @Override
    public boolean willActivityFinish() {
        return false;
    }

    @NonNull
    @Override
    public Intent getIntent(@NonNull Context context) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        return intent;
    }
}
```

Then call:

```
    // this will use your custom configuration
    Router.route( this, new DetailsRouter(3000) );
    
 
```   


- To customize intent and use startActivityForResult feature implement [RouteResultProtocol](https://github.com/syedowaisali/mint-router/blob/master/mint-router/src/main/java/net/crystalapps/mint/router/library/protocols/RouteResultProtocol.java)



## Contributing

Contributions are welcomed as long as they dont break the code. Please create an issue and have a discussion before pull request.

## Hosting

Hosted at JCenter: https://bintray.com/syedowaisali/maven/mint-router

## Authors

* **Owais Ali** - *Initial work* - [@syedowaisali](https://github.com/syedowaisali)

## License

This project is licensed under the Apache 2.0 License - see the [LICENSE.md](https://github.com/syedowaisali/mint-router/blob/master/LICENSE) file for details.

*Sources from Android and Android APIs are subject to the licensing terms as per Android Open Source Project (AOSP).*

