package com.epitech.billy.omega_splicer.bluetooth;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;

import com.epitech.billy.omega_splicer.App;
import com.epitech.billy.omega_splicer.bluetooth.models.BluetoothPlane;
import com.epitech.billy.omega_splicer.bluetooth.mapper.BluetoothPlaneDataMapper;
import com.epitech.billy.omega_splicer.domain.data.IScanOmegaSplicerPlanes;
import com.epitech.billy.omega_splicer.domain.models.Plane;

import java.lang.annotation.Target;
import java.util.List;

/**
 * Created by bichon_b on 3/19/16.
 */
public class ScanOmegaSplicerPlanes implements IScanOmegaSplicerPlanes {

    public static final int UNKNOWN_ERROR = 0;

    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothLeScanner mBluetoothLeScanner;

    enum Error implements IScanOmegaSplicerPlanes.Error {
//        BLUETOOTH_DISABLED(ScanOmegaSplicerPlanes.BLUETOOTH_DISABLED, "The bluetooth is not enabled on your device"),
        UNKNOWN_ERROR(ScanOmegaSplicerPlanes.UNKNOWN_ERROR, "An unknown error occur");

        private final int mCode;
        private final String mDescription;

        Error(int code, String description) {
            mCode = code;
            mDescription = description;
        }

        public String getDescription() {
            return mDescription;
        }

        public int getCode() {
            return mCode;
        }
    }

    private Context mContext;
    private Handler mHandler;

    private static final long SCAN_PERIOD = 20000;

    public ScanOmegaSplicerPlanes(Context context) {
        mContext = context;
        mHandler = new Handler();
    }

    @Override
    public void startScan(final Callback callback) {
        // This is is for SDK version 21 and greater
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mBluetoothLeScanner = App.BLUETOOTH_ADAPTER.getBluetoothLeScanner();

            final ScanCallback scanCallback = new ScanCallback() {
                @Override
                public void onScanResult(int callbackType, ScanResult result) {
                    super.onScanResult(callbackType, result);
                    // TODO filter only the planes
                    BluetoothPlane bluetoothPlane = new BluetoothPlane(result.getDevice());
                    bluetoothPlane.setSignalStrength(result.getRssi());
                    callback.onNewPlaneRetrieved(BluetoothPlaneDataMapper.transform(bluetoothPlane));
                }

                @Override
                public void onBatchScanResults(List<ScanResult> results) {
                    super.onBatchScanResults(results);
                    System.out.println("-----> BATCH");
                    System.out.println(results);
                }

                @Override
                public void onScanFailed(int errorCode) {
                    super.onScanFailed(errorCode);
                    System.out.println("-----> ERROR : " + errorCode);
                    callback.onError(Error.UNKNOWN_ERROR);
                }
            };

            // TODO a enlever
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (App.BLUETOOTH_ADAPTER != null && App.BLUETOOTH_ADAPTER.isEnabled()) {
                        Plane plane = new Plane("nothing found");
                        plane.setSignalStrength(10);
                        plane.setMacAddress("0");
                        callback.onNewPlaneRetrieved(plane);
                        mBluetoothLeScanner.stopScan(scanCallback);
                    }
                    callback.onFinish();
                }
            }, SCAN_PERIOD);
            mBluetoothLeScanner.startScan(scanCallback);
        }
        // This code is for SDK version 18 to 21
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            mBluetoothAdapter = App.BLUETOOTH_ADAPTER;

            final BluetoothAdapter.LeScanCallback leScanCallback = new BluetoothAdapter.LeScanCallback() {
                @Override
                public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
                    // TODO filter only the planes
                    BluetoothPlane bluetoothPlane = new BluetoothPlane(device);
                    bluetoothPlane.setSignalStrength(rssi);
                    callback.onNewPlaneRetrieved(BluetoothPlaneDataMapper.transform(bluetoothPlane));
                }
            };

            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (App.BLUETOOTH_ADAPTER != null && App.BLUETOOTH_ADAPTER.isEnabled()) {
                        mBluetoothAdapter.stopLeScan(leScanCallback);
                    }
                    callback.onFinish();
                }
            }, SCAN_PERIOD);

            mBluetoothAdapter.startLeScan(leScanCallback);
        }
    }
//
//    @TargetApi(18)
//    private void startScanVersion18(final Callback callback) {
//
//    }
//
//    @TargetApi(21)
//    private void startScanVersion21(final Callback callback) {
//
//    }
//
//    @TargetApi(23)
//    private void startScanVersion23(final Callback callback) {
//
//    }
}