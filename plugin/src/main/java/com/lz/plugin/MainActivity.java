package com.lz.plugin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lz.proxylib.PluginActivity;

public class MainActivity extends PluginActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.tv_content);
        textView.setText("proxy插件");
        Toast.makeText(getActivity(), "proxy插件", Toast.LENGTH_SHORT).show();

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Main4Activity.class));

            }
        });


    }
}
