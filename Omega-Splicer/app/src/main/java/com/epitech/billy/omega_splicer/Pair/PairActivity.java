package com.epitech.billy.omega_splicer.Pair;

import android.app.Activity;
import android.os.Bundle;

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
    }
}
