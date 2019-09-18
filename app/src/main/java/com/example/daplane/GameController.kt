package com.example.daplane

import android.graphics.Paint
import android.view.SurfaceHolder
import kotlin.concurrent.thread


class GameController(val holder: SurfaceHolder) {

    enum class GameStatus {
        IDLE, RUNNING, PAUSE, STOP
    }

    @Volatile
    var currentStatus = GameStatus.IDLE
    var currentClock = 0L

    fun stop() {
        currentStatus = GameStatus.STOP
        currentClock = 0L
    }

    fun pause() {
        currentStatus = GameStatus.PAUSE
    }

    fun start() {
        currentStatus = GameStatus.RUNNING
        loop()
    }

    private fun loop() {
        thread {
            while (true) {
                Thread.sleep(10)
                if (currentStatus != GameStatus.RUNNING) {
                    break
                } else {
                    GameModel.nextFrameData(currentClock)
                    nextFrame()
                    currentClock++
                }
            }
        }
    }

    private val mPaint = Paint();
    private fun nextFrame() {
        val canvas = holder.lockCanvas() ?: return
        GameModel.displayScenes.forEach {
            it.draw(canvas, mPaint);
        }
        holder.unlockCanvasAndPost(canvas)
    }

}