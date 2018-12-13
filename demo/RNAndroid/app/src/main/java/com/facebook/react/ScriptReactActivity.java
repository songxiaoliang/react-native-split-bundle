/**
 * Copyright (c) 2015-present, Facebook, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */

package com.facebook.react;

import javax.annotation.Nullable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import com.example.songlcy.rnandroid.util.ScriptUtil;
import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.modules.core.PermissionListener;

import java.io.File;

/**
 * ReactActivity 扩展，支持自动加载基础包 Bundle
 * Create by Songlcy
 */
public abstract class ScriptReactActivity extends Activity
        implements DefaultHardwareBackBtnHandler, PermissionAwareActivity {

    // BUNDLE 存储位置类型
    protected enum BUNDLE_TYPE{
        ASSET_TYPE,
        FILE_TYPE
    }

    private final ReactActivityDelegate mDelegate;

    protected ScriptReactActivity() {
        mDelegate = createReactActivityDelegate();
    }

    protected @Nullable String getMainComponentName() {
        return null;
    }

    protected ReactActivityDelegate createReactActivityDelegate() {
        return new ReactActivityDelegate(this, getMainComponentName());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialReactContext(savedInstanceState);
    }

    protected abstract String getBundlePath(); // 返回 Bundle 文件路径
    protected abstract BUNDLE_TYPE getBundlePathType();  //  Bundle 文件存储路径类型

    /**
     * 初始化 ReactContext 上下文环境
     */
    private void initialReactContext(final Bundle saveInstanceState) {

        final ReactInstanceManager rim = getReactInstanceManager();
        boolean hasStartedCreatingInitialContext = rim.hasStartedCreatingInitialContext();
        if(!hasStartedCreatingInitialContext) {
            rim.addReactInstanceEventListener(new ReactInstanceManager.ReactInstanceEventListener() {
                @Override
                public void onReactContextInitialized(ReactContext context) {

                    // 加载子模块，并绑定视图
                    loadJSBundle();
                    attachReactRootView(saveInstanceState);
                    rim.removeReactInstanceEventListener(this);
                }
            });
            // 初始化 ReactContext, 加载基础 bundle
            rim.createReactContextInBackground();
        } else {
            loadJSBundle();
            attachReactRootView(saveInstanceState);
        }
    }

    /**
     * 加载 bundle
     */
    private void loadJSBundle() {

        String bundlePath = getBundlePath();
        BUNDLE_TYPE bundleType = getBundlePathType();
        CatalystInstance catalystInstance = ScriptUtil.getCatalystInstance(getReactNativeHost());
        if(bundleType == BUNDLE_TYPE.ASSET_TYPE) {
            // 从 Asset 目录下加载 bundle 文件
            ScriptUtil.loadScriptFromAsset(this,catalystInstance,bundlePath,false);
        } else {
            // 从 File 目录下加载 bundle 文件
            File scriptFile = new File(getApplicationContext().getFilesDir()
                    + File.separator + bundlePath);
            ScriptUtil.loadScriptFromFile(scriptFile.getAbsolutePath(), catalystInstance, bundlePath,false);
        }
    }

    private void attachReactRootView(Bundle savedInstanceState) {
        mDelegate.onCreate(savedInstanceState);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mDelegate.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDelegate.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDelegate.onDestroy();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mDelegate.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return mDelegate.onKeyUp(keyCode, event) || super.onKeyUp(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        if (!mDelegate.onBackPressed()) {
            super.onBackPressed();
        }
    }

    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onNewIntent(Intent intent) {
        if (!mDelegate.onNewIntent(intent)) {
            super.onNewIntent(intent);
        }
    }

    @Override
    public void requestPermissions(
            String[] permissions,
            int requestCode,
            PermissionListener listener) {
        mDelegate.requestPermissions(permissions, requestCode, listener);
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            String[] permissions,
            int[] grantResults) {
        mDelegate.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    protected final ReactNativeHost getReactNativeHost() {
        return mDelegate.getReactNativeHost();
    }

    protected final ReactInstanceManager getReactInstanceManager() {
        return mDelegate.getReactInstanceManager();
    }

    protected final void loadApp(String appKey) {
        mDelegate.loadApp(appKey);
    }
}
