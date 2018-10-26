package com.lz.proxylib;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;


public abstract class PluginActivity extends Activity implements Pluginable, Attachable<Activity> {
    public final static String TAG = PluginActivity.class.getSimpleName();
    protected Activity mActivity;
    private Resources mResources;
    ProxyPluginApk mPluginApk;

    public Activity getActivity() {
        if (mActivity != null) {

            return mActivity;
        } else return this;
    }

    @Override
    public void attach(Activity proxy, ProxyPluginApk apk) {
        mActivity = proxy;
        mPluginApk = apk;
        mResources = apk.pluginRes;
    }

    @Override
    public void setContentView(int layoutResID) {
        if (mActivity != null) {
            mActivity.setContentView(layoutResID);
        } else {
            super.setContentView(layoutResID);
        }
    }

    @Override
    public void setContentView(View view) {
        if (mActivity != null)
            mActivity.setContentView(view);
        else
            super.setContentView(view);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        if (mActivity != null)
            mActivity.setContentView(view, params);
        else super.setContentView(view, params);
    }


    @Override
    public View findViewById(int id) {
        if (mActivity != null)
            return mActivity.findViewById(id);
        else return super.findViewById(id);
    }

    @Override
    public Resources getResources() {
        if (mResources != null) {

            return mResources;
        } else {
            return super.getResources();
        }
    }

    @Override
    public WindowManager getWindowManager() {
        if (mActivity != null)
            return mActivity.getWindowManager();
        else return super.getWindowManager();
    }

    @Override
    public ClassLoader getClassLoader() {
        if (mActivity != null)
            return mActivity.getClassLoader();
        else return super.getClassLoader();
    }

    @Override
    public Context getApplicationContext() {
        if (mActivity != null)
            return mActivity.getApplicationContext();
        else return super.getApplicationContext();
    }

    @Override
    public MenuInflater getMenuInflater() {
        if (mActivity != null)
            return mActivity.getMenuInflater();
        else return super.getMenuInflater();
    }


    @Override
    public Window getWindow() {
        if (mActivity != null)
            return mActivity.getWindow();
        else return super.getWindow();
    }

    @Override
    public Intent getIntent() {
        if (mActivity != null)
            return mActivity.getIntent();
        else return super.getIntent();
    }

    @Override
    public LayoutInflater getLayoutInflater() {
        if (mActivity != null)
            return mActivity.getLayoutInflater();
        else return super.getLayoutInflater();
    }

    @Override
    public String getPackageName() {
        if (mActivity != null)
            return mPluginApk.packageInfo.packageName;
        else return super.getPackageName();
    }

    @Override
    public void startActivity(Intent intent) {
        Intent intent1 = new Intent();
        intent1.putExtra(ProxyConstants.PLUGIN_CLASS_NAME, intent.getComponent().getClassName());
        mActivity.startActivity(intent1);
    }

    @Override
    public void onCreate(Bundle bundle) {
        // DO NOT CALL super.onCreate(bundle)
        // following same
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
    }
}
