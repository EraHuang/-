package com.era.cal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideBottomUIMenu();
        setContentView(R.layout.content_welcome);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        startMainActivity();
    }

    public void startMainActivity(){
        TimerTask delayTask = new TimerTask(){
            @Override
            public void run(){
                Intent mainIntent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(mainIntent);
                WelcomeActivity.this.finish();
            }
        };

        Timer timer = new Timer();
        timer.schedule(delayTask, 2000);

    }

    protected void hideBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
    }




}
