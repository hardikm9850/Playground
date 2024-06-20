package com.hardik.myapplication

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Handler
import android.util.AttributeSet
import android.view.View


class ChainView(context: Context?, attrs: AttributeSet?) :
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
            Color.GRAY,Color.GRAY
            // 25 elements { 2 lines of 9 vertical dots + 5 on top + 2 on diagonal side}
        )
        handler = Handler()
        paint.textSize = 20f * 2
        paint.isAntiAlias = true
        yellowPaint.color = Color.YELLOW
        startAnimation()
    }

    private val leftDiagonalIndex = 5

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val width = width
        val radius = 20
        val space = 150

        // Top left diagonal
        paint.color = colors[leftDiagonalIndex]
        canvas.drawText(
            leftDiagonalIndex.toString(),
            (width / 3 - 2.5 * space + space).toFloat(),
            (space + (0.4) * space).toFloat(),
            paint
        )

        paint.color = colors[colors.size - 1]
        //Top right diagonal
        canvas.drawText(
            (colors.size - 1).toString(),
            (width / 3 + 2.8 * space + space).toFloat(),
            (space + (0.4) * space).toFloat(),
            paint
        )

        // Draw the reversed U shape dots
        // Top horizontal line
        for (i in 0..4) {
            paint.color = colors[4 - i]
            canvas.drawText(
                (4 - i).toString(),
                (width / 2 - 2 * space + i * space).toFloat(), space.toFloat(),
                paint
            )
        }

        // Left vertical line
        for (i in 0..10) {
            paint.color = colors[i + 6]
            canvas.drawText(
                (i + 6).toString(),
                (width / 2 - 3 * space).toFloat(),
                (space + (i + 1) * space).toFloat(),
                //radius.toFloat(),
                paint
            )
        }

        // Right vertical line
        for (i in 0..10) {
            paint.color = colors[colors.size - 2 - i]
            canvas.drawText(
                (colors.size - 2 - i).toString(),
                (width / 2 + 3 * space).toFloat(),
                (space + (i + 1) * space).toFloat(),
                //radius.toFloat(),
                paint
            )
        }
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

