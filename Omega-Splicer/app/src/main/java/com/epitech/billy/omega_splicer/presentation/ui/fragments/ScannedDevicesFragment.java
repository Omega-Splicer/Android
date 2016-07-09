package com.epitech.billy.omega_splicer.presentation.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.epitech.billy.omega_splicer.R;
import com.epitech.billy.omega_splicer.bluetooth.ScanOmegaSplicerPlanes;
import com.epitech.billy.omega_splicer.domain.executors.impl.ThreadExecutor;
import com.epitech.billy.omega_splicer.presentation.models.PlaneModel;
import com.epitech.billy.omega_splicer.presentation.presenters.IScannedDevicesPresenter;
import com.epitech.billy.omega_splicer.presentation.presenters.impl.ScannedDevicesPresenter;
import com.epitech.billy.omega_splicer.presentation.ui.adapters.PlanesListAdapter;
import com.epitech.billy.omega_splicer.presentation.ui.managers.ControlScrollingLinearLayoutManager;
import com.epitech.billy.omega_splicer.threading.MainThread;

import java.util.List;

/**
 * Created by Billy on 07/04/2016.
 */
public class ScannedDevicesFragment extends Fragment implements IScannedDevicesPresenter.View {

    // UI stuff
    private ProgressBar mLoader;
    private RecyclerView mPlanesRecyclerView;
    private ImageButton mRefresh;

    // adapter
    private PlanesListAdapter mAdapter;

    // presenter
    private IScannedDevicesPresenter mPresenter;

    // context
    private Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.scanned_devices_view, container, false);

        mLoader = (ProgressBar) rootView.findViewById(R.id.scanned_devices_fragment_loader);
        mPlanesRecyclerView = (RecyclerView) rootView.findViewById(R.id.scanned_devices_fragment_devices_list);
        mRefresh = (ImageButton) rootView.findViewById(R.id.scanned_devices_fragment_refresh);
        initRefresh();

        mAdapter = new PlanesListAdapter(this.getContext());
        mPlanesRecyclerView.setAdapter(mAdapter);
        mPlanesRecyclerView.setHasFixedSize(false);

        mAdapter.addPlane(new PlaneModel("Test Plane"));
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = view.getContext();
        mPresenter = new ScannedDevicesPresenter(ThreadExecutor.getInstance(), MainThread.getInstance(), this, new ScanOmegaSplicerPlanes(mContext));
        ControlScrollingLinearLayoutManager controlScrollingLinearLayoutManager = new ControlScrollingLinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        controlScrollingLinearLayoutManager.setVerticalScrollEnable(false);
        mPlanesRecyclerView.setLayoutManager(controlScrollingLinearLayoutManager);
    }

    private void initRefresh() {
        if (mRefresh != null) {
            mRefresh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mPresenter.scan();
                }
            });
        }
    }

    public void startScanning() {
        mPresenter.scan();
    }

    @Override
    public void addPlaneToList(PlaneModel plane) {
        mAdapter.addPlane(plane);
    }

    @Override
    public void addPlanesToList(List<PlaneModel> planes) {
        mAdapter.addPlanes(planes);
    }

    @Override
    public void displayErrorBluetooth(String error) {
        Toast.makeText(mContext, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        mRefresh.setVisibility(View.GONE);
        mLoader.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mLoader.setVisibility(View.GONE);
        mRefresh.setVisibility(View.VISIBLE);
    }
}
