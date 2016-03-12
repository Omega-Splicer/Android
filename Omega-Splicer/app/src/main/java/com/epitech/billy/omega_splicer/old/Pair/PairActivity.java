package com.epitech.billy.omega_splicer.old.Pair;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.epitech.billy.omega_splicer.App;
import com.epitech.billy.omega_splicer.R;

import java.util.List;

/**
 * Created by Billy on 01/09/2015.
 *
 * Allow the user to pair his device with an Omega-Splicer plane
 */

// TODO: mettre au propre et gérer la question des versions x < 21 < y
public class PairActivity extends Activity {

    private RecyclerView mRecyclerView;
    private ProgressBar mBluetoothScanning;

    private BluetoothDevicesListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Handler mHandler;
    private BluetoothLeScanner mBluetoothLeScanner;

    private final static int REQUEST_ENABLE_BT = 1;
    private final static long SCAN_PERIOD = 10000;

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

    private ScanCallback mScanCallback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            super.onScanResult(callbackType, result);
            mAdapter.addDevice(result.getDevice());
            mAdapter.notifyDataSetChanged();
        }

        @Override
        public void onScanFailed(int errorCode) {
            super.onScanFailed(errorCode);
        }

        @Override
        public void onBatchScanResults(List<ScanResult> results) {
            super.onBatchScanResults(results);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pair);

        mBluetoothLeScanner = App.BLUETOOTH_ADAPTER.getBluetoothLeScanner();

        mRecyclerView = (RecyclerView) findViewById(R.id.pair_activity_bluetooth_devices_list);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new BluetoothDevicesListAdapter();

        findViewById(R.id.pair_activity_back_cloud).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        mBluetoothScanning = (ProgressBar) findViewById(R.id.pair_activity_blutooth_scanning);

        mHandler = new Handler();
        scanLeDevice(true);
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
    public void onBackPressed() {
        animatedClose();
        super.onBackPressed();
    }

    private void scanLeDevice(final boolean enable) {
        if (enable) {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mBluetoothScanning.setVisibility(View.GONE);
                    mBluetoothLeScanner.stopScan(mScanCallback);
                }
            }, SCAN_PERIOD);

            mBluetoothScanning.setVisibility(View.VISIBLE);
            mBluetoothLeScanner.startScan(mScanCallback);
        }
        else {
            mBluetoothScanning.setVisibility(View.GONE);
            mBluetoothLeScanner.stopScan(mScanCallback);
        }
    }

    private void animatedClose() {
        if  (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ((ImageView) findViewById(R.id.pair_activity_back_cloud)).setImageDrawable(null);

            final TextView textView = (TextView) findViewById(R.id.pair_activity_title);
            textView.setTextColor(Color.BLACK);

            final float startValue = 40;
            final float endValue = 25;

            ValueAnimator valueAnimator = ValueAnimator.ofFloat(startValue, endValue);
            valueAnimator.setDuration(150);

            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, (float) valueAnimator.getAnimatedValue());
                    if ((float) valueAnimator.getAnimatedValue() <= 75) {
                        textView.setAllCaps(false);
                    }
                }
            });

            valueAnimator.start();
        }
    }
}
