package com.example.daplane.entity

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import com.example.daplane.`interface`.Scene


class Floor(x: Float, y: Float, width: Int, height: Int) :
    Scene(x, y, width, height) {
    override fun nextFrame(clock: Long) {
        y += 1f
    }

    override fun draw(canvas: Canvas, paint: Paint) {
        paint.color = Color.parseColor("red")
        canvas.drawRect(x, y, x + width, y + height, paint)
    }
}