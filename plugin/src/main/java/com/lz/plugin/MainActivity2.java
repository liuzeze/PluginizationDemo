package com.lz.plugin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView viewById = (TextView) findViewById(R.id.tv_content);
        viewById.setText("hook插件");
        Toast.makeText(this, "hook插件", Toast.LENGTH_SHORT).show();
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, Main3Activity.class);
                MainActivity2.this.startActivity(intent);
            }


        });
    }

    @Override
    public void startActivity(Intent intent) {
        intent.setClassName("com.lz.plugin", intent.getComponent().getClassName());
        super.startActivity(intent);
    }
}
