package com.example.daplane

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.example.daplane.`interface`.Scene
import com.example.daplane.utils.ClockGenerator
import kotlin.concurrent.thread

class GameSurface(
    context: Context?,
    attrs: AttributeSet?
) : SurfaceView(context, attrs), SurfaceHolder.Callback {

    private var mPaint: Paint

    init {
        holder?.addCallback(this)
        mPaint = Paint()
        mPaint.style = Paint.Style.FILL;
        mPaint.strokeWidth = 14f;
        mPaint.color = Color.parseColor("red")

    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {

    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        thread {
            ClockGenerator.gameClock {
                GameModel.nextFrameData(it)
                nextFrame()
            }
        }
    }

    private fun nextFrame() {
        val canvas = holder?.lockCanvas() ?: return
        GameModel.scene.forEach {
            it.draw(canvas, mPaint);
        }
        holder.unlockCanvasAndPost(canvas)
    }


    private val notifyScene = mutableListOf<Scene>()
    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event == null) return true;
        //HIT TEST
        if (event.action == MotionEvent.ACTION_DOWN) {
            notifyScene.clear()
            GameModel.scene.forEach {
                if (it.hitTest(event)) {
                    notifyScene.add(it)
                }
            }
        } else {
            notifyScene.forEach {
                it.onTouch(event)
            }
        }
        return true
    }
}