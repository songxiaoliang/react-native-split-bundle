package com.example.songlcy.rnandroid;

import android.annotation.SuppressLint;

import com.facebook.react.ReactActivity;

import javax.annotation.Nullable;

@SuppressLint("Registered")
public class FirstModuleRNActivity extends ReactActivity{

    @Nullable
    @Override
    protected String getMainComponentName() {
        return "FirstModule";
    }

}
