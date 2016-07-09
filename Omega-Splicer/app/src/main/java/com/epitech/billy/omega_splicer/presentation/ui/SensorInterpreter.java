package com.epitech.billy.omega_splicer.presentation.ui;

import android.content.Context;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Surface;
import android.view.WindowManager;

/**
 * Created by Billy on 18/05/2016.
 */
public class SensorInterpreter {

    private float[] mTiltVector = new float[3];

    private boolean mInitState = false;
    private float[] mInitStateMatrix = new float[9];
    private float[] mRotationMatrix = new float[9];
    private float mTiltIntensity = 2.0f;

    public float[] interpretSensorEvent(@NonNull Context context, @Nullable SensorEvent event) {
        if (event == null) {
            return null;
        }

        float[] rotationVector = event.values;

        if (!mInitState) {
            SensorManager.getRotationMatrixFromVector(mInitStateMatrix, rotationVector);
            mInitState = true;
        }

        SensorManager.getRotationMatrixFromVector(mRotationMatrix, rotationVector);

        int rotation = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getRotation();

        switch (rotation) {
            case Surface.ROTATION_90:
                SensorManager.remapCoordinateSystem(mRotationMatrix, SensorManager.AXIS_Y, SensorManager.AXIS_MINUS_X, mRotationMatrix);
                break;
            case Surface.ROTATION_180:
                SensorManager.remapCoordinateSystem(mRotationMatrix, SensorManager.AXIS_MINUS_X, SensorManager.AXIS_MINUS_Y, mRotationMatrix);
                break;
            case Surface.ROTATION_270:
                SensorManager.remapCoordinateSystem(mRotationMatrix, SensorManager.AXIS_MINUS_Y, SensorManager.AXIS_X, mRotationMatrix);
                break;
        }
        SensorManager.getAngleChange(mTiltVector, mRotationMatrix, mInitStateMatrix);

        for (int i = 0; i < mTiltVector.length; i++) {
            mTiltVector[i] /= Math.PI;
            mTiltVector[i] *= mTiltIntensity;
        }

        return mTiltVector;
    }

    public void reset() {
        mInitState = false;
    }

    public void setTiltIntensity(float intensity) {
        if (intensity <= 0) {
            throw new IllegalArgumentException("The intensity must be positive");
        }
        mTiltIntensity = intensity;
    }

    public float getTiltIntensity() {
        return mTiltIntensity;
    }
}

