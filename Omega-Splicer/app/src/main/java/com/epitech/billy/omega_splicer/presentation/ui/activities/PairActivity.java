package com.epitech.billy.omega_splicer.presentation.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.epitech.billy.omega_splicer.R;
import com.epitech.billy.omega_splicer.presentation.models.Plane;
import com.epitech.billy.omega_splicer.presentation.presenters.IPairPresenter;

/**
 * Created by bichon_b on 3/4/16.
 */
public class PairActivity extends AppCompatActivity implements IPairPresenter.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pair);


    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void addPlaneToList(Plane plane) {

    }
}
