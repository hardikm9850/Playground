package com.hardik.myapplication;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class AnimatedDotView extends View {

    private Paint paint;
    private float radius; // Dot radius
    private int[] colors; // Array of colors for the first 3 dots
    private float animationProgress; // Progress for animation (0 to 1)

    public AnimatedDotView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        // Get attributes from XML (optional)
        //TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AnimatedDotView);
        radius = 10f;
        colors = new int[]{Color.RED,
                 Color.GREEN,
                Color.BLUE};

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        startAnimation();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Calculate dot positions based on view size and animation progress
        float centerX = getWidth() / 2f;
        float centerY = getHeight() / 2f;
        float dotSeparation = 3 * radius; // Adjust separation as needed

        // Draw bottom dots (without animation)
        for (int i = 3; i < colors.length; i++) {
            float x = centerX - (i - 2) * dotSeparation;
            float y = centerY + radius;
            paint.setColor(colors[i]);
            canvas.drawCircle(x, y, radius, paint);
        }

        // Draw top 3 dots with animation
        for (int i = 0; i < 3; i++) {
            float x = centerX - (i) * dotSeparation;
            float y = centerY - radius * (2 - animationProgress); // Animate Y position
            paint.setColor(colors[i]);
            canvas.drawCircle(x, y, radius, paint);
        }
    }

    // Function to start animation (optional)
    public void startAnimation() {
        // Use an object animator or other animation techniques to update animationProgress
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
        animator.setDuration(1000); // Adjust duration as needed
        animator.setRepeatCount(ValueAnimator.INFINITE); // Repeat animation
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                animationProgress = (float) animation.getAnimatedValue();
                invalidate(); // Trigger redraw to reflect animation progress
            }
        });
        animator.start();
    }
}
