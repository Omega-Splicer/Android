package com.epitech.billy.omega_splicer.presentation.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.epitech.billy.omega_splicer.R;
import com.epitech.billy.omega_splicer.bluetooth.ConnectWithOmegaSplicerPlane;
import com.epitech.billy.omega_splicer.bluetooth.GetPairedOmegaSplicerPlanes;
import com.epitech.billy.omega_splicer.domain.executors.impl.ThreadExecutor;
import com.epitech.billy.omega_splicer.presentation.models.PlaneModel;
import com.epitech.billy.omega_splicer.presentation.presenters.IPairedDevicesPresenter;
import com.epitech.billy.omega_splicer.presentation.presenters.impl.PairedDevicesPresenter;
import com.epitech.billy.omega_splicer.presentation.ui.adapters.PlanesListAdapter;
import com.epitech.billy.omega_splicer.presentation.ui.managers.ControlScrollingLinearLayoutManager;
import com.epitech.billy.omega_splicer.threading.MainThread;

import java.util.List;

/**
 * Created by Billy on 07/04/2016.
 */
public class PairedDevicesFragment extends Fragment implements IPairedDevicesPresenter.View {

    // UI stuff
    private ProgressBar mLoader;
    private RecyclerView mPlanesRecyclerView;

    // adapter
    private PlanesListAdapter mAdapter;

    // presenter
    private IPairedDevicesPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.paired_devices_view, container, false);

        mPresenter = new PairedDevicesPresenter(ThreadExecutor.getInstance(), MainThread.getInstance(), this, new GetPairedOmegaSplicerPlanes());

        mLoader = (ProgressBar) rootView.findViewById(R.id.paired_devices_fragment_loader);
        mPlanesRecyclerView = (RecyclerView) rootView.findViewById(R.id.paired_devices_fragment_devices_list);
        mAdapter = new PlanesListAdapter(this.getContext());
        mPlanesRecyclerView.setAdapter(mAdapter);
        mPlanesRecyclerView.setHasFixedSize(false);

        mAdapter.addPlane(new PlaneModel("Mockup Plane"));

        // todo put that on top
        final ConnectWithOmegaSplicerPlane connection = new ConnectWithOmegaSplicerPlane(this.getContext());

//        mAdapter.setOnItemClickListener(new PlanesListAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(PlaneModel plane) {
//                Toast.makeText(PairedDevicesFragment.this.getContext(), "Trying to connect to " + plane.getMacAddress(), Toast.LENGTH_SHORT).show();
//                connection.connect(plane.getMacAddress());
//            }
//        });
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ControlScrollingLinearLayoutManager controlScrollingLinearLayoutManager = new ControlScrollingLinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        controlScrollingLinearLayoutManager.setVerticalScrollEnable(false);
        mPlanesRecyclerView.setLayoutManager(controlScrollingLinearLayoutManager);
    }

    public IPairedDevicesPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.resume();
    }

    @Override
    public void addPlaneToList(PlaneModel plane) {
//        mAdapter.addPlane(plane);
//        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void addPlanesToList(List<PlaneModel> planes) {
//        mAdapter.addPlanes(planes);
//        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {
        mLoader.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mLoader.setVisibility(View.INVISIBLE);
    }
}
