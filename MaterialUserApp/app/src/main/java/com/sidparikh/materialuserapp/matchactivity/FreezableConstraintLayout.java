package com.sidparikh.materialuserapp.matchactivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;


public class FreezableConstraintLayout extends ConstraintLayout {
    // private static final String LOG_TAG = FreezableConstraintLayout.class.getSimpleName();


    private boolean isFrozen = false;

    private final Paint paint = new Paint();

    public FreezableConstraintLayout(Context context) {
        super(context);
        init();
    }

    public FreezableConstraintLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FreezableConstraintLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public FreezableConstraintLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        /* Create grayscale color filter */
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        paint.setColorFilter(new ColorMatrixColorFilter(cm));
    }

    /* Override the dispatchTouchEvent method to prevent children from receiving touch events
       when this layout is frozen */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (isFrozen) {
            return true;
        } else {
            return super.dispatchTouchEvent(ev);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        if (isFrozen) {
            canvas.saveLayer(null, paint, Canvas.ALL_SAVE_FLAG);
        }
        super.draw(canvas);
        if (isFrozen) {
            canvas.restore();
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        if (isFrozen) {
            canvas.saveLayer(null, paint, Canvas.ALL_SAVE_FLAG);
        }
        super.dispatchDraw(canvas);
        if (isFrozen) {
            canvas.restore();
        }
    }

    public void setFrozen(boolean p) {
        isFrozen = p;
        // Redraw to update color if appropriate
        invalidate();
    }
}
