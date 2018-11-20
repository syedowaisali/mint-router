package net.crystalapps.mint.router.library.interceptors;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import net.crystalapps.mint.router.library.protocols.RouteResultProtocol;

/**
 * Created by Syed Owais Ali on 7/6/2018.
 */
public interface RouteResultInterceptor {
    void onIntercept(int requestCode, int resultCode, @Nullable Intent data, @NonNull Context context, @NonNull RouteResultProtocol protocol);
}
