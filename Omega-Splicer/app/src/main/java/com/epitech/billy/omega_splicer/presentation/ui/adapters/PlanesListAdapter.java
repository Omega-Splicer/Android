package com.epitech.billy.omega_splicer.presentation.ui.adapters;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epitech.billy.omega_splicer.R;
import com.epitech.billy.omega_splicer.bluetooth.ConnectWithOmegaSplicerPlane;
import com.epitech.billy.omega_splicer.domain.executors.impl.ThreadExecutor;
import com.epitech.billy.omega_splicer.presentation.models.PlaneModel;
import com.epitech.billy.omega_splicer.presentation.presenters.IPlanesListPresenter;
import com.epitech.billy.omega_splicer.presentation.presenters.impl.PlanesListPresenter;
import com.epitech.billy.omega_splicer.threading.MainThread;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by bichon_b on 3/19/16.
 */
public class PlanesListAdapter extends RecyclerView.Adapter<PlanesListAdapter.ViewHolder> {

    // data array
    private ArrayList<PlaneModel> mPlanesList = new ArrayList<>();

    // unique service for all the item view
    private ConnectWithOmegaSplicerPlane mConnectWithPlaneService;

    //
    private ViewHolder mPreviousViewConnecting;

    public PlanesListAdapter(Context context) {
        mConnectWithPlaneService = new ConnectWithOmegaSplicerPlane(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_bluetooth_device, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final PlaneModel planeModel = mPlanesList.get(position);
        if (planeModel.getName() != null && !planeModel.getName().isEmpty())
            holder.name.setText(planeModel.getName());
        else
            holder.name.setText(planeModel.getMacAddress());
        holder.signalStrength.setText(String.format(Locale.FRANCE, "%d", mPlanesList.get(position).getSignalStrength()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPreviousViewConnecting != null) {
                    mPreviousViewConnecting.resetStatus();
                }
                holder.presenter.clickOnPlane(planeModel.getMacAddress());
                mPreviousViewConnecting = holder;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPlanesList.size();
    }

    public void addPlane(PlaneModel plane) {

//        System.out.println("nouveau plane " + plane + " with name " + plane.getName());
//        for (PlaneModel p : mPlanesList) {
//            System.out.println("item in list " + p + " with name " + p.getName());
//            if (p.equals(plane))
//                System.out.println("EQUALS");
//            else
//                System.out.println("NOT EQUALS");
//        }

        int index = mPlanesList.indexOf(plane);

        if (index != -1) {
            mPlanesList.remove(index);
            mPlanesList.add(index, plane);
            notifyItemChanged(index);
        }
        else {
            mPlanesList.add(plane);
            // todo petite erreur bizarre ici
//            notifyDataSetChanged();
            notifyItemInserted(mPlanesList.size() - 1);
        }
    }

    public void addPlanes(List<PlaneModel> planes) {
        for (PlaneModel plane : planes) {
            this.addPlane(plane);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements IPlanesListPresenter.View {

        // ui stuff
        public View mGeneralView;
        public TextView name;
        public TextView signalStrength;
        public TextView status;

        // presenter
        public PlanesListPresenter presenter;

        // Context
        private Context mContext;

        public ViewHolder(View itemView) {
            super(itemView);

            mGeneralView = itemView;
            presenter = new PlanesListPresenter(ThreadExecutor.getInstance(), MainThread.getInstance(), this, mConnectWithPlaneService);
            mContext = itemView.getContext();

            name = (TextView) itemView.findViewById(R.id.row_bluetooth_device_name);
            signalStrength = (TextView) itemView.findViewById(R.id.row_bluetooth_device_signal_strength);
            status = (TextView) itemView.findViewById(R.id.row_bluetooth_device_status);
        }

        @Override
        public void displayConnectingStatus() {
            status.setText(mContext.getString(R.string.pair_activity_connecting_status));
            status.setVisibility(View.VISIBLE);
        }

        @Override
        public void displayConnectedStatus() {
            status.setText(mContext.getString(R.string.pair_activity_connected_status));
            status.setVisibility(View.VISIBLE);
        }

        @Override
        public void resetStatus() {
            status.setText("");
            status.setVisibility(View.GONE);
        }

        @Override
        public void showInvalidAddressError() {
            Snackbar.make(mGeneralView, mContext.getString(R.string.pair_activity_invalid_address_error), Snackbar.LENGTH_LONG).show();
        }

        @Override
        public void showMissingAddressError() {
            Snackbar.make(mGeneralView, mContext.getString(R.string.pair_activity_missing_address_error), Snackbar.LENGTH_LONG).show();
        }

        @Override
        public void showUnknownError() {
            Snackbar.make(mGeneralView, mContext.getString(R.string.pair_activity_unknown_error), Snackbar.LENGTH_LONG).show();
        }

        @Override
        public void showCantConnectError() {
            Snackbar.make(mGeneralView, mContext.getString(R.string.pair_activity_cant_connect_error), Snackbar.LENGTH_SHORT).show();
        }

        @Override
        public void showLoading() {

        }

        @Override
        public void hideLoading() {

        }
    }
}
