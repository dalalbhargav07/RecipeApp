package com.example.dalalbhargav07.recipeapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by dalalbhargav07 on 04-04-2018.
 */

public class splash extends AppCompatActivity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler() {
            public void postDelayed(Runnable runnable, int splashTimeOut) {
            }

            @Override
            public void publish(LogRecord logRecord) {
                
            }

            @Override
            public void flush() {

            }

            @Override
            public void close() throws SecurityException {

            }
        }.postDelayed(new Runnable() {
 
            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(splash.this, MainActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
