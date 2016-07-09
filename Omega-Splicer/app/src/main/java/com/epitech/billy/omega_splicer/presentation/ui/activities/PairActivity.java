package com.epitech.billy.omega_splicer.presentation.ui.activities;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.epitech.billy.omega_splicer.App;
import com.epitech.billy.omega_splicer.R;
import com.epitech.billy.omega_splicer.bluetooth.BluetoothConnectionManager;
import com.epitech.billy.omega_splicer.domain.executors.impl.ThreadExecutor;
import com.epitech.billy.omega_splicer.presentation.presenters.IPairPresenter;
import com.epitech.billy.omega_splicer.presentation.presenters.impl.PairPresenter;
import com.epitech.billy.omega_splicer.presentation.ui.fragments.PairedDevicesFragment;
import com.epitech.billy.omega_splicer.threading.MainThread;

/**
 * Created by bichon_b on 3/4/16.
 * <p/>
 * This view will show all the available Omega_Splicer planes
 * <p/>
 */
public class PairActivity extends AppCompatActivity implements IPairPresenter.View {

    // Bluetooth
    private final static int REQUEST_ENABLE_BT = 1;
    private final static int REQUEST_ENABLE_POSITION = 2;

    // presenter
    private IPairPresenter mPresenter;

    // Fragments
//    private PairedDevicesFragment mPairedDevicesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation((App.getInstance().getGeneralOrientation().equals(getString(R.string.landscape_orientation_shared_preference))) ? ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE : ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pair);

        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.M)
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_ENABLE_POSITION);

        mPresenter = new PairPresenter(ThreadExecutor.getInstance(), MainThread.getInstance(), this, new BluetoothConnectionManager(this));
        mPresenter.setPairedSubviewPresenter(((PairedDevicesFragment) getSupportFragmentManager().findFragmentById(R.id.paired_devices_fragment)).getPresenter());
    }

    @Override
    protected void onResume() {
        Log.w("Pair activity", "resume again");
        super.onResume();
        mPresenter.resume();
//        mPairedDevicesFragment.startFetching();
    }

    @Override
    protected void onPause() {
        Log.w("Pair activity", "paused");
        mPresenter.pause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Log.w("Pair activity", "destroyed");
        mPresenter.destroy();
        super.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
        Log.w("Pair activity", "configuration changed");
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void displayErrorBluetoothDisabled() {
        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        mPresenter.pause();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.w("Pair activity", "activity resulted");
        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, getString(R.string.bluetooth_mandatory), Toast.LENGTH_SHORT).show();
                this.finish();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_ENABLE_POSITION) {

        }
    }
}
