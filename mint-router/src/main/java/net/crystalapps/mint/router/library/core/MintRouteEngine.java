package net.crystalapps.mint.router.library.core;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import net.crystalapps.mint.router.library.config.RouteConfig;
import net.crystalapps.mint.router.library.protocols.RouteProtocol;
import net.crystalapps.mint.router.library.protocols.RouteResultProtocol;

/**
 * Created by Syed Owais Ali on 7/6/2018.
 */
public class MintRouteEngine implements RouteEngine {

    private final Handler handler;
    private RouteConfig routeConfig;

    public MintRouteEngine() {
        handler = new Handler();
    }

    @Override
    public void onRoute(@NonNull Context context, @NonNull RouteProtocol protocol) {

        if (routeConfig == null) routeConfig = RouteConfig.getInstance();

        if (protocol.getLaunchDelay() > 0) {
            handler.postDelayed(() -> launch(context, protocol), protocol.getLaunchDelay());
        }
        else {
            launch(context, protocol);
        }
    }

    private void launch(@NonNull Context context, @NonNull RouteProtocol protocol) {

        protocol.onBeforeLaunch(context);

        if (routeConfig.getRequestInterceptor() != null) {
            routeConfig.getRequestInterceptor().onIntercept(context, protocol);
        }

        if (protocol instanceof RouteResultProtocol) {

            final RouteResultProtocol resultProtocol = (RouteResultProtocol) protocol;

            FragmentManager fragmentManager = getActivity(context).getSupportFragmentManager();
            ActivityResultFragment fragment = ActivityResultFragment.getInstance(fragmentManager, resultProtocol, routeConfig, context);
            fragmentManager.beginTransaction().add(fragment, "ACTIVITY_RESULT_FRAGMENT").commit();
            fragmentManager.executePendingTransactions();

            fragment.startActivityForResult(resultProtocol.getIntent(context), resultProtocol.getRequestCode());
        }
        else {
            context.startActivity(protocol.getIntent(context));
        }

        if (protocol.willActivityFinish()) {
            getActivity(context).finish();
        }
    }

    private FragmentActivity getActivity(@NonNull Context context) {
        return (FragmentActivity) context;
    }

    @SuppressLint("ValidFragment")
    public static class ActivityResultFragment extends Fragment {

        private final FragmentManager fragmentManager;
        private final RouteResultProtocol resultProtocol;
        private final RouteConfig routeConfig;
        private final Context context;

        ActivityResultFragment(FragmentManager fragmentManager, RouteResultProtocol resultProtocol, RouteConfig routeConfig, Context context) {
            this.fragmentManager = fragmentManager;
            this.resultProtocol = resultProtocol;
            this.routeConfig = routeConfig;
            this.context = context;
        }

        @Override
        public void onSaveInstanceState(@NonNull Bundle outState) {}

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if (routeConfig.getResultInterceptor() != null) {
                routeConfig.getResultInterceptor().onIntercept(requestCode, resultCode, data, context, resultProtocol);
                resultProtocol.onResult(requestCode, resultCode, data, context);
            }
            else {
                resultProtocol.onResult(requestCode, resultCode, data, context);
            }

            fragmentManager.beginTransaction().remove(this).commitAllowingStateLoss();
        }

        static ActivityResultFragment getInstance(FragmentManager fragmentManager, RouteResultProtocol resultProtocol, RouteConfig routeConfig, Context context) {
            return new ActivityResultFragment(fragmentManager, resultProtocol, routeConfig, context);
        }
    }
}
