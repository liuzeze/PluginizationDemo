package com.lz.plugin;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.lz.proxylib.PluginActivity;

public class MainActivity extends PluginActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((TextView) findViewById(R.id.tv_content)).setText("proxy插件");

    }
}
