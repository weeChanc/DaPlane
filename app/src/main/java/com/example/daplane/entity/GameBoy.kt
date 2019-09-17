package com.example.daplane.entity

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.MotionEvent
import com.example.daplane.GameModel
import com.example.daplane.`interface`.Scene

class GameBoy(x: Float, y: Float, width: Int, height: Int, touchable: Boolean, enabled: Boolean) :
    Scene(x, y, width, height, touchable, enabled) {

    var initClock: Long = -1;

    override fun nextFrame(clock: Long) {
        // 插值器

        if (initClock == -1L) {
            initClock = clock
        }

        val offsetClock = clock - initClock;
        if (offsetClock < 60) {
            y = y - (60 - offsetClock) * 0.2f;
        } else {
            y = y + (offsetClock - 60) * 0.2f;
        }

        GameModel.scene.forEach {
            if (it is Floor) {
                if (it.contains(this)) {
                    initClock = -1L
                }
            }
        }

    }

    override fun draw(canvas: Canvas, paint: Paint) {
        paint.color = Color.parseColor("red")
        canvas.drawRect(x, y, x + width, y + height, paint)
    }


    override fun onTouch(event: MotionEvent): Boolean {
        x = event.x

        return true
    }
}