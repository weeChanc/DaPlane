package com.example.daplane.`interface`

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.MotionEvent

open abstract class Scene(
    var x: Float,
    var y: Float,
    var width: Int,
    var height: Int,
    var touchable: Boolean = false,
    var enabled: Boolean = false
) {


    abstract fun draw(canvas: Canvas, paint: Paint)

    abstract fun nextFrame(clock: Long)

    fun contains(other: Scene): Boolean {
        return Rect.intersects(Rect(
            x.toInt(),
            y.toInt(),
            (x + width).toInt(),
            (y + height).toInt()
        ), Rect(
            other.x.toInt(),
            other.y.toInt(),
            (other.x + other.width).toInt(),
            (other.y + other.height).toInt()
        ))
    }

    open fun hitTest(event: MotionEvent): Boolean {
        if (Rect(
                x.toInt(),
                y.toInt(),
                (x + width).toInt(),
                (y + height).toInt()
            ).contains(event.x.toInt(), event.y.toInt())
        ) {
            return onTouch(event);
        }
        return false
    }

    open fun onTouch(event: MotionEvent): Boolean {
        return false;
    }
}