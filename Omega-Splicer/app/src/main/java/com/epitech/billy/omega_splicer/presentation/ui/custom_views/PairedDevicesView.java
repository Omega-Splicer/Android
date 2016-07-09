package com.epitech.billy.omega_splicer.presentation.ui.custom_views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epitech.billy.omega_splicer.R;
import com.epitech.billy.omega_splicer.presentation.models.PlaneModel;
import com.epitech.billy.omega_splicer.presentation.presenters.IPairedDevicesPresenter;

import java.util.List;

/**
 * Created by Billy on 07/04/2016.
 *
 */
public class PairedDevicesView extends ViewGroup {

    // presenter
    private IPairedDevicesPresenter mPairedDevicesPresenter;

    public PairedDevicesView(Context context) {
        this(context, null);
    }

    public PairedDevicesView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PairedDevicesView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        LayoutInflater inflater;
//        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        inflater.inflate(R.layout.paired_devices_view, null);
        inflate(getContext(), R.layout.paired_devices_view, this);
//        this.addView(LayoutInflater.from(context).inflate(R.layout.paired_devices_view, this, false));
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
    }

    public void setPresenter(IPairedDevicesPresenter pairedDevicesPresenter) {
        mPairedDevicesPresenter = pairedDevicesPresenter;
    }

//    @Override
//    public void showLoading() {
//
//    }
//
//    @Override
//    public void hideLoading() {
//
//    }
//
//    @Override
//    public void addPlaneToList(PlaneModel plane) {
//
//    }
//
//    @Override
//    public void addPlanesToList(List<PlaneModel> planes) {
//
//    }
}
