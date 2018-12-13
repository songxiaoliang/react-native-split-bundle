package com.example.songlcy.rnandroid;

import android.app.Application;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Nullable;

public class MainApplication extends Application implements ReactApplication {

    private static final boolean USE_LOCAL_BUNDLE = true; // 标识是否使用本地已打出的 bundle 文件

    private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
            if (USE_LOCAL_BUNDLE) {
                return false;
            }
            return BuildConfig.DEBUG;
        }

        @Override
        protected List<ReactPackage> getPackages() {
            return Arrays.<ReactPackage>asList(
                    new MainReactPackage()
            );
        }

        /**
         * 优先加载 common 模块
         * @return
         */
        @Nullable
        @Override
        protected String getBundleAssetName() {
            return "common.bundle";
        }

        /**
         * debug 环境需指定入口文件名称，例如：index.android
         */
//        @Override
//        protected String getJSMainModuleName() {
//            return "common";
//        }
    };

    @Override
    public ReactNativeHost getReactNativeHost() {
        return mReactNativeHost;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //  延迟去加载C++模块
//        SoLoader.init(this, false);
    }
}
