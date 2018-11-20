package net.crystalapps.mint.router.library.protocols;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Syed Owais Ali on 5/20/2018.
 */

public interface RouteResultProtocol extends RouteProtocol{

    default int getRequestCode() { return 1947; }
    void onResult(int requestCode, int resultCode, @Nullable Intent data, @NonNull Context context);
}
