package com.lz.pluginizationdemo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.lz.hooklib.HookPluginManager;
import com.lz.hooklib.ReflectUtil;
import com.lz.proxylib.ProxyConstants;
import com.lz.proxylib.ProxyPluginManager;

public class MainActivity extends AppCompatActivity {

    private Button mBtHook;
    private Button mBtProxy;
    //读写权限
    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    //请求状态码
    private static int REQUEST_PERMISSION_CODE = 1;


    private String mPluginPackageName = "com.lz.plugin";
    private String mPluginClassName2 = "com.lz.plugin.MainActivity2";
    private String mPluginClassName = "com.lz.plugin.MainActivity";


    private HookPluginManager mHookPluginManager;
    private ProxyPluginManager mProxyPluginManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtHook = (Button) findViewById(R.id.bt_hook);
        mBtProxy = (Button) findViewById(R.id.bt_proxy);

        //权限验证
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_PERMISSION_CODE);
            }
        }
        initHookManager();
        initProxyManager();

        initListener();
    }

    private void initListener() {
        mBtHook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mHookPluginManager.loadPlugin(LogicParam.PLUGIN_PATH)) {
                    Intent intent = new Intent();
                    intent.setClassName(mPluginPackageName, mPluginClassName2);
                    startActivity(intent);
                }
            }
        });
        mBtProxy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(ProxyConstants.PACKAGE_NAME, mPluginPackageName);
                intent.putExtra(ProxyConstants.PLUGIN_CLASS_NAME, mPluginClassName);
                mProxyPluginManager.startActivity(intent);
            }
        });
    }


    private void initProxyManager() {
        ProxyPluginManager.init(getApplicationContext());
        mProxyPluginManager = ProxyPluginManager.getInstance();
        mProxyPluginManager.loadApk(LogicParam.PLUGIN_PATH);
    }

    private void initHookManager() {

        //初始化反射相关的instrumentation
        ReflectUtil.init();

        //初始化管理类传入上下文
        mHookPluginManager = HookPluginManager.getInstance(getApplicationContext());
        //Hook 掉系统的钩子
        mHookPluginManager.hookInstrumentation();

        //hook掉当前acttivity的钩子
        mHookPluginManager.hookCurrentActivityInstrumentation(this);
    }
}
