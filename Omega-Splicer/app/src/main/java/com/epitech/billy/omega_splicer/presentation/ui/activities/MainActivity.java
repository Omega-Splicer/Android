package com.epitech.billy.omega_splicer.presentation.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.epitech.billy.omega_splicer.R;
import com.epitech.billy.omega_splicer.domain.executors.impl.ThreadExecutor;
import com.epitech.billy.omega_splicer.presentation.presenters.IMainPresenter;
import com.epitech.billy.omega_splicer.presentation.presenters.impl.MainPresenter;
import com.epitech.billy.omega_splicer.threading.MainThread;

/**
 * Created by bichon_b on 3/4/16.
 * <p/>
 * The first activity to be called, it will enable the user to navigate through the different menus
 * <p/>
 */
public class MainActivity extends AppCompatActivity implements IMainPresenter.View {

    // UI stuff
    private Button mFlyButton;
    private Button mPairButton;
    private Button mSettingButton;

    // Presenter
    private IMainPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainPresenter = new MainPresenter(ThreadExecutor.getInstance(), MainThread.getInstance(), this);

        mFlyButton = (Button) findViewById(R.id.main_activity_fly_button);
        mPairButton = (Button) findViewById(R.id.main_activity_pair_button);
        mSettingButton = (Button) findViewById(R.id.main_activity_setting_button);

        if (mFlyButton != null) {
            setupFlyButton();
        }

        if (mPairButton != null) {
            setupPairButton();
        }

        if (mSettingButton != null) {
            setupSettingButton();
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void navigateToFlyView() {
        startActivity(new Intent(MainActivity.this, FlyActivity.class));
    }

    @Override
    public void navigateToPairView() {
        startActivity(new Intent(MainActivity.this, PairActivity.class));
    }

    @Override
    public void navigateToSettingView() {
        startActivity(new Intent(MainActivity.this, SettingActivity.class));
    }

    private void setupFlyButton() {
        mFlyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainPresenter.clickOnFlyButton();
            }
        });
    }

    private void setupPairButton() {
        mPairButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainPresenter.clickOnPairButton();
            }
        });
    }

    private void setupSettingButton() {
        mSettingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainPresenter.clickOnSettingButton();
            }
        });
    }
}
