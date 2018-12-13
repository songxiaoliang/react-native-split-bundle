package com.example.songlcy.rnandroid;

import android.annotation.SuppressLint;

import com.facebook.react.ReactActivity;
import com.facebook.react.ScriptReactActivity;

import javax.annotation.Nullable;

@SuppressLint("Registered")
public class SecondModuleRNActivity extends ScriptReactActivity {

    @Nullable
    @Override
    protected String getMainComponentName() {
        return "SecondModule";
    }

    @Override
    protected String getBundlePath() {
        return "second_diff.bundle";
    }

    @Override
    protected ScriptReactActivity.BUNDLE_TYPE getBundlePathType() {
        return BUNDLE_TYPE.ASSET_TYPE;
    }

}
