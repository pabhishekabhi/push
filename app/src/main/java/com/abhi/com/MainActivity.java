package com.abhi.com;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.abhi.com.Permissions.RuntimePermissionHandler;
import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity {


    // Permission Branch
    private final int REQ_CODE_CAMERA_PERMISSION = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Token123", "Refreshed token: " + FirebaseInstanceId.getInstance().getToken());
        RuntimePermissionHandler.requestPermission(REQ_CODE_CAMERA_PERMISSION, this, mPermissionListener, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        RuntimePermissionHandler.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    private RuntimePermissionHandler.PermissionListener mPermissionListener = new RuntimePermissionHandler.PermissionListener() {
        @Override
        public void onRationale(final @NonNull RuntimePermissionHandler.PermissionRequest permissionRequest, final Activity target, final int requestCode, @NonNull final String[] permissions) {
            switch (requestCode) {
                case REQ_CODE_CAMERA_PERMISSION:
                    permissionRequest.proceed(target, requestCode, permissions);
                    break;

            };
        }

        @Override
        public void onAllowed(int i, @NonNull String[] strings) {
            Toast.makeText(MainActivity.this, "Allowed", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onDenied(@NonNull RuntimePermissionHandler.PermissionRequest permissionRequest, Activity activity, int i, @NonNull String[] strings, @NonNull int[] ints, RuntimePermissionHandler.DENIED_REASON denied_reason) {
            Toast.makeText(MainActivity.this, "Denied", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onNeverAsk(int i, @NonNull String[] strings, @NonNull int[] ints) {
            Toast.makeText(MainActivity.this, "Never Ask Me", Toast.LENGTH_SHORT).show();

        }
    };

}