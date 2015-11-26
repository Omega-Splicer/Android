package com.epitech.billy.omega_splicer.Pair;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.epitech.billy.omega_splicer.R;

/**
 * Created by Billy on 01/09/2015.
 *
 * Allow the user to pair his device with an Omega-Splicer plane
 */
public class PairActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pair);

        findViewById(R.id.pair_activity_back_cloud).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        animatedClose();
        super.onBackPressed();
    }

    private void animatedClose() {
        if  (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ((ImageView) findViewById(R.id.pair_activity_back_cloud)).setImageDrawable(null);

            final TextView textView = (TextView) findViewById(R.id.pair_activity_title);
            textView.setTextColor(Color.BLACK);

            final float startValue = 40;
            final float endValue = 25;

            ValueAnimator valueAnimator = ValueAnimator.ofFloat(startValue, endValue);
            valueAnimator.setDuration(150);

            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, (float) valueAnimator.getAnimatedValue());
                    if ((float) valueAnimator.getAnimatedValue() <= 75) {
                        textView.setAllCaps(false);
                    }
                }
            });

            valueAnimator.start();
        }
    }
}
