package com.mycompany.geenie;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread thread = new Thread() {
            public void run() {
                try {
                    sleep(5 * 1000);
                    Intent intent = new Intent(getBaseContext(), PhoneVerificationActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
        thread.start();;
    }
}
