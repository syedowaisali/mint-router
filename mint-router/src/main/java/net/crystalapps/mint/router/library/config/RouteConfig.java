package net.crystalapps.mint.router.library.config;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import net.crystalapps.mint.router.library.core.MintRouteEngine;
import net.crystalapps.mint.router.library.core.RouteEngine;
import net.crystalapps.mint.router.library.interceptors.RouteRequestInterceptor;
import net.crystalapps.mint.router.library.interceptors.RouteResultInterceptor;

/**
 * Created by Syed Owais Ali on 7/6/2018.
 */

@SuppressWarnings({"WeakerAccess","unused"})
public class RouteConfig {

    private static final RouteConfig INSTANCE = new RouteConfig();

    @Nullable private RouteRequestInterceptor requestInterceptor;
    @Nullable private RouteResultInterceptor resultInterceptor;
    @NonNull private RouteEngine engine;

    private RouteConfig() {
        engine = new MintRouteEngine();
    }

    public static RouteConfig getInstance() {
        return INSTANCE;
    }

    public RouteConfig addRequestInterceptor(@Nullable RouteRequestInterceptor requestInterceptor) {
        this.requestInterceptor = requestInterceptor;
        return this;
    }

    public RouteConfig addResultInterceptor(@Nullable RouteResultInterceptor resultInterceptor) {
        this.resultInterceptor = resultInterceptor;
        return this;
    }

    public RouteConfig updateEngine(@NonNull RouteEngine engine) {
        this.engine = engine;
        return this;
    }

    @Nullable
    public RouteRequestInterceptor getRequestInterceptor() {
        return requestInterceptor;
    }

    @Nullable
    public RouteResultInterceptor getResultInterceptor() {
        return resultInterceptor;
    }

    @NonNull
    public RouteEngine getEngine() {
        return engine;
    }
}
