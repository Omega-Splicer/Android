//package com.epitech.billy.omega_splicer;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.app.ActivityOptionsCompat;
//import android.support.v4.util.Pair;
//import android.view.View;
//import android.widget.Button;
//
//import FlyActivity;
//import com.epitech.billy.omega_splicer.Pair.PairActivity;
//import com.epitech.billy.omega_splicer.Setting.SettingActivity;
//
//public class MainActivity_old extends Activity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        View pairButton = findViewById(R.id.main_activity_pair_layout);
//        if (pairButton != null) {
//            setupPairButton(this, pairButton);
//        }
//
//        View flyButton = findViewById(R.id.main_activity_fly_layout);
//        if (flyButton != null) {
//            setupFlyButton(this, flyButton);
//        }
//
//        View settingButton = findViewById(R.id.main_activity_setting_layout);
//        if (settingButton != null) {
//            setupSettingLayout(this, settingButton);
//        }
//    }
//
//    private void setupPairButton(final Activity activity, final View pairButton) {
//        final View textView = findViewById(R.id.main_activity_pair_text);
//
//        pairButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,
//                        Pair.create(pairButton, "pair_transition_cloud"),
//                        Pair.create(textView, "pair_transition_text"));
//                ActivityCompat.startActivity(activity, new Intent(activity, PairActivity.class), options.toBundle());
//            }
//        });
//    }
//
//    private void setupFlyButton(final Activity activity, final View flyButton) {
//        final View panelView = findViewById(R.id.main_activity_panel_image);
//
//        flyButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, panelView, "fly_transition_panel");
//                ActivityCompat.startActivity(activity, new Intent(activity, FlyActivity.class), options.toBundle());
//            }
//        });
//    }
//
//    private void setupSettingLayout(final Activity activity, final View settingButton) {
//        final View textView = findViewById(R.id.main_activity_setting_text);
//
//        settingButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,
//                        Pair.create(settingButton, "setting_transition_cloud"),
//                        Pair.create(textView, "setting_transition_text"));
//                ActivityCompat.startActivity(activity, new Intent(activity, SettingActivity.class), options.toBundle());
//            }
//        });
//    }
//}