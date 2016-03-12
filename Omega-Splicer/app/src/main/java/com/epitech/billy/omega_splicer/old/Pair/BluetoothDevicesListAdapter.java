package com.epitech.billy.omega_splicer.old.Pair;

import android.bluetooth.BluetoothDevice;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epitech.billy.omega_splicer.R;

import java.util.ArrayList;

/**
 * Created by bichon_b on 1/31/16.
 *
 */
public class BluetoothDevicesListAdapter extends RecyclerView.Adapter<BluetoothDevicesListAdapter.ViewHolder> {

    private ArrayList<BluetoothDevice> mBluetoothDevices;

    public BluetoothDevicesListAdapter() {

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_bluetooth_device, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(mBluetoothDevices.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mBluetoothDevices.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            mTextView = (TextView) itemView.findViewById(R.id.row_bluetooth_device_name);
        }
    }

    public void addDevice(BluetoothDevice device) {
        mBluetoothDevices.add(device);
    }
}
