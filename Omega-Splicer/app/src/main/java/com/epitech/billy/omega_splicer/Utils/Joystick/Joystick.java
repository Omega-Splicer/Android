package com.epitech.billy.omega_splicer.Utils.Joystick;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Billy on 08/09/2015.
 *
 * http://www.akexorcist.com/2012/10/android-code-joystick-controller.html
 */
public class Joystick {
    private enum JOYSTICK_DIRECTION {
        NONE,
        UP,
        DOWN,
        RIGHT,
        LEFT,
        UPRIGHT,
        UPLEFT,
        DOWNRIGHT,
        DOWNLEFT
    }

    private int joystickAlpha = 200;
    private int layoutAlpha = 200;
    private int mOffset = 0;

    private Context mContext;
    private ViewGroup mLayout;
    private ViewGroup.LayoutParams mParams;
    private int mJoystickWidth;
    private int mJoystickHeight;

    private int mPositionX = 0;
    private int mPositionY = 0;
    private int mMinDistance = 0;
    private float mDistance = 0;
    private float mAngle = 0;


    private DrawCanvas mDraw;
    private Paint mPaint;
    private Bitmap mJoystick;

    private boolean touchState = false;

    public Joystick(Context context, ViewGroup layout, int joystickResId) {
        mContext = context;
        mJoystick = BitmapFactory.decodeResource(mContext.getResources(), joystickResId);
        mJoystickWidth = mJoystick.getWidth();
        mJoystickHeight = mJoystick.getHeight();

        mDraw = new DrawCanvas(mContext);
        mPaint = new Paint();
        mLayout = layout;
        mParams = mLayout.getLayoutParams();

        mDraw.position(getLayoutHeight() / 2, getLayoutWidth() / 2);
        draw();
    }

    public void drawJoystick(MotionEvent event) {
        mPositionX = (int) (event.getX() - (mParams.width / 2));
        mPositionY = (int) (event.getY() - (mParams.height / 2));
        mDistance = (float) Math.sqrt(Math.pow(mPositionX, 2) + Math.pow(mPositionY, 2));
        mAngle = (float) calculAngle(mPositionX, mPositionY);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mDistance <= (mParams.width / 2) - mOffset) {
                    mDraw.position(event.getX(), event.getY());
                    draw();
                    touchState = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (touchState) {
                    if (mDistance <= (mParams.width / 2) - mOffset) {
                        mDraw.position(event.getX(), event.getY());
                        draw();
                    }
                    else {
                        float x = (float) (Math.cos(Math.toRadians(calculAngle(mPositionX, mPositionY))) * ((mParams.width / 2) - mOffset));
                        float y = (float) (Math.sin(Math.toRadians(calculAngle(mPositionX, mPositionY))) * ((mParams.height / 2) - mOffset));
                        x += mParams.width / 2;
                        y += mParams.height / 2;
                        mDraw.position(x, y);
                        draw();
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                mDraw.position(getLayoutHeight() / 2, getLayoutWidth() / 2);
                draw();
//                mLayout.removeView(mDraw);
                touchState = false;
                break;
            default:
                break;
        }
    }

    public int[] getPosition() {
        if (mDistance > mMinDistance && touchState)
            return new int[] { mPositionX, mPositionY};
        return new int[] {0, 0};
    }

    public int getX() {
        if (mDistance > mMinDistance && touchState)
            return mPositionX;
        return 0;
    }

    public int getY() {
        if (mDistance > mMinDistance && touchState)
            return mPositionY;
        return 0;
    }

    public float getAngle() {
        if (mDistance > mMinDistance && touchState)
            return mAngle;
        return 0;
    }

    public float getDistance() {
        if (mDistance > mMinDistance && touchState)
            return mDistance;
        return 0;
    }

    public void setMinDistance(int minDistance) {
        mMinDistance = minDistance;
    }

    public int getMinDistance() {
        return mMinDistance;
    }

    public JOYSTICK_DIRECTION get8Direction() {
        if (mDistance > mMinDistance && touchState) {
            if (mAngle >= 247.5 && mAngle < 292.5)
                return JOYSTICK_DIRECTION.UP;
            else if (mAngle >= 292.5 && mAngle < 337.5)
                return JOYSTICK_DIRECTION.UPRIGHT;
            else if (mAngle >= 337.5 || mAngle < 22.5)
                return JOYSTICK_DIRECTION.RIGHT;
            else if (mAngle >= 22.5 && mAngle < 67.5)
                return JOYSTICK_DIRECTION.DOWNRIGHT;
            else if (mAngle >= 67.5 && mAngle < 112.5)
                return JOYSTICK_DIRECTION.DOWN;
            else if (mAngle >= 112.5 && mAngle < 157.5)
                return JOYSTICK_DIRECTION.DOWNLEFT;
            else if (mAngle >= 157.5 && mAngle < 202.5)
                return JOYSTICK_DIRECTION.LEFT;
            else if (mAngle >= 202.5 && mAngle < 247.5)
                return JOYSTICK_DIRECTION.UPLEFT;
        }
        return JOYSTICK_DIRECTION.NONE;
    }

    public JOYSTICK_DIRECTION get4Direction() {
        if (mDistance > mMinDistance && touchState) {
            if (mAngle >= 225 && mAngle < 315)
                return JOYSTICK_DIRECTION.UP;
            else if (mAngle >= 315 || mAngle < 45)
                return JOYSTICK_DIRECTION.RIGHT;
            else if (mAngle >= 45 && mAngle < 135)
                return JOYSTICK_DIRECTION.DOWN;
            else if (mAngle >= 135 && mAngle < 225)
                return JOYSTICK_DIRECTION.LEFT;
        }
        return JOYSTICK_DIRECTION.NONE;
    }

    public void setOffset(int offset) {
        mOffset = offset;
    }

    public int getOffset() {
        return mOffset;
    }

    public void setJoystickAlpha(int alpha) {
        joystickAlpha = alpha;
        mPaint.setAlpha(alpha);
    }

    public int getJoystickAlpha() {
        return joystickAlpha;
    }

    public void setLayoutAlpha(int alpha) {
        layoutAlpha = alpha;
        mLayout.getBackground().setAlpha(alpha);
    }

    public int getLayoutAlpha() {
        return layoutAlpha;
    }

    public void setJoystickSize(int width, int height) {
        mJoystick = Bitmap.createScaledBitmap(mJoystick, width, height, false);
        mJoystickWidth = mJoystick.getWidth();
        mJoystickHeight = mJoystick.getHeight();
    }

    public void setJoystickWidth(int width) {
        mJoystick = Bitmap.createScaledBitmap(mJoystick, width, mJoystickHeight, false);
        mJoystickWidth = mJoystick.getWidth();
    }

    public void setJoystickHeight(int height) {
        mJoystick = Bitmap.createScaledBitmap(mJoystick, mJoystickWidth, height, false);
        mJoystickHeight = mJoystick.getHeight();
    }

    public int getJoystickWidth() {
        return mJoystickWidth;
    }

    public int getJoystickHeight() {
        return mJoystickHeight;
    }

    public void setLayoutSize(int width, int height) {
        mParams.width = width;
        mParams.height = height;
    }

    public int getLayoutWidth() {
        return mParams.width;
    }

    public int getLayoutHeight() {
        return mParams.height;
    }

    private double calculAngle(float x, float y) {
        if (x >= 0 && y >= 0)
            return Math.toDegrees(Math.atan(y / x));
        else if (x >= 0 && y < 0)
            return Math.toDegrees(Math.atan(y / x)) + 360;
        else if (x < 0)
            return Math.toDegrees(Math.atan(y / x)) + 180;
        return 0;
    }

    private void draw() {
        try {
            mLayout.removeView(mDraw);
        } catch (Exception e) {}
        mLayout.addView(mDraw);
    }

    private class DrawCanvas extends View {
        float x, y;

        public DrawCanvas(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawBitmap(mJoystick, x, y, mPaint);
        }

        private void position(float posX, float posY) {
            x = posX - (mJoystickWidth / 2);
            y = posY - (mJoystickHeight / 2);
        }
    }
}
