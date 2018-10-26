package com.lz.hooklib;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.ContextThemeWrapper;

import java.lang.reflect.Method;

/**
 * Created by vimerzhao
 * Date: 2018/9/30
 * Description:
 */
public class HookedInstrumentation extends Instrumentation implements Handler.Callback {
    public static final String TAG = "HookedInstrumentation";
    protected Instrumentation mBase;
    private HookPluginManager mPluginManager;

    public HookedInstrumentation(Instrumentation base, HookPluginManager pluginManager) {
        mBase = base;
        mPluginManager = pluginManager;
    }

    /**
     * 覆盖掉原始Instrumentation类的对应方法,用于插件内部跳转Activity时适配
     *
     * @Override
     */
    public ActivityResult execStartActivity(
            Context who, IBinder contextThread, IBinder token, Activity target,
            Intent intent, int requestCode, Bundle options) {

        if (HookConstants.DEBUG) Log.e(TAG, "execStartActivity");
        mPluginManager.hookToStubActivity(intent);

        try {
            Method execStartActivity = Instrumentation.class.getDeclaredMethod(
                    "execStartActivity", Context.class, IBinder.class, IBinder.class,
                    Activity.class, Intent.class, int.class, Bundle.class);
            execStartActivity.setAccessible(true);
            return (ActivityResult) execStartActivity.invoke(mBase, who,
                    contextThread, token, target, intent, requestCode, options);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("do not support!!!" + e.getLocalizedMessage());
        }
    }

    @Override
    public Activity newActivity(ClassLoader cl, String className, Intent intent) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        if (HookConstants.DEBUG) Log.e(TAG, "newActivity");

        if (mPluginManager.hookToPluginActivity(intent)) {
            String targetClassName = intent.getComponent().getClassName();
            HookPluginApp pluginApp = mPluginManager.getLoadedPluginApk();
            Activity activity = mBase.newActivity(pluginApp.mClassLoader, targetClassName, intent);
            activity.setIntent(intent);
            ReflectUtil.setField(ContextThemeWrapper.class, activity, HookConstants.FIELD_RESOURCES, pluginApp.mResources);
            return activity;
        }

        if (HookConstants.DEBUG) Log.e(TAG, "super.newActivity(...)");
        return super.newActivity(cl, className, intent);
    }

    @Override
    public boolean handleMessage(Message message) {
        if (HookConstants.DEBUG) Log.e(TAG, "handleMessage");
        return false;
    }


    @Override
    public void callActivityOnCreate(Activity activity, Bundle icicle) {
        if (HookConstants.DEBUG) Log.e(TAG, "callActivityOnCreate");
        super.callActivityOnCreate(activity, icicle);
    }
}
