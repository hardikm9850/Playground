package com.hardik.myapplication;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class UShapeView extends View {

    private static final int NUM_LEFT_RIGHT_DOTS = 10;
    private static final int NUM_BOTTOM_DOTS = 5;
    private static final int NUM_DIAGONAL_DOTS = 2;
    private Paint paint;
    private float dotRadius;

    public UShapeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK); // Set your desired dot color here
        dotRadius = 15f;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float centerX = getWidth() / 2f;
        float centerY = getHeight() / 2f;
        float dotSeparation = 3 * dotRadius; // Adjust separation as needed

        // Calculate starting Y position for left and right dots
        float startY = centerY - (NUM_LEFT_RIGHT_DOTS / 2f) * dotSeparation;

        // Draw left dots
        for (int i = 0; i < NUM_LEFT_RIGHT_DOTS; i++) {
            float x = dotRadius;
            float y = startY + i * dotSeparation;
            drawDot(canvas, x, y);
        }

        // Draw right dots
        for (int i = 0; i < NUM_LEFT_RIGHT_DOTS; i++) {
            float x = getWidth() - dotRadius;
            float y = startY + i * dotSeparation;
            drawDot(canvas, x, y);
        }

        // Draw bottom dots
        float startX = centerX - (NUM_BOTTOM_DOTS / 2f) * dotSeparation;
        float bottomY = getHeight() - dotRadius;
        for (int i = 0; i < NUM_BOTTOM_DOTS; i++) {
            float x = startX + i * dotSeparation;
            float y = bottomY;
            drawDot(canvas, x, y);
        }

        // Draw diagonal dots (assuming diagonal starts from bottom left)
        float diagonalX1 = dotRadius;
        float diagonalY1 = bottomY - dotSeparation;
        float diagonalX2 = diagonalX1 + dotSeparation;
        float diagonalY2 = bottomY;
        drawDot(canvas, diagonalX1, diagonalY1);
        drawDot(canvas, diagonalX2, diagonalY2);
    }

    private void drawDot(Canvas canvas, float x, float y) {
        canvas.drawCircle(x, y, dotRadius, paint);
    }
}
