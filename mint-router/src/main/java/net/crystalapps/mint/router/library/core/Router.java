package net.crystalapps.mint.router.library.core;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import net.crystalapps.mint.router.library.config.RouteConfig;
import net.crystalapps.mint.router.library.protocols.RouteProtocol;

/**
 * Created by Syed Owais Ali on 5/19/2018.
 */

public class Router {

    public static void route(@NonNull Context context, @NonNull Class<? extends FragmentActivity> clazz) {
        route(context, clazz, false);
    }

    public static void route(@NonNull Context context, @NonNull Class<? extends FragmentActivity> clazz, final boolean isFinish) {
        route(context, new RouteProtocol() {

            @NonNull
            @Override
            public Intent getIntent(@NonNull Context context) {
                return new Intent(context, clazz);
            }

            @Override
            public boolean willActivityFinish() {
                return isFinish;
            }
        });
    }

    public static void route(@NonNull Context context, @NonNull RouteProtocol protocol) {
        RouteConfig.getInstance().getEngine().onRoute(context, protocol);
    }

}