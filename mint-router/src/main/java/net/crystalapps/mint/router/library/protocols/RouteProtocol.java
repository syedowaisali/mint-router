package net.crystalapps.mint.router.library.protocols;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

/**
 * Created by Syed Owais Ali on 5/20/2018.
 */

public interface RouteProtocol {

    default void onBeforeLaunch(@NonNull Context context) {}
    default int getLaunchDelay() { return 0; }
    default boolean willActivityFinish() { return false; }
    @NonNull
    Intent getIntent(@NonNull Context context);
}