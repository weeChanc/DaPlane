package com.example.daplane.base

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint


class Spirit(x: Float, y: Float, width: Int, height: Int, val bitmap: Bitmap) :
    Scene(x, y, width, height) {


    override fun draw(canvas: Canvas, paint: Paint) {
        canvas.drawBitmap(bitmap, x, y, paint)
    }

    override fun nextFrame(clock: Long) {

    }
}