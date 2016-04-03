package com.epitech.billy.omega_splicer.presentation.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epitech.billy.omega_splicer.R;
import com.epitech.billy.omega_splicer.presentation.models.Plane;

import java.util.ArrayList;

/**
 * Created by bichon_b on 3/19/16.
 */
public class PlanesAvailableAdapter extends RecyclerView.Adapter<PlanesAvailableAdapter.ViewHolder> {

    private ArrayList<Plane> mPlanesList = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_bluetooth_device, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(mPlanesList.get(position).getName());
        holder.signalStrength.setText(Integer.toString(mPlanesList.get(position).getSignalStrength()));
    }

    @Override
    public int getItemCount() {
        return mPlanesList.size();
    }

    public void addPlane(Plane plane) {
        mPlanesList.add(plane);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public TextView signalStrength;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.row_bluetooth_device_name);
            signalStrength = (TextView) itemView.findViewById(R.id.row_bluetooth_device_signal_strength);
        }
    }
}
