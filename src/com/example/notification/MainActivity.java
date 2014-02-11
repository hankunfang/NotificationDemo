package com.example.notification;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.widget.CompoundButton;
import android.widget.TextView;

/**
 * 类MainActivity.java的实现描述：TODO 类实现描述
 * 
 * @author hankunfang 2013年12月8日 下午2:55:36
 */
public class MainActivity extends Activity {

    private TextView            mTextView;
    private ToggleSwitch        mSwitch;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) this.findViewById(R.id.tv_1);
        mSwitch = (ToggleSwitch) this.findViewById(R.id.switch_1);
        
        final SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), 0);
        mSwitch.setChecked(sharedPreferences.getBoolean("set", false));
        
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    sharedPreferences.edit().putBoolean("set", true).commit();
                    Intent serviceIntent = new Intent("com.example.NotificationService");
                    startService(serviceIntent);
                } else {
                    sharedPreferences.edit().putBoolean("set", false).commit();
                    // TODO
                    // 销毁消息获取服务
                    Intent serviceIntent = new Intent("com.example.NotificationService");
                    stopService(serviceIntent);
                }
            }
        });
        
    }


    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
