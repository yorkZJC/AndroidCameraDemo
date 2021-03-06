/*
 * Copyright 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.camera2demo.activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.android.camera2demo.R;
import com.android.camera2demo.fragment.Camera2BasicFragment;
import com.android.camera2demo.fragment.Camera2DoubleTextViewFragment;
import com.android.camera2demo.fragment.Camera2VideoFragment;
import com.android.camera2demo.fragment.Camera2YuvCallbackFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class CameraActivity extends AppCompatActivity {
    private FrameLayout mContainer;
    private Button mBtnRecord;
    private Button mBtnDoublePreview;
    private Button mBtnBase;
    private Button mBtnPreviewYuvCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        initView();

       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(!Settings.canDrawOverlays(getApplicationContext())) {
                //启动Activity让用户授权
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                intent.setData(Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent,100);
            }
        }*/
    }

    private void initView(){
        mContainer = findViewById(R.id.container);
        mBtnRecord = findViewById(R.id.btn_record);
        mBtnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFrameLayout(true);
                switchFragment(Camera2VideoFragment.newInstance());
            }
        });

        mBtnDoublePreview = findViewById(R.id.btn_double_preview);
        mBtnDoublePreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showFrameLayout(true);
               switchFragment(Camera2DoubleTextViewFragment.newInstance());
            }
        });

        mBtnBase = findViewById(R.id.btn_base);
        mBtnBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFrameLayout(true);
                switchFragment(Camera2BasicFragment.newInstance());
            }
        });

        mBtnPreviewYuvCallback = findViewById(R.id.btn_yuv_callback);
        mBtnPreviewYuvCallback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFrameLayout(true);
                switchFragment(Camera2YuvCallbackFragment.newInstance());
            }
        });
    }

    private void showFrameLayout(boolean show){
        if(show){
            mContainer.setVisibility(View.VISIBLE);
        }else{
            mContainer.setVisibility(View.GONE);
        }
    }

    private void switchFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }
}
