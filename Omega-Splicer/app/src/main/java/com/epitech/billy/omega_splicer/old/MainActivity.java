package com.epitech.billy.omega_splicer.old;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.epitech.billy.omega_splicer.App;
import com.epitech.billy.omega_splicer.R;
import com.epitech.billy.omega_splicer.old.Fly.FlyActivity;
import com.epitech.billy.omega_splicer.old.Pair.PairActivity;
import com.epitech.billy.omega_splicer.old.Setting.SettingActivity;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends Activity {

    private final static int REQUEST_ENABLE_BT = 1;

    private final BroadcastReceiver mBluetoothReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();

            if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
                final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);

                switch (state) {
                    case BluetoothAdapter.STATE_OFF:
                        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                        break;
                    default:
                        break;
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);

        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(this, getString(R.string.bluetooth_not_enable), Toast.LENGTH_SHORT).show();
            finish();
        }

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, getString(R.string.bluetooth_mandatory), Toast.LENGTH_SHORT).show();
                this.finish();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (App.BLUETOOTH_ADAPTER == null || !App.BLUETOOTH_ADAPTER.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

        IntentFilter filter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(mBluetoothReceiver, filter);
    }

    @Override
    protected void onStop() {
        unregisterReceiver(mBluetoothReceiver);

        super.onStop();
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