package com.epitech.billy.omega_splicer.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.os.Build;
import android.os.Handler;

import com.epitech.billy.omega_splicer.App;
import com.epitech.billy.omega_splicer.domain.data.IGetAllOmegaSplicerPlanes;
import com.epitech.billy.omega_splicer.domain.models.Plane;

import java.util.List;

/**
 * Created by bichon_b on 3/19/16.
 */
public class GetAllOmegaSplicerPlanes implements IGetAllOmegaSplicerPlanes {

    public static final int BLUETOOTH_DISABLED = 0;
    public static final int UNKNOWN_ERROR = 1;

    enum Error implements IGetAllOmegaSplicerPlanes.Error{
        BLUETOOTH_DISABLED(GetAllOmegaSplicerPlanes.BLUETOOTH_DISABLED, "The bluetooth is not enabled on your device"),
        UNKNOWN_ERROR(GetAllOmegaSplicerPlanes.UNKNOWN_ERROR, "An unknown error occur");

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

    public GetAllOmegaSplicerPlanes(Context context) {
        mContext = context;
        mHandler = new Handler();
    }

    @Override
    public void getAllPlanes(final Callback callback) {
        if (App.BLUETOOTH_ADAPTER == null || !App.BLUETOOTH_ADAPTER.isEnabled()) {
            callback.onError(Error.BLUETOOTH_DISABLED);
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            final BluetoothLeScanner bluetoothLeScanner = App.BLUETOOTH_ADAPTER.getBluetoothLeScanner();

            final ScanCallback scanCallback = new ScanCallback() {
                @Override
                public void onScanResult(int callbackType, ScanResult result) {
                    super.onScanResult(callbackType, result);
                    Plane plane = new Plane(result.getDevice().getName());
                    plane.setSignalStrength(result.getRssi());
                    System.out.println("##### FOUND SOMETHING:");
                    System.out.println(result);
                    callback.onNewPlaneRetrieved(plane);
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

            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Plane plane = new Plane("nothing found");
                    plane.setSignalStrength(10);
                    callback.onNewPlaneRetrieved(plane);
                    bluetoothLeScanner.stopScan(scanCallback);
                    callback.onFinish();
                }
            }, SCAN_PERIOD);
            bluetoothLeScanner.startScan(scanCallback);
        }
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            final BluetoothAdapter bluetoothAdapter = App.BLUETOOTH_ADAPTER;

            final BluetoothAdapter.LeScanCallback leScanCallback = new BluetoothAdapter.LeScanCallback() {
                @Override
                public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
                    Plane plane = new Plane(device.getName());
                    plane.setSignalStrength(rssi);
                    callback.onNewPlaneRetrieved(plane);
                }
            };

            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    bluetoothAdapter.stopLeScan(leScanCallback);
                    callback.onFinish();
                }
            }, SCAN_PERIOD);

            bluetoothAdapter.startLeScan(leScanCallback);
        }
    }
}