package com.lz.plugin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lz.proxylib.ProxyActivity;
import com.lz.proxylib.ProxyConstants;
import com.lz.proxylib.ProxyPluginManager;

public class TempActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ProxyPluginManager.init(getApplicationContext());
        ProxyPluginManager mProxyPluginManager = ProxyPluginManager.getInstance();
        mProxyPluginManager.loadApk(getPackageCodePath());
        Intent intent = new Intent(this, ProxyActivity.class);
        intent.putExtra(ProxyConstants.PACKAGE_NAME, getPackageName());
        intent.putExtra(ProxyConstants.PLUGIN_CLASS_NAME, "com.lz.plugin.MainActivity");
        mProxyPluginManager.startActivity(intent);
        finish();
    }
}
