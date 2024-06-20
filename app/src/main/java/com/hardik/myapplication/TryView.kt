package com.hardik.myapplication

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Handler
import android.util.AttributeSet
import android.view.View


class TryView(context: Context?, attrs: AttributeSet?) :
    View(context, attrs) {
    private lateinit var paint: Paint
    private lateinit var colors: IntArray
    private var handler: Handler? = null
    private val delay = 300 // milliseconds
    private val yellowPaint = Paint(Color.YELLOW)

    init {
        init()
    }

    private fun init() {
        paint = Paint()
        colors = intArrayOf(
            Color.RED, Color.GREEN, Color.BLUE, Color.GRAY, // RGB colors
            Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY,
            Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY,
            Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY,
            Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY,
            Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY,
            Color.GRAY, Color.GRAY, Color.GRAY,
            Color.GRAY, Color.GRAY
            // 25 elements { 2 lines of 9 vertical dots + 5 on top + 2 on diagonal side}
        )
        handler = Handler()
        paint.textSize = 20f * 2
        paint.isAntiAlias = true
        yellowPaint.color = Color.YELLOW
        //startAnimation()
    }

    private val leftDiagonalIndex = 5

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val width = width
        val height = height
        val radius = 20f
        val space = 150
        val margin = 150f
        val marginLeft = margin
        val marginRight = margin
        val cx = width / 2.toFloat()
        val cy = height / 2.toFloat()
        val lineHeight = height / 3
        //canvas.drawCircle(cx, cy, radius, paint)
        // Left line
        canvas.drawLine(marginLeft, cy - lineHeight, marginLeft, cy + lineHeight, paint)
        // Right line
        canvas.drawLine(
            width - marginRight,
            cy - lineHeight,
            width - marginRight,
            cy + lineHeight,
            paint
        )

        //======== top line =======
        canvas.drawLine(
            marginLeft + 100f,
            cy - lineHeight - 200f,
            width - marginRight - 100f,
            cy - lineHeight - 200f,
            paint
        )

        //======== diagonal =======
        canvas.drawLine(
            marginRight,
            cy - lineHeight,
            marginLeft + 100f,
            cy - lineHeight - 200f,
            paint
        )

        canvas.drawLine(
            width - marginRight,
            cy - lineHeight,
            width - marginRight - 100f,
            cy - lineHeight - 200f,
            paint
        )

//======== end =======
        canvas.drawCircle(
            marginLeft + 50f,
            cy + lineHeight + 100f,
            radius,
            paint
        )
        canvas.drawCircle(
            width - marginRight - 50f,
            cy + lineHeight + 100f,
            radius,
            paint
        )


    }

    private fun startAnimation() {
        handler!!.postDelayed(object : Runnable {
            override fun run() {
                // Rotate the colors array to create the animation effect in the correct U shape direction
                val temp = colors[0]
                System.arraycopy(colors, 1, colors, 0, colors.size - 1)
                colors[colors.size - 1] = temp
                invalidate() // Redraw the view
                handler!!.postDelayed(this, delay.toLong())
            }
        }, delay.toLong())
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        handler!!.removeCallbacksAndMessages(null) // Remove all callbacks when view is detached
    }
}

