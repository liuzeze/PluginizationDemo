package com.lz.hooklib;

import android.content.res.Resources;

/**
 * Created by vimerzhao
 * Date: 2018/9/30
 * Description:
 */
public class HookPluginApp {
    public Resources mResources;
    public ClassLoader mClassLoader;


    public HookPluginApp(Resources mResources) {
        this.mResources = mResources;
    }
}
