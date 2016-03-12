package com.epitech.billy.omega_splicer.old.Utils.Joystick;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.epitech.billy.omega_splicer.R;

/**
 * Created by Billy on 09/09/2015.
 *
 */
public class JoystickLayout extends RelativeLayout {

    private JoystickLayout self;
    private Joystick mJoystick;

    private int mOffset;
    private int mMinDistance;
    private int mResId;

    public JoystickLayout(Context context) {
        this(context, null, 0);
    }

    public JoystickLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public JoystickLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        this(context, attrs, defStyleAttr);
    }

    public JoystickLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.self = this;
        if (isInEditMode())
            showRender(context, attrs);
        else
            init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.JoystickLayout);
        mOffset = 0;
        mMinDistance = 0;
        mResId = -1;

        final int N = typedArray.getIndexCount();
        for (int i = 0; i < N; i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.JoystickLayout_offset:
                    mOffset = typedArray.getInt(i, 0);
                    break;
                case R.styleable.JoystickLayout_minDistance:
                    mMinDistance = typedArray.getInt(i, 0);
                    break;
                case R.styleable.JoystickLayout_joystickSrc:
                    mResId = typedArray.getResourceId(i, -1);
                    break;
                default:
                    break;
            }
        }
        typedArray.recycle();

        if (mResId == -1) {
            throw new RuntimeException("JoystickLayout must have a \"joystickSrc\" attribute");
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            JoystickBuilder builder = new JoystickBuilder(self.getContext(), self, mResId)
                    .setOffset(mOffset)
                    .setJoystickSize(150, 150)
                    .setLayoutSize(500, 500)
                    .setMinDistance(mMinDistance);

            mJoystick = builder.build();

            self.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    mJoystick.drawJoystick(event);
                    return true;
                }
            });
        }
    }

    private void showRender(Context context, AttributeSet attrs) {
        ImageView imageView = new ImageView(context);

        int tmp = attrs.getAttributeResourceValue(null, "joystickSrc", -1);
        if (tmp == -1)
            throw new RuntimeException("JoystickLayout must have a \"joystickSrc\" attribute");
        imageView.setImageResource(tmp);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        imageView.setLayoutParams(params);
        self.addView(imageView);
    }
}
