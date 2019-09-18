package com.example.daplane

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.example.daplane.base.Scene

class GameSurface(
    context: Context?,
    attrs: AttributeSet?
) : SurfaceView(context, attrs), SurfaceHolder.Callback {

    private var controller: GameController

    init {
        Log.i("GameSurface", "init: ");
        holder?.addCallback(this)
        controller = GameController(holder);
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
        Log.i("GameSurface", "change: ");
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
        Log.i("GameSurface", "destory: ");
        controller.pause()
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        Log.i("GameSurface", "create: ");
        controller.start()
    }

    private val notifyScene = mutableListOf<Scene>()
    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event == null) return true;
        //HIT TEST
        if (event.action == MotionEvent.ACTION_DOWN) {
            notifyScene.clear()
            GameModel.displayScenes.forEach {
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