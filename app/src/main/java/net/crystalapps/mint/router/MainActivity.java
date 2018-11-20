package net.crystalapps.mint.router;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import net.crystalapps.mint.router.library.core.Router;
import net.crystalapps.mint.router.library.protocols.RouteResultProtocol;

public class MainActivity extends AppCompatActivity {

    private ImageView photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        photo = findViewById(R.id.photo);
        findViewById(R.id.btnStartActivity).setOnClickListener(this::startActivity);
        findViewById(R.id.btnCaptureImage).setOnClickListener(this::captureImage);
        checkPermission();
    }

    private void checkPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1947);
        }
    }

    private void startActivity(View v) {
        Router.route(this, SecondActivity.class);
    }

    private void captureImage(View v) {
        Router.route(this, new RouteResultProtocol() {
            @Override
            public void onResult(int requestCode, int resultCode, @Nullable Intent data, @NonNull Context context) {
                photo.setImageBitmap((Bitmap) data.getExtras().get("data"));
            }

            @NonNull
            @Override
            public Intent getIntent(@NonNull Context context) {
                return new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            }
        });
    }
}