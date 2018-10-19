package com.lz.proxylib;

public interface Attachable<T> {
    void attach(T proxy, ProxyPluginApk apk);
}

