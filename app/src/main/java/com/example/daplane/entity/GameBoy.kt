package com.example.daplane.entity

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import com.example.daplane.GameModel
import com.example.daplane.base.Scene
import com.example.daplane.utils.Interpolator
import com.example.daplane.utils.parabolaGen

class GameBoy(x: Float, y: Float, width: Int, height: Int) :
    Scene(x, y, width, height) {

    var innerClock: Long = -1;
    var interceptor: Interpolator? = null;

    override fun nextFrame(clock: Long) {

        if (innerClock == -1L) {
            interceptor = parabolaGen(offset = y);
        }
        innerClock++;

        if (interceptor == null) return;
        y = interceptor!!.invoke(innerClock);

        GameModel.displayScenes.forEach {
            if (it is Floor) {
                if (it.contains(this)) {
                    innerClock = -1L
                }
            }
        }

    }

    override fun draw(canvas: Canvas, paint: Paint) {
        paint.color = Color.parseColor("blue")
        canvas.drawRect(x, y, x + width, y + height, paint)
    }


    override fun onTouch(event: MotionEvent): Boolean {
        x = event.x
        return true
    }
}