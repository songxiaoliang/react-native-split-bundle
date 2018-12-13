package com.example.songlcy.rnandroid.util;

import android.content.Context;
import android.util.Log;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.ScriptLoadBridge;
import com.facebook.react.bridge.ReactContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;

/**
 * RN context 工具类
 * Create by Songlcy
 */
public class ScriptUtil {

    private static HashSet<String> jsBundleContainer = new HashSet<>(10);

    /**
     * 是否已调用 createReactContextInBackground 初始化RN 上下文环境
     * 在 onDestroy 之后将返回false，直到创建了新的初始上下文。
     * @param reactNativeHost
     * @return
     */
    public static boolean hasStartedCreatingInitialContext(ReactNativeHost reactNativeHost) {

        ReactInstanceManager rim = reactNativeHost.getReactInstanceManager();

        if (rim == null) {
            Log.e("ContextCreatingInitial:", "ReactInstanceManager未创建");
            return false;
        }
        return rim.hasStartedCreatingInitialContext();
    }

    /**
     * 获取 CatalystInstance 实例
     * @param reactNativeHost
     * @return
     */
    public static CatalystInstance getCatalystInstance(ReactNativeHost reactNativeHost) {

        ReactInstanceManager rim = reactNativeHost.getReactInstanceManager();
        if(rim == null) {
            Log.e("getCatalystInstance:", "ReactInstanceManager未创建");
            return null;
        }

        ReactContext reactContext = rim.getCurrentReactContext();
        if(reactContext == null) {
            Log.e("getCatalystInstance:", "ReactContext未创建");
            return null;
        }

        return reactContext.getCatalystInstance();
    }


    /**
     * 从Asset目录下加载 Bundle 模块
     * @param context
     * @param instance
     * @param assetURL
     * @param loadSynchronously
     */
    public static void loadScriptFromAsset(Context context,
                                          CatalystInstance instance,
                                          String assetURL,
                                          boolean loadSynchronously) {

        if(jsBundleContainer.contains(assetURL)) {
            return;
        }

        ScriptLoadBridge.loadScriptFromAsset(context, instance, assetURL, loadSynchronously);
        jsBundleContainer.add(assetURL);
    }

    /**
     * 从指定文件目录下加载 Bundle 模块
     * @param fileName
     * @param instance
     * @param sourceURL
     * @param loadSynchronously
     */
    public static void loadScriptFromFile(String fileName,
                                          CatalystInstance instance,
                                          String sourceURL,
                                          boolean loadSynchronously) {

        if(jsBundleContainer.contains(sourceURL)) {
            return;
        }

        ScriptLoadBridge.loadScriptFromFile(fileName, instance, sourceURL, loadSynchronously);
        jsBundleContainer.add(sourceURL);
    }

    /**
     * 清空缓存
     */
    public static void clearJsBundleContainer() {
        jsBundleContainer.clear();
    }

    /**
     * 直接执行 ReactContext 初始化，Bundle加载
     * 不会绑定视图（attachToReactInstanceManager 未执行）
     * @param reactNativeHost
     */
    public static void recreateReactContextInBackgroundInner(ReactNativeHost reactNativeHost) {

        ReactInstanceManager rim = reactNativeHost.getReactInstanceManager();
        if (rim == null) {
            Log.e("recreateReactContext:", "ReactInstanceManager未创建");
        }

        try {
            Method method = rim.getClass().getDeclaredMethod("recreateReactContextInBackgroundInner");
            method.setAccessible(true);
            method.invoke(rim);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将 ReactContext 当前状态调整为 resume
     * 系统会遍历 mLifecycleEventListeners 调用注册了 LifecycleEventListener 事件的 onHostResume方法
     * 例如：在Module中实现 LifecycleEventListener 接口
     * @param reactNativeHost
     * @param state
     */
    public static void moveToResumedLifecycleState(ReactNativeHost reactNativeHost, boolean state) {

        ReactInstanceManager rim = reactNativeHost.getReactInstanceManager();
        if (rim == null) {
            Log.e("moveToResumedLifecycle:", "ReactInstanceManager未创建");
            return;
        }

        try {
            Method method = rim.getClass().getDeclaredMethod("getDeclaredMethod", boolean.class);
            method.setAccessible(true);

            method.invoke(rim, state);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回已运行的JS Bundle的源URL，如果尚未运行JS bundle，则返回 null
     * @param reactNativeHost
     * @return
     */
    public static String getCurrentSourceURL(ReactNativeHost reactNativeHost) {
       return getCatalystInstance(reactNativeHost).getSourceURL();
    }

}
