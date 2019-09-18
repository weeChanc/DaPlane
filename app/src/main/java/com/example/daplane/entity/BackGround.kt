package com.example.daplane.entity

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.example.daplane.base.Scene

class BackGround(
    x: Float,
    y: Float,
    width: Int,
    height: Int
) : Scene(x, y, width, height) {
    override fun draw(canvas: Canvas, paint: Paint) {
        paint.color = Color.parseColor("white")
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)
    }

    override fun nextFrame(clock: Long) {

    }

}