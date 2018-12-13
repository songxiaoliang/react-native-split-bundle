package com.example.songlcy.rnandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.songlcy.rnandroid.util.ScriptUtil;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.bridge.ReactContext;
import com.facebook.soloader.SoLoader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnLoad;
    private Button btnUnLoad;
    private Button btnLoadA;
    private Button btnLoadB;
    private ReactInstanceManager rim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getReactInstanceManager();
        initView();
        initListener();

    }

    private void getReactInstanceManager() {
        rim = ((MainApplication)getApplication()).getReactNativeHost().getReactInstanceManager();
    }

    private void initView() {
        btnLoad = findViewById(R.id.btn_load);
        btnUnLoad = findViewById(R.id.btn_unload);
        btnLoadA = findViewById(R.id.btn_loadA);
        btnLoadB = findViewById(R.id.btn_loadB);
    }

    private void initListener() {
        btnLoad.setOnClickListener(this);
        btnUnLoad.setOnClickListener(this);
        btnLoadA.setOnClickListener(this);
        btnLoadB.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_load:
                loadReactContext();
                break;
            case R.id.btn_unload:
                unLoadReactContext();
                break;
            case R.id.btn_loadA:
                ReactNativeHost reactNativeHost = ((MainApplication)getApplication()).getReactNativeHost();
                if(ScriptUtil.hasStartedCreatingInitialContext(reactNativeHost)) {
                    startRNActivity("A");
                } else {
                    Toast.makeText(this, "在加载RN模块前，需要先初始化ReactContext（RN上下文）", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_loadB:
                startRNActivity("B");
                break;
            default:
                break;
        }
    }

    /**
     * 创建 ReactContext
     */
    private void loadReactContext() {
        Log.e("loadReactContext:", "RN C++ 层开始加载");
        SoLoader.init(getApplication(),false);
        Log.e("loadReactContext:", "RN C++ 层加载完成");
        Log.e("loadReactContext:", "基础 bundle 开始加载");
        if(!rim.hasStartedCreatingInitialContext()) {
            rim.addReactInstanceEventListener(new ReactInstanceManager.ReactInstanceEventListener() {

                // 初始化react上下文时调用（所有模块都已注册）。 总是调用UI线程。
                @Override
                public void onReactContextInitialized(ReactContext context) {
                    Log.e("onReactContextInit:", "基础 bundle 加载完成");
                    Toast.makeText(MainActivity.this, "基础 bundle 加载完成", Toast.LENGTH_SHORT).show();
                    Log.e(this.getClass().getName(), "子模块 bundle 开始加载");
                    loadSubModule();
                    Log.e("onReactContextInit:", "子模块 bundle 加载完成");
                    Toast.makeText(MainActivity.this, "子模块 bundle 加载完成", Toast.LENGTH_SHORT).show();
                    rim.removeReactInstanceEventListener(this);
                }
            });
        }

        rim.createReactContextInBackground();
    }

    /**
     * 卸载 ReactContext
     */
    private void unLoadReactContext() {
        rim.destroy();
    }

    /**
     * 预加载子模块 bundle
     */
    private void loadSubModule() {
        ScriptUtil.loadScriptFromAsset(this,rim.getCurrentReactContext().getCatalystInstance(),"first_diff.bundle", false);
//        ScriptUtil.loadScriptFromAsset(this,rim.getCurrentReactContext().getCatalystInstance(),"second_diff.bundle", false);
    }

    /**
     * 跳转到指定 RN 模块
     * @param module
     */
    private void startRNActivity(String module) {
        Intent intent = new Intent();
        String className = module == "A" ? FirstModuleRNActivity.class.getName() : SecondModuleRNActivity.class.getName();
        intent.setClassName(this, className);
        startActivity(intent);
    }

}
