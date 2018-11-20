package net.crystalapps.mint.router.library.interceptors;

import android.content.Context;
import android.support.annotation.NonNull;

import net.crystalapps.mint.router.library.protocols.RouteProtocol;

/**
 * Created by Syed Owais Ali on 6/28/2018.
 */
public interface RouteRequestInterceptor {
    void onIntercept(@NonNull Context context, @NonNull RouteProtocol protocol);
}
