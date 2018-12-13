package com.facebook.react.bridge;

import android.content.Context;

/**
 * JSBundle 加载工具类
 * Create by Songlcy
 */
public class ScriptLoadBridge {

    /**
     * 从Asset目录下加载
     * @param context
     * @param instance
     * @param assetURL
     * @param loadSynchronously
     */
    public static void loadScriptFromAsset(Context context,
                                           CatalystInstance instance,
                                           String assetURL,
                                           boolean loadSynchronously) {
        String bundleSource = assetURL;
        if(!assetURL.contains("assets://")) {
            bundleSource = "assets://" + assetURL;
        }

        ((CatalystInstanceImpl) instance).loadScriptFromAssets(context.getAssets(),bundleSource,loadSynchronously);
    }

    /**
     * 从指定文件目录下加载
     * @param fileName
     * @param instance
     * @param sourceURL
     * @param loadSynchronously
     */
    public static void loadScriptFromFile(String fileName,
                                          CatalystInstance instance,
                                          String sourceURL,
                                          boolean loadSynchronously) {
        ((CatalystInstanceImpl) instance).loadScriptFromFile(fileName, sourceURL,loadSynchronously);
    }
}
