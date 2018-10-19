package com.lz.pluginizationdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by vimerzhao
 * Date: 2018/9/30
 * Description:
 *
 * 插桩页面  动态替换成需要加载的页面
 */
public class StubActivity extends AppCompatActivity {
    public static final String TAG = "StubActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
