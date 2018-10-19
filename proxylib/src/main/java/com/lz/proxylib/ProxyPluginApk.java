package com.lz.proxylib;

import android.content.pm.PackageInfo;
import android.content.res.Resources;

import dalvik.system.DexClassLoader;

public class ProxyPluginApk {
    public PackageInfo packageInfo;
    public DexClassLoader classLoader;
    public Resources pluginRes;

    public ProxyPluginApk(Resources pluginRes) {
        this.pluginRes = pluginRes;
    }

}
