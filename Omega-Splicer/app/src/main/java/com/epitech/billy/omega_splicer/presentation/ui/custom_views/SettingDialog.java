package com.epitech.billy.omega_splicer.presentation.ui.custom_views;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.epitech.billy.omega_splicer.App;
import com.epitech.billy.omega_splicer.R;
import com.epitech.billy.omega_splicer.domain.executors.impl.ThreadExecutor;
import com.epitech.billy.omega_splicer.presentation.presenters.ISettingPresenter;
import com.epitech.billy.omega_splicer.presentation.presenters.impl.SettingPresenter;
import com.epitech.billy.omega_splicer.storage.SettingSharedPreferencesStore;
import com.epitech.billy.omega_splicer.threading.MainThread;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bichon_b on 3/8/16.
 * <p/>
 * This is a popup shown in the fly page in order to change the setting.
 * It uses the same presenter as the setting view.
 * <p/>
 */
public class SettingDialog extends AlertDialog implements ISettingPresenter.View {

    // UI Stuff
    private TextView mSettingDialogTitle;
    private TextView mSettingDialogControlTitle;
    private TextView mSettingDialogSpeedUnitTitle;
    private Spinner mSettingDialogControlSpinner;
    private Spinner mSettingDialogSpeedUnitSpinner;
    private Button mSettingDialogCancelButton;
    private Button mSettingDialogOkButton;

    // Presenter
    private ISettingPresenter mSettingPresenter;

    // Context
    private Context mContext;

    public SettingDialog(Context context) {
        super(context);
        mContext = context;
        mSettingPresenter = new SettingPresenter(ThreadExecutor.getInstance(), MainThread.getInstance(), this, new SettingSharedPreferencesStore(context));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_setting_layout);

        mSettingDialogTitle = (TextView) findViewById(R.id.setting_popup_title);
        mSettingDialogControlTitle = (TextView) findViewById(R.id.setting_popup_control);
        mSettingDialogSpeedUnitTitle = (TextView) findViewById(R.id.setting_popup_speed_unit);
        mSettingDialogControlSpinner = (Spinner) findViewById(R.id.setting_popup_control_spinner);
        mSettingDialogSpeedUnitSpinner = (Spinner) findViewById(R.id.setting_popup_speed_unit_spinner);
        mSettingDialogCancelButton = (Button) findViewById(R.id.setting_popup_cancel_button);
        mSettingDialogOkButton = (Button) findViewById(R.id.setting_popup_ok_button);

        if (mSettingDialogTitle != null) {
            mSettingDialogTitle.setTypeface(App.ROBOTO_REGULAR);
        }
        setupControlSetting();
        setupControlSpeedUnit();
        setupCancelButton();
        setupOkButton();
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        mSettingPresenter.resume();
    }

    private void setupControlSetting() {
        if (mSettingDialogControlTitle != null) {
            mSettingDialogControlTitle.setTypeface(App.ROBOTO_REGULAR);
        }

        List<String> controlList = new ArrayList<>();
        controlList.add(mContext.getString(R.string.control_buttons));
        controlList.add(mContext.getString(R.string.control_gyroscope));
        mSettingDialogControlSpinner.setAdapter(new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_dropdown_item, controlList));
    }

    private void setupControlSpeedUnit() {
        if (mSettingDialogSpeedUnitTitle != null) {
            mSettingDialogSpeedUnitTitle.setTypeface(App.ROBOTO_REGULAR);
        }

        List<String> speedUnitList = new ArrayList<>();
        speedUnitList.add(mContext.getString(R.string.speed_unit_kmph));
        speedUnitList.add(mContext.getString(R.string.speed_unit_mps));
        speedUnitList.add(mContext.getString(R.string.speed_unit_mph));
        mSettingDialogSpeedUnitSpinner.setAdapter(new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_dropdown_item, speedUnitList));
    }

    private void setupCancelButton() {
        mSettingDialogCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void setupOkButton() {
        mSettingDialogOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (mSettingDialogControlSpinner.getSelectedItemPosition()) {
                    case 0:
                        mSettingPresenter.changeControlSetting(mContext.getString(R.string.button_control_shared_preference));
                        break;
                    case 1:
                        mSettingPresenter.changeControlSetting(mContext.getString(R.string.gyroscope_control_shared_preference));
                        break;
                    default:
                        mSettingPresenter.changeControlSetting(mContext.getString(R.string.button_control_shared_preference));
                        break;
                }

                switch (mSettingDialogSpeedUnitSpinner.getSelectedItemPosition()) {
                    case 0:
                        mSettingPresenter.changeSpeedUnitSetting(mContext.getString(R.string.km_speed_unit_shared_preference));
                        break;
                    case 1:
                        mSettingPresenter.changeSpeedUnitSetting(mContext.getString(R.string.ms_speed_unit_shared_preference));
                        break;
                    case 2:
                        mSettingPresenter.changeSpeedUnitSetting(mContext.getString(R.string.mph_speed_unit_shared_preference));
                        break;
                    default:
                        mSettingPresenter.changeSpeedUnitSetting(mContext.getString(R.string.km_speed_unit_shared_preference));
                        break;
                }
                dismiss();
            }
        });
    }

    @Override
    public void showControlSetting(String controlSetting) {
        if (mSettingDialogControlSpinner != null) {
            if (controlSetting.equals(mContext.getString(R.string.button_control_shared_preference))) {
                mSettingDialogControlSpinner.setSelection(0);
            }
            else if (controlSetting.equals(mContext.getString(R.string.gyroscope_control_shared_preference))) {
                mSettingDialogControlSpinner.setSelection(1);
            }
        }
    }

    @Override
    public void showSpeedUnitSetting(String speedUnitSetting) {
        if (mSettingDialogSpeedUnitSpinner != null) {
            if (speedUnitSetting.equals(mContext.getString(R.string.km_speed_unit_shared_preference))) {
                mSettingDialogSpeedUnitSpinner.setSelection(0);
            }
            else if (speedUnitSetting.equals(mContext.getString(R.string.ms_speed_unit_shared_preference))) {
                mSettingDialogSpeedUnitSpinner.setSelection(1);
            }
            else if (speedUnitSetting.equals(mContext.getString(R.string.mph_speed_unit_shared_preference))) {
                mSettingDialogSpeedUnitSpinner.setSelection(2);
            }
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
