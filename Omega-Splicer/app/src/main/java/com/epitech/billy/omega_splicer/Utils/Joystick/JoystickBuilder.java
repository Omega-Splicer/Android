package com.epitech.billy.omega_splicer.Utils.Joystick;

import android.content.Context;
import android.view.ViewGroup;

/**
 * Created by Billy on 09/09/2015.
 *
 */
public class JoystickBuilder {

    private int joystickAlpha = 200;
    private int layoutAlpha = 200;
    private int offset = 0;
    private int minDistance = 0;
    private Context context;
    private ViewGroup viewGroup;
    private int resId;
    private int joystickWidth = -1;
    private int joystickHeight = -1;
    private int layoutWidth = -1;
    private int layoutHeight = -1;


    public JoystickBuilder(Context context, ViewGroup viewGroup, int resId) {
        this.context = context;
        this.viewGroup = viewGroup;
        this.resId = resId;
    }

    public JoystickBuilder setJoystickAlpha(int alpha) {
        this.joystickAlpha = alpha;
        return this;
    }

    public JoystickBuilder setLayoutAlpha(int alpha) {
        this.layoutAlpha = alpha;
        return this;
    }

    public JoystickBuilder setOffset(int offset) {
        this.offset = offset;
        return this;
    }

    public JoystickBuilder setMinDistance(int minDistance) {
        this.minDistance = minDistance;
        return this;
    }

    public JoystickBuilder setJoystickWidth(int width) {
        this.joystickWidth = width;
        return this;
    }

    public JoystickBuilder setJoystickHeight(int height) {
        this.joystickHeight = height;
        return this;
    }

    public JoystickBuilder setJoystickSize(int width, int height) {
        this.joystickWidth = width;
        this.joystickHeight = height;
        return this;
    }

    public JoystickBuilder setLayoutSize(int width, int height) {
        this.layoutWidth = width;
        this.layoutHeight = height;
        return this;
    }

    public Joystick build() {
        Joystick joystick = new Joystick(this.context, this.viewGroup, this.resId);

        joystick.setOffset(this.offset);
        joystick.setJoystickAlpha(this.joystickAlpha);
        joystick.setLayoutAlpha(this.layoutAlpha);
        joystick.setMinDistance(this.minDistance);
        if (this.joystickHeight >= 0)
            joystick.setJoystickHeight(this.joystickHeight);
        if (this.joystickWidth >= 0)
            joystick.setJoystickWidth(this.joystickWidth);
        if (this.layoutWidth >= 0 && this.layoutHeight >= 0)
            joystick.setLayoutSize(this.layoutWidth, this.layoutHeight);
        return joystick;
    }

}
