package com.epitech.billy.omega_splicer.presentation.ui.activities;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.epitech.billy.omega_splicer.R;
import com.epitech.billy.omega_splicer.bluetooth.GetAllOmegaSplicerPlanes;
import com.epitech.billy.omega_splicer.domain.executors.impl.ThreadExecutor;
import com.epitech.billy.omega_splicer.presentation.models.Plane;
import com.epitech.billy.omega_splicer.presentation.presenters.IPairPresenter;
import com.epitech.billy.omega_splicer.presentation.presenters.impl.PairPresenter;
import com.epitech.billy.omega_splicer.presentation.ui.adapters.PlanesAvailableAdapter;
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

    // UI stuff
    ProgressBar mLoader;
    RecyclerView mPlaneList;

    // adapter
    PlanesAvailableAdapter mAdapter;

    // presenter
    IPairPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pair);
        mPresenter = new PairPresenter(ThreadExecutor.getInstance(), MainThread.getInstance(), this, new GetAllOmegaSplicerPlanes(this));

        mLoader = (ProgressBar) findViewById(R.id.pair_activity_blutooth_scanning);
        mPlaneList = (RecyclerView) findViewById(R.id.pair_activity_bluetooth_devices_list);
        mPlaneList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mPlaneList.setHasFixedSize(true);
        mAdapter = new PlanesAvailableAdapter();
        mPlaneList.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.resume();
    }

    @Override
    public void showLoading() {
        mLoader.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mLoader.setVisibility(View.GONE);
    }

    @Override
    public void addPlaneToList(Plane plane) {
        mAdapter.addPlane(plane);
        mAdapter.notifyItemInserted(mAdapter.getItemCount() - 1);
    }

    @Override
    public void displayErrorBluetooth(String error) {
        Toast.makeText(PairActivity.this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayErrorBluetoothDisabled() {
        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
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
}
