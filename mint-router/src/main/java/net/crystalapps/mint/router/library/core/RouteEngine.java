package net.crystalapps.mint.router.library.core;

import android.content.Context;
import android.support.annotation.NonNull;

import net.crystalapps.mint.router.library.protocols.RouteProtocol;

/**
 * Created by Syed Owais Ali on 7/6/2018.
 */
public interface RouteEngine {

    void onRoute(@NonNull Context context, @NonNull RouteProtocol protocol);
}
