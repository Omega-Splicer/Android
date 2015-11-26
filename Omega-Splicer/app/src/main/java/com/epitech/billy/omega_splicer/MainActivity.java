package com.epitech.billy.omega_splicer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.Button;

import com.epitech.billy.omega_splicer.Fly.FlyActivity;
import com.epitech.billy.omega_splicer.Pair.PairActivity;
import com.epitech.billy.omega_splicer.Setting.SettingActivity;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button flyButton = (Button) findViewById(R.id.main_activity_fly_button);
        if (flyButton != null) {
            setupFlyButton(this, flyButton);
        }

        Button pairButton = (Button) findViewById(R.id.main_activity_pair_button);
        if (pairButton != null) {
            setupPairButton(this, pairButton);
        }

        Button settingButton = (Button) findViewById(R.id.main_activity_setting_button);
        if (settingButton != null) {
            setupSettingLayout(this, settingButton);
        }
    }

    private void setupFlyButton(final Activity activity, final View flyButton) {
        flyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity, FlyActivity.class));
            }
        });
    }

    private void setupPairButton(final Activity activity, final View pairButton) {
        pairButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity, PairActivity.class));
            }
        });
    }

    private void setupSettingLayout(final Activity activity, final View settingButton) {
        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity, SettingActivity.class));
            }
        });
    }
}